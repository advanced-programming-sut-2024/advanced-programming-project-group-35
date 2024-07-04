package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

import java.util.ArrayList;

public class HopeOfTheAenSeidheAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        Row close = abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        Row ranged = abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace();
        transferSiegeCards(close, ranged);
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void transferSiegeCards(Row close, Row ranged) {
        ArrayList<Card> closeCopy = new ArrayList<>(close.getCards());
        ArrayList<Card> rangedCopy = new ArrayList<>(ranged.getCards());
        for (Card card : rangedCopy) {
            if (ranged.getSpecialPlace() == null && close.getSpecialPlace() != null) {
                ranged.removeCard(card);
                close.addCard(card);
            }
        }
        for (Card card : closeCopy) {
            if (close.getSpecialPlace() == null && ranged.getSpecialPlace() != null) {
                close.removeCard(card);
                ranged.addCard(card);
            }
        }
    }
}
