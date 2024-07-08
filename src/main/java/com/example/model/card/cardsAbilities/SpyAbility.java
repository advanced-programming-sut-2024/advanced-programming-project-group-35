package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Deck;
import com.example.model.game.Hand;
import com.example.model.game.place.RowsInGame;

public class SpyAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Deck deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck();
        Hand hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand();

        if (deck.getSize() > 1) {
            Card selectedCard1 = deck.getCard(0);
            Card selectedCard2 = deck.getCard(1);
            deck.removeCard(selectedCard1);
            deck.removeCard(selectedCard2);
            hand.addCard(selectedCard1);
            hand.addCard(selectedCard2);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(selectedCard1.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(selectedCard2.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().addMouseEventsForHandCards();
        } else if (deck.getSize() == 1) {
            Card selectedCard = deck.getCard(0);
            deck.removeCard(selectedCard);
            hand.addCard(selectedCard);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(selectedCard.getIdInGame(), "currentPlayerHandObservableList", "currentPlayerDeckObservableList");
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().addMouseEventsForHandCards();
        }
    }
}
