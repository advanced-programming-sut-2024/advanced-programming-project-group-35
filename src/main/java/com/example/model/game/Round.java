package com.example.model.game;

import java.util.HashMap;

public class Round {
    private int roundNumber;
    private boolean isDraw;
    private boolean isWon;
    private Player winner;
    private HashMap<Player, Integer> scores = new HashMap<>();

    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
    }
    public void addScore(Player player, int score) {
        scores.put(player, score);
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public boolean isWon() {
        return isWon;
    }

    public Player getWinner() {
        return winner;
    }

    public HashMap<Player, Integer> getScores() {
        return scores;
    }
}
