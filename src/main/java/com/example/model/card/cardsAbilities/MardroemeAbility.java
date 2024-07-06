package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;

public class MardroemeAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        if (abilityContext.getRow() != null || !abilityContext.getRow().isEmpty()) {
            for (Card card : abilityContext.getRow().getCards()) {
                if (card != null && card.getAbilityName() ==  AbilityName.BERSERKER)
                    ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).applyMardroeme();
            }
            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(((Card)abilityContext.getParam("mardroemeCard")).getIdInGame(), (String) abilityContext.getParam("dest"), RowsInGame.currentPlayerDiscardPlace.toString());
        }
    }
}
