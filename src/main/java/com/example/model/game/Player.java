package com.example.model.game;

import com.example.model.game.place.Row;

import java.util.HashMap;

public class Player {
    private int numberOfCrystals;
    private int numberOfVetoCards;
    private String username;
    private Board board;
    private int score;
    private boolean passRound;
    private HashMap<Integer, Integer> scoresOfRounds;
    private int specialCardCounter;


    public Player(String username) {
        this.username = username;
        this.board = new Board();
        this.scoresOfRounds = new HashMap<>();
        this.numberOfCrystals = 2;
        this.numberOfVetoCards = 2;
        this.passRound = false;
    }
    public void updateScore() {
        int result = 0;
        for (Row row : board.getRows()) {
            result += row.getStrength();
        }
        score = result;
    }

    public boolean isPassRound() {
        return passRound;
    }

    public void setPassRound(boolean passRound) {
        this.passRound = passRound;
    }

    public boolean canVetoCard() {
        if (numberOfVetoCards > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void decreaseNumberOfVetoCards() {
        numberOfVetoCards--;
    }
    public void decreaseCrystals() {
        numberOfCrystals--;
    }


    public int getNumberOfCrystals() {
        return numberOfCrystals;
    }
    public void increaseCrystals() {
        numberOfCrystals++;
    }

    public void enterScore(int key, int value) {
        scoresOfRounds.put(key, value);
    }


    public void increaseScore(int a) {
        score += a;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public Board getBoard() {
        return board;
    }

    public int getSpecialCardCounter() {
        return specialCardCounter;
    }

    public void setSpecialCardCounter(int specialCardCounter) {
        this.specialCardCounter = specialCardCounter;
    }
}
