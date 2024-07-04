package com.example.model.game;

import com.example.model.game.place.SpellPlace;

public class OnlineTable { //Table but players doesn't change
    private Player player1;
    private Player player2;
    private SpellPlace spellPlace;
    private int roundNumber;
    private Round currentRound;

    public OnlineTable(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public SpellPlace getSpellPlace() {
        return spellPlace;
    }

    public void setSpellPlace(SpellPlace spellPlace) {
        this.spellPlace = spellPlace;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public int getId() {
        return player1.getId() + player2.getId();
    }
}
