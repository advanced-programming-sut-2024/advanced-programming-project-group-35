package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;

import java.util.ArrayList;

public class TightBondAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        ArrayList<Card> tightBondCards = new ArrayList<>();
        int sumOfTightBondCardsPowers = 0;
        for (Card card : abilityContext.getRow().getCards()) {
            if (card.getAbilityName() == AbilityName.TIGHT_BOND) {
                tightBondCards.add(card);
                sumOfTightBondCardsPowers += ((UnitCard)card).getPower();
            }
        }
        if (tightBondCards.size() > 1) {
            for (Card card : tightBondCards) {
                ((UnitCard)card).setCurrentPower(sumOfTightBondCardsPowers);
                ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
            }
        }
        abilityContext.getRow().updateStrength();
    }
}
