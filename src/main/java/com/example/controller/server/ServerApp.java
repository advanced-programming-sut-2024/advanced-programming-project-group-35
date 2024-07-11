package com.example.controller.server;

import com.example.model.*;
import com.example.model.card.enums.FactionsType;
import com.example.model.deckmanager.DeckManager;
import com.example.model.deckmanager.DeckToJson;
import com.example.model.game.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.model.FriendRequest;
import com.example.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ServerApp {
    private static Server server;
    private static int[] randomPlayers = new int[2];

    private static int[] tournamentPlayers = new int[8];

    private static String[] randomPlayersDecks = new String[2];

    public static void setServer(Server server) {
        ServerApp.server = server;
    }

    public static Server getServer() {
        return server;
    }

    public static ArrayList<User> allUsers = new ArrayList<User>();
    public static void saveUsers(String filename) {
        for (User user : allUsers) {
            System.out.println(user.getUsername());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(allUsers, writer);
            System.out.println("Users data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users data.");
        }
    }


    public static void loadUsers(String filename) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(filename)) {
            Type userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            allUsers = gson.fromJson(reader, userListType);
            System.out.println("Users data loaded successfully.");
        } catch (IOException e) {
        }
    }

    public static void acceptFriendRequest(int userID, int friendUserID) {
        User user = getUserByID(userID);
        User friend = getUserByID(friendUserID);
        user.addFriend(friend);
        friend.addFriend(user);
        ServerApp.saveUsers("users.json");
    }

    public static void rejectFriendRequest(int userID, int friendUserID) {
        User user = getUserByID(userID);
        User friend = getUserByID(friendUserID);
        user.rejectFriendRequest(friend);
        friend.rejectFriendRequest(user);
        ServerApp.saveUsers("users.json");
    }

    public static void sendFriendRequest(int userID, int friendUserID) {
        User user = ServerApp.getUserByID(userID);
        User friend = ServerApp.getUserByID(friendUserID);
        setFriendRequest(user, friend);
    }

    private static void setFriendRequest(User user, User friend) {
        FriendRequest friendRequest = new FriendRequest(user, friend);
        user.addFriendRequest(friendRequest);
        friend.addFriendRequest(friendRequest);
        System.out.println("now is saving");
        ServerApp.saveUsers("users.json");
        System.out.println("saved");
    }

    public static void setUserOnline(int userID) {
        User user = getUserByID(userID);
        user.setOnline(true);
        if (user.isInGame()) {
            GameHandler gameHandler = games.get(user.getGameID());
            if (gameHandler != null) {
                gameHandler.playerReconnected(userID);
            }
        }
    }

    public static User getUserByID(int userID) {
        for (User user : allUsers) {
            if (user.getID() == userID) {
                return user;
            }
        }
        return null;
    }

    public static void setUserOffline(int userID) {
        User user = getUserByID(userID);
        user.setOnline(false);
        if (user.isInGame()) {
            GameHandler gameHandler = games.get(user.getGameID());
            if (gameHandler != null) {
                gameHandler.playerDisconnected(userID);
            }
        }
    }

    public static void sendMessage(int senderID, int receiverID, String message) {
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(receiverID);
        if (clientConnector == null) {
            return;
        }
        //send message
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("MESSAGE|").append(senderID).append("|").append(message);
        clientConnector.sendMessage(messageBuilder.toString());
    }

    public static void sendGameRequest(int senderID, int receiverID) {
        //check if they are friends:
        User sender = getUserByID(senderID);
        User receiver = getUserByID(receiverID);
        if (sender == null || receiver == null) {
            sendError(0, senderID, "User not found.");
            return;
        }
        if (!sender.isFriend(receiver)) {
            sendError(0, senderID, "You are not friends.");
            return;
        }
        if (receiver.isInGame()) {
            sendError(0, senderID, "User is in game.");
            return;
        }
        if (!receiver.isOnline()) {
            sendError(0, senderID, "User is offline.");
            return;
        }
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(receiverID);
        if (clientConnector == null) {
            return;
        }
        //send request
        StringBuilder requestBuilder = new StringBuilder();
        GameRequest gameRequest = new GameRequest(senderID, receiverID);
        receiver.addGameRequest(gameRequest);
        sender.addGameRequest(gameRequest);
        requestBuilder.append("REQUEST|").append(senderID).append("|").append(receiverID);
        clientConnector.sendMessage(requestBuilder.toString());
    }

    public static void StartOnlineGame(int player1ID, int player2ID, String playerDeck1, String playerDeck2) throws InterruptedException {
        //find user connector
        PlayerHandler clientConnector1 = server.getClientConnector(player1ID);
        PlayerHandler clientConnector2 = server.getClientConnector(player2ID);
        if (clientConnector1 == null || clientConnector2 == null) {
            System.out.println("client connector not found");
            return;
        }
        System.out.println("client connectors found");
        //send request
        StringBuilder requestBuilder = new StringBuilder();

        DeckToJson deckPlayer2 = DeckManager.getDeckToJsonByCardNames(playerDeck2);


        ServerApp.getServer().players.get(player1ID).setInGame(true);
        ServerApp.getServer().players.get(player2ID).setInGame(true);
        boolean isPrivate = checkPrivacy(player1ID, player2ID);

        if (deckPlayer2.getFaction().equals("ScoiaTael")) {
            GameHandler gameHandler = new GameHandler(player2ID, player1ID);
            requestBuilder.append("GameStarts|").append(player2ID).append("|").append(playerDeck2).append("|").append(player1ID).append("|").append(playerDeck1).append("|").append(gameHandler.getGameId());
        } else {
            GameHandler gameHandler = new GameHandler(player1ID, player2ID);
            requestBuilder.append("GameStarts|").append(player1ID).append("|").append(playerDeck1).append("|").append(player2ID).append("|").append(playerDeck2).append("|").append(gameHandler.getGameId());
        }
        clientConnector1.sendMessage(requestBuilder.toString());
        clientConnector2.sendMessage(requestBuilder.toString());
    }

    private static boolean checkPrivacy(int player1ID, int player2ID) {
        User player1 = getUserByID(player1ID);
        User player2 = getUserByID(player2ID);
        return player1.isPrivate() || player2.isPrivate();
    }

    private static DeckToJson getDeckToJsonByCardNames(String deck) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DeckToJson newDeck = objectMapper.readValue(deck, DeckToJson.class);
            return newDeck;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void randomGame(int senderID, String deck) throws InterruptedException {
        PlayerHandler clientConnector = server.getClientConnector(senderID);
        if (clientConnector == null) {
            return;
        }
        //find random player
        if (randomPlayers[0] == 0) {
            randomPlayers[0] = senderID;
            randomPlayersDecks[0] = deck;
            System.out.println("first random player detected");
            return;
        }
        randomPlayers[1] = senderID;
        randomPlayersDecks[1] = deck;
        System.out.println("second random player detected");
        //send request
        StartOnlineGame(randomPlayers[0], randomPlayers[1], randomPlayersDecks[0], randomPlayersDecks[1]);
        randomPlayers[0] = 0;
        randomPlayers[1] = 0;
    }

    public static void tournament(int senderID) throws InterruptedException {
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(senderID);
        if (clientConnector == null) {
            return;
        }
        //find random player
        int counter = 0, flag = 0;
        for (int i = 0; i < tournamentPlayers.length; i++) {
            if (tournamentPlayers[i] == 0) {
                if (flag == 0){
                    tournamentPlayers[i] = senderID;
                }
                flag = 1;
            } else {
                counter++;
            }
        }
        if (counter < 8) return;
        //send request
        HashMap<Integer, PlayerHandler> players = new HashMap<>();
        for (int i = 0; i < tournamentPlayers.length; i++) {
            server.players.get(tournamentPlayers[i]).sendMessage("Tournament_Started");
            players.put(tournamentPlayers[i], server.players.get(tournamentPlayers[i]));
        }

        startTournament(players);
    }

    private static void startTournament(HashMap<Integer, PlayerHandler> players) throws InterruptedException { //2-step elimination
        TournamentHandler tournamentHandler = new TournamentHandler(players);
        tournamentHandler.startTournament();
    }


    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        ServerApp.allUsers = allUsers;
    }

    public static void acceptGameRequest(int userID, int friendUserID) {
        User user = getUserByID(userID);
        User friend = getUserByID(friendUserID);
        user.acceptGameRequest(friendUserID);
        friend.acceptGameRequest(userID);
        ServerApp.saveUsers("users.json");
    }

    public static void rejectGameRequest(int userID, int friendUserID) {
        User user = getUserByID(userID);
        User friend = getUserByID(friendUserID);
        user.rejectGameRequest(friendUserID);
        friend.rejectGameRequest(userID);
        ServerApp.saveUsers("users.json");
    }

    public static boolean areFriends(int userID, int friendUserID) {
        User user = getUserByID(userID);
        User friend = getUserByID(friendUserID);
        return user.isFriend(friend);
    }

    public static boolean hasFriendRequest(int userID, int friendUserID) {
        User user = getUserByID(userID);
        User friend = getUserByID(friendUserID);
        return user.hasFriendRequest(friend);
    }

    public static void sendError(int senderID, int receiverID, String message) {
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(receiverID);
        if (clientConnector == null) {
            return;
        }
        //send message
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("ERROR|").append(senderID).append("|").append(message);
        clientConnector.sendMessage(messageBuilder.toString());
    }
}
