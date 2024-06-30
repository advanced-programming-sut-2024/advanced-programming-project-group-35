package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Deck;
import com.example.model.game.Hand;

import java.util.Random;

public class SpyAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Deck deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck();
        Hand hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand();
        if (deck.getSize() > 1) {
            int index1 = new Random().nextInt(deck.getSize());
            int index2 = index1 - 1;
            if (index2 == -1)
                index2 = 1;
            Card selectedCard1 = deck.getCard(index1);
            Card selectedCard2 = deck.getCard(index2);
            deck.removeCard(selectedCard1);
            deck.removeCard(selectedCard2);
            hand.addCard(selectedCard1);
            hand.addCard(selectedCard2);
            //TODO گرافیک انتقال کارت
        } else if (deck.getSize() == 1) {
            Card selectedCard = deck.getCard(0);
            deck.removeCard(selectedCard);
            hand.addCard(selectedCard);
            //TODO گرافیک انتقال کارت
        }
    }
}
