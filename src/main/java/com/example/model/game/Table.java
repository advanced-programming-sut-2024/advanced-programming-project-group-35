package com.example.model.game;

import com.example.model.card.enums.FactionsType;
import com.example.model.game.place.SpellPlace;

public class Table {
    private Player currentPlayer;
    private Player opponent;
    private SpellPlace spellPlace;
    private int roundNumber;
    private boolean roundWon;
    private boolean roundDraw;
    private Player roundWinner;


    public Table(Player player1, Player player2) {
        if (player2.getBoard().getDeck().getFaction().equals(FactionsType.ScoiaTael)) {
            this.currentPlayer = player2;
            this.opponent = player1;
        } else {
            this.currentPlayer = player1;
            this.opponent = player2;
        }
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean isRoundWon() {
        return roundWon;
    }

    public void setRoundWon(boolean roundWon) {
        this.roundWon = roundWon;
    }

    public boolean isRoundDraw() {
        return roundDraw;
    }

    public void setRoundDraw(boolean roundDraw) {
        this.roundDraw = roundDraw;
    }

    public Player getRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(Player roundWinner) {
        this.roundWinner = roundWinner;
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
