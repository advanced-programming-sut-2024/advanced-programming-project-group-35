package com.example.model.game;

import com.example.model.card.LeaderCard;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.Card;

import java.util.ArrayList;

public class Deck {
    private FactionsType faction;
    private LeaderCard leader;
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public LeaderCard getLeader() {
        return leader;
    }

    public void setLeader(LeaderCard leader) {
        this.leader = leader;
    }


    public Card getCard(int index) {
        return cards.get(index);
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void clear() {
        cards.clear();
    }
    public int getSize() {
        return cards.size();
    }
    public void shuffle() {
        // shuffle the deck
    }
    public void setFaction(FactionsType faction) {
        this.faction = faction;
    }
    public FactionsType getFaction() {
        return faction;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
