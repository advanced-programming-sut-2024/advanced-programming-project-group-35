package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.*;
import com.example.model.game.place.RowsInGame;

import java.util.ArrayList;

public class CrachAnCraiteAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        moveCardsFromDiscardPileToDeck(abilityContext.getTable(), abilityContext.getTable().getCurrentPlayer(), abilityContext.getTable().getCurrentPlayer().getBoard());
        moveCardsFromDiscardPileToDeck(abilityContext.getTable(), abilityContext.getTable().getOpponent(), abilityContext.getTable().getOpponent().getBoard());
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void moveCardsFromDiscardPileToDeck(Table table, Player player,Board board) {
        Deck deck = board.getDeck();
        deck.shuffle();
        DiscardPile discardPile = board.getDiscardPile();
        ArrayList<Card> cardsToTransfer = new ArrayList<>(discardPile.getCards());
        for (Card card : cardsToTransfer) {
            if (card != null) {
                if (player == table.getCurrentPlayer()) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerHand.toString());
                } else {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentHand.toString());
                }
            }
        }
    }
}
