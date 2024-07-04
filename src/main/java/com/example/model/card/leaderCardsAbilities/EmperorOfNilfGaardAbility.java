package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.game.Table;

public class EmperorOfNilfGaardAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        abilityContext.getTable().getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
