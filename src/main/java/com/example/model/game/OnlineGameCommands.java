package com.example.model.game;

import com.example.model.card.leaderCardsAbilities.LeaderAbility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum OnlineGameCommands {
    MOVE_CARD_AND_DO_ABILITY("player\\|(?<playerPriority>\\d+)\\|movedCard\\|(?<cardId>\\d+)\\|from\\|(?<origin>\\S+)\\|to\\|(?<dest>\\S+)\\|andDoAbility"),
    MOVE_CARD_AND_DONT_DO_ABILITY("player\\|(?<playerPriority>\\d+)\\|movedCard\\|(?<cardId>\\d+)\\|from\\|(?<origin>\\S+)\\|to\\|(?<dest>\\S+)\\|andDontDoAbility"),
    CHANGE_TURN("change turn"),
    PASSED_ROUND("player\\|(?<playerId>\\d+)\\|passedRound"),
    LEADER_ABILITY("leader ability done"),
    ;
    private final String pattern;

    OnlineGameCommands(String pattern) {
        this.pattern = pattern;
    }
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
