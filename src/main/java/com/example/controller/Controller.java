package com.example.controller;

public enum Controller {
    LOGIN_MENU_CONTROLLER(new LoginMenuController()),
    MAIN_MENU_CONTROLLER(new MainMenuController()),
    PROFILE_MENU_CONTROLLER(new ProfileMenuController()),
    GAME_MENU_CONTROLLER(new GameMenuControllerForOnlineGame()),
    PRE_GAME_MENU_CONTROLLER(new PreGameMenuController()),
    GAME_HISTORY_MENU_CONTROLLER(new GameHistoryController()),
    SCORE_TABLE_MENU_CONTROLLER(new ScoreTableController()),
    FRIENDS_MENU_CONTROLLER(new FriendsMenuController()),
    GAME_REQUEST_HISTORY_MENU_CONTROLLER(new GameRequestHistoryMenuController()),
    TV_MENU_CONTROLLER(new TVMenuController()),
    TOURNAMENT_MENU_CONTROLLER(new TournamentMenuController()),

    ;
    private final AppController controller;

    Controller(AppController controller) {
        this.controller = controller;
    }

    public void run() {
        this.controller.run();
    }

    public AppController getController() {
        return controller;
    }

}
