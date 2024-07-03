package com.example.model.game;

import com.example.model.User;

import java.util.HashMap;

public class Player {
    private int numberOfCrystals;
    private int numberOfVetoCards;
    private int id;
    private String name;
    private Board board;
    private int score;
    private boolean passRound;
    private HashMap<Integer, Integer> scoresOfRounds;

    public Player(String name) {
        this.name = name;
        this.id = User.getUserByUsername(name).getID();
        this.board = new Board();
        this.scoresOfRounds = new HashMap<>();
        this.numberOfCrystals = 2;
        this.numberOfVetoCards = 2;
        this.passRound = false;
    }

    public Player(int ID){
        this.name = User.getUserByID(ID).getUsername();
        this.id = ID;
        this.board = new Board();
        this.scoresOfRounds = new HashMap<>();
        this.numberOfCrystals = 2;
        this.numberOfVetoCards = 2;
        this.passRound = false;
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

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public int getId() {
        return id;
    }
}
