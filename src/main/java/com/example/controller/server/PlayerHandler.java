package com.example.controller.server;

import com.example.model.ServerApp;
import com.example.model.User;
import com.example.model.game.OnlineTable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class PlayerHandler implements Runnable {
    private static final String USERS_FILE = "users.json";
    private Socket socket;
    private Server server;
    private int ID;
    private OnlineTable table;
    private PrintWriter out;
    private BufferedReader in;

    public PlayerHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Handle login/registration
//            ID = Integer.parseInt(in.readLine());
//            server.addPlayer(ID, this);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                String[] parts = inputLine.split("\\|");
                if ("SYSTEM".equals(parts[0])) {
                    if ("LOAD_USERS".equals(parts[1])) {
                        handleLoadUsers(out);
                    } else if ("SAVE_USERS".equals(parts[1])) {
                        handleSaveUsers(in, out);
                    } else if ("ACCEPT_FRIEND_REQUEST".equals(parts[1])) {
                        int userID = Integer.parseInt(parts[2]);
                        int friendUserID = Integer.parseInt(parts[3]);
                        ServerApp.acceptFriendRequest(userID, friendUserID);
                    } else if ("REJECT_FRIEND_REQUEST".equals(parts[1])) {
                        int userID = Integer.parseInt(parts[2]);
                        int friendUserID = Integer.parseInt(parts[3]);
                        ServerApp.rejectFriendRequest(userID, friendUserID);
                    } else if ("SEND_FRIEND_REQUEST".equals(parts[1])) {
                        int userID = Integer.parseInt(parts[2]);
                        int friendUserID = Integer.parseInt(parts[3]);
                        ServerApp.sendFriendRequest(userID, friendUserID);
                    } else if ("SET_USER_ONLINE".equals(parts[1])) {
                        int userID = Integer.parseInt(parts[2]);
                        ServerApp.setUserOnline(userID);
                    } else if ("SET_USER_OFFLINE".equals(parts[1])) {
                        int userID = Integer.parseInt(parts[2]);
                        ServerApp.setUserOffline(userID);
                    }
                } else if ("ClientConnector".equals(parts[0])) {
                    ID = Integer.parseInt(parts[1]);
                    server.addClientConnector(ID, this);
                } else if ("Message".equals(parts[0])) {
                    System.out.println("Received message: " + parts[3]);
                    int receiverID = Integer.parseInt(parts[2]);
                    int senderID = Integer.parseInt(parts[1]);
                    String message = parts[3];
                    ServerApp.sendMessage(senderID, receiverID, message);
                } else {
                    handleCommand(inputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.removePlayer(ID);
        }
    }

    private static void handleSaveUsers(BufferedReader in, PrintWriter out) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while (!(line = in.readLine()).equals("END_JSON")) {
            jsonBuilder.append(line).append("\n");
        }

        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            writer.write(jsonBuilder.toString());
        }
        ServerApp.loadUsers(USERS_FILE);
        out.println("Users data saved successfully on server.");
    }

    private static void handleLoadUsers(PrintWriter out) throws IOException {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            int character;
            while ((character = reader.read()) != -1) {
                out.print((char) character);
            }
            out.println("\nEND_JSON");
        }
    }

    private void handleCommand(String command) {

    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void setCurrentGame(OnlineTable game) {
        this.table = game;
    }

    // Other methods for player-specific actions
}
