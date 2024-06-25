package com.example.view;

public enum Menu {
    LOGIN_MENU("Gwent", "/FXML/LoginMenu.fxml"),
    MAIN_MENU("Main Menu", "/FXML/MainMenu.fxml"),
    PROFILE_MENU("Profile Menu", ""),
    PREGAME_MENU("Pre Game Menu", ""),
    GAME_MENU("Game Menu", "");
    private final String title;
    private final String fxmlFile;

    Menu(String title, String fxmlFile) {
        this.title = title;
        this.fxmlFile = fxmlFile;
    }

    public String getTitle() {
        return title;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }
}

