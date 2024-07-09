package com.example.controller;

import com.example.model.App;
import com.example.model.User;
import com.example.view.Menu;

import java.io.IOException;

public class MainMenuController extends AppController {
    private final User loggedInUser = App.getLoggedInUser();
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.MAIN_MENU);
            App.setCurrentController(Controller.MAIN_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void createGame(String opponentName) {

    }
    public void logout() throws IOException {
        App.setLoggedInUser(null);
        App.setCurrentMenu(Menu.LOGIN_MENU);
        Controller.LOGIN_MENU_CONTROLLER.run();
    }
    public void openProfileMenu() {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }

    public void openPreGameMenu() {
        App.setCurrentMenu(Menu.PREGAME_MENU);
        Controller.PRE_GAME_MENU_CONTROLLER.run();
    }

    public void openScoreTable() {
        App.setCurrentMenu(Menu.SCORE_TABLE_MENU);
        Controller.SCORE_TABLE_MENU_CONTROLLER.run();
    }
}
