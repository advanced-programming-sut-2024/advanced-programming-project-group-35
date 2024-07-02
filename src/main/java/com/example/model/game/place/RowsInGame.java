package com.example.model.game.place;

public enum RowsInGame {
    currentPlayerHand("currentPlayerHandObservableList"),
    weatherObservableList("weatherObservableList"),
    currentPlayerCloseCombat("currentPlayerCloseCombatObservableList"),
    currentPlayerRanged("currentPlayerRangedObservableList"),
    currentPlayerSiege("currentPlayerSiegeObservableList"),
    currentPlayerCloseCombatSpecialPlace("currentPlayerCloseCombatSpecialPlaceObservableList"),
    currentPlayerRangedSpecialPlace("currentPlayerRangedSpecialPlaceObservableList"),
    currentPlayerSiegeSpecialPlace("currentPlayerSiegeSpecialPlaceObservableList"),
    opponentPlayerCloseCombat("opponentPlayerCloseCombatObservableList"),
    opponentPlayerRanged("opponentPlayerRangedObservableList"),
    opponentPlayerSiege("opponentPlayerSiegeObservableList"),
    opponentPlayerCloseCombatSpecialPlace("opponentPlayerCloseCombatSpecialPlaceObservableList"),
    opponentPlayerRangedSpecialPlace("opponentPlayerRangedSpecialPlaceObservableList"),
    opponentPlayerSiegeSpecialPlace("opponentPlayerSiegeSpecialPlaceObservableList"),

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
