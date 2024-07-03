package com.example.model.game;

import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DiscardPile {
    private ObservableList<Card> cards = FXCollections.observableArrayList();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public int getSize() {
        return cards.size();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public ObservableList<Card> getCards() {
        return cards;
    }
}
