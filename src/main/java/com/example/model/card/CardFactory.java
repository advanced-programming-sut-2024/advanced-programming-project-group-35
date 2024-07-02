package com.example.model.card;

import com.example.model.card.cardsAbilities.DecoyAbility;
import com.example.model.card.cardsAbilities.WeatherAbility;
import com.example.model.card.enums.Abilities;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;

public class CardFactory {
    public static Card getCardByName(String cardName) {
        CardData cardData = CardData.getCardDataByName(cardName);
        switch (cardData.getType()) {
            case "soldier" -> {
                return new UnitCard(cardData.getPower(), Abilities.getAbilityByName(cardData.getAbilityName()), cardData.getPlaceToBe(), false, cardData, false);
            }
            case "hero" -> {
                return new UnitCard(cardData.getPower(), Abilities.getAbilityByName(cardData.getAbilityName()), cardData.getPlaceToBe(), true, cardData, false);
            }
            case "special" -> {
                if (cardData.getAbility().equals("weather")) {
                    return new WeatherCard(cardData.getPlaceToBe(), new WeatherAbility(), cardData);
                }
                else if (cardData.getAbility().equals("decoy")) {
                    return new WeatherCard(null, null, null);
                } else {
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }
}
