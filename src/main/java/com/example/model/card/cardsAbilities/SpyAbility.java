package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.game.Deck;
import com.example.model.game.Hand;
import com.example.model.game.place.RowsInGame;

import java.util.ArrayList;
import java.util.Random;

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
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(selectedCard1.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(selectedCard2.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().addMouseEventsForHandCards();
        } else if (deck.getSize() == 1) {
            Card selectedCard = deck.getCard(0);
            deck.removeCard(selectedCard);
            hand.addCard(selectedCard);
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(selectedCard.getIdInGame(), "currentPlayerHandObservableList", "currentPlayerDeckObservableList");
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().addMouseEventsForHandCards();
        }
    }
}
