package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;

public class CommandersHornCardAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        for (Card card : abilityContext.getRow().getCards()) {
            if (card != null) {
                if (card != abilityContext.getCard() && (card instanceof UnitCard)) {
                    ((UnitCard)card).duplicatePower();
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                } else if (abilityContext.getCard() == null && (card instanceof  UnitCard)) {
                    ((UnitCard)card).duplicatePower();
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                }
            }
        }
        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().updateAllLabels();
    }
}
