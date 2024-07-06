package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;

public class CardFactory {
    public static Card getCardByName(String cardName) {
        CardData cardData = CardData.getCardDataByName(cardName);
        switch (cardData.getType()) {
            case "soldier" -> {
                if (!cardData.getAbilityName().equals("transformer")) {
                    return new UnitCard(cardData.getPower(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData.getPlaceToBe(), false, cardData, false);
                } else {
                    return new UnitCard(cardData.getPower(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData.getPlaceToBe(), false, cardData, true);
                }
            }
            case "hero" -> {
                return new UnitCard(cardData.getPower(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData.getPlaceToBe(), true, cardData, false);
            }
            case "special" -> {
                if (cardData.getAbilityName().equals("weather")) {
                    return new WeatherCard(cardData.getPlaceToBe(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                } else if (cardData.getAbilityName().equals("decoy")) {
                    return new SpecialCard(cardData.getPlaceToBe(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                } else if (cardData.getAbilityName().equals("commander_horn")) {
                    return new SpecialCard(cardData.getPlaceToBe(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                } else if (cardData.getAbilityName().equals("scorch")) {
                    return new SpecialCard(cardData.getPlaceToBe(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                } else if (cardData.getAbilityName().equals("mardroeme")) {
                    return new SpecialCard(cardData.getPlaceToBe(), AbilityName.getAbilityByName(cardData.getAbilityName()), AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);
                }else {
                    System.out.println("bia to card factory ke ridi to card data(parsa)");
                    return new SpecialCard(cardData.getPlaceToBe(), null, AbilityName.getAbilityNameByName(cardData.getAbilityName()), cardData);

                }
            }
            default -> {
                return null;
            }
        }
    }
}
