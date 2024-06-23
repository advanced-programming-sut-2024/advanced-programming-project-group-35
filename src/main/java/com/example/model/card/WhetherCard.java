package com.example.model.card;

import com.example.model.card.enums.WhetherTypes;
import com.example.model.card.unitCards.Action;
import com.example.model.game.Board;

public class WhetherCard extends Card implements Action, Observer {
    private WhetherTypes type;
    @Override
    public void update(Board board) {
        //
    }

    @Override
    public void doAction() {
        
    }
}
