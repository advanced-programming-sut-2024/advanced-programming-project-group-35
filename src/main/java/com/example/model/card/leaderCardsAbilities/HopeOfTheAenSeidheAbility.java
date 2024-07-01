package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

import java.util.ArrayList;

public class HopeOfTheAenSeidheAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        Row close = table.getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        Row ranged = table.getCurrentPlayer().getBoard().getRangedCardPlace();
        transferSiegeCards(close, ranged);
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void transferSiegeCards(Row close, Row ranged) {
        ArrayList<UnitCard> closeCopy = new ArrayList<>(close.getCards());
        ArrayList<UnitCard> rangedCopy = new ArrayList<>(ranged.getCards());
        for (UnitCard card : rangedCopy) {
            if (ranged.getSpecialPlace() == null && close.getSpecialPlace() != null) {
                ranged.removeCard(card);
                close.addCard(card);
            }
        }
        for (UnitCard card : closeCopy) {
            if (close.getSpecialPlace() == null && ranged.getSpecialPlace() != null) {
                close.removeCard(card);
                ranged.addCard(card);
            }
        }
    }
}
