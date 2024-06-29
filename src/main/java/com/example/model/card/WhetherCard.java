package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.card.enums.WhetherTypes;
import com.example.model.card.unitCards.Action;
import com.example.model.game.Board;
import com.example.model.game.place.UnitPlace;

public class WhetherCard extends Card implements Action, Observer {
    private WhetherTypes type;

    public WhetherCard(String imageAddress, UnitPlace place, AbilityName abilityName, Ability ability, CardName cardName) {
        super(imageAddress, place, abilityName, ability, cardName);
    }

    @Override
    public void update(Board board) {
        //
    }

    @Override
    public void doAction() {
        
    }
}
