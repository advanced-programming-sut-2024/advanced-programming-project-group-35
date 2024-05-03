package Model;

import Controller.LoginMenuController;
import View.*;

import java.util.Scanner;

public enum Menu {
    LOGIN_MENU(new LoginMenuView()),
    MAIN_MENU(new MainMenuView()),
    PROFILE_MENU(new ProfileMenuView()),
    GAME_MENU(new GameMenuView());
    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }
    public String getName() {
        return this.menu.getName();
    }
}
