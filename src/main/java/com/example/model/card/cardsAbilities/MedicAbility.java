package com.example.model.card.cardsAbilities;


import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.DiscardPile;
import com.example.model.game.place.Place;
import com.example.model.game.place.RowsInGame;

import java.util.Random;

public class MedicAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        DiscardPile discardPile = abilityContext.getTable().getCurrentPlayer().getBoard().getDiscardPile();
        if (!discardPile.getCards().isEmpty()) {
            int random = new Random().nextInt(discardPile.getSize());
            Card cardToRevive = discardPile.getCard(random);
            if (cardToRevive != null) {
                if (cardToRevive.getPlace() == Place.CLOSE_COMBAT) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(cardToRevive.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                } else if (cardToRevive.getPlace() == Place.SIEGE) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(cardToRevive.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerSiege.toString());
                } else {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(cardToRevive.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerRanged.toString());
                }
            }
        }
    }
}
