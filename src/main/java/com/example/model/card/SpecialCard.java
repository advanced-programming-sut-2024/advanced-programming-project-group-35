package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;

public class SpecialCard extends Card{
    public SpecialCard(Place place, Ability ability, AbilityName abilityName, CardData cardData) {
        super(place, ability, abilityName, cardData);
    }
}
