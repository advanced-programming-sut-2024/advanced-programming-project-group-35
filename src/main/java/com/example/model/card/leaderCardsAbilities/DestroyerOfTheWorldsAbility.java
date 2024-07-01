package com.example.model.card.leaderCardsAbilities;

import com.example.model.game.Table;

public class DestroyerOfTheWorldsAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        // TODO کارت های هند نشون داده میشه دوتا انتخاب میشه و از هند حذف میشن و کارت های دک نشون داده میشن یکی انتخاب میشه و وارد هند میشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
