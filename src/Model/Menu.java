package Model;

import View.*;

import java.util.Scanner;

public enum Menu {
    LoginMenu(new LoginMenuView()),
    MainMenu(new MainMenuView()),
    ProfileMenu(new ProfileMenuView()),
    GameMenu(new GameMenuView());
    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }
    public void getInput(Scanner scanner) {
        this.menu.getInput(scanner);
    }
}
