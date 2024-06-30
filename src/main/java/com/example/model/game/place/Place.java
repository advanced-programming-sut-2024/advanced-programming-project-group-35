package com.example.model.game.place;

public enum Place {
    CLOSE_COMBAT,
    RANGED,
    SIEGE,
    WEATHER,
    LEADER,
    AGILE,
    SPECIAL;

    public static Place getPlaceToBeByName(String placeToBe) {
        switch (placeToBe) {
            case "melee" -> {
                return CLOSE_COMBAT;
            }
            case "ranged" -> {
                return RANGED;
            }
            case "siege" -> {
                return SIEGE;
            }
            case "weather" -> {
                return WEATHER;
            }
            case "leader" -> {
                return LEADER;
            }
            case "special" -> {
                return SPECIAL;
            }
            case "agile" -> {
                return AGILE;
            }
            default -> {
                return null;
            }
        }
    }
}
