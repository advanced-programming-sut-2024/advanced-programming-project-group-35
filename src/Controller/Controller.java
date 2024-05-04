package Controller;


import java.util.Scanner;

public enum Controller {
    LOGIN_MENU_CONTROLLER(new LoginMenuController()),
    MAIN_MENU_CONTROLLER(new MainMenuController()),
    PROFILE_MENU_CONTROLLER(new ProfileMenuController()),
    GAME_MENU_CONTROLLER(new GameMenuController());
    private final AppController controller;

    Controller(AppController controller) {
        this.controller = controller;
    }
    public void run(Scanner scanner) {
        this.controller.run(scanner);
    }
}
