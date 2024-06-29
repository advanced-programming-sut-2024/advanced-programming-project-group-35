package com.example.model.card.factions;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.SpecialCard;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Deck;
import com.example.model.game.Player;
import com.example.model.game.place.Row;

import java.util.Random;

public class Skellige implements Factions {
    // در اول راند سه باید کال شه
    FactionsType factionsType = FactionsType.Skellige;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(AbilityContext abilityContext, Player player) {
        if (abilityContext.getTable().getRoundNumber() == 3) {
            Deck deck = player.getBoard().getDeck();
            if (deck.getSize() > 1) {
                int index1 = new Random().nextInt(deck.getSize());
                int index2 = index1 - 1;
                if (index2 == -1)
                    index2 = 1;
                Card selectedCard1 = deck.getCard(index1);
                Card selectedCard2 = deck.getCard(index2);
                Row row1 = player.getBoard().getRowByName(selectedCard1.getPlace());
                Row row2 = player.getBoard().getRowByName(selectedCard2.getPlace());
                if (selectedCard1 instanceof SpecialCard) {
                    row1.setSpecialPlace((SpecialCard) selectedCard1);
                } else {
                    row1.addCard((UnitCard) selectedCard1);
                }
                if (selectedCard2 instanceof SpecialCard) {
                    row2.setSpecialPlace((SpecialCard) selectedCard2);
                } else {
                    row2.addCard((UnitCard) selectedCard2);
                }
                deck.removeCard(selectedCard1);
                deck.removeCard(selectedCard2);
            } else if (deck.getSize() == 1) {
                Card selectedCard = deck.getCard(0);
                deck.removeCard(selectedCard);
                Row row = player.getBoard().getRowByName(selectedCard.getPlace());
                if (selectedCard instanceof SpecialCard) {
                    row.setSpecialPlace((SpecialCard) selectedCard);
                } else {
                    row.addCard((UnitCard) selectedCard);
                }
            }
        }
    }
}
