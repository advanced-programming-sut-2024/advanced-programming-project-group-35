package com.example.model.card.leaderCardsAbilities;

import com.example.model.game.Table;

public class EmperorOfNilfGaardAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        table.getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
