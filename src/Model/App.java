package Model;

import Model.Card.Card;
import Model.User.User;
import java.util.ArrayList;

public class App {
    ArrayList<User> allUsers = new ArrayList<User>();
    ArrayList<Card> allCards = new ArrayList<Card>();
    private static Menu currentMenu = Menu.LoginMenu;

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getUserByUsername(String username) {
        return null;
    }
}
