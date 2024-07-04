package com.example.model.alerts;

import com.example.Main;

public enum NotificationsData {
    DRAW_ROUND("Draw Round", "notif_draw_round.png"),
    YOU_WON_THIS_ROUND("You Won This Round", "notif_win_round.png"),
    OPPONENT_WON_THIS_ROUND("Opponent Won This Round", "notif_lose_round.png"),
    YOUR_OPPONENT_HAS_PASSED("Your Opponent Has Passed", "notif_round_passed.png"),
    TURN_PASSED("Turn Passed", "notif_round_passed.png"),
    OPPONENT_TURN("Opponent's Turn", "notif_op_turn.png"),
    YOUR_TURN("Your Turn", "notif_me_turn.png"),
    ROUND_START("Round Start", "notif_round_start.png"),
    ;
    private final String message;
    private final String imageAddress;

    NotificationsData(String message, String imageAddress) {
        String srcPath = Main.class.getResource("/images/icons/").toExternalForm();
        this.message = message;
        this.imageAddress = srcPath + imageAddress;
    }

    public String getMessage() {
        return message;
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
