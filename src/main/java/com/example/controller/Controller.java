package com.example.controller;

public enum Controller {
    LOGIN_MENU_CONTROLLER(new LoginMenuController()),
    MAIN_MENU_CONTROLLER(new MainMenuController()),
    PROFILE_MENU_CONTROLLER(new ProfileMenuController()),
    GAME_MENU_CONTROLLER(new GameMenuController()),
    PRE_GAME_MENU_CONTROLLER(new PregameController()),
    ;
    private final AppController controller;

    Controller(AppController controller) {
        this.controller = controller;
    }
    public void run() {
        this.controller.run();
    }
    public void runCommand(String input){
        this.controller.runCommand(input);
    }
}
