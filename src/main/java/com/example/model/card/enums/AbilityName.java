package com.example.model.card.enums;

import com.example.model.card.Ability;
import com.example.model.card.cardsAbilities.*;

public enum AbilityName {
    COMMANDERS_HORN("commanders_horn"),
    DECOY("decoy"),
    MEDIC("medic"),
    MORALE_BOOST("moral_boost"),
    SCORCH("scorch"),
    TIGHT_BOND("tight_bound"),
    MUSTER("muster"),
    SPY("spy"),
    BERSERKER("berserker"),
    MARDROEME("mardroeme"),
    TRANSFORMER("transformer"),
    WEATHER("weather"),
    NONE("");
    private final String abilityName;

    AbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public static AbilityName getAbilityNameByName(String abilityName) {
        for (AbilityName abilities : AbilityName.values()) {
            if (abilities.abilityName.equals(abilityName)) return abilities;
        }
        return null;
    }

    public static Ability getAbilityByName(String ability) {
        switch (ability) {
            case "muster" -> {
                return new MusterAbility();
            }
            case "spy" -> {
                return new SpyAbility();
            }
            case "moral_boost" -> {
                return new MoralBoostAbility();
            }
            case "scorch" -> {
                return new ScorchAbility();
            }
            case "medic" -> {
                return new MedicAbility();
            }
            case "berserker" -> {
                return new BerskerAbility();
            }
            case "transformer" -> {
                return new TransformerAbility();
            }
            case "weather" -> {
                return new WeatherAbility();
            }
            case "tight_bound" -> {
                return new TightBondAbility();
            }
            case "mardroeme" -> {
                return new MardroemeAbility();
            }
            case "commanders_horn" -> {
                return new CommandersHornCardAbility();
            }
            case "decoy" -> {
                return new DecoyAbility();
            }
            default -> {
                return null;
            }
        }
    }
}
