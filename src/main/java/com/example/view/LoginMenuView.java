package com.example.view;

import com.example.model.IO.errors.LoginMenuErrors;

public class LoginMenuView {
    public static String toString(LoginMenuErrors error) {
        switch (error) {
            case INVALID_COMMAND:
                return "Invalid command";
            case USER_ALREADY_EXISTS:
                return "User already exists";
            case INVALID_USERNAME_FORMAT:
                return "Invalid username format";
            case INVALID_PASSWORD:
                return "Invalid password";
            case REGISTER_SUCCESSFUL:
                return "Register successful";
            case USER_DOESNT_EXIST:
                return "User doesn't exist";
            case PASSWORD_DOESNT_MATCH:
                return "Password doesn't match";
            case LOGIN_SUCCESSFUL:
                return "Login successful";
            case NO_ACCOUNT_TO_LOGOUT:
                return "No account to logout";
            case INVALID_USERNAME:
                return "Invalid username";
            case WEAK_PASSWORD:
                return "Weak password";
            case INVALID_EMAIL:
                return "Invalid email";
            case WRONG_ANSWER_CONFIRMATION:
                return "Wrong answer confirmation";
            case PASSWORD_CHANGED:
                return "Password changed";
            case SET_PASSWORD:
                return "Set your new password";
            default:
                return "";
        }
    }
}
