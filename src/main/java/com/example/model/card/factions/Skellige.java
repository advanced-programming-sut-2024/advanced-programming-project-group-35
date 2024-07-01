package com.example.model.card.factions;

import com.example.model.card.Card;
import com.example.model.card.CardData;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Deck;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

import java.util.Random;

public class Skellige implements Factions {
    FactionsType factionsType = FactionsType.Skellige;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(Table table, Player player) {
        if (table.getRoundNumber() == 3) {
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
                if (selectedCard1 instanceof CardData) {
                    row1.setSpecialPlace((CardData) selectedCard1);
                    //TODO گرافیک انتقال کارت
                } else {
                    row1.addCard((UnitCard) selectedCard1);
                    //TODO گرافیک انتقال کارت
                }
                if (selectedCard2 instanceof CardData) {
                    row2.setSpecialPlace((CardData) selectedCard2);
                    //TODO گرافیک انتقال کارت
                } else {
                    row2.addCard((UnitCard) selectedCard2);
                    //TODO گرافیک انتقال کارت
                }
                deck.removeCard(selectedCard1);
                deck.removeCard(selectedCard2);
            } else if (deck.getSize() == 1) {
                Card selectedCard = deck.getCard(0);
                deck.removeCard(selectedCard);
                Row row = player.getBoard().getRowByName(selectedCard.getPlace());
                if (selectedCard instanceof CardData) {
                    row.setSpecialPlace((CardData) selectedCard);
                    //TODO گرافیک انتقال کارت
                } else {
                    row.addCard((UnitCard) selectedCard);
                    //TODO گرافیک انتقال کارت
                }
            }
        }
    }
}
