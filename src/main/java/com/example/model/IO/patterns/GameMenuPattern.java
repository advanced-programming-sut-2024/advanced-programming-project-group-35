package com.example.model.IO.patterns;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuPattern {
    //
    ;
    private final String pattern;

    GameMenuPattern(String pattern) {
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
