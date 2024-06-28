package com.example.model.card.unitCardsAbilities;


import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.game.DiscardPile;

import java.util.Random;

public class MedicAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        DiscardPile discardPile = abilityContext.getTable().getCurrentPlayer().getBoard().getDiscardPile();
        Card cardToRevive = discardPile.getCard(new Random().nextInt(discardPile.getSize()));
        discardPile.removeCard(cardToRevive);
        abilityContext.getTable().getCurrentPlayer().getBoard().getHand().addCard(cardToRevive);
    }
}
