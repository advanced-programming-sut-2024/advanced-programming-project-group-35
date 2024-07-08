package com.example.controller;

import com.example.model.App;
import com.example.model.alerts.AlertType;
import com.example.model.deckmanager.DeckManager;
import com.example.model.alerts.NotificationsData;
import com.example.model.card.*;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.deckmanager.DeckToJson;
import com.example.model.game.*;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
import com.example.view.Menu;
import com.example.view.menuControllers.GameMenuControllerView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class GameMenuControllerForOnlineGame extends AppController {
    private Table table;
    private GameMenuControllerView gameMenuControllerView;
    private int turn = 1;

    @Override
    public void run() {
        System.out.println("waiting");
        Platform.runLater(() -> {
            try {
                App.getAppView().showMenu(Menu.GAME_MENU);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            App.setCurrentController(Controller.GAME_MENU_CONTROLLER);
            gameMenuControllerView = App.getAppView().getGameMenuControllerView();
            startRound(table);
//            App.getAppView().showNotification(NotificationsData.ROUND_START.getMessage(), NotificationsData.ROUND_START.getImageAddress(), null);
        });
    }

    public GameMenuControllerView getGameMenuControllerView() {
        return gameMenuControllerView;
    }

    public void vetoCard(Player player, ObservableList<Card> selectedCards) {
        if (player.canVetoCard()) {
            Deck deck = player.getBoard().getDeck();
            Hand hand = player.getBoard().getHand();
            for (int i = 0; i < selectedCards.size(); i++) {
                Card ranomCard = deck.getCard(new Random().nextInt(deck.getSize()));
                hand.removeCard(selectedCards.get(i));
                hand.addCard(ranomCard);
                deck.removeCard(ranomCard);
                deck.addCard(selectedCards.get(i));
                player.decreaseNumberOfVetoCards();
            }
        }
        if (turn == 1) {
            App.getAppView().showNotification(NotificationsData.ROUND_START.getMessage(), NotificationsData.ROUND_START.getImageAddress(), null);
        }
    }


    private Card getCardById(int cardId, ObservableList<Card> originRow) {
        for (Card card : originRow) {
            if (card != null && card.getIdInGame() == cardId) {
                return card;
            }
        }
        return null;
    }

    public void moveCardFromOriginToDestinationAndDoAbility(int cardId, String origin, String destination) {
        System.out.println(origin);
        ObservableList<Card> originRow = (ObservableList<Card>) getRowListByName(origin);
        ObservableList<Card> destinationRow = (ObservableList<Card>) getRowListByName(destination);
        Card card = getCardById(cardId, originRow);

        synchronized (originRow) {
            originRow.remove(card);
        }
        synchronized (destinationRow) {
            if (!destinationRow.contains(card)) {
                destinationRow.add(card);
            }
        }
        gameMenuControllerView.moveCardToDestinationFlowPane(cardId, origin, destination);


        if (card instanceof UnitCard) {
            AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
            abilityContext.addParam("dest", destination);

            switch (card.getAbilityName()) {
                case MUSTER:
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.MUSTER);
                    break;
                case MORALE_BOOST:
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.MORALE_BOOST);
                    break;
                case SPY:
                    abilityContext = new AbilityContext(table, null, null);
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.SPY);
                    break;
                case COMMANDER_HORN:
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.COMMANDER_HORN);
                    break;
                case TIGHT_BOND:
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.TIGHT_BOND);
                    break;
                case SCORCH:
                    abilityContext = new AbilityContext(table, (UnitCard) card, null);
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.SCORCH);
                    break;
                case MEDIC:
                    abilityContext = new AbilityContext(table, (UnitCard) card, null);
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.MEDIC);
                    break;
            }
        } else if (card instanceof SpecialCard) {
            AbilityContext abilityContext;
            switch (card.getAbilityName()) {
                case COMMANDER_HORN:
                    abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.COMMANDER_HORN);
                    break;
                case SCORCH:
                    abilityContext = new AbilityContext(table, null, null);
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.SCORCH);
                    break;
                case WEATHER:
                    abilityContext = new AbilityContext(table, null, null);
                    abilityContext.addParam("card", card);
                    ((WeatherCard) card).setPlayer(table.getCurrentPlayer());
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.SCORCH);
                    break;
                case MARDROEME:
                    abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                    abilityContext.addParam("dest", destination);
                    abilityContext.addParam("mardroemeCard", card);
                    doNonLeaderCardsAbility(card, abilityContext, AbilityName.MARDROEME);
                    break;
            }
        }

        App.out.println("player|" + table.getPlayerInTurn().getPriorityInGame() + "|movedCard|" + cardId + "|from|" + origin + "|" + destination + "andDoAbility");
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
        gameMenuControllerView.updateAllLabels();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), event -> {
            System.out.println(table.getCurrentPlayer().getBoard().getHand().getCards().size());
            if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
                passRound();
            } else if (!table.getOpponent().isPassRound()) {
                changeTurn();
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }


    public void doDecoyAbility(int decoyCardId, int selectedCardId, String dest) {
        Card decoyCard = getCardById(decoyCardId, table.getCurrentPlayer().getBoard().getHand().getCards());
        Card selectedCard = getCardById(selectedCardId, (ObservableList<Card>) getRowListByName(dest));
        AbilityContext abilityContext = new AbilityContext(table, null, getRowByName(dest));
        abilityContext.addParam("decoyCard", decoyCard);
        abilityContext.addParam("cardToSwap", selectedCard);
        abilityContext.addParam("dest", dest);
        doNonLeaderCardsAbility(decoyCard, abilityContext, null);
        gameMenuControllerView.setPowerOfCardDefault(selectedCardId);
        gameMenuControllerView.addMouseEventsForHandCards();
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), event -> {
            if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
                passRound();
            } else if (!table.getOpponent().isPassRound()) {
                changeTurn();
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
        gameMenuControllerView.removeStyleClass();
        saveLog("decoy ability done, decoyCardId: " + decoyCardId + " selectedCardId " + selectedCardId);
    }

    private void doNonLeaderCardsAbility(Card card, AbilityContext abilityContext, AbilityName abilityName) {
        if (card.getAbility() != null) {
            if (card.getAbilityName() == AbilityName.DECOY || card.getAbilityName() == AbilityName.SCORCH) {
                card.getAbility().apply(abilityContext);
            } else {
                gameMenuControllerView.getGameCardViewWithCardId(card.getIdInGame()).doAbilityAnimation(abilityName);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.5), event -> {
                    card.getAbility().apply(abilityContext);
                });
                Timeline timeline = new Timeline(keyFrame);
                timeline.setCycleCount(1);
                timeline.play();
            }
        }
    }


    private String getRowNameBySpecialPlaceName(String specialPlaceName) {
        switch (specialPlaceName) {
            case "currentPlayerCloseCombatSpecialPlace" -> {
                return RowsInGame.currentPlayerCloseCombat.toString();
            }
            case "currentPlayerRangedSpecialPlace" -> {
                return RowsInGame.currentPlayerRanged.toString();
            }
            case "currentPlayerSiegeSpecialPlace" -> {
                return RowsInGame.currentPlayerSiege.toString();
            }
            case "opponentSiegeSpecialPlace" -> {
                return RowsInGame.opponentSiege.toString();
            }
            case "opponentCloseCombatSpecialPlace" -> {
                return RowsInGame.opponentCloseCombat.toString();
            }
            case "opponentRangedSpecialPlace" -> {
                return RowsInGame.opponentRanged.toString();
            }
            default -> {
                return null;
            }
        }
    }

    public void moveCardFromOriginToDestinationAndDontDoAbility(int cardId, String origin, String destination) {
        moveCardAndDontDoAbilityBase(cardId, origin, destination);
        saveLog("card with id: " + cardId + " moved from " + origin + " to " + destination + " and ability not applied");
    }

    public void moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(int cardId, String origin, String destination) {
        moveCardAndDontDoAbilityBase(cardId, origin, destination);
    }

    private void moveCardAndDontDoAbilityBase(int cardId, String origin, String destination) {
        System.out.println(destination);
        ObservableList<Card> originRow = (ObservableList<Card>) getRowListByName(origin);
        ObservableList<Card> destinationRow = (ObservableList<Card>) getRowListByName(destination);
        Card card = getCardById(cardId, originRow);
        synchronized (originRow) {
            originRow.remove(card);
        }
        synchronized (destinationRow) {
            if (!destinationRow.contains(card)) {
                destinationRow.add(card);
            }
        }
        gameMenuControllerView.moveCardToDestinationFlowPane(cardId, origin, destination);
        gameMenuControllerView.updateAllLabels();
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
    }

    private Row getRowByName(String rowName) {
        switch (rowName) {
            case "currentPlayerSiege" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace();
            }
            case "currentPlayerRanged" -> {
                return table.getCurrentPlayer().getBoard().getRangedCardPlace();
            }
            case "currentPlayerCloseCombat" -> {
                return table.getCurrentPlayer().getBoard().getCloseCombatCardPlace();
            }
            case "opponentSiege" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace();
            }
            case "opponentCloseCombat" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace();
            }
            case "opponentRanged" -> {
                return table.getOpponent().getBoard().getRangedCardPlace();
            }
            default -> {
                return null;
            }
        }
    }

    private ObservableList<? extends Card> getRowListByName(String rowName) {
        switch (rowName) {
            case "currentPlayerHand" -> {
                return table.getCurrentPlayer().getBoard().getHand().getCards();
            }
            case "currentPlayerDeck" -> {
                return table.getCurrentPlayer().getBoard().getDeck().getCards();
            }
            case "currentPlayerSiege" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace().getCards();
            }
            case "currentPlayerRanged" -> {
                return table.getCurrentPlayer().getBoard().getRangedCardPlace().getCards();
            }
            case "currentPlayerCloseCombat" -> {
                return table.getCurrentPlayer().getBoard().getCloseCombatCardPlace().getCards();
            }
            case "currentPlayerCloseCombatSpecialPlace" -> {
                return table.getCurrentPlayer().getBoard().getCloseCombatCardPlace().getSpecialPlace();
            }
            case "currentPlayerRangedSpecialPlace" -> {
                return table.getCurrentPlayer().getBoard().getRangedCardPlace().getSpecialPlace();
            }
            case "opponentHand" -> {
                return table.getOpponent().getBoard().getHand().getCards();
            }
            case "currentPlayerSiegeSpecialPlace" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace().getSpecialPlace();
            }
            case "opponentSiege" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace().getCards();
            }
            case "opponentCloseCombat" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace().getCards();
            }
            case "opponentRanged" -> {
                return table.getOpponent().getBoard().getRangedCardPlace().getCards();
            }
            case "opponentSiegeSpecialPlace" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace().getSpecialPlace();
            }
            case "opponentCloseCombatSpecialPlace" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace().getSpecialPlace();
            }
            case "opponentRangedSpecialPlace" -> {
                return table.getOpponent().getBoard().getRangedCardPlace().getSpecialPlace();
            }
            case "opponentDiscardPlace" -> {
                return table.getOpponent().getBoard().getDiscardPile().getCards();
            }
            case "currentPlayerDiscardPlace" -> {
                return table.getCurrentPlayer().getBoard().getDiscardPile().getCards();
            }
            case "weather" -> {
                return table.getSpellPlace().getCards();
            }
            default -> {
                return null;
            }
        }
    }

    public void startNewGame(String player1Name, String player2Name, String player1DeckNames, String player2DeckNames) {
        Player player1 = new Player(player1Name, Integer.parseInt(player1Name));
        Player player2 = new Player(player2Name, Integer.parseInt(player2Name));

        DeckToJson deck1 = DeckManager.getDeckToJsonByCardNames(player1DeckNames);
        DeckToJson deck2 = DeckManager.getDeckToJsonByCardNames(player2DeckNames);

        player1.getBoard().setDeck(DeckManager.loadDeck(deck1, 1));
        player2.getBoard().setDeck(DeckManager.loadDeck(deck2, 2));

        player1.getBoard().setHand(DeckManager.loadHand(deck1, 1));
        player2.getBoard().setHand(DeckManager.loadHand(deck2, 2));

        player1.setSpecialCardCounter(player1.getSpecialCardCounter());
        player2.setSpecialCardCounter(player2.getSpecialCardCounter());

        player1.setPriorityInGame(1);
        player2.setPriorityInGame(2);

        table = new Table(player1, player2);

//        saveLog(generateInitialDeckData());
        Round round1 = new Round(1);
        table.addRound(round1);
        table.setCurrentRound(round1);
    }

    public void saveLog(String command) {
//        try {
//            String filePath = getString(table);
//            JsonObject jsonObject;
//
//            File jsonFile = new File(filePath);
//            if (jsonFile.exists()) {
//                try (FileReader reader = new FileReader(jsonFile)) {
//                    jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
//                }
//            } else {
//                jsonObject = new JsonObject();
//            }
//
//            JsonArray commands;
//            if (jsonObject.has("commands")) {
//                commands = jsonObject.getAsJsonArray("commands");
//            } else {
//                commands = new JsonArray();
//                jsonObject.add("commands", commands);
//            }
//
//            commands.add(command);
//
//            try (FileWriter writer = new FileWriter(filePath)) {
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                gson.toJson(jsonObject, writer);
//                System.out.println("game log Saved Successfully.");
//            }
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//            System.err.println("Failed to save game log." + e.getMessage());
//        }
    }

    private void saveLog(LinkedHashMap<String, Object> deckData) {
//        try {
//            String filePath = getString(table);
//            JsonObject jsonObject;
//
//            File jsonFile = new File(filePath);
//            if (jsonFile.exists()) {
//                try (FileReader reader = new FileReader(jsonFile)) {
//                    jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
//                }
//            } else {
//                jsonObject = new JsonObject();
//            }
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            for (Map.Entry<String, Object> entry : deckData.entrySet()) {
//                JsonElement element = gson.toJsonTree(entry.getValue());
//                jsonObject.add(entry.getKey(), element);
//            }
//
//            try (FileWriter writer = new FileWriter(filePath)) {
//                gson.toJson(jsonObject, writer);
//                System.out.println("game log Saved Successfully.");
//            }
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//            System.err.println("Failed to save game log." + e.getMessage());
//        }
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

//    private static String getString(Table table) throws URISyntaxException {
//        String rootPath = new File(GameMenuControllerForOnlineGame.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getPath();
//        String logsDirPath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "gameLogs";
//        File logsDir = new File(logsDirPath);
//        if (!logsDir.exists()) {
//            logsDir.mkdirs();
//        }
//        String filePath = logsDirPath + File.separator + table.getGameId() + ".json";
//        return filePath;
//    }

    public void passRound() {
        saveLog("player: " + table.getCurrentPlayer().getUsername() + " passed round");
        table.getCurrentPlayer().setPassRound(true);
        if (table.getOpponent().isPassRound()) {
            changeRound();
        } else {
            changeTurn();
        }
    }

    private void startRound(Table table) {
        System.out.println(turn);
        if (turn == 1) {
            gameMenuControllerView.showVetoCards();
        }
        if (table.getRoundNumber() == 1) {
            LeaderCard leaderCard1 = table.getCurrentPlayer().getBoard().getDeck().getLeader();
            LeaderCard leaderCard2 = table.getOpponent().getBoard().getDeck().getLeader();
            if (leaderCard1.canDoAction() && leaderCard1.getLeaderName() == CardData.leaders_scoiatael_francesca_copper) {
                AbilityContext abilityContext = new AbilityContext(table, null, null);
//                abilityContext.addParam("origin", RowsInGame.currentPlayerDeck.toString());
//                abilityContext.addParam("dest", RowsInGame.currentPlayerHand.toString());
                abilityContext.addParam("player", table.getCurrentPlayer());
                leaderCard1.getAbility().apply(abilityContext);
            }
            if (leaderCard2.canDoAction() && leaderCard2.getLeaderName() == CardData.leaders_scoiatael_francesca_copper) {
                AbilityContext abilityContext = new AbilityContext(table, null, null);
//                abilityContext.addParam("origin", RowsInGame.opponentPlayerDeck.toString());
//                abilityContext.addParam("dest", RowsInGame.opponentPlayerHand.toString());
                abilityContext.addParam("player", table.getOpponent());
                leaderCard2.getAbility().apply(abilityContext);
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
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), event -> {
            if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
                table.getCurrentPlayer().setPassRound(true);
                passRound();
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void endRound() {
        Player player1 = table.getCurrentPlayer();
        Player player2 = table.getOpponent();
        table.getCurrentRound().addScore(player1, player1.getScore());
        table.getCurrentRound().addScore(player2, player2.getScore());
        if (player1.getScore() == player2.getScore()) {
            table.getCurrentRound().setDraw(true);
            table.getCurrentRound().setWon(false);
            table.getCurrentRound().setWinner(null);
            player1.decreaseCrystals();
            player2.decreaseCrystals();
            gameMenuControllerView.updateAllLabels();
        } else if (player1.getScore() > player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player1);
            player2.decreaseCrystals();
            gameMenuControllerView.updateAllLabels();
        } else if (player1.getScore() < player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player2);
            player1.decreaseCrystals();
            gameMenuControllerView.updateAllLabels();
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
        setPowerOfCardsDefault();
        disApplyWeatherCards();
        backCardsToDiscardPiles();
    }

    private void setPowerOfCardsDefault() {
        gameMenuControllerView.setPowerOfCardsDefault();
    }

    private void backCardsToDiscardPiles() {
        gameMenuControllerView.backCardsToDiscardPlaces();
        gameMenuControllerView.updateAllLabels();
        saveLog("cards back to discardPlace");
    }

    private void changeRound() {
        endRound();
        if (table.getCurrentPlayer().getNumberOfCrystals() == 0) {
            endGame(table, table.getOpponent());
        } else if (table.getOpponent().getNumberOfCrystals() == 0) {
            endGame(table, table.getCurrentPlayer());
        } else {
            if (table.getCurrentRound().isWon()) {
                Player winner = table.getCurrentRound().getWinner();
                if (table.getCurrentPlayer() != winner) {
                    table.setOpponent(table.getCurrentPlayer());
                    table.setCurrentPlayer(winner);
                }
            }
            changeTurnWithNoLog();
            Round round = new Round(table.getRoundNumber() + 1);
            table.getCurrentPlayer().setPassRound(false);
            table.getOpponent().setPassRound(false);
            table.addRound(round);
            table.setCurrentRound(round);
            table.setRoundNumber(table.getRoundNumber() + 1);
            startRound(table);
        }
    }

    private void changeTurn() {
        turn++;
        gameMenuControllerView.changeTurn();
        saveLog("change turn");
    }

    private void changeTurnWithNoLog() {
        gameMenuControllerView.changeTurn();
    }

    private void endGame(Table table, Player winner) {
        System.out.println("end game");
        //TODO نمایش برنده
        //TODO نمایش مجموع امتیاز های هر فرد در تمام راند ها
        //TODO اضافه کردن گیم دیتا
    }

    public void disApplyWeatherCards() {
        gameMenuControllerView.disApplyWeatherCards();
    }

    public Table getTable() {
        return table;
    }

    public void doCurrentPlayerLeaderAbility() {
        LeaderCard leaderCard = table.getCurrentPlayer().getBoard().getDeck().getLeader();
        if (leaderCard.getAbility() != null && leaderCard.canDoAction()) {
            leaderCard.getAbility().apply(new AbilityContext(table, null, null));
            if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
                passRound();
            } else if (!table.getOpponent().isPassRound()) {
                changeTurn();
            }
        } else {
            if (leaderCard.getAbility() == null) {
                System.out.println("1");
            } else if (!leaderCard.canDoAction()) {
                System.out.println("2");
            }
            System.out.println("doCurrentPlayerLeaderAbility in GameMenuController");
        }
    }

    public void addRandomCardToDeck() {
        Deck deck = table.getCurrentPlayer().getBoard().getDeck();
        Card randomCard = deck.getCard(new Random().nextInt(deck.getSize()));
        if (randomCard != null) {
            moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(randomCard.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
        }
        saveLog("add random card to deck cheat code");
    }

    public void recoverLeaderAbility() {
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(true);
        gameMenuControllerView.updateAllLabels();
        saveLog("recover leader ability cheat code");
    }

    public void recoverCrystals() {
        table.getCurrentPlayer().setNumberOfCrystals(2);
        gameMenuControllerView.updateAllLabels();
        saveLog("recover crystals cheat code");
    }

    public void luckOpponentLeaderAbility() {
        table.getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        gameMenuControllerView.updateAllLabels();
        saveLog("luck opponent leader ability cheat code");
    }

    public void luckOpponentEmotes() {

    }

    public void setClownForOpponent() {
        gameMenuControllerView.setClownImageForOpponentLeaderCard();
        saveLog("set clown picture for opponent leader card cheat code");
    }

    public void addDecoyCard() {
        gameMenuControllerView.addDecoyCard();
        saveLog("add decoy card cheat code");
    }
}