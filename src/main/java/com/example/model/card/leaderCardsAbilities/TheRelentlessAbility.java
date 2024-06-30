package com.example.model.card.leaderCardsAbilities;

import com.example.model.game.Table;

public class TheRelentlessAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO تمام کارت های غیر هیرو کارتای مرده حریف باید نشون داده بشه و یکی انتخاب شه و وارد هند بشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
