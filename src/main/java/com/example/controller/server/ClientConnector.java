package com.example.controller.server;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.App;
import com.example.model.User;
import com.example.model.card.enums.CardData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientConnector implements Runnable {
    private volatile boolean running = true;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8080;
    private final int userID;
    private final Socket socket;
    private final BufferedReader reader;

    public ClientConnector(int userID) throws IOException {
        this.socket = new Socket(SERVER_IP, SERVER_PORT);
        this.userID = userID;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // ارسال نام کاربری به سرور
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("ClientConnector|" + userID);
    }

    @Override
    public void run() {
        try {
            while (running) {
                String message = reader.readLine();
                System.out.println("Received: " + message);
                if (message == null) {
                    // Connection closed by server
                    break;
                }
                processMessage(message);
            }
        } catch (SocketException e) {
            handleDisconnection("Connection lost: " + e.getMessage());
        } catch (IOException e) {
            handleDisconnection("Error reading from server: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void handleDisconnection(String message) {
        Platform.runLater(() -> {
            App.getAppView().showMessage(message);
        });
    }

    private void closeConnection() {
        running = false;
        try {
            if (reader != null) reader.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    private void processMessage(String message) {
        System.out.println("Processing message: " + message);
        String parts[] = message.split("\\|");
        if (message.startsWith("FRIEND_REQUEST_ACCEPTED:")) {
            App.updateUserInfo();
        } else if (message.startsWith("NEW_FRIEND_REQUEST:")) {
            System.out.println(App.getLoggedInUser().getFriendRequests());
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(4000);
                    App.updateUserInfo();
                    Thread.sleep(1000);
                    System.out.println(App.getLoggedInUser().getFriendRequests());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        } else if (message.startsWith("FRIEND_REQUEST_REJECTED:")) {
            App.updateUserInfo();
        } else if (message.startsWith("FRIEND_REQUEST_CANCELED:")) {
            App.updateUserInfo();
        } else if (message.startsWith("FRIEND_REMOVED:")) {
            App.updateUserInfo();
        } else if (message.startsWith("MESSAGE")) {
            processMessageAlert(message);
        } else if (message.startsWith("REQUEST")) {
            processRequest(message);
        } else if (message.startsWith("GameStarts")){
            System.out.println("GameStarts -message");
            App.getLoggedInUser().setInGame(true);
            ((GameMenuControllerForOnlineGame)Controller.GAME_MENU_CONTROLLER.getController()).startNewGame(parts[1], parts[3], parts[2], parts[4]);
            Controller.GAME_MENU_CONTROLLER.getController().run();
        }
    }

    private void processRequest(String message) {
        String[] parts = message.split("\\|");
        int senderID = Integer.parseInt(parts[1]);
        String senderName = User.getUserByID(senderID).getUsername();
        //show a message with accept and reject buttons
        Platform.runLater(() -> {
            App.getAppView().showRequest(senderName, senderID);
        });
    }

    private void processMessageAlert(String message) {
        String[] parts = message.split("\\|");
        int senderID = Integer.parseInt(parts[1]);
        String senderName = User.getUserByID(senderID).getUsername();
        System.out.println(parts[2]);
        App.getAppView().showMessage(senderName + ": " + parts[2]);
    }


    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
