package com.example.model.card.enums;

public enum CardName {
    SCORCH("Scorch"),
    BERSERKER("Berserker"),
    VIDKAARL("Vidkaarl"),
    SVANRIGE("Svanrige"),
    UDALRYK("Udalryk"),
    DONAR_AN_HINDAR("Donar an Hindar"),
    CLAN_AN_CRAITE("Clan An Craite"),
    BLUEBOY_LUGOS("Blueboy Lugos"),
    MARDOEME("Mardoeme"),
    BITING_FROST("Biting Frost"),
    TORRENTIAL_RAIN("Torrential Rain"),
    IMPENETRABLE_FOG("Impenetrable fog"),
    SKELLIGE_STORM("Skellige Storm"),
    CLEAR_WEATHER("Clear Weather");

    private final String name;

    CardName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
