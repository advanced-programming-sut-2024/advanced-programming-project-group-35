package com.example.model.IO.patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuPatterns {
    //
    ;
    private final String pattern;

    MainMenuPatterns(String pattern) {
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
