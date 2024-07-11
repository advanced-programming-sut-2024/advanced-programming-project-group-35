package com.example.model.game;

import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private DiscardPile discardPile = new DiscardPile();
    private Hand hand;

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    private Deck deck;
    private CloseCombatRow closeCombatCardPlace = new CloseCombatRow();
    private RangedRow rangedCardPlace = new RangedRow();
    private SiegeRow siegeCardPlace = new SiegeRow();
    private ArrayList<Row> rows = new ArrayList<>(Arrays.asList(closeCombatCardPlace, rangedCardPlace, siegeCardPlace));

    public CloseCombatRow getCloseCombatCardPlace() {
        return closeCombatCardPlace;
    }

    public RangedRow getRangedCardPlace() {
        return rangedCardPlace;
    }

    public SiegeRow getSiegeCardPlace() {
        return siegeCardPlace;
    }

    public Hand getHand() {
        return hand;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Row getRowByName(Place place) {
        switch (place) {
            case CLOSE_COMBAT -> {
                return closeCombatCardPlace;
            }
            case RANGED -> {
                return rangedCardPlace;
            }
            case SIEGE -> {
                return siegeCardPlace;
            }
            default -> {
                return null;
            }
        }
    }
}
