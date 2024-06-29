package com.example.model.game;

import com.example.model.card.Card;
import com.example.model.card.enums.CardName;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<>();
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public Card getCardByName(CardName cardName) {
        for (Card card : cards) {
            if (card.getCardName() == cardName) return card;
        }
        return null;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
