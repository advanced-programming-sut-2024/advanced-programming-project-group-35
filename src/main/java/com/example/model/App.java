package com.example.model;

import com.example.controller.Controller;
import com.example.controller.LoginMenuController;
import com.example.model.card.Card;
import com.example.model.user.User;
import com.example.view.AppView;
import com.example.view.Menu;

import java.util.ArrayList;

public class App {
    private static ArrayList<String> securityQuestions = new ArrayList<String>();
    static {
        securityQuestions.add("What is your favorite color?");
        securityQuestions.add("What is your favorite food?");
        securityQuestions.add("What is your favorite movie?");
        securityQuestions.add("What is your favorite book?");
        securityQuestions.add("What is your favorite song?");
    }
    private static ArrayList<User> allUsers = new ArrayList<User>();
    private static ArrayList<Card> allCards = new ArrayList<Card>();
    private static Controller currentController;
    private static User loggedInUser;
    private static Menu currentMenu = Menu.LOGIN_MENU;
    private static AppView appView;

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
        App.loggedInUser = loggedInUser;
    }

    public static User getUserByUsername(String username) {
        for (User user : App.allUsers) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static void addNewUser(User newUser) {
        App.allUsers.add(newUser);
    }

    public static void addSecurityQuestion(String question) {
        App.securityQuestions.add(question);
    }

    public static ArrayList<String> getSecurityQuestions() {
        return App.securityQuestions;
    }
    public static int getRankByUsername(String username) {
        int rank = 1;
        for (User user : App.allUsers) {
            if (user.getUsername().equals(username)) {
                for (User user1 : App.allUsers) {
                    if (user1.getScore() > user.getScore()) rank++;
                }
                return rank;
            }
        }
        return 0;
    }
}
