package com.example.model.deckmanager;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class DeckToJson implements Serializable {
    public String faction;
    public String leader;

    public ArrayList<String> cards;
    public ArrayList<String> hand;
    public ArrayList<String> restOfCards;

    public DeckToJson(String faction, String leader) {
        this.faction = faction;
        this.leader = leader;
    }

    public ArrayList<String> getRestOfCards() {
        return restOfCards;
    }

    public void setRestOfCards(ArrayList<String> restOfCards) {
        this.restOfCards = restOfCards;
    }

    public DeckToJson() {
    }

    public ArrayList<String> getHand() {
        if (hand == null) this.hand = new ArrayList<>();
        return hand;
    }

    public void setHand(ArrayList<String> hand) {
        this.hand = hand;
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

}
