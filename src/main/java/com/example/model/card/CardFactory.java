package com.example.model.card;

import com.example.model.card.cardsAbilities.DecoyAbility;
import com.example.model.card.cardsAbilities.WeatherAbility;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;

public class CardFactory {
    public static Card getCardByName(String cardName) {
        CardData cardData = CardData.getCardDataByName(cardName);
        switch (cardData.getType()) {
            case "soldier" -> {
                return new UnitCard(cardData.getPower(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData.getPlaceToBe(), false, cardData, false);
            }
            case "hero" -> {
                return new UnitCard(cardData.getPower(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData.getPlaceToBe(), true, cardData, false);
            }
            case "special" -> {
                if (cardData.getAbility().equals("weather")) {
                    return new WeatherCard(cardData.getPlaceToBe(), new WeatherAbility(), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                } else if (cardData.getAbility().equals("decoy")) {
                    return new SpecialCard(cardData.getPlaceToBe(), new DecoyAbility(), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                } else {
                    return new SpecialCard(cardData.getPlaceToBe(), null, AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                }
            }
            default -> {
                return null;
            }
        }
    }
}
