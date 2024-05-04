package Model;

import Model.Card.Card;
import Model.User.User;
import java.util.ArrayList;

public class App {
    private static ArrayList<User> allUsers = new ArrayList<User>();
    private static ArrayList<Card> allCards = new ArrayList<Card>();
    private static Menu currentMenu = Menu.LOGIN_MENU;
    private static User loggedInUser;

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
}
