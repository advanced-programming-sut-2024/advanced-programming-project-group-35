package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;

public class MardroemeAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        for (UnitCard card : abilityContext.getRow().getCards()) {
            if (card.getAbility() instanceof BerskerAbility)
                card.applyMardroeme();
        }
    }
}
