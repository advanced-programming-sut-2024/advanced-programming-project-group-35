package com.example;


import com.example.controller.EmailVerification;
import com.example.controller.server.ClientConnector;
import com.example.model.App;
import com.example.model.DatabaseManager;
import com.example.model.User;
import com.example.view.AppView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import static com.example.model.App.clientConnector;

public class Main extends Application {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8080;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public static void main(String[] args) throws IOException {
//        App.setAllUsers(DatabaseManager.getAllUsers());
//        App.loadUsers("users.json");
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            App.saveUsers("users.json");
//            DatabaseManager.createNewDatabase();
//            DatabaseManager.createUsersTable();
//            saveUsersToDatabase();
//        }));
        Runtime.getRuntime().addShutdownHook(new Thread(Main::shutdown));
        launch(args);
    }



    public static void shutdown() {
        if (App.getLoggedInUser() != null){
            playerShutedDown();
        }
        System.out.println("shut down");
        //TODO
//        System.out.println("Shutdown hook is running...");
//        DatabaseManager.createNewDatabase();
//        DatabaseManager.createUsersTable();
//        DatabaseManager.clearUsersTable();
//        saveUsersToDatabase();
//        System.out.println("Detailed user information:");
//        DatabaseManager.printDetailedUserInfo();
//        System.out.println("Total number of users in database: " + DatabaseManager.getUserCount());
//        try {
//            Thread.sleep(1000); // 1 second delay
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Shutdown hook completed.");
//        App.saveUsers("users.json");
    }

    private static void playerShutedDown() {
//        User loggedInUser = App.getLoggedInUser();
//        if (loggedInUser.isInGame()) {
//            App.getServerConnector().setUserOffline(loggedInUser);
//        }
        App.logout();

    }

    public static void saveUsersToDatabase() {
//        ArrayList<User> users = App.getAllUsers();
//        System.out.println("Number of users to save: " + users.size());
//        for (User user : users) {
////            System.out.println("Saving user: " + user.getUsername());
//            DatabaseManager.insertOrUpdateUser(user);
//        }
    }


    @Override
    public void start(Stage stage){
        AppView appView = new AppView();
        App.setAppView(appView);
        connectToServer();
        appView.start(stage);
        App.setServerData(socket, out, in);
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