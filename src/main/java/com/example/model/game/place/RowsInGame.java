package com.example.model.game.place;

public enum RowsInGame {
    currentPlayerHand("currentPlayerHandObservableList"),
    weather("weatherObservableList"),
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
    public static RowsInGame getRowInGameByName(String rowName) {
        for (RowsInGame row : RowsInGame.values()) {
            if (row.listName.equals(rowName)) return row;
        }
        return null;
    }
}
