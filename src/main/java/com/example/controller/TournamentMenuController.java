package com.example.controller;

import com.example.model.App;
import com.example.model.User;
import com.example.view.Menu;

public class TournamentMenuController extends AppController {
    private final User loggedInUser = App.getLoggedInUser();
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.TOURNAMENT_MENU);
            App.setCurrentController(Controller.TOURNAMENT_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
