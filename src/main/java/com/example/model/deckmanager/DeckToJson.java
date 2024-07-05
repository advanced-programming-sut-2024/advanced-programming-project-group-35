package com.example.model.deckmanager;

import java.util.ArrayList;

public class DeckToJson {
    private String faction;
    private String leader;
    private int totalNumberOfCards;
    private int totalNumberOfHeros;
    private int totalNumberOfSoldiers;
    private int totalNumberOfSpecialCards;
    private int sumOfPowers;
    private ArrayList<String> cards;

    public DeckToJson(String faction, String leader, int totalNumberOfCards, int totalNumberOfHeros, int totalNumberOfSoldiers, int totalNumberOfSpecialCards, int sumOfPowers, ArrayList<String> cards) {
        this.faction = faction;
        this.leader = leader;
        this.totalNumberOfCards = totalNumberOfCards;
        this.totalNumberOfHeros = totalNumberOfHeros;
        this.totalNumberOfSoldiers = totalNumberOfSoldiers;
        this.totalNumberOfSpecialCards = totalNumberOfSpecialCards;
        this.sumOfPowers = sumOfPowers;
        this.cards = cards;
    }

    public String getFaction() {
        return faction;
    }

    public String getLeader() {
        return leader;
    }

    public int getTotalNumberOfCards() {
        return totalNumberOfCards;
    }

    public int getTotalNumberOfHeros() {
        return totalNumberOfHeros;
    }

    public int getTotalNumberOfSoldiers() {
        return totalNumberOfSoldiers;
    }

    public int getTotalNumberOfSpecialCards() {
        return totalNumberOfSpecialCards;
    }

    public int getSumOfPowers() {
        return sumOfPowers;
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
