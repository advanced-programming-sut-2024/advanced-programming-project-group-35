package com.example.model.card;

import com.example.model.card.enums.UnitAbility;
import com.example.model.card.enums.UnitPlace;
import com.example.model.game.Board;

public abstract class UnitCard extends Card {
    private UnitPlace unitPlace;
    private UnitAbility ability;
    private int strength;
    @Override
    public void update(Board board){
        //
    }

    public int getStrength() {
        return strength;
    }
}
