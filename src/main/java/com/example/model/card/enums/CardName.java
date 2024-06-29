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
    KING_BRAN("King Bran"),
    ;
    private final String name;

    CardName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
