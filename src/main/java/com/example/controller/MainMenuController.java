package com.example.controller;

import com.example.model.App;
import com.example.view.Menu;

public class MainMenuController extends AppController {
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.MAIN_MENU);
            App.setCurrentController(Controller.MAIN_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void runCommand(String input) {

    }
    public void createGame(String opponentName) {

    }

    public void menuEnter(Menu menu) {
    }
    public void logout() {
    }
    public void showCurrentMenu() {
    }
}
