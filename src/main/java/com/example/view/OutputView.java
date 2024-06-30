package com.example.view;

import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.model.alerts.AlertType;

public class OutputView {
    private static Errors lastError;
    public static Errors showOutputAlert (Errors error) {
        lastError = error;
        switch (error) {
            case INVALID_COMMAND:
                App.getAppView().showAlert("Invalid command", AlertType.ERROR.getType());
            case USER_ALREADY_EXISTS:
                App.getAppView().showAlert("User already exists", AlertType.ERROR.getType());
            case INVALID_USERNAME_FORMAT:
                App.getAppView().showAlert("Invalid username format", AlertType.ERROR.getType());
            case INVALID_PASSWORD:
                App.getAppView().showAlert("Invalid password", AlertType.ERROR.getType());
            case REGISTER_SUCCESSFUL:
                App.getAppView().showAlert("Register successful", AlertType.SUCCESS.getType());
            case USER_DOESNT_EXIST:
                App.getAppView().showAlert("User doesn't exist", AlertType.ERROR.getType());
            case PASSWORD_DOESNT_MATCH:
                App.getAppView().showAlert("Password doesn't match", AlertType.ERROR.getType());
            case LOGIN_SUCCESSFUL:
                App.getAppView().showAlert("Login successful", AlertType.SUCCESS.getType());
            case NO_ACCOUNT_TO_LOGOUT:
                App.getAppView().showAlert("No account to logout", AlertType.ERROR.getType());
            case INVALID_USERNAME:
                App.getAppView().showAlert("Invalid username", AlertType.ERROR.getType());
            case WEAK_PASSWORD:
                App.getAppView().showAlert("Weak password", AlertType.ERROR.getType());
            case INVALID_EMAIL:
                App.getAppView().showAlert("Invalid email", AlertType.ERROR.getType());
            case WRONG_ANSWER_CONFIRMATION:
                App.getAppView().showAlert("Wrong answer confirmation", AlertType.ERROR.getType());
            case PASSWORD_CHANGED:
                App.getAppView().showAlert("Password changed", AlertType.SUCCESS.getType());
            case SET_PASSWORD:
                App.getAppView().showAlert("Set your new password", AlertType.SUCCESS.getType());
            case WRONG_SECURITY_QUESTION:
                App.getAppView().showAlert("Wrong security question", AlertType.ERROR.getType());
            case USERNAME_CHANGED:
                App.getAppView().showAlert("Username changed", AlertType.SUCCESS.getType());
            case INVALID_NICKNAME:
                App.getAppView().showAlert("Invalid nickname", AlertType.ERROR.getType());
            case NICKNAME_CHANGED:
                App.getAppView().showAlert("Nickname changed", AlertType.SUCCESS.getType());
            case EMAIL_CHANGED:
                App.getAppView().showAlert("Email changed", AlertType.SUCCESS.getType());
            case PASSWORD_IS_SAME:
                App.getAppView().showAlert("Password is the same", AlertType.ERROR.getType());
            case SPECIAL_CARD_ERROR:
                App.getAppView().showAlert("You already selected 10 special cards", AlertType.ERROR.getType());
            case NOT_ENOUGH_CARDS:
                App.getAppView().showAlert("You have to choose 22 cards at least", AlertType.ERROR.getType());
            case DONT_HAVE_PLAYED_GAME:
                App.getAppView().showAlert("You diden't played any game to show game data.", AlertType.ERROR.getType());
            default:
                return null;
        }
    }

    public static Errors getLastError() {
        return lastError;
    }
}
