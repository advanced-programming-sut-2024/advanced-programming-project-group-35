package com.example.view;

public enum Menu {
    LOGIN_MENU("Gwent", "/FXML/LoginMenu.fxml"),
    MAIN_MENU("Main Menu", "/FXML/MainMenu.fxml"),
    PROFILE_MENU("Profile Menu", "/FXML/ProfileMenu.fxml"),
    PREGAME_MENU("Pre Game Menu", "/FXML/preGameMenu.fxml"),
    GAME_MENU("Game Menu", "/FXML/gameMenu.fxml"),
    GAME_HISTORY_MENU("Game History Menu", "/FXML/gameHistoryMenu.fxml"),
    SCORE_TABLE_MENU("Score Table Menu", "/FXML/scoreTableMenu.fxml"),
    FRIENDS_MENU("Friends Menu", "/FXML/friendsMenu.fxml"),
    GAME_REQUEST_HISTORY_MENU("Game Request History Menu", "/FXML/gameRequestHistory.fxml"),
    TV_MENU("TV Menu", "/FXML/tvMenu.fxml"),
    TOURNAMENT_MENU("Tournament Menu", "/FXML/tournamentMenu.fxml"),
    RESULT_MENU("Result Menu", "/FXML/resultMenu.fxml"),

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

