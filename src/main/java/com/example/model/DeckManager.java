package com.example.model;

import com.example.controller.GameMenuController;
import com.example.model.card.Card;
import com.example.model.card.CardFactory;
import com.example.model.card.LeaderCard;
import com.example.model.card.LeaderFactory;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.factions.Factions;
import com.example.model.game.Deck;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;

public class DeckManager {
    public static void saveDeck(ArrayList<String> deckData, int playerId) {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("faction", deckData.get(0));
        data.put("leader", deckData.get(1));
        data.put("totalNumberOfCards", deckData.get(2));
        data.put("totalNumberOfSoldiers", deckData.get(3));
        data.put("totalNumberOfSpecialCards", deckData.get(4));
        data.put("sumOfPowers", deckData.get(5));
        data.put("cards", deckData.get(6));
        try {
            String rootPath = new File(GameMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
            String decksDataPath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "decksData";
            File decksDataDir = new File(decksDataPath);
            if (!decksDataDir.exists()) {
                decksDataDir.mkdirs();
            }
            String filePath = decksDataPath + File.separator + playerId + ".json";
            JsonObject jsonObject = new JsonObject();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                JsonElement element = gson.toJsonTree(entry.getValue());
                jsonObject.add(entry.getKey(), element);
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(jsonObject, writer);
                System.out.println("deck Saved Successfully.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to save game log." + e.getMessage());
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
            deck.setFactionAbility(FactionsType.getAbilityByName(faction));
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

    public static Deck loadDeck(ArrayList<String> cardNames, int first) {
        Deck deck = new Deck();
        deck.setLeader(LeaderFactory.getLeaderCardByName(cardNames.get(1)));
        deck.setFaction(FactionsType.getFactionByName(cardNames.get(0)));
        deck.setFactionAbility(FactionsType.getAbilityByName(cardNames.get(0)));
        for (int i = 6; i < cardNames.size(); i++) {
            Card card = CardFactory.getCardByName(cardNames.get(i));
            card.setIdInGame(first * 100 + i - 1);
            deck.addCard(card);
        }
        return deck;
    }
}
