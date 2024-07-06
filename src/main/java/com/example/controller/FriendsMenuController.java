package com.example.controller;

import com.example.model.App;
import com.example.view.Menu;

public class FriendsMenuController extends AppController {
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.FRIENDS_MENU);
            App.setCurrentController(Controller.FRIENDS_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
