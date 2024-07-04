package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.game.Table;

public class TheRelentlessAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        //TODO تمام کارت های غیر هیرو کارتای مرده حریف باید نشون داده بشه و یکی انتخاب شه و وارد هند بشه
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
