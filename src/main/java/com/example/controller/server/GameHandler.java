package com.example.controller.server;

import com.example.model.ChatMessage;
import com.example.model.Log;
import com.example.model.ServerApp;
import com.example.model.game.OnlineTable;
import com.example.model.game.Player;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GameHandler implements Runnable {
    private ArrayList<PlayerHandler> spectators = new ArrayList<>();
    private List<ChatMessage> chatHistory = new ArrayList<>();
    private int winnerID;
    private boolean isGameEnded = false;
    private int player1ID;
    private int player2ID;
    private PlayerHandler player1Handler;
    private PlayerHandler player2Handler;
    private BufferedReader player1In;
    private BufferedReader player2In;
    private PrintWriter player1Out;
    private PrintWriter player2Out;
    private boolean isPlayer1Turn = true;
    private Log gameHistory;

    public GameHandler(int player1ID, int player2ID) throws InterruptedException {
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        player1Handler = ServerApp.getServer().players.get(player1ID);
        player2Handler = ServerApp.getServer().players.get(player2ID);
        this.player1In = player1Handler.getIn();
        this.player2In = player2Handler.getIn();
        this.player1Out = player1Handler.getOut();
        this.player2Out = player2Handler.getOut();
        Player player1 = new Player(player1ID);
        Player player2 = new Player(player2ID);
        player1Handler.wait();
        player2Handler.wait();
        gameHistory = new Log(player1, player2);
    }

    private void endGame() {
        notifyAll();
    }

    @Override
    public void run() {
        try {
            player1Out.println("GAME_STARTED|");
            player2Out.println("GAME_STARTED|");
            String inputLine;
            while (true) {
                if (isPlayer1Turn) {
                    inputLine = player1In.readLine();
                    if (inputLine.startsWith("CHAT|")) {
                        handleMessage(inputLine, player1ID);
                        continue;
                    }
                    if (inputLine.startsWith("REACTION|")) {
                        handleReaction(inputLine, player1ID);
                        continue;
                    }
                    if (inputLine.startsWith("EMOTE|")) {
                        handleEmote(inputLine, player1ID);
                        continue;
                    }
                    player2Out.println(inputLine);
                    gameHistory.addCommand(inputLine);
                    if (inputLine.equals("END_GAME|")) {
                        endGame();
                        break;
                    }
                    isPlayer1Turn = false;
                } else {
                    inputLine = player2In.readLine();
                    if (inputLine.startsWith("CHAT|")) {
                        handleMessage(inputLine, player2ID);
                        continue;
                    }
                    if (inputLine.startsWith("REACTION|")) {
                        handleReaction(inputLine, player2ID);
                        continue;
                    }
                    if (inputLine.startsWith("EMOTE|")) {
                        handleEmote(inputLine, player2ID);
                        continue;
                    }
                    player1Out.println(inputLine);
                    gameHistory.addCommand(inputLine);
                    if (inputLine.equals("END_GAME|")) {
                        endGame();
                        break;
                    }
                    isPlayer1Turn = true;
                }
                for (PlayerHandler spectator : spectators) {
                    spectator.getOut().println(inputLine);
                }
            }
            inputLine = player1In.readLine();
            winnerID = Integer.parseInt(inputLine.split("\\|")[1]);
            isGameEnded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEmote(String inputLine, int player1ID) {
        broadcastToAll(inputLine + "|" + player1ID);
    }

    private void handleReaction(String input, int senderId) {
        String[] parts = input.split("\\|");
        int messageIndex = Integer.parseInt(parts[2]);
        ChatMessage message = chatHistory.get(messageIndex);
        message.addReaction(senderId);
        broadcastToAll("REACTION|" + messageIndex + "|" + senderId);
    }

    private void broadcastToAll(String s) {
        for (PlayerHandler spectator : spectators) {
            spectator.getOut().println(s);
        }
        player1Out.println(s);
        player2Out.println(s);
    }

    private void handleMessage(String inputLine, int senderID) {
        String[] parts = inputLine.split("\\|");
        ChatMessage message = new ChatMessage(senderID, parts[2]);
        if (parts.length > 3 && parts[3].equals("REPLY_TO")) {
            int replyToIndex = Integer.parseInt(parts[4]);
            message.setReplyTo(chatHistory.get(replyToIndex));
        }
        chatHistory.add(message);
        broadcastToAll("CHAT|" + message.getSender() + "|" + message.getContent() + "|" + message.getHour() + message.getMinute() + (message.getReplyTo() != null ? "|REPLY_TO|" + message.getReplyTo().getSender() + "|" + message.getReplyTo().getContent() : ""));
    }

    public void addSpectator(PlayerHandler spectator) throws InterruptedException {
        //send game history until now:
        gameHistory.sendInitial(spectator);
        for (String command : gameHistory.getCommands()) {
            spectator.getOut().println(command);
        }
        spectators.add(spectator);
        spectator.wait();
    }

    public int getWinnerID() {
        return winnerID;
    }

    // اضافه کردن getter برای isGameEnded
    public boolean isGameEnded() {
        return isGameEnded;
    }
}
