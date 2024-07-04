package com.example;

import com.example.model.App;
import com.example.model.DatabaseManager;
import com.example.model.User;
import com.example.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        App.setAllUsers(DatabaseManager.getAllUsers());
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
        System.out.println("Shutdown hook is running...");
        DatabaseManager.createNewDatabase();
        DatabaseManager.createUsersTable();
        DatabaseManager.clearUsersTable();
        saveUsersToDatabase();
//        System.out.println("Detailed user information:");
//        DatabaseManager.printDetailedUserInfo();
        System.out.println("Total number of users in database: " + DatabaseManager.getUserCount());
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Shutdown hook completed.");
        App.saveUsers("users.json");
    }

    public static void saveUsersToDatabase() {
        ArrayList<User> users = App.getAllUsers();
        System.out.println("Number of users to save: " + users.size());
        for (User user : users) {
//            System.out.println("Saving user: " + user.getUsername());
            DatabaseManager.insertOrUpdateUser(user);
        }
    }


    @Override
    public void start(Stage stage){
        AppView appView = new AppView();
        App.setAppView(appView);
        appView.start(stage);
    }
}