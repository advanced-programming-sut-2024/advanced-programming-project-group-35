package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.card.cardsAbilities.CommandersHornCardAbility;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class TheBeautifulAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        Row ranged = table.getCurrentPlayer().getBoard().getRangedCardPlace();
        if (!(ranged.getSpecialPlace().getAbility() instanceof CommandersHornCardAbility)) {
            for (UnitCard card : ranged.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
