package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.card.cardsAbilities.CommandersHornCardAbility;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class KingOfTermeriaAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Row siege = table.getCurrentPlayer().getBoard().getSiegeCardPlace();
        if (!(siege.getSpecialPlace().getAbility() instanceof CommandersHornCardAbility)) {
            for (UnitCard card : siege.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
