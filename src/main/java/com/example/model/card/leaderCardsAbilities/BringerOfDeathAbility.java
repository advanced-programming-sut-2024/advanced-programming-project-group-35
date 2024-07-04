package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.cardsAbilities.CommandersHornCardAbility;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class BringerOfDeathAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        Row close = abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        if (close.getSpecialCard().getAbilityName() == AbilityName.COMMANDER_HORN) {
            for (Card card : close.getCards()) {
                ((UnitCard)card).duplicatePower();
            }
        }
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
