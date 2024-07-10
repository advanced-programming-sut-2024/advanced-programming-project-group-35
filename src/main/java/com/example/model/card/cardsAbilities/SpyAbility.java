package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Deck;
import com.example.model.game.Hand;
import com.example.model.game.place.RowsInGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Collections;

public class SpyAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Deck deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck();
        if (deck.getSize() > 1) {
            Collections.shuffle(deck.getCards());
            Card selectedCard1 = deck.getCard(0);
            Card selectedCard2 = deck.getCard(1);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(selectedCard1.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(selectedCard2.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
        } else if (deck.getSize() == 1) {
            Collections.shuffle(deck.getCards());
            Card selectedCard = deck.getCard(0);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(selectedCard.getIdInGame(),RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5)));
        timeline.setOnFinished(e -> {
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().addMouseEventsForHandCards();
        });
        timeline.setCycleCount(1);
        timeline.play();
    }
}
