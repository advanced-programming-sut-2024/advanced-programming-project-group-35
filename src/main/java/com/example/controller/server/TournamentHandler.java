package com.example.controller.server;

import com.example.controller.server.GameHandler;
import com.example.controller.server.PlayerHandler;
import com.example.model.ServerApp;
import com.example.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TournamentHandler {
    private HashMap<Integer, PlayerHandler> participants;
    private List<Integer> players;
    private List<GameHandler> currentGames;
    private int round;

    public TournamentHandler(HashMap<Integer, PlayerHandler> participants) {
        this.participants = participants;
        this.players = new ArrayList<>(participants.keySet());
        this.currentGames = new ArrayList<>();
        this.round = 1;
    }

    public void startTournament() throws InterruptedException {
        while (participants.size() > 1) {
            playRound();
            round++;
        }
        // اعلام برنده نهایی
        announceWinner(participants.keySet().iterator().next());
    }

    private void playRound() throws InterruptedException {
        Collections.shuffle(players);
        currentGames.clear();

        for (int i = 0; i < participants.size(); i += 2) {
            int id1 = players.get(i);
            int id2 = players.get(i + 1);
            PlayerHandler player1 = participants.get(id1);
            PlayerHandler player2 = participants.get(id2);

            GameHandler game = new GameHandler(id1, id2);
            currentGames.add(game);
            new Thread(game).start();
        }

        // انتظار برای پایان همه بازی‌های این دور
        for (GameHandler game : currentGames) {
            while (!game.isGameEnded()) {
                Thread.sleep(100);
            }
        }

        // حذف بازنده‌ها از لیست شرکت‌کنندگان
        HashMap<Integer, PlayerHandler> winners = new HashMap<>();
        for (GameHandler game : currentGames) {
            winners.put(game.getWinnerID(), ServerApp.getServer().players.get(game.getWinnerID()));
        }
        participants = winners;
    }

    private void announceWinner(int id) {
        for (PlayerHandler player : ServerApp.getServer().players.values()) {
            player.getOut().println("TOURNAMENT_WINNER|" + User.getUserByID(id).getUsername());
        }
    }
}