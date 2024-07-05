package com.example.model.game;

import com.example.model.card.LeaderCard;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.Card;
import com.example.model.card.factions.Factions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Deck {
    private FactionsType faction;
    private Factions factionAbility;
    private LeaderCard leader;
    private ArrayList<Card> cards = new ArrayList<>();

    public Factions getFactionAbility() {
        return factionAbility;
    }

    public void setFactionAbility(Factions factionAbility) {
        this.factionAbility = factionAbility;
    }

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
        Collections.shuffle(cards);
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

    public LinkedHashMap<String, String> toHash(int index) {
        LinkedHashMap<String, String> hash = new LinkedHashMap<>();
        hash.put("faction", faction.toString());
        hash.put("leader", leader.getName());
        for (int i = 0; i < cards.size(); i++) {
            hash.put(String.valueOf(index * 100 + i), cards.get(i).getName());
        }
        return hash;
    }
}
