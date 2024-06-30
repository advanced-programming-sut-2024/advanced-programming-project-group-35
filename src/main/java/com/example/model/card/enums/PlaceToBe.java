package com.example.model.card.enums;

public enum PlaceToBe {
    MELEE,
    RANGED,
    SIEGE,
    WEATHER,
    LEADER,
    AGILE,
    SPECIAL;

    public static PlaceToBe getPlaceToBeByName(String placeToBe) {
        switch (placeToBe) {
            case "melee" -> {
                return MELEE;
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
