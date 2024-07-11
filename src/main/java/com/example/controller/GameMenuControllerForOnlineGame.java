package com.example.controller;

import com.example.model.App;
import com.example.model.GameData;
import com.example.model.IO.patterns.CheatCodes;
import com.example.model.alerts.*;
import com.example.model.deckmanager.DeckManager;
import com.example.model.card.*;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.deckmanager.DeckToJson;
import com.example.model.game.*;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
import com.example.view.Menu;
import com.example.view.menuControllers.GameMenuControllerViewForOnlineGame;
import com.example.view.menuControllers.ResultMenuControllerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class GameMenuControllerForOnlineGame extends AppController {
    private Table table;
    private GameMenuControllerViewForOnlineGame gameMenuControllerViewForOnlineGame;
    private int turn = 1;

    @Override
    public void run() {
        System.out.println("waiting");
        Platform.runLater(() -> {
            try {
                App.getAppView().showMenu(Menu.GAME_MENU);
                App.setCurrentMenu(Menu.GAME_MENU);
                App.setCurrentController(Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME);
                gameMenuControllerViewForOnlineGame = App.getAppView().getGameMenuControllerForOnlineGame();
                startRound(table);
                handleCommand();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Matcher matcher;

    private void handleCommand() {
        Thread thread = new Thread(() -> {
            try {
                String message;
                while ((message = App.in.readLine()) != null) {
                    if (OnlineGameCommands.CHANGE_TURN.getMatcher(message) != null) {
                        changeTurnWithNoLog();
                    } else if ((matcher = OnlineGameCommands.PASSED_ROUND.getMatcher(message)) != null) {
                        passRound(matcher);
                    } else if ((matcher = OnlineGameCommands.MOVE_CARD_AND_DO_ABILITY.getMatcher(message)) != null) {
                        moveCardAndDoAbility(matcher);
                    } else if ((matcher = OnlineGameCommands.MOVE_CARD_AND_DONT_DO_ABILITY.getMatcher(message)) != null) {
                        moveCardAndDontDoAbility(matcher);
                    } else if (CheatCodes.RECOVER_LEADER_ABILITY.matched(message)) {
                        table.getOpponent().getBoard().getDeck().getLeader().setCanDoAction(true);
                        gameMenuControllerViewForOnlineGame.updateAllLabels();
                    } else if (OnlineGameCommands.LEADER_ABILITY.getMatcher(message) != null) {
                        table.getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
                        gameMenuControllerViewForOnlineGame.updateAllLabels();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    private void passRound(Matcher matcher) {
        int playerPriority = Integer.parseInt(matcher.group("playerId"));
        if (playerPriority != table.getCurrentPlayer().getPriorityInGame()) {
            table.getOpponent().setPassRound(true);
            passRoundWithNoLog();
        } else {
            passRound();
        }
    }

    private void moveCardAndDoAbility(Matcher matcher) {
        int playerPriority = Integer.parseInt(matcher.group("playerPriority"));
        int cardId = Integer.parseInt(matcher.group("cardId"));
        String origin = matcher.group("origin");
        String dest = matcher.group("dest");
        if (isYou(playerPriority)) {
            moveCardAndDoAbilityForCurrentPlayer(cardId, origin, dest);
        } else {
            moveCardAndDoAbilityForOpponent(cardId, reverseRow(origin), reverseRow(dest));
        }
    }

    private void moveCardAndDontDoAbility(Matcher matcher) {
        int playerPriority = Integer.parseInt(matcher.group("playerPriority"));
        int cardId = Integer.parseInt(matcher.group("cardId"));
        String origin = matcher.group("origin");
        String dest = matcher.group("dest");
        if (isYou(playerPriority)) {
            moveCardAndDontDoAbilityForCurrentPlayer(cardId, origin, dest);
        } else {
            moveCardAndDontDoAbilityForOpponent(cardId, reverseRow(origin), reverseRow(dest));
        }
    }

    private boolean isYou(int playerPriority) {
        if (playerPriority == table.getCurrentPlayer().getPriorityInGame()) return true;
        else return false;
    }


    public GameMenuControllerViewForOnlineGame getGameMenuControllerView() {
        return gameMenuControllerViewForOnlineGame;
    }


    public void vetoCard(Player player, ObservableList<Card> selectedCards) {
        if (player.canVetoCard()) {
            Deck deck = player.getBoard().getDeck();
            for (int i = 0; i < selectedCards.size(); i++) {
                Card ranomCard = deck.getCard(new Random().nextInt(deck.getSize()));
                moveCardAndDontDoAbilityForCurrentPlayer(selectedCards.get(i).getIdInGame(), RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerDeck.toString());
                moveCardAndDontDoAbilityForCurrentPlayer(ranomCard.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
                player.decreaseNumberOfVetoCards();
            }
        }
        if (turn == 1) {
            App.getAppView().showNotification(NotificationsData.ROUND_START.getMessage(), NotificationsData.ROUND_START.getImageAddress(), null);
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5)));
        timeline.setOnFinished(e -> {
            getGameMenuControllerView().addMouseEventsForHandCards();
        });
        timeline.setCycleCount(1);
        timeline.play();
    }


    Card getCardById(int cardId, ObservableList<Card> originRow) {
        for (Card card : originRow) {
            if (card != null && card.getIdInGame() == cardId) {
                return card;
            }
        }
        return null;
    }

    public void moveCardAndDoAbilityForCurrentPlayer(int cardId, String origin, String destination) {
        ObservableList<Card> originRow = (ObservableList<Card>) getRowListByName(origin);
        Card card = getCardById(cardId, originRow);
        moveCard(cardId, origin, destination);


        if (card instanceof UnitCard) {
            AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
            abilityContext.addParam("dest", destination);

            switch (card.getAbilityName()) {
                case MUSTER:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.MUSTER);
                    break;
                case MORALE_BOOST:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.MORALE_BOOST);
                    break;
                case SPY:
                    abilityContext = new AbilityContext(table, null, null);
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.SPY);
                    break;
                case COMMANDER_HORN:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.COMMANDER_HORN);
                    break;
                case TIGHT_BOND:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.TIGHT_BOND);
                    break;
                case SCORCH:
                    abilityContext = new AbilityContext(table, (UnitCard) card, null);
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.SCORCH);
                    break;
                case MEDIC:
                    abilityContext = new AbilityContext(table, (UnitCard) card, null);
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.MEDIC);
                    break;
            }
        } else if (card instanceof SpecialCard) {
            AbilityContext abilityContext;
            switch (card.getAbilityName()) {
                case COMMANDER_HORN:
                    abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.COMMANDER_HORN);
                    break;
                case SCORCH:
                    if (destination.contains("SpecialPlace")) {
                        abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                    } else {
                        abilityContext = new AbilityContext(table, null, getRowByName(destination));
                    }
                    abilityContext.addParam("dest", destination);
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.SCORCH);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
                    timeline.setOnFinished(e -> {
                        moveCardAndDontDoAbilityForCurrentPlayer(cardId, destination, RowsInGame.currentPlayerDiscardPlace.toString());
                    });
                    timeline.setCycleCount(1);
                    timeline.play();
                    break;
                case WEATHER:
                    abilityContext = new AbilityContext(table, null, null);
                    abilityContext.addParam("card", card);
                    ((WeatherCard) card).setPlayer(table.getCurrentPlayer());
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.WEATHER);
                    break;
                case MARDROEME:
                    abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                    abilityContext.addParam("dest", destination);
                    abilityContext.addParam("mardroemeCard", card);
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.MARDROEME);
                    break;
            }
        }

        App.out.println("player|" + table.getCurrentPlayer().getPriorityInGame() + "|movedCard|" + cardId + "|from|" + origin + "|to|" + destination + "|andDoAbility");
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
        gameMenuControllerViewForOnlineGame.updateAllLabels();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), e -> {
            if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty() && !table.getCurrentPlayer().getBoard().getDeck().getLeader().canDoAction()) {
                passRound();
            } else if (!table.getOpponent().isPassRound()) {
                changeTurn();
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void moveCardAndDoAbilityForOpponent(int cardId, String origin, String destination) {
        ObservableList<Card> originRow = (ObservableList<Card>) getRowListByName(origin);
        Card card = getCardById(cardId, originRow);
        moveCard(cardId, origin, destination);

        if (card instanceof UnitCard) {
            AbilityContext abilityContext = new AbilityContext(table, (UnitCard) card, getRowByName(destination));
            abilityContext.addParam("dest", destination);

            switch (card.getAbilityName()) {
                case MUSTER:
                    doNonLeaderCardsAbilityForOpponent(card, AbilityName.MUSTER);
                    break;
                case MORALE_BOOST:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.MORALE_BOOST);
                    break;
                case SPY:
                    doNonLeaderCardsAbilityForOpponent(card, AbilityName.SPY);
                    break;
                case COMMANDER_HORN:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.COMMANDER_HORN);
                    break;
                case TIGHT_BOND:
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.TIGHT_BOND);
                    break;
                case SCORCH:
                    doNonLeaderCardsAbilityForOpponent(card, AbilityName.SCORCH);
                    break;
                case MEDIC:
                    doNonLeaderCardsAbilityForOpponent(card, AbilityName.MEDIC);
                    break;
            }
        } else if (card instanceof SpecialCard) {
            AbilityContext abilityContext;
            switch (card.getAbilityName()) {
                case COMMANDER_HORN:
                    abilityContext = new AbilityContext(table, null, getRowByName(getRowNameBySpecialPlaceName(destination)));
                    abilityContext.addParam("dest", destination);
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.COMMANDER_HORN);
                    break;
                case SCORCH:
                    doNonLeaderCardsAbilityForOpponent(card, AbilityName.SCORCH);
                    break;
                case WEATHER:
                    abilityContext = new AbilityContext(table, null, null);
                    abilityContext.addParam("card", card);
                    ((WeatherCard) card).setPlayer(table.getCurrentPlayer());
                    doNonLeaderCardsAbilityForCurrentPlayer(card, abilityContext, AbilityName.WEATHER);
                    break;
                case MARDROEME:
                    doNonLeaderCardsAbilityForOpponent(card, AbilityName.MARDROEME);
                    break;
            }
        }

        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
        gameMenuControllerViewForOnlineGame.updateAllLabels();
    }


    public void doDecoyAbility(int decoyCardId, int selectedCardId, String dest) {
        Card decoyCard = getCardById(decoyCardId, table.getCurrentPlayer().getBoard().getHand().getCards());
        Card selectedCard = getCardById(selectedCardId, (ObservableList<Card>) getRowListByName(dest));
        AbilityContext abilityContext = new AbilityContext(table, null, getRowByName(dest));
        abilityContext.addParam("decoyCard", decoyCard);
        abilityContext.addParam("cardToSwap", selectedCard);
        abilityContext.addParam("dest", dest);
        doNonLeaderCardsAbilityForCurrentPlayer(decoyCard, abilityContext, null);
        gameMenuControllerViewForOnlineGame.setPowerOfCardDefault(selectedCardId);
        gameMenuControllerViewForOnlineGame.addMouseEventsForHandCards();
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
        gameMenuControllerViewForOnlineGame.removeStyleClass();
    }

    private void doNonLeaderCardsAbilityForOpponent(Card card, AbilityName abilityName) {
        if (card.getAbility() != null) {
            if (card.getAbilityName() != AbilityName.DECOY || card.getAbilityName() != AbilityName.SCORCH) {
                gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(card.getIdInGame()).doAbilityAnimation(abilityName);
            }
        }
    }

    private void doNonLeaderCardsAbilityForCurrentPlayer(Card card, AbilityContext abilityContext, AbilityName abilityName) {
        if (card.getAbility() != null) {
            if (card.getAbilityName() == AbilityName.DECOY || card.getAbilityName() == AbilityName.SCORCH) {
                card.getAbility().apply(abilityContext);
            } else {
                gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(card.getIdInGame()).doAbilityAnimation(abilityName);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.5), event -> {
                    card.getAbility().apply(abilityContext);
                });
                Timeline timeline = new Timeline(keyFrame);
                timeline.setCycleCount(1);
                timeline.play();
            }
        }
    }

    public void moveCardAndDontDoAbilityForCurrentPlayer(int cardId, String origin, String destination) {
        moveCard(cardId, origin, destination);
        App.out.println("player|" + table.getPlayerInTurn().getPriorityInGame() + "|movedCard|" + cardId + "|from|" + origin + "|to|" + destination + "|andDontDoAbility");
    }

    public void moveCardAndDontDoAbilityForOpponent(int cardId, String origin, String destination) {
        moveCard(cardId, origin, destination);
    }

    public void moveCard(int cardId, String origin, String destination) {
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
        if (destination == RowsInGame.opponentDeck.toString() || destination == RowsInGame.currentPlayerDeck.toString()) {
            gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).setVisible(false);
        }
        if (destination == RowsInGame.opponentHand.toString() || destination == RowsInGame.currentPlayerHand.toString()) {
            gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).setVisible(true);
        }
        Row destRow = getRowByName(destination);
        if (destRow != null && destRow.isApplyWeather() && (card instanceof UnitCard) && !((UnitCard) card).isHero()) {
            if (table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName().getName().equals("leaders_skellige_king_bran")) {
                ((UnitCard) card).setPowerHalf();
                gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).updatePowerLabelAfterWeather();
            } else {
                ((UnitCard) card).setPowerOne();
                gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).updatePowerLabelAfterWeather();
            }
        } else if (destRow != null && destRow.getSpecialCard() != null && destRow.getSpecialCard().getAbilityName() == AbilityName.COMMANDER_HORN && (card instanceof UnitCard)) {
            ((UnitCard) card).duplicatePower();
            gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).updatePowerLabel();
        } else if (destRow != null && rowHasCommanderHorn(destinationRow) && (card instanceof UnitCard)) {
            if (card.getAbilityName() != AbilityName.COMMANDER_HORN) {
                ((UnitCard) card).duplicatePower();
                gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).updatePowerLabel();
            }
        } else if (destRow != null && rowHasMoralBoost(destinationRow) && (card instanceof UnitCard) && !((UnitCard) card).isHero()) {
            if (card.getAbilityName() != AbilityName.MORALE_BOOST) {
                ((UnitCard) card).applyMoralBoost();
                gameMenuControllerViewForOnlineGame.getGameCardViewWithCardId(cardId).updatePowerLabel();
            }
        }
        gameMenuControllerViewForOnlineGame.moveCardToDestinationFlowPane(cardId, origin, destination);
        gameMenuControllerViewForOnlineGame.updateAllLabels();
        table.getCurrentPlayer().updateScore();
        table.getOpponent().updateScore();
    }

    private boolean rowHasCommanderHorn(ObservableList<Card> cards) {
        for (Card card : cards) {
            if (card != null && card instanceof UnitCard && (card.getAbilityName() == AbilityName.COMMANDER_HORN))
                return true;
        }
        return false;
    }

    private boolean rowHasMoralBoost(ObservableList<Card> cards) {
        for (Card card : cards) {
            if (card != null && card instanceof UnitCard && (card.getAbilityName() == AbilityName.MORALE_BOOST))
                return true;
        }
        return false;
    }


    public void startNewGame(int player1Id, int player2Id, String player1DeckNames, String player2DeckNames, int gameId) {
        Player player1 = new Player(App.getUserByID(player1Id).getUsername(), player1Id);
        Player player2 = new Player(App.getUserByID(player2Id).getUsername(), player2Id);

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

        table = new Table(player1, player2, gameId);

        Round round1 = new Round(1);
        table.addRound(round1);
        table.setCurrentRound(round1);
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

    private void passRoundWithNoLog() {
        App.getAppView().showNotification(NotificationsData.YOUR_OPPONENT_HAS_PASSED.getMessage(), NotificationsData.YOUR_OPPONENT_HAS_PASSED.getImageAddress(), "");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.1)));
        timeline.play();
        timeline.setCycleCount(1);
        timeline.setOnFinished(actionEvent -> {
            if (table.getCurrentPlayer().isPassRound()) {
                changeRound();
            }
        });
    }


    public void passRound() {
        App.out.println("player|" + table.getCurrentPlayer().getPriorityInGame() + "|passedRound");
        App.getAppView().showNotification(NotificationsData.TURN_PASSED.getMessage(), NotificationsData.TURN_PASSED.getImageAddress(), "");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.1)));
        timeline.play();
        timeline.setCycleCount(1);
        timeline.setOnFinished(actionEvent -> {
            table.getCurrentPlayer().setPassRound(true);
            if (table.getOpponent().isPassRound()) {
                changeRound();
            } else {
                changeTurn();
            }
        });
    }

    private void startRound(Table table) {
        if (turn == 1) {
            gameMenuControllerViewForOnlineGame.showVetoCards();
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
            if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty() && !table.getCurrentPlayer().getBoard().getDeck().getLeader().canDoAction()) {
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
            App.getAppView().showNotification(NotificationsData.DRAW_ROUND.getMessage(), NotificationsData.DRAW_ROUND.getImageAddress(), "");
            table.getCurrentRound().setDraw(true);
            table.getCurrentRound().setWon(false);
            table.getCurrentRound().setWinner(null);
            player1.decreaseCrystals();
            player2.decreaseCrystals();
            gameMenuControllerViewForOnlineGame.updateAllLabels();
        } else if (player1.getScore() > player2.getScore()) {
            if (player1 == table.getCurrentPlayer()) {
                App.getAppView().showNotification(NotificationsData.YOU_WON_THIS_ROUND.getMessage(), NotificationsData.YOU_WON_THIS_ROUND.getImageAddress(), "");
            } else {
                App.getAppView().showNotification(NotificationsData.OPPONENT_WON_THIS_ROUND.getMessage(), NotificationsData.OPPONENT_WON_THIS_ROUND.getImageAddress(), "");
            }
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player1);
            player2.decreaseCrystals();
            gameMenuControllerViewForOnlineGame.updateAllLabels();
        } else if (player1.getScore() < player2.getScore()) {
            if (player2 == table.getCurrentPlayer()) {
                App.getAppView().showNotification(NotificationsData.YOU_WON_THIS_ROUND.getMessage(), NotificationsData.YOU_WON_THIS_ROUND.getImageAddress(), "");
            } else {
                App.getAppView().showNotification(NotificationsData.OPPONENT_WON_THIS_ROUND.getMessage(), NotificationsData.OPPONENT_WON_THIS_ROUND.getImageAddress(), "");
            }
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player2);
            player1.decreaseCrystals();
            gameMenuControllerViewForOnlineGame.updateAllLabels();
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
        gameMenuControllerViewForOnlineGame.setPowerOfCardsDefault();
    }

    private void backCardsToDiscardPiles() {
        gameMenuControllerViewForOnlineGame.backCardsToDiscardPlaces();
        gameMenuControllerViewForOnlineGame.updateAllLabels();
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


    private void disApplyWeatherForRows() {
        for (Row row : table.getOpponent().getBoard().getRows()) {
            row.setApplyWeather(false);
        }
        for (Row row : table.getCurrentPlayer().getBoard().getRows()) {
            row.setApplyWeather(false);
        }
    }

    private void changeTurn() {
        turn++;
        table.swapPlayers();
        gameMenuControllerViewForOnlineGame.changeTurn();
        App.out.println("change turn");
    }

    private void changeTurnWithNoLog() {
        turn++;
        table.swapPlayers();
        gameMenuControllerViewForOnlineGame.changeTurn();
    }


    private void endGame(Table table, Player winner) {
        System.out.println("end game");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2)));
        timeline.setOnFinished(e -> {
            table.setFinalWinner(winner);
            LocalDateTime localDateTime = LocalDateTime.now();
            String date = localDateTime.getYear() + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getDayOfMonth() + "-" + localDateTime.getHour() + ":" + localDateTime.getMinute();
            GameData gameData = new GameData(table.getOpponent().getUsername(), date, finalScore(table.getCurrentPlayer()), finalScore(table.getOpponent()), roundScores(table.getCurrentPlayer()), roundScores(table.getOpponent()), winner.getUsername());
            App.getLoggedInUser().addGameData(gameData);
            ResultMenuControllerView.setTable(table);
            App.setCurrentMenu(Menu.RESULT_MENU);
            Controller.RESULT_MENU_CONTROLLER.run();
        });
        timeline.setCycleCount(1);
        timeline.play();

    }

    private int[] roundScores(Player player) {
        int[] scores = new int[20];
        for (int i = 0; i < table.getRounds().size(); i++) {
            Round round = table.getRounds().get(i);
            scores[i] = round.getScores().get(player);
        }
        return scores;
    }

    private int finalScore(Player player) {
        int finalScore = 0;
        for (int i = 0; i < table.getRounds().size(); i++) {
            Round round = table.getRounds().get(i);
            finalScore += round.getScores().get(player);
        }
        return finalScore;
    }

    public void disApplyWeatherCards() {
        disApplyWeatherForRows();
        gameMenuControllerViewForOnlineGame.disApplyWeatherCards();
        gameMenuControllerViewForOnlineGame.removeWeatherPictures();
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
        App.out.println("leader ability done");
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
        gameMenuControllerViewForOnlineGame.updateAllLabels();
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
            case "opponentDeck" -> {
                return table.getOpponent().getBoard().getDeck().getCards();
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

    private String reverseRow(String row) {
        switch (row) {
            case "currentPlayerHand" -> {
                return RowsInGame.opponentHand.toString();
            }
            case "currentPlayerDeck" -> {
                return RowsInGame.opponentDeck.toString();
            }
            case "currentPlayerSiege" -> {
                return RowsInGame.opponentSiege.toString();
            }
            case "currentPlayerRanged" -> {
                return RowsInGame.opponentRanged.toString();
            }
            case "currentPlayerCloseCombat" -> {
                return RowsInGame.opponentCloseCombat.toString();
            }
            case "currentPlayerCloseCombatSpecialPlace" -> {
                return RowsInGame.opponentCloseCombatSpecialPlace.toString();
            }
            case "currentPlayerRangedSpecialPlace" -> {
                return RowsInGame.opponentRangedSpecialPlace.toString();
            }
            case "opponentHand" -> {
                return RowsInGame.currentPlayerHand.toString();
            }
            case "currentPlayerSiegeSpecialPlace" -> {
                return RowsInGame.opponentSiegeSpecialPlace.toString();
            }
            case "opponentSiege" -> {
                return RowsInGame.currentPlayerSiege.toString();
            }
            case "opponentCloseCombat" -> {
                return RowsInGame.currentPlayerCloseCombat.toString();
            }
            case "opponentRanged" -> {
                return RowsInGame.currentPlayerRanged.toString();
            }
            case "opponentSiegeSpecialPlace" -> {
                return RowsInGame.currentPlayerSiegeSpecialPlace.toString();
            }
            case "opponentCloseCombatSpecialPlace" -> {
                return RowsInGame.currentPlayerCloseCombatSpecialPlace.toString();
            }
            case "opponentRangedSpecialPlace" -> {
                return RowsInGame.currentPlayerRangedSpecialPlace.toString();
            }
            case "opponentDiscardPlace" -> {
                return RowsInGame.currentPlayerDiscardPlace.toString();
            }
            case "currentPlayerDiscardPlace" -> {
                return RowsInGame.opponentDiscardPlace.toString();
            }
            case "weather" -> {
                return RowsInGame.weather.toString();
            }
            default -> {
                return null;
            }
        }
    }


    public void addRandomCardToDeck() {
        Deck deck = table.getCurrentPlayer().getBoard().getDeck();
        Card randomCard = deck.getCard(new Random().nextInt(deck.getSize()));
        if (randomCard != null) {
            moveCardAndDontDoAbilityForCurrentPlayer(randomCard.getIdInGame(), RowsInGame.currentPlayerDeck.toString(), RowsInGame.currentPlayerHand.toString());
        }
    }

    public void recoverLeaderAbility() {
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(true);
        App.out.println(CheatCodes.RECOVER_LEADER_ABILITY.getPattern());
        gameMenuControllerViewForOnlineGame.updateAllLabels();
    }

    public void recoverCrystals() {
        table.getCurrentPlayer().setNumberOfCrystals(2);
        gameMenuControllerViewForOnlineGame.updateAllLabels();
    }

    public void luckOpponentLeaderAbility() {
        table.getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        gameMenuControllerViewForOnlineGame.updateAllLabels();
    }

    public void luckOpponentEmotes() {
        //TODO
    }

    public void setClownForOpponent() {
        gameMenuControllerViewForOnlineGame.setClownImageForOpponentLeaderCard();
    }

    public void addDecoyCard() {
        gameMenuControllerViewForOnlineGame.addDecoyCard();
    }

    public void sendEmote(Emote emote, Emotes emoteType) {
        //App.getAppView().showEmote(emoteType);
        App.getServerConnector().sendEmote(emote, App.getLoggedInUser().getId());
    }

    public void sendTextEmote(TextEmote textEmote, String text) {
        //App.getAppView().showTextEmote(text);
        App.getServerConnector().sendTextEmote(textEmote, text, App.getLoggedInUser().getId());
    }

    public void setTable(Table table) {
        this.table = table;
    }


}
