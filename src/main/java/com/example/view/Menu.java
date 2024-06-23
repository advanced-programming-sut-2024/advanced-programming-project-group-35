package com.example.view;

public enum Menu {
    LOGIN_MENU("Gwent", "/FXML/LoginMenu.fxml", "/images/login-menu-background.png"),
    MAIN_MENU("Main Menu", "", ""),
    PROFILE_MENU("Profile Menu", "", ""),
    PREGAME_MENU("Pre Game Menu", "", ""),
    GAME_MENU("Game Menu", "", "");
    private final String title;
    private final String fxmlFile;
    private final String backGroundImagePath;

    Menu(String title, String fxmlFile, String backGroundImagePath) {
        this.title = title;
        this.fxmlFile = fxmlFile;
        this.backGroundImagePath = backGroundImagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

    public String getBackGroundImagePath() {
        return backGroundImagePath;
    }
}

