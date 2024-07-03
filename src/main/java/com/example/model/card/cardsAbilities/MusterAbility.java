package com.example.model.card.cardsAbilities;

import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class MusterAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        ObservableList<Card> hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards();
        ObservableList<Card> deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getCards();
        for (Card card : hand) {
            if (card.getAbilityName() == AbilityName.MUSTER) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getHand().removeCard(card);

            }
        }
        for (Card card : deck) {
            if (card.getAbilityName() == AbilityName.MUSTER) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().removeCard(card);
                abilityContext.getRow().addCard((UnitCard) card);
            }
        }
    }
}
