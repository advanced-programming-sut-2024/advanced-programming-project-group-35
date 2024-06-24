package com.example.model.card;


import com.example.model.card.enums.LeadersAbility;
import com.example.model.card.unitCards.Action;
import com.example.model.game.Board;

public class LeadersCard implements Observer, Action {
    private LeadersAbility ability;

    @Override
    public void update(Board board) {
    }

    @Override
    public void doAction() {

    }
}
