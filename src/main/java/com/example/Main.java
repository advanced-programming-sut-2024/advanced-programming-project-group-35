package com.example;


import com.example.controller.server.ClientConnector;
import com.example.model.App;
import com.example.view.AppView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.example.model.App.clientConnector;

public class Main extends Application {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8080;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        AppView appView = new AppView();
        App.setAppView(appView);
        connectToServer();
        App.setServerData(socket, out, in);
        appView.start(stage);
    }

    private void connectToServer() {
        new Thread(() -> {
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//                // اینجا می‌توانید نام کاربری را از UI دریافت کنید
//                String username = "YourUsername";
//                out.println(username);
//
//                String serverResponse;
//                while ((serverResponse = in.readLine()) != null) {
//                    final String message = serverResponse;
//                    Platform.runLater(() -> {
//                        // اینجا پاسخ سرور را در UI نمایش دهید
//                        System.out.println("Server: " + message);
//                    });
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}