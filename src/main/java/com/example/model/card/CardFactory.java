package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.card.unitCardsAbilities.BreskerAbility;
import com.example.model.card.unitCardsAbilities.MardroemeAbility;
import com.example.model.card.unitCardsAbilities.MoralBoostAbility;
import com.example.model.card.unitCardsAbilities.TightBondAbility;
import com.example.model.game.place.UnitPlace;

public class CardFactory {
    public static Card getCardByName(String cardName) {
        Card card;
        switch (cardName) {
            case "Mardoeme" -> {
                card = new SpecialCard("", UnitPlace.SPECIAL,new MardroemeAbility(), CardName.MARDOEME);
                return card;
            }
            case "Berserker" -> {
                card = new UnitCard(4, "", new BreskerAbility(), UnitPlace.CLOSE, false, CardName.BERSERKER, false);
                return card;
            }
            case "Vidkaarl" -> {
                card = new UnitCard(14, "", new MoralBoostAbility(), UnitPlace.CLOSE, false, CardName.VIDKAARL, false);
                return card;
            }
            case "Svanrige" -> {
                card = new UnitCard(4, "1", null, UnitPlace.CLOSE, false,  CardName.SVANRIGE, false);
                return card;
            }
            case "Udalryk" -> {
                card = new UnitCard(4, "2", null, UnitPlace.CLOSE, false,  CardName.UDALRYK, false);
                return card;
            }
            case "Donar an Hindar" -> {
                card = new UnitCard(4, "3", null, UnitPlace.CLOSE, false,  CardName.DONAR_AN_HINDAR, false);
                return card;
            }
            case "Clan An Craite" -> {
                card = new UnitCard(6, "", new TightBondAbility(), UnitPlace.CLOSE,  false, CardName.CLAN_AN_CRAITE, false);
                return card;
            }
            case "Blueboy Lugos" -> {
                card = new UnitCard(6, "", null, UnitPlace.CLOSE, false,  CardName.BLUEBOY_LUGOS, false);
                return card;
            }
            default -> {
                return null;
            }
        }
    }
}
