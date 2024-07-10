package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.RowsInGame;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MusterAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        Card card = abilityContext.getCard();
        String[] parts = card.getName().split("_");
        ObservableList<Card> hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards();
        ObservableList<Card> deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getCards();

        List<Card> handCopy = new ArrayList<>(hand);
        for (Card card1 : handCopy) {
            if (card1 != null && card1.getAbilityName() == AbilityName.MUSTER && abilityContext.getCard() != card1 && card.getPlace() == card1.getPlace()) {
                String[] parts1 = card1.getName().split("_");
                if (parts[1].equals(parts1[1])) {
                    String dest = (String) abilityContext.getParam("dest");
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbility(card1.getIdInGame(), RowsInGame.currentPlayerHand.toString(), dest);
                }
            }
        }

        List<Card> deckCopy = new ArrayList<>(deck);
        for (Card card1 : deckCopy) {
            if (card1 != null && card1.getAbilityName() == AbilityName.MUSTER && card.getPlace() == card1.getPlace()) {
                String[] parts1 = card1.getName().split("_");
                if (parts[1].equals(parts1[1])) {
                    String dest = (String) abilityContext.getParam("dest");
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbility(card1.getIdInGame(), RowsInGame.currentPlayerHand.toString(), dest);
                }
            }
        }
    }
}
