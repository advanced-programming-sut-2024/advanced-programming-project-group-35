package com.example.model.card.unitCardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;
public class CommandersHornCardAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
       for (UnitCard card : abilityContext.getRow().getCards()) {
           card.applyCommanderHorn();
       }
    }
}
