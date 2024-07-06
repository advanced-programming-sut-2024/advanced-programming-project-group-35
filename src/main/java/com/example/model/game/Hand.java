package com.example.model.game;

import com.example.model.card.Card;
import com.example.model.card.enums.CardData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Hand {
    private ObservableList<Card> cards = FXCollections.observableArrayList();
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public Card getCardByName(CardData cardData) {
        for (Card card : cards) {
            if (card.getCardName() == cardData) return card;
        }
        return null;
    }

    public ObservableList<Card> getCards() {
        return cards;
    }
}
