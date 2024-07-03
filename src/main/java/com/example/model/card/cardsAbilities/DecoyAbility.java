package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.*;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;

public class DecoyAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Card cardToSwap = (Card) abilityContext.getParam("cardToSwap");
        Card decoyCard = (Card) abilityContext.getParam("decoyCard");
        String dest = (String) abilityContext.getParam("dest");
        Row row = abilityContext.getRow();
        row.removeCard(cardToSwap);
        ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(cardToSwap.getIdInGame(), dest, RowsInGame.currentPlayerHand.toString());
        abilityContext.getTable().getCurrentPlayer().getBoard().getHand().addCard(cardToSwap);
        abilityContext.getTable().getCurrentPlayer().getBoard().getHand().removeCard(decoyCard);
        ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(decoyCard.getIdInGame(), RowsInGame.currentPlayerHand.toString(), dest);
        row.addCard(decoyCard);
    }
}
