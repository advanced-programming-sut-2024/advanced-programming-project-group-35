package com.example.controller;

import com.example.model.App;
import com.example.model.user.User;
import com.example.view.Menu;

import java.util.regex.Matcher;

public class LoginMenuController extends AppController {
    @Override
    public void run(){
        try {
            App.getAppView().showMenu(Menu.LOGIN_MENU);
            App.setCurrentController(Controller.LOGIN_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void runCommand(String input) {
        try {
            Matcher matcher;
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
    public void menuEnter(Menu menu) {
    }

    public void menuExit() {

    }

    public void showCurrentMenu() {
    }

    public void registerNewUser(String username, String password, String nickname, String email, int securityQuestionNumber, String securityQuestionAnswer) {
    }

    private String generateRandomPassword() {
        return null;
    }

    public void loginUser(User user) {
        App.setLoggedInUser(user);
    }
}
