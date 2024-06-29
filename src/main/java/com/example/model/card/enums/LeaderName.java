package com.example.model.card.enums;

public enum LeaderName {
    KING_BRAN("King Bran"),
    CRACH_AN_CRAITE("Crach an Craite"),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe"),
    PUREBLOOD_ELF("Pureblood Elf"),
    THE_STEEL_FORGED("The Steel-Forged"),
    DAISY_OF_THE_VALLEY("Daisy of the Valley"),
    THE_BEAUTIFUL("The Beautiful"),
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna"),
    THE_TREACHEROUS("The Treacherous"),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders"),
    DESTROYER_OF_WORLDS("Destroyer of Worlds"),
    KING_OF_THE_WILD_HUNT("King of the wild Hunt"),
    BRINGER_OF_DEATH("Bringer of Death"),
    INVADER_OF_THE_NORTH("Invader of the North"),
    THE_RELENTLESS("The Relentless"),
    EMPEROR_OF_NILFGAARD("Emperor of Nilfgaard"),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty"),
    THE_WHITE_FLAME("The White Flame");
    private final String name;

    LeaderName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
