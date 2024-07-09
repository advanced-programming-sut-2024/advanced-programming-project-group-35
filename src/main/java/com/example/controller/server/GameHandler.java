package com.example.controller.server;

import com.example.model.chat.ChatBox;
import com.example.model.chat.ChatMessage;
import com.example.model.Log;
import com.example.model.deckmanager.DeckToJson;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameHandler implements Runnable {
    private int gameID;
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
        player1Handler.setInGame(true);
        player2Handler.setInGame(true);
        this.player1In = player1Handler.getIn();
        this.player2In = player2Handler.getIn();
        this.player1Out = player1Handler.getOut();
        this.player2Out = player2Handler.getOut();

//        player1Handler.wait();
//        player2Handler.wait();
//        gameHistory = new Log(player1, player2);
        gameID = LocalDateTime.now().hashCode();
        ServerApp.addGame(gameID, this);
        player1Handler.setGameHandler(this);
        player2Handler.setGameHandler(this);
        gameHistory = new Log(player1ID, player2ID, new DeckToJson(), new DeckToJson());
    }

    public void setDeckToJson(DeckToJson deck1, DeckToJson deck2) {
        gameHistory.deck1 = deck1;
        gameHistory.deck2 = deck2;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        isPlayer1Turn = player1Turn;
    }

    private void endGame() {
        //notifyAll();
        ServerApp.removeGame(gameID);
    }

    public void command(String command) {
        System.out.println("game handler command: " + command);
        if (command.startsWith("END_GAME")) {
            endGame();
        } else if (command.startsWith("CHAT|")) {
            handleMessage(command);
        } else if (command.startsWith("REACTION|")) {
            handleReaction(command);
        } else if (command.startsWith("EMOTE|")) {
            handleEmote(command);
        } else {
            if (isPlayer1Turn) {
                player1Out.println(command);
            } else {
                player2Out.println(command);
            }
            gameHistory.addCommand(command);
            broadcastToSpectators(command);
        }
    }

    @Override
    public void run() {
        Thread run = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
//            player1Out.println("GAME_STARTED|" + gameID);
//            player2Out.println("GAME_STARTED|" + gameID);
                String inputLine;
                while (true) {
                    if (isPlayer1Turn) {
                        inputLine = player1In.readLine();
                        System.out.println("test kir khar2");
                        System.out.println(inputLine + "\n\n\n\n\n");
                        player2Out.println(inputLine);
                        gameHistory.addCommand(inputLine);
                        if (inputLine.equals("END_GAME|")) {
                            endGame();
                            break;
                        }
                        isPlayer1Turn = false;
                    } else {
                        System.out.println("kir shodim2");
                        inputLine = player2In.readLine();
                        System.out.println("test kir khar3");
                        System.out.println(inputLine + "\n\n\n\n\n");
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
        });
        run.start();
    }

    private void handleEmote(String inputLine) { // EMOTE|senderID|emoteIndex or EMOTE|senderID|Message
        broadcastToAll(inputLine);
    }

    private void handleReaction(String input) { // REACTION|senderID|messageIndex|reactionIndex
        String[] parts = input.split("\\|");
        int messageIndex = Integer.parseInt(parts[2]);
        int senderId = Integer.parseInt(parts[1]);
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

    private void handleMessage(String inputLine) { // CHAT|senderID|content|hour|minute|REPLY_TO|replyToIndex
        String[] parts = inputLine.split("\\|");
        int senderID = Integer.parseInt(parts[1]);
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


    private void broadcastToSpectators(String command) {
        for (PlayerHandler spectator : spectators) {
            spectator.getOut().println(command);
        }
    }
}
