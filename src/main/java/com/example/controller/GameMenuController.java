package com.example.controller;

import com.example.Main;
import com.example.model.App;
import com.example.model.DeckManager;
import com.example.model.GameData;
import com.example.model.alerts.NotificationsData;
import com.example.model.card.*;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.*;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
import com.example.view.AppView;
import com.example.view.Menu;
import com.example.view.menuControllers.GameMenuControllerView;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class GameMenuController extends AppController {
    private Table table;
    private GameMenuControllerView gameMenuControllerView;

    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.GAME_MENU);
            App.setCurrentController(Controller.GAME_MENU_CONTROLLER);
            gameMenuControllerView = App.getAppView().getGameMenuControllerView();
            App.getAppView().showNotification(NotificationsData.ROUND_START.getMessage(), NotificationsData.ROUND_START.getImageAddress());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void vetoCard(Player player, Card selectedCard) {
        //TODO
        if (player.canVetoCard()) {
            Deck deck = player.getBoard().getDeck();
            Hand hand = player.getBoard().getHand();
            Card ranomCard = deck.getCard(new Random().nextInt(deck.getSize()));
            hand.removeCard(selectedCard);
            hand.addCard(ranomCard);
            deck.removeCard(ranomCard);
            deck.addCard(selectedCard);
            player.decreaseNumberOfVetoCards();
            //TODO گرافیک جابه جایی کارت
        }
    }

    public void doUnitCardAction(Card card, AbilityContext abilityContext) {
        if (card.getAbility() != null) {
            card.getAbility().apply(abilityContext);
        }
        if (abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
            passRound(abilityContext.getTable());
        } else if (!abilityContext.getTable().getOpponent().isPassRound()) {
            changeTurn(abilityContext.getTable());
        }
    }

    public void doLeaderCardAction(LeaderCard leaderCard, Table table) {
        if (leaderCard.getAbility() != null && leaderCard.canDoAction()) {
            leaderCard.getAbility().apply(table);
        }
        if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
            passRound(table);
        } else if (!table.getOpponent().isPassRound()) {
            changeTurn(table);
        }
    }

    public void moveCardFromOriginToDestination(int cardId, String origin, String destination) {
        ObservableList<Card> originRow = (ObservableList<Card>) getRowByName(origin);
        ObservableList<Card> destinationRow = (ObservableList<Card>) getRowByName(destination);
        Card card = null;
        for (Card card1 : originRow) {
            if (card1.getIdInGame() == cardId) {
                card = card1;
                break;
            }
        }
        originRow.remove(card);
        destinationRow.add(card);
        gameMenuControllerView.moveCardToDestinationFlowPane(cardId, origin, destination);
        saveLog("card with id: " + cardId + " moved from " + origin + " to " + destination);
    }

    private ObservableList<? extends Card> getRowByName(String rowName) {
        switch (rowName) {
            case "currentPlayerHandObservableList" -> {
                return table.getCurrentPlayer().getBoard().getHand().getCards();
            }
            case "currentPlayerSiegeObservableList" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace().getCards();
            }
            case "currentPlayerRangedObservableList" -> {
                return table.getCurrentPlayer().getBoard().getRangedCardPlace().getCards();
            }
            case "currentPlayerCloseCombatObservableList" -> {
                return table.getCurrentPlayer().getBoard().getCloseCombatCardPlace().getCards();
            }
            case "currentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return table.getCurrentPlayer().getBoard().getCloseCombatCardPlace().getSpecialPlace();
            }
            case "currentPlayerRangedSpecialPlaceObservableList" -> {
                return table.getCurrentPlayer().getBoard().getRangedCardPlace().getSpecialPlace();
            }
            case "currentPlayerSiegeSpecialPlaceObservableList" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace().getSpecialPlace();
            }
            case "opponentSiegeObservableList" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace().getCards();
            }
            case "opponentCloseCombatObservableList" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace().getCards();
            }
            case "opponentRangedObservableList" -> {
                return table.getOpponent().getBoard().getRangedCardPlace().getCards();
            }
            case "opponentSiegeSpecialPlaceObservableList" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace().getSpecialPlace();
            }
            case "opponentCloseCombatSpecialPlaceObservableList" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace().getSpecialPlace();
            }
            case "opponentRangedSpecialPlaceObservableList" -> {
                return table.getOpponent().getBoard().getRangedCardPlace().getSpecialPlace();
            }
            case "weatherObservableList" -> {
                return table.getSpellPlace().getCards();
            }
            default -> {
                return null;
            }
        }
    }

    public void startNewGame(String player1Name, String player2Name, ArrayList<String> player1DeckNames, ArrayList<String> player2DeckNames, String player1SpecialCard, String player2SpecialCard) {
        Deck player1Deck = DeckManager.loadDeck(player1DeckNames, 1);
        Deck player2Deck = DeckManager.loadDeck(player2DeckNames, 2);
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        player1.getBoard().setDeck(player1Deck);
        player2.getBoard().setDeck(player2Deck);
        player1.getBoard().setHandForStartGame(player1Deck);
        player2.getBoard().setHandForStartGame(player2Deck);
        player1.setSpecialCardCounter(Integer.parseInt(player1SpecialCard));
        player2.setSpecialCardCounter(Integer.parseInt(player2SpecialCard));
        table = new Table(player1, player2);
        saveLog(generateInitialDeckData());
        Round round1 = new Round(1);
        table.addRound(round1);
        table.setCurrentRound(round1);
        startRound(table);
    }

    public void saveLog(String command) {
        try {
            String filePath = getString(table);
            JsonObject jsonObject;

            File jsonFile = new File(filePath);
            if (jsonFile.exists()) {
                try (FileReader reader = new FileReader(jsonFile)) {
                    jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                }
            } else {
                jsonObject = new JsonObject();
            }

            JsonArray commands;
            if (jsonObject.has("commands")) {
                commands = jsonObject.getAsJsonArray("commands");
            } else {
                commands = new JsonArray();
                jsonObject.add("commands", commands);
            }

            commands.add(command);

            try (FileWriter writer = new FileWriter(filePath)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(jsonObject, writer);
                System.out.println("game log Saved Successfully.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to save game log." + e.getMessage());
        }
    }

    private void saveLog(LinkedHashMap<String, Object> deckData) {
        try {
            String filePath = getString(table);
            JsonObject jsonObject;

            File jsonFile = new File(filePath);
            if (jsonFile.exists()) {
                try (FileReader reader = new FileReader(jsonFile)) {
                    jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                }
            } else {
                jsonObject = new JsonObject();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (Map.Entry<String, Object> entry : deckData.entrySet()) {
                JsonElement element = gson.toJsonTree(entry.getValue());
                jsonObject.add(entry.getKey(), element);
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(jsonObject, writer);
                System.out.println("game log Saved Successfully.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to save game log." + e.getMessage());
        }
    }

    private LinkedHashMap<String, Object> generateInitialDeckData() {
        LinkedHashMap<String, Object> deckData = new LinkedHashMap<>();
        deckData.put("player1", table.getCurrentPlayer().getUsername());
        deckData.put("player1Faction", FactionsType.toSting(table.getCurrentPlayer().getBoard().getDeck().getFaction()));
        deckData.put("player1Hand", table.getCurrentPlayer().getBoard().getHand().getCards().stream()
                .map(card -> {
                    Map<String, Object> cardData = new LinkedHashMap<>();
                    cardData.put("name", card.getName());
                    cardData.put("id", card.getIdInGame());
                    return cardData;
                }).collect(Collectors.toList()));
        deckData.put("player1Deck", table.getCurrentPlayer().getBoard().getDeck().getCards().stream()
                .map(card -> {
                    Map<String, Object> cardData = new LinkedHashMap<>();
                    cardData.put("name", card.getName());
                    cardData.put("id", card.getIdInGame());
                    return cardData;
                }).collect(Collectors.toList()));

        deckData.put("player2", table.getOpponent().getUsername());
        deckData.put("player2Faction", FactionsType.toSting(table.getOpponent().getBoard().getDeck().getFaction()));
        deckData.put("player2Hand", table.getOpponent().getBoard().getHand().getCards().stream()
                .map(card -> {
                    Map<String, Object> cardData = new LinkedHashMap<>();
                    cardData.put("name", card.getName());
                    cardData.put("id", card.getIdInGame());
                    return cardData;
                }).collect(Collectors.toList()));
        deckData.put("player2Deck", table.getOpponent().getBoard().getDeck().getCards().stream()
                .map(card -> {
                    Map<String, Object> cardData = new LinkedHashMap<>();
                    cardData.put("name", card.getName());
                    cardData.put("id", card.getIdInGame());
                    return cardData;
                }).collect(Collectors.toList()));
        return deckData;
    }

    private static String getString(Table table) throws URISyntaxException {
        String rootPath = new File(GameMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
        String logsDirPath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "gameLogs";
        File logsDir = new File(logsDirPath);
        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }
        String filePath = logsDirPath + File.separator + table.getGameId() + ".json";
        return filePath;
    }

    public void passRound(Table table) {
        table.getCurrentPlayer().setPassRound(true);
        if (table.getOpponent().isPassRound()) {
            changeRound(table);
        } else {
            changeTurn(table);
        }
    }

    private void startRound(Table table) {
        if (table.getRoundNumber() == 1) {
            LeaderCard leaderCard1 = table.getCurrentPlayer().getBoard().getDeck().getLeader();
            LeaderCard leaderCard2 = table.getOpponent().getBoard().getDeck().getLeader();
            if (leaderCard1.canDoAction() && leaderCard1.getLeaderName() == CardData.leaders_scoiatael_francesca_copper) {
                leaderCard1.getAbility().apply(table);
            }
            if (leaderCard2.canDoAction() && leaderCard2.getLeaderName() == CardData.leaders_scoiatael_francesca_copper) {
                leaderCard2.getAbility().apply(table);
            }
        } else if (table.getRoundNumber() == 3) {
            FactionsType factionsType1 = table.getCurrentPlayer().getBoard().getDeck().getFaction();
            FactionsType factionsType2 = table.getOpponent().getBoard().getDeck().getFaction();
            if (factionsType1 == FactionsType.Skellige) {
                table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
            }
            if (factionsType2 == FactionsType.Skellige) {
                table.getOpponent().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
            }
        }
    }

    private void endRound(Table table) {
        backCardsToDiscardPiles(table);
        disApplyWeatherCards(table);
        Player player1 = table.getCurrentPlayer();
        Player player2 = table.getOpponent();
        table.getCurrentRound().addScore(player1, player1.getScore());
        table.getCurrentRound().addScore(player2, player2.getScore());
        if (player1.getScore() == player2.getScore()) {
            table.getCurrentRound().setDraw(true);
            table.getCurrentRound().setWon(false);
            table.getCurrentRound().setWinner(null);
        } else if (player1.getScore() > player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player1);
        } else if (player1.getScore() < player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player2);
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getFaction() == FactionsType.Monsters) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
        }
        if (table.getOpponent().getBoard().getDeck().getFaction() == FactionsType.Monsters) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getFaction() == FactionsType.EmpireNilfgaardian) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
        }
        if (table.getOpponent().getBoard().getDeck().getFaction() == FactionsType.EmpireNilfgaardian) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getFaction() == FactionsType.RealmsNorthern) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
        }
        if (table.getOpponent().getBoard().getDeck().getFaction() == FactionsType.RealmsNorthern) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
        }
    }

    private void backCardsToDiscardPiles(Table table) {
        //TODO(گرافیک) باید چک شه اگه ریموو کارت ترو بود حذف کنیم
    }

    private void changeRound(Table table) {
        endRound(table);
        if (table.getCurrentPlayer().getNumberOfCrystals() == 0) {
            endGame(table, table.getOpponent());
        } else if (table.getOpponent().getNumberOfCrystals() == 0) {
            endGame(table, table.getCurrentPlayer());
        } else {
            Round round = new Round(table.getRoundNumber() + 1);
            table.getCurrentPlayer().setPassRound(false);
            table.getOpponent().setPassRound(false);
            table.addRound(round);
            table.setCurrentRound(round);
            table.setRoundNumber(table.getRoundNumber() + 1);
            startRound(table);
        }
    }

    private void changeTurn(Table table) {
        table.swapPlayers();
        //TODO گرافیک
    }

    private void endGame(Table table, Player winner) {
        //TODO نمایش برنده
        //TODO نمایش مجموع امتیاز های هر فرد در تمام راند ها
        //TODO اضافه کردن گیم دیتا
    }

    public void disApplyWeatherCards(Table table) {
        //TODO
    }

    public Table getTable() {
        return table;
    }
}
