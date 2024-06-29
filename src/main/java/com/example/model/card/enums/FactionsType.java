package com.example.model.card.enums;

import com.example.Main;

public enum FactionsType {
    EmpireNilfgaardian,
    Monsters,
    RealmsNorthern,
    ScoiaTael,
    Skellige,
    ;

    public static FactionsType getFactionByName(String factionName) {
        switch (factionName) {
            case "EmpireNilfgaardian" -> {
                return EmpireNilfgaardian;
            }
            case "Monsters" -> {
                return Monsters;
            }
            case "RealmsNorthern" -> {
                return RealmsNorthern;
            }
            case "ScoiaTael" -> {
                return ScoiaTael;
            }
            case "Skellige" -> {
                return Skellige;
            }
            default -> {
                return null;
            }
        }
    }
}
