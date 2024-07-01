package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.card.cardsAbilities.CommandersHornCardAbility;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class BringerOfDeathAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Row close = table.getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        if (close.getSpecialPlace().getAbility() instanceof CommandersHornCardAbility) {
            for (UnitCard card : close.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
