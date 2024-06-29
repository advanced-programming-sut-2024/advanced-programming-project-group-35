package com.example.controller;

import com.example.model.App;
import com.example.view.Menu;

public class GameHistoryController extends AppController {
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.GAME_HISTORY_MENU);
            App.setCurrentController(Controller.GAME_HISTORY_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
