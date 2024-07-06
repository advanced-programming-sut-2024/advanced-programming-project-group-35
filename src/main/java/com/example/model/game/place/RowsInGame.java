package com.example.model.game.place;

public enum RowsInGame {
    currentPlayerHand("currentPlayerHand"),
    currentPlayerDeck("currentPlayerDeck"),
    opponentHand("opponentHand"),
    opponentDeck("opponentDeck"),
    weather("weather"),
    currentPlayerCloseCombat("currentPlayerCloseCombat"),
    currentPlayerRanged("currentPlayerRanged"),
    currentPlayerSiege("currentPlayerSiege"),
    currentPlayerCloseCombatSpecialPlace("currentPlayerCloseCombatSpecialPlace"),
    currentPlayerRangedSpecialPlace("currentPlayerRangedSpecialPlace"),
    currentPlayerSiegeSpecialPlace("currentPlayerSiegeSpecialPlace"),
    opponentCloseCombat("opponentCloseCombat"),
    opponentRanged("opponentRanged"),
    opponentSiege("opponentSiege"),
    opponentCloseCombatSpecialPlace("opponentCloseCombatSpecialPlace"),
    opponentRangedSpecialPlace("opponentRangedSpecialPlace"),
    opponentSiegeSpecialPlace("opponentSiegeSpecialPlace"),

    currentPlayerDiscardPlace("currentPlayerDiscardPlace"),
    opponentDiscardPlace("opponentDiscardPlace"),
    ;
    private final String listName;

    RowsInGame(String listName) {
        this.listName = listName;
    }

    @Override
    public String toString() {
        return listName;
    }
}
