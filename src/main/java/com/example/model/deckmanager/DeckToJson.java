package com.example.model.deckmanager;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class DeckToJson implements Serializable {
    public String faction;
    public String leader;

    public ArrayList<String> cards;

    public DeckToJson(String faction, String leader) {
        this.faction = faction;
        this.leader = leader;
    }

    public DeckToJson() {
    }

    public String getFaction() {
        return faction;
    }

    public String getLeader() {
        return leader;
    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public ArrayList<String> toDeck() {
        ArrayList<String> deck = new ArrayList<>();
        deck.add(faction);
        deck.add(leader);
        for (String card : cards){
            deck.add(card);
        }
        return deck;
    }
}
