package com.example.controller.server;

import com.example.model.FriendRequest;
import com.example.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnector {
    private static final String SERVER_IP = "localhost"; // آدرس IP سرور
    private static final int SERVER_PORT = 8080; // پورت سرور

    public void saveUsers(ArrayList<User> allUsers) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUsers = gson.toJson(allUsers);

        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("SYSTEM|SAVE_USERS");
            out.println(jsonUsers);
            out.println("END_JSON");

            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> loadUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("SYSTEM|LOAD_USERS");

            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while (!(line = in.readLine()).equals("END_JSON")) {
                jsonBuilder.append(line).append("\n");
            }
            //System.out.println(jsonBuilder.toString());
            Type userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            allUsers = gson.fromJson(jsonBuilder.toString(), userListType);
            System.out.println("Users data loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    public ArrayList<User> getAllUsers() {
        return loadUsers();
    }

    public void acceptFriendRequest(FriendRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|ACCEPT_FRIEND_REQUEST|");
            out.print(request.getSender().getID());
            out.print("|");
            out.println(request.getReceiver().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectFriendRequest(FriendRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|REJECT_FRIEND_REQUEST");
            out.print(request.getSender().getID());
            out.print("|");
            out.println(request.getReceiver().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFriendRequest(FriendRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|SEND_FRIEND_REQUEST|");
            out.print(request.getSender().getID());
            out.print("|");
            out.println(request.getReceiver().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserOnline(User loggedInUser) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|SET_USER_ONLINE|");
            out.println(loggedInUser.getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserOffline(User loggedInUser) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|SET_USER_OFFLINE|");
            out.println(loggedInUser.getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(int senderID, int receiverID, String message) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("Message|");
            out.print(senderID);
            out.print("|");
            out.print(receiverID);
            out.print("|");
            out.println(message);
            System.out.println("-message sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGameRequest(int senderID, int receiverID) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("GameRequest|");
            out.print(senderID);
            out.print("|");
            out.println(receiverID);
            System.out.println("-game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRandomGameRequest(int id) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("RandomGameRequest|");
            out.println(id);
            System.out.println("-random game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTournamentGameRequest(int id) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("TournamentGameRequest|");
            out.println(id);
            System.out.println("-tournament game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}