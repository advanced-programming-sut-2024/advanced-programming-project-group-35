package com.example.model.deckmanager;

import com.example.controller.GameMenuController;
import com.example.model.App;
import com.example.model.alerts.AlertType;
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

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class DeckManager {
    public static void saveDeck(DeckToJson deckData, int playerId, String name) {
        try {
            String rootPath = new File(GameMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
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
            String rootPath = new File(GameMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
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
        for (int i = 0; i < deckToJson.getCards().size(); i++) {
            Card card = CardFactory.getCardByName(deckToJson.getCards().get(i));
            card.setIdInGame(first * 100 + i - 1);
            deck.addCard(card);
        }
        return deck;
    }
}
