package com.example.model.card.unitCards;

import com.example.model.card.Ability;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.game.place.UnitPlace;

public class CommandersHornCard extends UnitCard implements Action {

    public CommandersHornCard(int power, String imageAddress, Ability ability, UnitPlace place, boolean isHero, AbilityName abilityName, CardName cardName, boolean noRemove) {
        super(power, imageAddress, ability, place, isHero, abilityName, cardName, noRemove);
    }

    @Override
    public void doAction() {

    }
}
