package com.example.model;

import com.example.model.card.Card;
import com.example.model.card.CardFactory;
import com.example.model.card.LeaderCard;
import com.example.model.card.LeaderFactory;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Deck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class DeckManager {
    public static void saveDeck(Deck deck, String filename) {
        Map<String, Object> deckData = new HashMap<>();
        deckData.put("faction", deck.getFaction());
        deckData.put("leader", deck.getLeader().getName());
        deckData.put("cards", deck.getCards().stream().map(Card::getName).toArray());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(deckData, writer);
            System.out.println("Deck saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save deck: " + e.getMessage());
        }
    }

    public static Deck loadDeck(String filename) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(filename)) {
            Type deckDataType = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> deckData = gson.fromJson(reader, deckDataType);

            String faction = (String) deckData.get("faction");
            String leaderCardName = (String) deckData.get("leader");
            List<String> cardNames = (List<String>) deckData.get("cards");

            Deck deck = new Deck();
            deck.setLeader(LeaderFactory.getLeaderCardByName(leaderCardName));
            deck.setFaction(FactionsType.getFactionByName(faction));
            for (String cardName : cardNames) {
                deck.addCard(CardFactory.getCardByName(cardName));
            }
            System.out.println("Deck loaded successfully.");
            return deck;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load deck: " + e.getMessage());
            return null;
        }
    }

    public static Deck loadDeck(ArrayList<String> cardNames) {
        Deck deck = new Deck();
        deck.setLeader(LeaderFactory.getLeaderCardByName(cardNames.get(1)));
        deck.setFaction(FactionsType.getFactionByName(cardNames.get(0)));

        for (int i = 2; i < cardNames.size(); i++) {
            deck.addCard(CardFactory.getCardByName(cardNames.get(i)));
        }
        return deck;
    }
}
