package com.example.model;

import com.example.controller.server.ClientConnector;
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

public class ServerApp {
    private static Server server;

    public static void setServer(Server server) {
        ServerApp.server = server;
    }

    public static Server getServer() {
        return server;
    }

    private static ArrayList<User> allUsers = new ArrayList<User>();
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
}
