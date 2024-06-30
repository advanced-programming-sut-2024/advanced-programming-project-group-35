package com.example.model;

public class GameData {
    private String opponentName;
    private String date;
    private int currentPlayerFinalScore;
    private int opponentFinalScore;
    private int starterPlayerScores[];
    private int opponentPlayerScores[];
    private String winnerName; //TODO چک شه اگه نال بود باید بنویسه مساوی

    public GameData(String opponentName, String date, int currentPlayerFinalScore, int opponentFinalScore, int[] starterPlayerScores, int[] opponentPlayerScores, String winnerName) {
        this.opponentName = opponentName;
        this.date = date;
        this.currentPlayerFinalScore = currentPlayerFinalScore;
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

    public int getCurrentPlayerFinalScore() {
        return currentPlayerFinalScore;
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
