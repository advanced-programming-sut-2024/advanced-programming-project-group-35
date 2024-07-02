package com.example.model.game.place;

public enum RowsInGame {
    currentPlayerHandObservableList("currentPlayerHandObservableList"),
    currentPlayerCloseCombatObservableList("currentPlayerCloseCombatObservableList"),
    currentPlayerRangedObservableList("currentPlayerRangedObservableList"),
    currentPlayerSiegeObservableList("currentPlayerSiegeObservableList"),
    weatherObservableList("weatherObservableList"),
    currentPlayerCloseCombatSpecialPlace("currentPlayerCloseCombatSpecialPlaceObservableList"),
    currentPlayerRangedSpecialPlace("currentPlayerRangedSpecialPlaceObservableList"),
    currentPlayerSiegeSpecialPlace("currentPlayerSiegeSpecialPlaceObservableList")
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
