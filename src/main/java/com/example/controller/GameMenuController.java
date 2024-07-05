package com.example.controller;

import com.example.model.App;
import com.example.model.DeckManager;
import com.example.model.GameData;
import com.example.model.alerts.NotificationsData;
import com.example.model.card.*;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.*;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
import com.example.view.Menu;
import com.example.view.menuControllers.GameMenuControllerView;
import com.google.gson.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            startRound(table);
            App.getAppView().showNotification(NotificationsData.ROUND_START.getMessage(), NotificationsData.ROUND_START.getImageAddress(), null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public GameMenuControllerView getGameMenuControllerView() {
        return gameMenuControllerView;
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


    private Card getCardById(int cardId, ObservableList<Card> originRow) {
        for (Card card : originRow) {
            if (card != null && card.getIdInGame() == cardId) {
                return card;
            }
        }
        return null;
    }

    public void moveCardFromOriginToDestinationAndDoAbility(int cardId, String origin, String destination) {
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
            if (card.getAbilityName() == AbilityName.MUSTER) {
                AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
                abilityContext.addParam("dest", destination);
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.MUSTER);
            } else if (card.getAbilityName() == AbilityName.MORALE_BOOST) {
                AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.MORALE_BOOST);
            } else if (card.getAbilityName() == AbilityName.SPY) {
                AbilityContext abilityContext = new AbilityContext(table, null, null);
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.SPY);
            } else if (card.getAbilityName() == AbilityName.COMMANDER_HORN) {
                AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.COMMANDER_HORN);
            } else if (card.getAbilityName() == AbilityName.TIGHT_BOND) {
                AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.TIGHT_BOND);
            } else if (card.getAbilityName() == AbilityName.SCORCH) {
                AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, null);
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.SCORCH);
            } else if (card.getAbilityName() == AbilityName.MEDIC) {
                AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, null);
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.MEDIC);
            }
        } else if (card instanceof SpecialCard) {
            if (card.getAbilityName() == AbilityName.COMMANDER_HORN) {
                AbilityContext abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.COMMANDER_HORN);
            } else if (card.getAbilityName() == AbilityName.SCORCH) {
                AbilityContext abilityContext = new AbilityContext(table, null, null);
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.SCORCH);
            } else if (card.getAbilityName() == AbilityName.WEATHER) {
                AbilityContext abilityContext = new AbilityContext(table, null, null);
                ((WeatherCard) card).setPlayer(table.getCurrentPlayer());
                doNonLeaderCardsAbility(card, abilityContext, AbilityName.SCORCH);
            }
        }

        saveLog("card with id: " + cardId + " moved from " + origin + " to " + destination + " and ability applied");
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
        gameMenuControllerView.updateAllLabels();
        if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
            passRound();
        } else if (!table.getOpponent().isPassRound()) {
            changeTurn();
        }
    }

    public void doDecoyAbility(int decoyCardId, int selectedCardId, String dest) {
        Card decoyCard = getCardById(decoyCardId, table.getCurrentPlayer().getBoard().getHand().getCards());
        Card selectedCard = getCardById(selectedCardId, (ObservableList<Card>) getRowListByName(dest));
        AbilityContext abilityContext = new AbilityContext(table, null, getRowByName(dest));
        abilityContext.addParam("decoyCard", decoyCard);
        abilityContext.addParam("cardToSwap", selectedCard);
        abilityContext.addParam("dest", dest);
        doNonLeaderCardsAbility(decoyCard, abilityContext, null);
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
        if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
            passRound();
        } else if (!table.getOpponent().isPassRound()) {
            changeTurn();
            System.out.println("12345");
        }
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
            case "currentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return RowsInGame.currentPlayerCloseCombat.toString();
            }
            case "currentPlayerRangedSpecialPlaceObservableList" -> {
                return RowsInGame.currentPlayerRanged.toString();
            }
            case "currentPlayerSiegeSpecialPlaceObservableList" -> {
                return RowsInGame.currentPlayerSiege.toString();
            }
            case "opponentSiegeSpecialPlaceObservableList" -> {
                return RowsInGame.opponentPlayerSiege.toString();
            }
            case "opponentCloseCombatSpecialPlaceObservableList" -> {
                return RowsInGame.opponentPlayerCloseCombat.toString();
            }
            case "opponentRangedSpecialPlaceObservableList" -> {
                return RowsInGame.opponentPlayerRanged.toString();
            }
            default -> {
                return null;
            }
        }
    }

    public void moveCardFromOriginToDestinationAndDontDoAbility(int cardId, String origin, String destination) {
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
        saveLog("card with id: " + cardId + " moved from " + origin + " to " + destination + " and ability applied");
    }

    private Row getRowByName(String rowName) {
        switch (rowName) {
            case "currentPlayerSiegeObservableList" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace();
            }
            case "currentPlayerRangedObservableList" -> {
                return table.getCurrentPlayer().getBoard().getRangedCardPlace();
            }
            case "currentPlayerCloseCombatObservableList" -> {
                return table.getCurrentPlayer().getBoard().getCloseCombatCardPlace();
            }
            case "opponentSiegeObservableList" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace();
            }
            case "opponentCloseCombatObservableList" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace();
            }
            case "opponentRangedObservableList" -> {
                return table.getOpponent().getBoard().getRangedCardPlace();
            }
            default -> {
                return null;
            }
        }
    }

    private ObservableList<? extends Card> getRowListByName(String rowName) {
        switch (rowName) {
            case "currentPlayerHandObservableList" -> {
                return table.getCurrentPlayer().getBoard().getHand().getCards();
            }
            case "currentPlayerDeckObservableList" -> {
                return table.getCurrentPlayer().getBoard().getDeck().getCards();
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
            case "opponentPlayerHandObservableList" -> {
                return table.getOpponent().getBoard().getHand().getCards();
            }
            case "currentPlayerSiegeSpecialPlaceObservableList" -> {
                return table.getCurrentPlayer().getBoard().getSiegeCardPlace().getSpecialPlace();
            }
            case "opponentPlayerSiegeObservableList" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace().getCards();
            }
            case "opponentPlayerCloseCombatObservableList" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace().getCards();
            }
            case "opponentPlayerRangedObservableList" -> {
                return table.getOpponent().getBoard().getRangedCardPlace().getCards();
            }
            case "opponentPlayerSiegeSpecialPlaceObservableList" -> {
                return table.getOpponent().getBoard().getSiegeCardPlace().getSpecialPlace();
            }
            case "opponentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return table.getOpponent().getBoard().getCloseCombatCardPlace().getSpecialPlace();
            }
            case "opponentPlayerRangedSpecialPlaceObservableList" -> {
                return table.getOpponent().getBoard().getRangedCardPlace().getSpecialPlace();
            }
            case "opponentPlayerDiscardPlace" -> {
                return table.getOpponent().getBoard().getDiscardPile().getCards();
            }
            case "currentPlayerDiscardPlace" -> {
                return table.getCurrentPlayer().getBoard().getDiscardPile().getCards();
            }
            case "weatherObservableList" -> {
                return table.getSpellPlace().getCards();
            }
            default -> {
                return null;
            }
        }
    }

    public void startNewGame(String player1Name, String player2Name, LinkedList<String> player1DeckNames, LinkedList<String> player2DeckNames, String player1SpecialCard, String player2SpecialCard) {
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
        } else if (player1.getScore() > player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player1);
            player2.decreaseCrystals();
        } else if (player1.getScore() < player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player2);
            player1.decreaseCrystals();
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
        gameMenuControllerView.backCardsToDiscardPiles();
        gameMenuControllerView.updateAllLabels();
    }

    private void changeRound() {
        endRound();
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

    private void changeTurn() {
        gameMenuControllerView.changeTurn();
        saveLog("change turn");
    }

    private void endGame(Table table, Player winner) {
        System.out.println("end game");
        //TODO نمایش برنده
        //TODO نمایش مجموع امتیاز های هر فرد در تمام راند ها
        //TODO اضافه کردن گیم دیتا
    }

    public void disApplyWeatherCards() {
        //TODO
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
}
