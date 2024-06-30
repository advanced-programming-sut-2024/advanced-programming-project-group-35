package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;

import java.util.ArrayList;

public class MusterAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        ArrayList<Card> hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards();
        ArrayList<Card> deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getCards();
        for (Card card : hand) {
            if (card.getAbility() instanceof MusterAbility) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getHand().removeCard(card);
                //TODO انتفال کارت از هند به مقصد و اضافه کردن به سطر مقصد
            }
        }
        for (Card card : deck) {
            if (card.getAbility() instanceof MusterAbility) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().removeCard(card);
                //TODO انتفال کارت از هند به مقصد و اضافه کردن به سطر مقصد
            }
        }
    }
}
