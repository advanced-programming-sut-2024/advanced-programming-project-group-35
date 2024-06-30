package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;

import java.util.ArrayList;
public class TightBondAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        ArrayList<UnitCard> tightBondCards = new ArrayList<>();
        int sumOfTightBondCardsPowers = 0;
        for (UnitCard card : abilityContext.getRow().getCards()) {
            if (card.getAbility() instanceof TightBondAbility) {
                tightBondCards.add(card);
                sumOfTightBondCardsPowers += card.getCurrentPower();
            }
        }
        for (UnitCard card : tightBondCards) {
           card.setCurrentPower(sumOfTightBondCardsPowers);
        }
        abilityContext.getRow().updateStrength();
    }
}
