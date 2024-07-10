package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.RowsInGame;

public class MardroemeAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        if (abilityContext.getRow() != null || !abilityContext.getRow().isEmpty()) {
            for (Card card : abilityContext.getRow().getCards()) {
                if (card != null && card.getAbilityName() ==  AbilityName.BERSERKER)
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).applyMardroeme();
            }
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(((Card)abilityContext.getParam("mardroemeCard")).getIdInGame(), (String) abilityContext.getParam("dest"), RowsInGame.currentPlayerDiscardPlace.toString());
        }
    }
}
