package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;
import com.example.model.game.place.Row;

public class DecoyAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        UnitCard cardToSwap = (UnitCard) abilityContext.getParam("cardToSwap");
        Row row = abilityContext.getRow();
        row.removeCard(cardToSwap);
        abilityContext.getTable().getCurrentPlayer().getBoard().getHand().addCard(cardToSwap);
        row.addCard(abilityContext.getCard());
    }
}
