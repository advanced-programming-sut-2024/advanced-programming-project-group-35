package com.example.view;

public enum Menu {
    LOGIN_MENU("Gwent", "/FXML/LoginMenu.fxml"),
    MAIN_MENU("Main Menu", "/FXML/MainMenu.fxml"),
    PROFILE_MENU("Profile Menu", "/FXML/ProfileMenu.fxml"),
    PREGAME_MENU("Pre Game Menu", "/FXML/preGameMenu.fxml"),
    GAME_MENU("Game Menu", ""),
    GAME_HISTORY_MENU("Game History Menu", "/FXML/gameHistoryMenu.fxml"),
    SCORE_TABLE_MENU("Score Table Menu", "/FXML/scoreTableMenu.fxml"),
    ;
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

