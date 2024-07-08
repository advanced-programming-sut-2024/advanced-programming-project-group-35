package com.example.model;

public class GameData {
    private String opponentName;
    private String date;
    private int currentPlayerFinalScore;
    private int opponentFinalScore;
    private int starterPlayerScores[];
    private int opponentPlayerScores[];
    private String winnerName; //TODO چک شه اگه نال بود باید بنویسه مساوی
    private boolean isDraw = false;

    public GameData(String opponentName, String date, int currentPlayerFinalScore, int opponentFinalScore, int[] starterPlayerScores, int[] opponentPlayerScores, String winnerName) {
        this.opponentName = opponentName;
        this.date = date;
        this.currentPlayerFinalScore = currentPlayerFinalScore;
        this.opponentFinalScore = opponentFinalScore;
        this.starterPlayerScores = starterPlayerScores;
        this.opponentPlayerScores = opponentPlayerScores;
        if (winnerName == null) {
            this.isDraw = true;
        }
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

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrentPlayerFinalScore(int currentPlayerFinalScore) {
        this.currentPlayerFinalScore = currentPlayerFinalScore;
    }

    public void setOpponentFinalScore(int opponentFinalScore) {
        this.opponentFinalScore = opponentFinalScore;
    }

    public void setStarterPlayerScores(int[] starterPlayerScores) {
        this.starterPlayerScores = starterPlayerScores;
    }

    public void setOpponentPlayerScores(int[] opponentPlayerScores) {
        this.opponentPlayerScores = opponentPlayerScores;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
