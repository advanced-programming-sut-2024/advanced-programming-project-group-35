package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MusterAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Card card = abilityContext.getCard();
        ObservableList<Card> hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards();
        ObservableList<Card> deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getCards();

        int count = 0;

        List<Card> handCopy = new ArrayList<>(hand);
        for (Card card1 : handCopy) {
            if (card1.getAbilityName() == AbilityName.MUSTER && card.getPlace() == card1.getPlace() && (count++ < 4)) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getHand().removeCard(card);
                String dest = (String) abilityContext.getParam("dest");
                ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card1.getIdInGame(), "currentPlayerHandObservableList", dest);
            }
        }

        List<Card> deckCopy = new ArrayList<>(deck);
        for (Card card1 : deckCopy) {
            if (card1.getAbilityName() == AbilityName.MUSTER && card.getPlace() == card1.getPlace() && (count++ < 4)) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().removeCard(card);
                abilityContext.getRow().addCard((UnitCard) card);
                String dest = (String) abilityContext.getParam("dest");
                ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card1.getIdInGame(), "currentPlayerHandObservableList", dest);
            }
        }
    }
}
