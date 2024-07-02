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

    public static String getFactionDeckShieldImageAddress(FactionsType factionsType) {
        String path = Main.class.getResource("/images/icons/").toString();
        switch (factionsType) {
            case EmpireNilfgaardian -> {
                return (path + "deck_shield_nilfgaard.png");
            }
            case Monsters -> {
                return (path + "deck_shield_monsters.png");
            }
            case RealmsNorthern -> {
                return (path + "deck_shield_realms.png");
            }
            case ScoiaTael -> {
                return (path + "deck_shield_scoiatael.png");
            }
            case Skellige -> {
                return (path + "deck_shield_skellige.png");
            }
            default -> {
                return null;
            }
        }
    }
    public static String getFactionBackDeckImageAddress(FactionsType factionsType) {
        String path = Main.class.getResource("/images/icons/").toString();
        switch (factionsType) {
            case EmpireNilfgaardian -> {
                return (path + "deck_back_nilfgaard.jpg");
            }
            case Monsters -> {
                return (path + "deck_back_monsters.jpg");
            }
            case RealmsNorthern -> {
                return (path + "deck_back_realms.jpg");
            }
            case ScoiaTael -> {
                return (path + "deck_back_scoiatael.jpg");
            }
            case Skellige -> {
                return (path + "deck_back_skellige.jpg");
            }
            default -> {
                return null;
            }
        }
    }
}
