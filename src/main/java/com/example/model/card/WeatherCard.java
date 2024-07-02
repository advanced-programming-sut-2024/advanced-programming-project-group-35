package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;

public class WeatherCard extends SpecialCard {
    public WeatherCard(Place place, Ability ability, AbilityName abilityName, CardData cardData) {
        super(place, ability, abilityName, cardData);
    }
}
