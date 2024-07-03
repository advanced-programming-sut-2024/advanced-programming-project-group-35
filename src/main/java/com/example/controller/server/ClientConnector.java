package com.example.controller.server;

import com.example.model.App;
import com.example.model.User;
import com.example.model.card.enums.CardData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnector implements Runnable {
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
            String message;
            while ((message = reader.readLine()) != null) {
                processMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String message) {

        if (message.startsWith("FRIEND_REQUEST_ACCEPTED:")) {
            App.getAppView().updateUserInfo();
        } else if (message.startsWith("NEW_FRIEND_REQUEST:")) {
            App.getAppView().updateUserInfo();
        } else if (message.startsWith("FRIEND_REQUEST_REJECTED:")) {
            App.getAppView().updateUserInfo();
        } else if (message.startsWith("FRIEND_REQUEST_CANCELED:")) {
            App.getAppView().updateUserInfo();
        } else if (message.startsWith("FRIEND_REMOVED:")) {
            App.getAppView().updateUserInfo();
        } else if (message.startsWith("MESSAGE")) {
            String[] parts = message.split("\\|");
                App.getAppView().showMessage(parts[2]);
        }
    }


    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
