package com.example.model;


import com.example.model.user.User;

import java.util.Date;
import java.util.HashMap;

public class GameData {
    private String opponentName;
    private String date;
    private int starterPlayerFinalScore;
    private int opponentFinalScore;
    private int starterPlayerScores[];
    private int opponentPlayerScores[];
    private String winnerName;

    public GameData(String opponentName, String date, int starterPlayerFinalScore, int opponentFinalScore, int[] starterPlayerScores, int[] opponentPlayerScores, String winnerName) {
        this.opponentName = opponentName;
        this.date = date;
        this.starterPlayerFinalScore = starterPlayerFinalScore;
        this.opponentFinalScore = opponentFinalScore;
        this.starterPlayerScores = starterPlayerScores;
        this.opponentPlayerScores = opponentPlayerScores;
        this.winnerName = winnerName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public String getDate() {
        return date;
    }

    public int getStarterPlayerFinalScore() {
        return starterPlayerFinalScore;
    }

    public int getOpponentFinalScore() {
        return opponentFinalScore;
    }

    public int[] getStarterPlayerScores() {
        return starterPlayerScores;
    }

    public int[] getOpponentPlayerScores() {
        return opponentPlayerScores;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
