package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.game.Table;

public class KingOfTheWildHuntAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        //TODO  کارت های غیر هیرو از کارتای مرده نشون داده میشه و یکی انتخاب میشه و وارد هند میشه
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
