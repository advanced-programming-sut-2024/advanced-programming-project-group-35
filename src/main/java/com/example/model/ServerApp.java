package com.example.model;

import com.example.controller.server.ClientConnector;
import com.example.controller.server.GameHandler;
import com.example.controller.server.PlayerHandler;
import com.example.controller.server.Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerApp {
    private static Server server;
    private static int[] randomPlayers = new int[2];
    private static int[] tournamentPlayers = new int[8];

    public static void setServer(Server server) {
        ServerApp.server = server;
    }

    public static Server getServer() {
        return server;
    }

    public static ArrayList<User> allUsers = new ArrayList<User>();
    public static HashMap<Integer, GameHandler> games = new HashMap<>();

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
        User user = allUsers.get(userID);
        User friend = allUsers.get(friendUserID);
        user.addFriend(friend);
        friend.addFriend(user);
    }

    public static void rejectFriendRequest(int userID, int friendUserID) {
        User user = allUsers.get(userID);
        User friend = allUsers.get(friendUserID);
        FriendRequest friendRequest = user.getFriendRequest(friend);
        friendRequest.reject();
    }

    public static void sendFriendRequest(int userID, int friendUserID) {
        User user = allUsers.get(userID);
        User friend = allUsers.get(friendUserID);
        User.sendFriendRequest(user, friend);
    }

    public static void setUserOnline(int userID) {
        User user = getUserByID(userID);
        user.setOnline(true);
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
        User user = allUsers.get(userID);
        user.setOnline(false);
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
            sendMessage(senderID, senderID, "User not found.");
            return;
        }
        if (!sender.isFriend(receiver)) {
            sendMessage(senderID, senderID, "You are not friends.");
            return;
        }
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(receiverID);
        if (clientConnector == null) {
            return;
        }
        //send request
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("REQUEST|").append(senderID).append("|").append(receiverID);
        clientConnector.sendMessage(requestBuilder.toString());
    }

    public static void StartOnlineGame(int player1ID, int player2ID) {
        //find user connector
        PlayerHandler clientConnector1 = server.getClientConnector(player1ID);
        PlayerHandler clientConnector2 = server.getClientConnector(player2ID);
        if (clientConnector1 == null || clientConnector2 == null) {
            return;
        }
        //send request
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("GameStarts|").append(player1ID).append("|").append(player2ID);
        clientConnector1.sendMessage(requestBuilder.toString());
        clientConnector2.sendMessage(requestBuilder.toString());
    }

    public static void randomGame(int senderID) {
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(senderID);
        if (clientConnector == null) {
            return;
        }
        //find random player
        if (randomPlayers[0] == 0) {
            randomPlayers[0] = senderID;
            return;
        }
        randomPlayers[1] = senderID;
        //send request
        StartOnlineGame(randomPlayers[0], randomPlayers[1]);
        randomPlayers[0] = 0;
        randomPlayers[1] = 0;
    }

    public static void tournament(int senderID) {
        //find user connector
        PlayerHandler clientConnector = server.getClientConnector(senderID);
        if (clientConnector == null) {
            return;
        }
        //find random player
        for (int i = 0; i < tournamentPlayers.length; i++) {
            if (tournamentPlayers[i] == 0) {
                tournamentPlayers[i] = senderID;
                return;
            }
        }
        //send request
        for (int i = 0; i < tournamentPlayers.length; i++) {
            if (tournamentPlayers[i] == 0) {
                return;
            }
        }
        startTournament();
    }

    private static void startTournament() { //2-step elimination
        //send request
        StartOnlineGame(tournamentPlayers[0], tournamentPlayers[1]);
        StartOnlineGame(tournamentPlayers[2], tournamentPlayers[3]);
        StartOnlineGame(tournamentPlayers[4], tournamentPlayers[5]);
        StartOnlineGame(tournamentPlayers[6], tournamentPlayers[7]);
    }

    public static void addGame(int gameID, GameHandler gameHandler) {
        games.put(gameID, gameHandler);
    }

    public static void removeGame(int gameID) {
        games.remove(gameID);
    }
}
