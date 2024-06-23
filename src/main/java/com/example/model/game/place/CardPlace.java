package com.example.model.game.place;
import com.example.model.card.UnitCard;

import java.util.ArrayList;

public abstract class CardPlace {
    ArrayList<UnitCard> cards = new ArrayList<UnitCard>();

    public void addCard(UnitCard card) {
        cards.add(card);
    }

    public void removeCard(UnitCard card) {
        cards.remove(card);
    }

    public ArrayList<UnitCard> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }

    public int getStrength() {
        int strength = 0;
        for (UnitCard card : cards) {
            strength += card.getStrength();
        }
        return strength;
    }
}
