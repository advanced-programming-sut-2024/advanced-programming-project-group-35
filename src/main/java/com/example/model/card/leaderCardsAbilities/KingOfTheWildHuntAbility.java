package com.example.model.card.leaderCardsAbilities;

import com.example.model.game.Table;

public class KingOfTheWildHuntAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO  کارت های غیر هیرو از کارتای مرده نشون داده میشه و یکی انتخاب میشه و وارد هند میشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
