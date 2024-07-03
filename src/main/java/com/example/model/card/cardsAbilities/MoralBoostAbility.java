package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;

public class MoralBoostAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        for (UnitCard card : abilityContext.getRow().getCards()) {
            if (card != abilityContext.getCard() && ((card instanceof UnitCard) && !(card).isHero())) {
                card.applyMoralBoost();
                ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
            }
        }
        abilityContext.getRow().updateStrength();
    }
}
