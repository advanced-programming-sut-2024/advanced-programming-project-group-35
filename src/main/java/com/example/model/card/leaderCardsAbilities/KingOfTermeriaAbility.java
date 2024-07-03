package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.card.cardsAbilities.CommandersHornCardAbility;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class KingOfTermeriaAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Row siege = table.getCurrentPlayer().getBoard().getSiegeCardPlace();
        if (!(siege.getSpecialCard().getAbilityName() == AbilityName.COMMANDER_HORN)) {
            for (UnitCard card : siege.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
