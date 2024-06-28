package com.example.model.game;

import java.util.HashMap;

public class Player {
    private String name;
    private Board board;
    private int score;
    private HashMap<Integer, Integer> scoresOfRounds;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.scoresOfRounds = new HashMap<>();
    }
    public void enterScore(int key, int value) {
        scoresOfRounds.put(key, value);
    }


    public void increaseScore(int a) {
        score += a;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

}
