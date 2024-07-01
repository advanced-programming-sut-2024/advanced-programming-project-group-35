package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class QueenOfDolBlathanna implements LeaderAbility {
    @Override
    public void apply(Table table) {
        if (!table.getOpponent().getBoard().getRangedCardPlace().isEmpty() && table.getOpponent().getBoard().getRangedCardPlace().getStrength() >= 10) {
            removeMaxPoweredCardInARow(table.getCurrentPlayer(), table.getOpponent().getBoard().getCloseCombatCardPlace());
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
    private void removeMaxPoweredCardInARow(Player player, Row row) {
        if (!row.isEmpty() && row.getStrength() >= 10) {
            int maximumPowerInRow = 0;
            UnitCard maxPoweredCard = new UnitCard(0, null, null, false, null, false);
            for (UnitCard card : player.getBoard().getRangedCardPlace().getCards()) {
                if (maxPoweredCard.getCurrentPower() >= maximumPowerInRow) {
                    maxPoweredCard = card;
                    maximumPowerInRow = maxPoweredCard.getCurrentPower();
                }
            }
            player.getBoard().getRangedCardPlace().removeCard(maxPoweredCard);
            player.getBoard().getDiscardPile().addCard(maxPoweredCard);
        }
    }
}
