package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Board;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.RowsInGame;

import java.util.Random;

public class InvaderOfTheNorthAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        removeRandomCardFromDiscardPileAndAddToHand(abilityContext.getTable(), abilityContext.getTable().getCurrentPlayer().getBoard(), abilityContext.getTable().getCurrentPlayer());
        removeRandomCardFromDiscardPileAndAddToHand(abilityContext.getTable(), abilityContext.getTable().getOpponent().getBoard(), abilityContext.getTable().getOpponent());
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void removeRandomCardFromDiscardPileAndAddToHand(Table table, Board board, Player player) {
        Card randomCard = board.getDiscardPile().getCard(new Random().nextInt(board.getDiscardPile().getSize()));
        if (randomCard != null) {
            if (player == table.getCurrentPlayer()) {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(randomCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentDiscardPlace.toString());

            } else {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(randomCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerHand.toString());
            }
        }
    }
}
