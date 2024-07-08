package com.example.model.deckmanager;

import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.App;
import com.example.model.alerts.AlertType;
import com.example.model.card.Card;
import com.example.model.card.CardFactory;
import com.example.model.card.LeaderFactory;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Deck;
import com.example.model.game.Hand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import java.io.*;
import java.net.URISyntaxException;

public class DeckManager {
    public static void saveDeck(DeckToJson deckData, int playerId, String name) {
        try {
            String rootPath = new File(GameMenuControllerForOnlineGame.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
            String decksDataPath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "decksData";
            File decksDataDir = new File(decksDataPath);
            if (!decksDataDir.exists()) {
                decksDataDir.mkdirs();
            }
            String playerDeckDataDirPath = decksDataPath + File.separator + playerId;
            File playerDeckData = new File(playerDeckDataDirPath);
            if (!playerDeckData.exists()) {
                playerDeckData.mkdirs();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String filePath = playerDeckDataDirPath + File.separator + name + ".json";
            String json = gson.toJson(deckData);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(json);
                System.out.println("deck Saved Successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            App.getAppView().showAlert("Unable to save deck", AlertType.ERROR.getType());
        }
    }

    public static void saveDeck(DeckToJson deckData, String path, String name) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = path + File.separator + name + ".json";
        String json = gson.toJson(deckData);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(json);
            System.out.println("deck Saved Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeckToJson loadDeck(String filePath) {
        Gson gson = new GsonBuilder().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            DeckToJson deckToJSon = gson.fromJson(reader, DeckToJson.class);
            return deckToJSon;
        } catch (IOException e) {
            return null;
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static DeckToJson loadDeck(String fileName, int playerId) {
        try {
            String rootPath = new File(GameMenuControllerForOnlineGame.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
            String filePath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "decksData" + File.separator + playerId + File.separator + fileName + ".json";

            Gson gson = new GsonBuilder().create();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                DeckToJson deckToJson = gson.fromJson(reader, DeckToJson.class);
                return deckToJson;
            } catch (IOException e) {
                return null;
            } catch (JsonSyntaxException e) {
                return null;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Deck loadDeck(DeckToJson deckToJson, int first) {
        Deck deck = new Deck();
        deck.setLeader(LeaderFactory.getLeaderCardByName(deckToJson.getLeader()));
        deck.setFaction(FactionsType.getFactionByName(deckToJson.getFaction()));
        deck.setFactionAbility(FactionsType.getAbilityByName(deckToJson.getFaction()));
        for (int i = 0; i < deckToJson.getRestOfCards().size(); i++) {
            Card card = CardFactory.getCardByName(deckToJson.getRestOfCards().get(i));
            card.setIdInGame(first * 100 + i + 11);
            deck.addCard(card);
        }
        return deck;
    }

    public static Hand loadHand(DeckToJson deckToJson, int first) {
        Hand hand = new Hand();
        for (int i = 0; i < deckToJson.getCards().size(); i++) {
            Card card = CardFactory.getCardByName(deckToJson.getHand().get(i));
            card.setIdInGame(first * 100 + i);
            hand.addCard(card);
        }
        return hand;
    }

    public static DeckToJson getDeckToJsonByCardNames(String deck) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DeckToJson newDeck = objectMapper.readValue(deck, DeckToJson.class);
            return newDeck;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDeckString(DeckToJson deck) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(deck);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
