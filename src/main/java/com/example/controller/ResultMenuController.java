package com.example.controller;

import com.example.model.App;
import com.example.model.User;
import com.example.view.Menu;

public class ResultMenuController extends AppController {
    private final User loggedInUser = App.getLoggedInUser();
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.RESULT_MENU);
            App.setCurrentController(Controller.RESULT_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
