package com.example.model.IO.patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Patterns {
    //register -u <username> -p <password> <password_confirm> -n <nickname> -e <email>
    REGISTER_USER("register -u (?<username>\\S+) -p (?<password>\\S+) (?<confirmPassword>\\S+) -n (?<nickname>\\S+) -e (?<email>\\S+)"),
    //pick question -q <question_number> -a <answer> -c <answer_confirm>
    PICK_QUESTION("pick question -q (?<questionNumber>\\d+) -a (?<answer>\\S+) -c (?<confirmAnswer>\\S+)"),
    //login -u <username> -p <password> --stayLoggedIn
    LOGIN_USER("login -u (?<username>\\S+) -p (?<password>\\S+)( --stayLoggedIn)?"),
    //forgot password -u <username>
    FORGOT_PASSWORD("forgot password -u (?<username>\\S+)"),
    //answer -q <question_number> -a <answer>
    ANSWER_QUESTION("answer -q (?<questionNumber>\\d+) -a (?<answer>\\S+)"),
    //set password -p <password> <password_confirm>
    SET_PASSWORD("set password -p (?<password>\\S+) (?<confirmPassword>\\S+)"),
    //menu enter register menu
    MENU_ENTER_REGISTER_MENU("menu enter register menu"),
    //menu exit
    EXIT("exit");
    public final String pattern;

    Patterns(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMather(String input) {
        java.util.regex.Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
