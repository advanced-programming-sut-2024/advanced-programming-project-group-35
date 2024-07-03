package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

public class SonOfMedellAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        removeMaxPoweredCardInARow(table.getOpponent(), table.getOpponent().getBoard().getRangedCardPlace());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
    private void removeMaxPoweredCardInARow(Player player, Row row) {
        if (!row.isEmpty() && row.getStrength() >= 10) {
            int maximumPowerInRow = 0;
            UnitCard maxPoweredCard = new UnitCard(0, null, null, null, false, null, false);
            for (Card card : player.getBoard().getRangedCardPlace().getCards()) {
                if (card.getAbilityName() != AbilityName.DECOY && maxPoweredCard.getCurrentPower() >= maximumPowerInRow) {
                    maxPoweredCard = (UnitCard) card;
                    maximumPowerInRow = maxPoweredCard.getCurrentPower();
                }
            }
            player.getBoard().getRangedCardPlace().removeCard(maxPoweredCard);
            player.getBoard().getDiscardPile().addCard(maxPoweredCard);
        }
    }
}
