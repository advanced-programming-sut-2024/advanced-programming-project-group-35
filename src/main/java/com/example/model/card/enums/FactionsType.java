package com.example.model.card.enums;

import com.example.Main;

public enum FactionsType {
    EmpireNilfgaardian,
    Monsters,
    RealmsNorthern,
    ScoiaTael,
    Skellige,
    ;
    public final static int numberOfFactions = 5;
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

    public static String toSting(FactionsType factionsType) {
        switch (factionsType) {
            case EmpireNilfgaardian -> {
                return ("EmpireNilfgaardian");
            }
            case Monsters -> {
                return ("Monsters");
            }
            case RealmsNorthern -> {
                return ("RealmsNorthern");
            }
            case ScoiaTael -> {
                return ("ScoiaTael");
            }
            case Skellige -> {
                return ("Skellige");
            }
            default -> {
                return null;
            }
        }
    }
}
