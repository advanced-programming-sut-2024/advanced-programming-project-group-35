package com.example.model.card;

import com.example.model.card.enums.CardName;
import com.example.model.game.place.UnitPlace;

public class WeatherCard extends SpecialCard {
    public WeatherCard(UnitPlace place, Ability ability, CardName cardName) {
        super(place, ability, cardName);
    }
}
