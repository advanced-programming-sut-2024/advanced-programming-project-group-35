package com.example.controller;

import java.util.*;
import java.util.concurrent.*;

import com.example.model.GameState;
import com.example.model.game.Player;
import com.google.gson.Gson;

public class GameServer implements Runnable {
    private Map<Integer, Player> players;
    private List<Integer> spectators;
    private GameState currentState;
    private List<GameState> gameHistory;
    private Gson gson;
    private BlockingQueue<PlayerAction> actionQueue;
    private volatile boolean running;

    public GameServer() {
        this.players = new ConcurrentHashMap<>();
        this.spectators = new CopyOnWriteArrayList<>();
        this.gameHistory = new CopyOnWriteArrayList<>();
        this.gson = new Gson();
        this.actionQueue = new LinkedBlockingQueue<>();
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                PlayerAction action = actionQueue.poll(100, TimeUnit.MILLISECONDS);
                if (action != null) {
                    processPlayerAction(action.playerId, action.action);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    public void connectPlayer(int playerId, String deck) {
        if (players.size() < 2) {
            players.put(playerId, new Player(playerId));
            if (players.size() == 2) {
                startGame();
            }
        }
    }

    public void connectSpectator(int spectatorId) {
        spectators.add(spectatorId);
        if (currentState != null) {
            sendGameState(spectatorId, currentState);
        }
    }

    private void sendGameState(int playerId, GameState gameState) {
        String jsonState = gson.toJson(gameState);
        // Here you would use your networking code to send jsonState to the client
    }

    private void startGame() {
        currentState = new GameState(new ArrayList<>(players.values()));
        gameHistory.add(currentState);
        broadcastGameState();
    }

    public void queuePlayerAction(int playerId, String action) {
        actionQueue.offer(new PlayerAction(playerId, action));
    }

    private void processPlayerAction(int playerId, String action) {
        // Update game state based on player action
        currentState.updateFromAction(playerId, action);
        gameHistory.add(currentState);
        broadcastGameState();
    }

    private void broadcastGameState() {
        String jsonState = gson.toJson(currentState);
        for (int playerId : players.keySet()) {
            sendToClient(playerId, jsonState);
        }
        for (int spectatorId : spectators) {
            sendToClient(spectatorId, jsonState);
        }
    }

    private void sendToClient(int clientId, String jsonState) {
        // Implement network sending logic here
        System.out.println("Sending to " + clientId + ": " + jsonState);
    }

    public List<GameState> getGameHistory() {
        return new ArrayList<>(gameHistory);
    }

    public void stop() {
        running = false;
    }

    private static class PlayerAction {
        int playerId;
        String action;

        PlayerAction(int playerId, String action) {
            this.playerId = playerId;
            this.action = action;
        }
    }
}

// Player and GameState classes remain the same