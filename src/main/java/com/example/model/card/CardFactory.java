package com.example.model.card;

import com.example.model.card.cardsAbilities.WeatherAbility;
import com.example.model.card.enums.Abilities;

public class CardFactory {
    public static Card getCardByName(String cardName) {
        com.example.model.card.enums.CardData cardData = com.example.model.card.enums.CardData.getCardDataByName(cardName);
        switch (cardData.getName()) {
            case "soldier" -> {
                return new UnitCard(cardData.getPower(), Abilities.getAbilityByName(cardData.getAbilityName()), cardData.getPlaceToBe(), false, cardData, false);
            }
            case "hero" -> {
                return new UnitCard(cardData.getPower(), Abilities.getAbilityByName(cardData.getAbilityName()), cardData.getPlaceToBe(), true, cardData, false);
            }
            case "special" -> {
                if (cardData.getAbility().equals("weather")) {
                    return new WeatherCard(cardData.getPlaceToBe(), new WeatherAbility(), cardData);
                } else {
                    return new CardData(cardData.getPlaceToBe(), null, cardData);
                }
            }
            default -> {
                return null;
            }
        }
    }
}
