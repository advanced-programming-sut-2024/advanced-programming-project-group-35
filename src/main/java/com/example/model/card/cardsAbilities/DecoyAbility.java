package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.*;
import com.example.model.game.place.RowsInGame;

public class DecoyAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Card cardToSwap = (Card) abilityContext.getParam("cardToSwap");
        Card decoyCard = (Card) abilityContext.getParam("decoyCard");
        String dest = (String) abilityContext.getParam("dest");
        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(cardToSwap.getIdInGame(), dest, RowsInGame.currentPlayerHand.toString());
        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(decoyCard.getIdInGame(), RowsInGame.currentPlayerHand.toString(), dest);
    }
}
