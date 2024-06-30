package com.example.model.card.leaderCardsAbilities;

import com.example.model.game.Table;

public class HisImperialMajestyAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO باید سه کارت رندوم از هند حریف نمایش داده بشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
