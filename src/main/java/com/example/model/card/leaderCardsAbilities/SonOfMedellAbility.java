package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.game.Player;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;

public class SonOfMedellAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        removeMaxPoweredCardInARow(abilityContext.getTable().getOpponent(), abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
    private void removeMaxPoweredCardInARow(Player player, Row row) {
        if (!row.isEmpty() && row.getStrength() >= 10) {
            int maximumPowerInRow = 0;
            UnitCard maxPoweredCard = new UnitCard(0, null, null,null, false, null, false);
            for (Card card : player.getBoard().getRangedCardPlace().getCards()) {
                if (card instanceof UnitCard) {
                    if (((UnitCard)card).getCurrentPower() >= maximumPowerInRow) {
                        maxPoweredCard.setIdInGame(card.getIdInGame());
                        maximumPowerInRow = ((UnitCard)card).getCurrentPower();
                    }
                }
            }
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(maxPoweredCard.getIdInGame(), RowsInGame.opponentRangedSpecialPlace.toString(), RowsInGame.opponentDiscardPlace.toString());
        }
    }
}
