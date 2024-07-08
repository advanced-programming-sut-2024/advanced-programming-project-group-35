package com.example.model.game;

import com.example.model.App;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.place.SpellPlace;
import java.util.ArrayList;
import java.util.Date;

public class Table {
    private Player currentPlayer;
    private Player opponent;
    private SpellPlace spellPlace;
    private int roundNumber;
    private ArrayList<Round> rounds;
    private Round currentRound;
    private Player playerInTurn;
    public Table(Player player1, Player player2) {
      this.playerInTurn = player1;
        if (player1.getId() == App.getLoggedInUser().getId()) {
            this.currentPlayer = player1;
            this.opponent = player2;
        } else {
            this.currentPlayer = player2;
            this.opponent = player1;
        }
        this.rounds = new ArrayList<>();
        this.roundNumber = 1;
        this.spellPlace = new SpellPlace();
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }


    public void addRound(Round round) {
        rounds.add(round);
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public SpellPlace getSpellPlace() {
        return spellPlace;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }
}
