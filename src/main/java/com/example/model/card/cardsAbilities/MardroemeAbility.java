package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;

public class MardroemeAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        for (Card card : abilityContext.getRow().getCards()) {
            if (card.getAbilityName() ==  AbilityName.BERSERKER)
                ((UnitCard)card).applyMardroeme();
        }
    }
}
