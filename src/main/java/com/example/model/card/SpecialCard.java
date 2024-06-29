package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.game.place.UnitPlace;

public class SpecialCard extends Card{
    public SpecialCard(String imageAddress, UnitPlace place, Ability ability, CardName cardName) {
        super(imageAddress, place, ability, cardName);
    }
}
