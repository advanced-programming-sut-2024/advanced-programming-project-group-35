package com.example.model.card.enums;

import com.example.model.card.Ability;
import com.example.model.card.cardsAbilities.*;

public enum Abilities {
    COMMANDERS_HORN,
    DECOY,
    MEDIC,
    MORALE_BOOST,
    SCORCH,
    TIGHT_BOND,
    MUSTER,
    SPY,
    BERSERKER,
    MARDROEME,
    TRANSFORMER,
    NONE;

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
