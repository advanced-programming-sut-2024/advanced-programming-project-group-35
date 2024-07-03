package com.example.model.game;

import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private DiscardPile discardPile;
    private Hand hand = new Hand();
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

    public void setHandForStartGame(Deck deck) {
//        int specialCards = 0, heroCards = 0;
//        Collections.shuffle(deck.getCards());
//        for (Card card : deck.getCards()) {
//            if (card instanceof SpecialCard) {
//                hand.addCard(card);
//                deck.removeCard(card);
//                if ((++specialCards) == 4) break;
//            }
//        }
//        Collections.shuffle(deck.getCards());
//        for (Card card : deck.getCards()) {
//            if (card instanceof UnitCard && ((UnitCard) card).isHero()) {
//                hand.addCard(card);
//                deck.removeCard(card);
//                if ((++heroCards) == 2) break;
//            }
//        }
//        for (int i = 0; i < 10 - specialCards - heroCards; i++) {
//            hand.addCard(deck.getCard(i));
//            deck.removeCard(deck.getCard(i));
//        }
        //TODO تابع زیر برای تست کارو راحت میکنه
        List<Card> deckCopy = new ArrayList<>(deck.getCards());
        for (Card card : deckCopy) {
            if (card.getAbilityName() == AbilityName.TIGHT_BOND) {
                hand.addCard(card);
                deck.removeCard(card);
            }
        }
        for (int i = 0; i < 9; i++) {
            hand.addCard(deck.getCard(i));
            deck.removeCard(deck.getCard(i));
        }
    }
}
