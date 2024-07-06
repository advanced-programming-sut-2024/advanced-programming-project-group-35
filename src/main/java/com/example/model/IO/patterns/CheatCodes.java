package com.example.model.IO.patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCodes {
    ADD_CARD_TO_HAND("add a random card to hand"),
    RECOVER_CRYSTALS ("recover crystals"),
    RECOVER_LEADER_ABILITY("recover leader ability"),
    LUCK_OPPONENT_LEADER_ABILITY("luck opponent leader ability"),
    LUCK_OPPONENT_EMOTES("luck opponent emotes"),
    SET_CLOWN_PICTURE_FOR_OPPONENT_LEADER_CARD("set clown picture for opponent leader card"),
    ADD_DECOY_CARD("add decoy card");
    private final String pattern;

    CheatCodes(String pattern) {
        this.pattern = pattern;
    }
    public boolean matched(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) return true;
        else return false;
    }
}
