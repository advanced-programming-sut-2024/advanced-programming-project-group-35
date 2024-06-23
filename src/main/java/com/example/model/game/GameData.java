package com.example.model.game;


import com.example.model.user.User;

import java.util.Date;

public class GameData {
    private User opponent;
    private Date date; // baste be niaz mitoonim date ro ham ye class konim
    private int starterPlayerScore;
    private int opponentScore;
    private User winner;

    public GameData(User opponent, Date date, int starterPlayerScore, int opponentScore, User winner) {
        this.opponent = opponent;
        this.date = date;
        this.starterPlayerScore = starterPlayerScore;
        this.opponentScore = opponentScore;
        this.winner = winner;
    }

    public User getOpponent() {
        return opponent;
    }

    public Date getDate() {
        return date;
    }

    public int getStarterPlayerScore() {
        return starterPlayerScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public User getWinner() {
        return winner;
    }
}
