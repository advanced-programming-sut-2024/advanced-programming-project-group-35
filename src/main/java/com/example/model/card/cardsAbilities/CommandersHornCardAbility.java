package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;
public class CommandersHornCardAbility implements Ability {
    //TODO نمایش بوق در صفحه
    @Override
    public void apply(AbilityContext abilityContext) {
       for (UnitCard card : abilityContext.getRow().getCards()) {
           card.duplicatePower();
       }
       abilityContext.getRow().updateStrength();
    }
}
