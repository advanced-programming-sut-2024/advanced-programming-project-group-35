package com.example.model.card;

import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;

public class SpecialCard extends Card{
    public SpecialCard(Place place, Ability ability, CardData cardData) {
        super(place, ability, cardData);
    }
}
