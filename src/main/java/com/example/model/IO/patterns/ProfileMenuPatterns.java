package com.example.model.IO.patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuPatterns {
    REGISTER_USER("")
    ;
    private final String pattern;

    ProfileMenuPatterns(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMather(String input) {
       Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
