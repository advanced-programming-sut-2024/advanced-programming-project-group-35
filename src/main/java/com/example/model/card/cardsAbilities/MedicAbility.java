package com.example.model.card.cardsAbilities;


import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.DiscardPile;
import com.example.model.game.place.RowsInGame;

import java.util.Random;

public class MedicAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        DiscardPile discardPile = abilityContext.getTable().getCurrentPlayer().getBoard().getDiscardPile();
        if (!discardPile.getCards().isEmpty()) {
            Card cardToRevive = discardPile.getCard(new Random().nextInt(discardPile.getSize()));
            discardPile.removeCard(cardToRevive);
            abilityContext.getTable().getCurrentPlayer().getBoard().getHand().addCard(cardToRevive);
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(cardToRevive.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerHand.toString());
        }
    }
}
