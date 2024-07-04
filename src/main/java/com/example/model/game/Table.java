package com.example.model.game;

import com.example.model.card.enums.FactionsType;
import com.example.model.game.place.SpellPlace;
import java.util.ArrayList;
import java.util.Date;

public class Table {
    private int gameId;
    private Player currentPlayer;
    private Player opponent;
    private SpellPlace spellPlace;
    private int roundNumber;
    private ArrayList<Round> rounds;
    private Round currentRound;
    public Table(Player player1, Player player2) {
        if (player2.getBoard().getDeck().getFaction().equals(FactionsType.ScoiaTael)) {
            this.currentPlayer = player2;
            this.opponent = player1;
        } else {
            this.currentPlayer = player1;
            this.opponent = player2;
        }
        this.rounds = new ArrayList<>();
        this.roundNumber = 1;
        this.gameId = new Date().hashCode();
        this.spellPlace = new SpellPlace();
    }

    public int getGameId() {
        return gameId;
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

    public void setSpellPlace(SpellPlace spellPlace) {
        this.spellPlace = spellPlace;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void swapPlayers() {
        Player tmp = currentPlayer;
        currentPlayer = opponent;
        opponent = tmp;
    }

}
