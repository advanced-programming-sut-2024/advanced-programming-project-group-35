package com.example.controller;

import com.example.controller.server.ServerConnector;
import com.example.model.App;
import com.example.model.FriendRequest;
import com.example.model.IO.errors.Errors;
import com.example.model.User;
import com.example.view.Menu;
import com.example.view.OutputView;

import java.util.List;

public class ProfileMenuController extends AppController {
    private User loggedInUser;
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.PROFILE_MENU);
            App.setCurrentController(Controller.PROFILE_MENU_CONTROLLER);
            loggedInUser = App.getLoggedInUser();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void showGameHistory() {
        if (App.getLoggedInUser().getNumberOfPlayedGames() == 0) {
            OutputView.showOutputAlert(Errors.DONT_HAVE_PLAYED_GAME);
        }
        else {
            App.setCurrentMenu(Menu.GAME_HISTORY_MENU);
            Controller.GAME_HISTORY_MENU_CONTROLLER.run();
        }
    }

    public Errors editUsername(String username) {
        if (username == null || !isValidUsername(username)) {
            return OutputView.showOutputAlert(Errors.INVALID_USERNAME_FORMAT);
        }
        if (username.equals(loggedInUser.getUsername())) {
            return OutputView.showOutputAlert(Errors.INVALID_USERNAME);
        }
        if (App.getUserByUsername(username) != null) {
            return OutputView.showOutputAlert(Errors.USER_ALREADY_EXISTS);
        }
        loggedInUser.setUsername(username);
        return OutputView.showOutputAlert(Errors.USERNAME_CHANGED);
    }
    public Errors editNickname(String nickname) {
        if (nickname == null || nickname.equals("")) {
            return null;
        }
        if (nickname.equals(loggedInUser.getNickname())) {
            return OutputView.showOutputAlert(Errors.INVALID_NICKNAME);
        }
        loggedInUser.setNickname(nickname);
        return OutputView.showOutputAlert(Errors.NICKNAME_CHANGED);
    }
    public Errors editEmail(String email) {
        if (!isValidEmail(email)) {
            OutputView.showOutputAlert(Errors.INVALID_EMAIL);
        }
        if (email.equals(loggedInUser.getEmail())) {
            return OutputView.showOutputAlert(Errors.INVALID_EMAIL);
        }
        loggedInUser.setEmail(email);
        return OutputView.showOutputAlert(Errors.EMAIL_CHANGED);
    }
    public Errors editPassword(String oldPassword, String password, String confirmPassword) {
        if (!oldPassword.equals(loggedInUser.getPassword())) {
            return OutputView.showOutputAlert(Errors.PASSWORD_DOESNT_MATCH);
        }
        Errors error = handlePassword(password, confirmPassword);
        if (error != null) {
            return OutputView.showOutputAlert(error);
        }
        if (password.equals(loggedInUser.getPassword())) {
            return OutputView.showOutputAlert(Errors.PASSWORD_IS_SAME);
        }
        loggedInUser.setPassword(password);
        return OutputView.showOutputAlert(Errors.PASSWORD_CHANGED);
    }
    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    private Errors handlePassword(String newPassword, String confirmPassword) {
        if (!isValidPassword(newPassword)) {
            return Errors.INVALID_PASSWORD;
        }
        if (isWeakPassword(newPassword)) {
            return Errors.WEAK_PASSWORD;
        }
        if (!newPassword.equals(confirmPassword)) {
            return Errors.PASSWORD_DOESNT_MATCH;
        }
        return null;
    }
    private boolean isWeakPassword(String password) {
        return !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^\\S{8,}$");
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9-]*$");
    }

    private ServerConnector networkManager; // برای ارتباط با سرور

    // ارسال درخواست دوستی
    public void sendFriendRequest(int receiverId) {
        FriendRequest request = new FriendRequest(App.getLoggedInUser().getID(), receiverId);
        networkManager.sendFriendRequest(request);
    }

    // دریافت لیست درخواست‌های دوستی
    public List<FriendRequest> getFriendRequests() {
        return App.getLoggedInUser().getFriendRequests();
    }

    // پذیرش درخواست دوستی
    public void acceptFriendRequest(FriendRequest request) {
        request.accept();
        networkManager.acceptFriendRequest(request);
        App.getLoggedInUser().getFriends().add(request.getSender());
    }

    // رد درخواست دوستی
    public void rejectFriendRequest(FriendRequest request) {
        request.reject();
        networkManager.rejectFriendRequest(request);
    }

    // دریافت لیست دوستان
    public List<User> getFriendList() {
        return App.getLoggedInUser().getFriends();
    }

    // به‌روزرسانی اطلاعات کاربر از سرور
    public void updateUserInfo() {
        User updatedUser = User.getUserByID(App.getLoggedInUser().getID());
        App.getLoggedInUser().setFriends(updatedUser.getFriends());
        App.getLoggedInUser().setFriendRequests(updatedUser.getFriendRequests());
    }
}
