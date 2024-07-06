package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.game.Board;
import com.example.model.game.Table;

public class HisImperialMajestyAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        //TODO باید سه کارت رندوم از هند حریف نمایش داده بشه
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
