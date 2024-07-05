package com.example.model;

import com.example.controller.Controller;
import com.example.controller.server.ClientConnector;
import com.example.controller.server.ServerConnector;
import com.example.model.card.Card;
import com.example.view.AppView;
import com.example.view.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class App {
    private static ServerConnector serverConnector = new ServerConnector();
    public static ClientConnector clientConnector ;
    private static ArrayList<String> securityQuestions = new ArrayList<String>();

    static {
        securityQuestions.add("What is your favorite color?");
        securityQuestions.add("What is your favorite food?");
        securityQuestions.add("What is your favorite movie?");
        securityQuestions.add("What is your favorite book?");
        securityQuestions.add("What is your favorite song?");
    }

    //private static ArrayList<User> allUsers = new ArrayList<User>();
    private static ArrayList<Card> allCards = new ArrayList<Card>();
    private static ArrayList<User> allUsers = new ArrayList<User>();
    private static Controller currentController;
    private static User loggedInUser;
    private static Menu currentMenu = Menu.LOGIN_MENU;
    private static AppView appView;
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;


    public static AppView getAppView() {
        return appView;
    }

    public static void setAppView(AppView appView) {
        App.appView = appView;
    }

    public static Controller getCurrentController() {
        return currentController;
    }

    public static void setCurrentController(Controller currentController) {
        App.currentController = currentController;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        if (loggedInUser == null) {
            serverConnector.setUserOffline(App.loggedInUser);
            clientConnector.close();
            return;
        }
        if (App.loggedInUser != null) {
            serverConnector.setUserOffline(App.loggedInUser);
            clientConnector.close();
        }
        App.loggedInUser = loggedInUser;
        connectSereverToApp();
        serverConnector.setUserOnline(loggedInUser);
    }

    private static void connectSereverToApp() {
        out.println("SET_PLAYER|" + loggedInUser.getID());
        try {
            clientConnector = new ClientConnector(loggedInUser.getID());
            new Thread(clientConnector).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : App.allUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static void addNewUser(User newUser) {
        App.allUsers().add(newUser);
        serverConnector.saveUsers(allUsers);
    }

    public static void addSecurityQuestion(String question) {
        App.securityQuestions.add(question);
    }

    public static ArrayList<String> getSecurityQuestions() {
        return App.securityQuestions;
    }

    public static int getRankByUsername(String username) {
        int rank = 1;
        for (User user : App.allUsers()) {
            if (user.getUsername().equals(username)) {
                for (User user1 : App.allUsers()) {
                    if (user1.getScore() > user.getScore()) rank++;
                }
                return rank;
            }
        }
        return 0;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers();
    }

    private static ArrayList<User> allUsers(){
        updateUsersFromServer();
        return allUsers;
    }

    private static void updateUsersFromServer() {
        allUsers = serverConnector.getAllUsers();
    }

    public static ServerConnector getServerApp() {
        return serverConnector;
    }

    public static void setServerData(Socket socket, PrintWriter out, BufferedReader in) {
        App.socket = socket;
        App.out = out;
        App.in = in;
    }

    public void updateUserInfo() {
        updateUsersFromServer();
        appView.updateUserInfo();
    }
}
