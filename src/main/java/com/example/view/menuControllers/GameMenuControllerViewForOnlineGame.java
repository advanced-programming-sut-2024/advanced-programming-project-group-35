package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.App;
import com.example.model.alerts.*;
import com.example.model.alerts.AlertType;
import com.example.model.card.*;
import com.example.model.card.Card;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.Place;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameMenuControllerViewForOnlineGame {
    public Rectangle opponentPlayerShadowRectangle;
    public Rectangle currentPlayerShadowRectangle;
    public Label opponentPlayerSiegePower;
    public Label opponentPlayerRangedPower;
    public Label opponentPlayerCloseCombatPower;
    public Label currentPlayerCloseCombatPower;
    public Label currentPlayerRangedPower;
    public Label currentPlayerSiegePower;
    public Label currentPlayerAllScoreCounter;
    public Label opponentPlayerAllScoreCounter;
    public Label currentPlayerUsername;
    public Label currentPlayerFactionName;
    public Label currentPlayerSpecialCardCounter;
    public Label opponentPlayerUsername;
    public Label opponentPlayerFactionName;
    public Label opponentPlayerSpecialCardCounter;
    public Label opponentPlayerDeckCardCounter;
    public Label currentPlayerDeckCardCounter;
    public ImageView opponentPlayerDeckPlace;
    public ImageView currentPlayerDeckPlace;
    public ImageView opponentPlayerLeaderCard;
    public ImageView currentPlayerLeaderCard;
    public ImageView currentPlayerFirstJem;
    public ImageView currentPlayerSecondJem;
    public ImageView opponentPlayerFirstJem;
    public ImageView opponentPlayerSecondJem;
    public ImageView opponentPlayerExcellenceShower;
    public ImageView currentPlayerExcellenceShower;
    public ImageView musicToggleIcon;
    public ImageView opponentPlayerLeaderAbilityEnable;
    public ImageView currentPlayerLeaderAbilityEnable;
    public ImageView currentPlayerProfilePic;
    public ImageView currentPlayerProfilePicBorder;
    public ImageView opponentPlayerProfilePic;
    public ImageView opponentPlayerProfilePicBorder;
    public ImageView cardDetailShower;
    public ImageView currentPlayerFactionIcon;
    public ImageView opponentPlayerFactionIcon;
    public ImageView weatherRow1;
    public ImageView weatherRow2;
    public ImageView weatherRow3;
    public ImageView weatherRow4;
    public ImageView weatherRow5;
    public ImageView weatherRow6;
    public FlowPane currentPlayerDeck;
    public FlowPane vetoCardPane = new FlowPane();
    public ImageView vetoCardBackground;
    public VBox vetoCardBox;
    public Button vetoCardButton;
    public Button passRoundButton;
    public Button terminalButton;
    public Button emoteButton1;
    public Button emoteButton2;
    public Button emoteButton3;
    public Button emoteButton4;
    public Button emoteButton5;
    public Button emoteButton6;
    public Button emoteButton7;
    public Button emoteButton8;
    public Button showEmoteButton;
    public Button hideEmoteButton;
    public Button showCustomEmoteHboxButton;
    public HBox customEmoteHbox;
    public Button hideCustomEmoteHboxButton;
    public TextField emoteTextField;
    private ObservableList<PreGameCard> vetoCardsToShow = FXCollections.observableArrayList();
    private ObservableList<Card> allCardsToVeto = FXCollections.observableArrayList();
    private ObservableList<Card> cardsToVeto = FXCollections.observableArrayList();
    public Button leaderAbility;
    @FXML
    private FlowPane currentPlayerHand;
    @FXML
    private FlowPane currentPlayerRanged;
    @FXML
    private FlowPane currentPlayerCloseCombat;
    @FXML
    private FlowPane opponentCloseCombat;
    @FXML
    private FlowPane opponentRanged;
    @FXML
    private FlowPane opponentSiege;
    @FXML
    private FlowPane currentPlayerSiegeSpecialPlace;
    @FXML
    private FlowPane currentPlayerRangedSpecialPlace;
    @FXML
    private FlowPane currentPlayerCloseCombatSpecialPlace;
    @FXML
    private FlowPane opponentCloseCombatSpecialPlace;
    @FXML
    private FlowPane opponentRangedSpecialPlace;
    @FXML
    private FlowPane opponentSiegeSpecialPlace;
    @FXML
    private FlowPane currentPlayerDiscardPlace;
    @FXML
    private FlowPane currentPlayerSiege;
    @FXML
    private FlowPane weather;
    private FlowPane opponentDeck = new FlowPane();
    @FXML
    private FlowPane opponentDiscardPlace;
    private FlowPane opponentHand = new FlowPane();
    private ObservableList<GameCardView> allCardsInGame = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerDeckObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerHandObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerSiegeObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerRangedObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerCloseCombatObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentCloseCombatObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentRangedObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> weatherObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentHandObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentDeckObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerCloseCombatSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerRangedSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerSiegeSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentSiegeObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentSiegeSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentCloseCombatSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentRangedSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerDiscardPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentDiscardPlaceObservablePlace = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentDiscardPlaceObservableList = FXCollections.observableArrayList();
    private ArrayList<ObservableList<GameCardView>> currentPlayerField = new ArrayList<>(Arrays.asList(currentPlayerRangedObservableList, currentPlayerSiegeObservableList, currentPlayerCloseCombatObservableList));
    private ArrayList<ObservableList<GameCardView>> opponentField = new ArrayList<>(Arrays.asList(opponentRangedObservableList, opponentSiegeObservableList, opponentCloseCombatObservableList));
    private ArrayList<ObservableList<GameCardView>> currentPlayerSpecialField = new ArrayList<>(Arrays.asList(currentPlayerRangedSpecialPlaceObservableList, currentPlayerSiegeSpecialPlaceObservableList, currentPlayerCloseCombatSpecialPlaceObservableList));
    private ArrayList<ObservableList<GameCardView>> opponentSpecialField = new ArrayList<>(Arrays.asList(opponentRangedSpecialPlaceObservableList, opponentSiegeSpecialPlaceObservableList, opponentCloseCombatSpecialPlaceObservableList));
    private ArrayList<ObservableList<GameCardView>> currentPlayerBoard = new ArrayList<>();
    private ArrayList<ObservableList<GameCardView>> opponentBoard = new ArrayList<>();
    private ArrayList<ObservableList<GameCardView>> allRowsObservableLists = new ArrayList<>();
    private ArrayList<FlowPane> currentPlayerFieldFlowPane = new ArrayList<>(Arrays.asList(currentPlayerRanged, currentPlayerSiege, currentPlayerCloseCombat));
    private ArrayList<FlowPane> opponentFieldFlowPane = new ArrayList<>(Arrays.asList(opponentRanged, opponentSiege, opponentCloseCombat));
    private ArrayList<FlowPane> currentPlayerSpecialFieldFlowPane = new ArrayList<>(Arrays.asList(currentPlayerRangedSpecialPlace, currentPlayerSiegeSpecialPlace, currentPlayerCloseCombatSpecialPlace));
    private ArrayList<FlowPane> opponentSpecialFieldFlowPane = new ArrayList<>(Arrays.asList(opponentRangedSpecialPlace, opponentSiegeSpecialPlace, opponentCloseCombatSpecialPlace));
    private ArrayList<FlowPane> currentPlayerBoardFlowPane = new ArrayList<>();
    private ArrayList<FlowPane> opponentBoardFlowPane = new ArrayList<>();
    private ArrayList<FlowPane> allRowsFlowPane = new ArrayList<>();


    private Table table;
    GameMenuControllerForOnlineGame controller = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController();

    public void passRound(MouseEvent mouseEvent) {
        controller.passRound();
    }


    @FXML
    public void initialize() {
        addAllCards();

        table = controller.getTable();

        addGameCardViewsToAllCards();

        setBoardForCurrentPlayer();

        setBoardForOpponent();

        if (!currentPlayerHand.getChildren().isEmpty()) {
            currentPlayerHand.getChildren().clear();
        }
        setInitialPictureForLeaderCards();

        addCurrentPlayerHandCards(table);
    }

    private void setInitialPictureForLeaderCards() {
        opponentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getOpponent().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
        currentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
    }

    private void setBoardForOpponent() {
        opponentPlayerFactionName.setText(table.getOpponent().getBoard().getDeck().getFaction().toString());
        opponentPlayerUsername.setText(table.getOpponent().getUsername());
        opponentPlayerCloseCombatPower.setText("0");
        opponentPlayerRangedPower.setText("0");
        opponentPlayerSiegePower.setText("0");
        opponentPlayerAllScoreCounter.setText(String.valueOf(table.getOpponent().getScore()));
        opponentPlayerSpecialCardCounter.setText(String.valueOf(table.getOpponent().getSpecialCardCounter()));
        opponentPlayerDeckCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getDeck().getSize()));
        opponentPlayerLeaderAbilityEnable.setVisible(table.getOpponent().getBoard().getDeck().getLeader().canDoAction());
        Image opponentFactionIcon = new Image(FactionsType.getFactionDeckShieldImageAddress(table.getOpponent().getBoard().getDeck().getFaction()));
        opponentPlayerFactionIcon.setImage(opponentFactionIcon);
        opponentPlayerFirstJem.setVisible(true);
        opponentPlayerSecondJem.setVisible(true);
        opponentPlayerExcellenceShower.setVisible(false);
        Image opponentDeckPlace = new Image(FactionsType.getFactionBackDeckImageAddress(table.getOpponent().getBoard().getDeck().getFaction()));
        opponentPlayerDeckPlace.setImage(opponentDeckPlace);
    }

    private void setBoardForCurrentPlayer() {
        currentPlayerFactionName.setText(table.getCurrentPlayer().getBoard().getDeck().getFaction().toString());
        currentPlayerUsername.setText(table.getCurrentPlayer().getUsername());
        currentPlayerCloseCombatPower.setText("0");
        currentPlayerRangedPower.setText("0");
        currentPlayerSiegePower.setText("0");
        currentPlayerAllScoreCounter.setText(String.valueOf(table.getCurrentPlayer().getScore()));
        currentPlayerSpecialCardCounter.setText(String.valueOf(table.getCurrentPlayer().getSpecialCardCounter()));
        currentPlayerDeckCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getDeck().getSize()));
        currentPlayerLeaderAbilityEnable.setVisible(table.getCurrentPlayer().getBoard().getDeck().getLeader().canDoAction());
        Image currentFactionIcon = new Image(FactionsType.getFactionDeckShieldImageAddress(table.getCurrentPlayer().getBoard().getDeck().getFaction()));
        currentPlayerFactionIcon.setImage(currentFactionIcon);
        currentPlayerFirstJem.setVisible(true);
        currentPlayerSecondJem.setVisible(true);
        currentPlayerExcellenceShower.setVisible(false);
        Image currentDeckPlace = new Image(FactionsType.getFactionBackDeckImageAddress(table.getCurrentPlayer().getBoard().getDeck().getFaction()));
        currentPlayerDeckPlace.setImage(currentDeckPlace);
    }

    private void addAllCards() {
        currentPlayerBoard.addAll(currentPlayerSpecialField);
        currentPlayerBoard.addAll(currentPlayerField);

        opponentBoard.addAll(opponentSpecialField);
        opponentBoard.addAll(opponentField);

        allRowsObservableLists.addAll(currentPlayerBoard);
        allRowsObservableLists.addAll(opponentBoard);

        currentPlayerBoardFlowPane.addAll(currentPlayerSpecialFieldFlowPane);
        currentPlayerBoardFlowPane.addAll(currentPlayerFieldFlowPane);

        opponentBoardFlowPane.addAll(opponentSpecialFieldFlowPane);
        opponentBoardFlowPane.addAll(opponentFieldFlowPane);

        allRowsFlowPane.addAll(currentPlayerBoardFlowPane);
        allRowsFlowPane.addAll(opponentBoardFlowPane);
    }


    private void addGameCardViewsToAllCards() {
        for (Card card : table.getCurrentPlayer().getBoard().getHand().getCards()) {
            allCardsInGame.add(new GameCardView(card));
        }
        for (Card card : table.getCurrentPlayer().getBoard().getDeck().getCards()) {
            allCardsInGame.add(new GameCardView(card));
        }
        for (Card card : table.getOpponent().getBoard().getHand().getCards()) {
            allCardsInGame.add(new GameCardView(card));
        }
        for (Card card : table.getOpponent().getBoard().getDeck().getCards()) {
            allCardsInGame.add(new GameCardView(card));
        }
    }


    private void addCurrentPlayerHandCards(Table table) {
        for (GameCardView gameCardView : allCardsInGame) {
            if (table.getCurrentPlayer().getBoard().getHand().getCards().contains(gameCardView.getCard())) {
                currentPlayerHandObservableList.add(gameCardView);
            }
        }
        for (GameCardView gameCardView : allCardsInGame) {
            if (table.getOpponent().getBoard().getHand().getCards().contains(gameCardView.getCard())) {
                opponentHandObservableList.add(gameCardView);
            }
        }
        updateCurrentPlayerHand();
    }

    private void updateCurrentPlayerHand() {
        addMouseEventsForHandCards();
        currentPlayerHand.getChildren().addAll(currentPlayerHandObservableList);
        opponentHand.getChildren().addAll(opponentHandObservableList);
    }

    public void addMouseEventsForHandCards() {
        for (GameCardView gameCardView : currentPlayerHandObservableList) {
            gameCardView.setOnMouseEntered(e -> {
                gameCardView.setTranslateY(-15);
                DropShadow dropShadow = new DropShadow();
                dropShadow.setColor(Color.YELLOW);
                dropShadow.setRadius(10);
                gameCardView.setEffect(dropShadow);
            });
            gameCardView.setOnMouseExited(e -> {
                gameCardView.setTranslateY(0);
                gameCardView.setEffect(null);
            });
            gameCardView.setOnMouseClicked(e -> {
                setOnMouseClickForDestinationFlowPane(gameCardView.getCard().getIdInGame());
                setOnMouseClickForShowCardDetails(gameCardView.getCard());
                if (gameCardView.getCard().getAbilityName() != AbilityName.DECOY) {
                    removeEventForDecoyAbility();
                }
            });
        }
    }

    private void setOnMouseClickForShowCardDetails(Card card) {
        String imageAddress = Main.class.getResource("/images/cards/" + card.getCardData().getImageAddress()).toExternalForm();
        cardDetailShower.setImage(new Image(imageAddress));
    }

    public void addMouseEventForDecoyAbility(ObservableList<GameCardView> gameCardViews, int decoyCardId, String originRow) {
        for (GameCardView gameCardView : gameCardViews) {
            gameCardView.setOnMouseEntered(e -> {
                DropShadow dropShadow = new DropShadow();
                dropShadow.setColor(Color.YELLOW);
                dropShadow.setRadius(10);
                gameCardView.setEffect(dropShadow);
            });
            gameCardView.setOnMouseExited(e -> {
                gameCardView.setEffect(null);
            });
            gameCardView.setOnMouseClicked(e -> {
                controller.doDecoyAbility(decoyCardId, gameCardView.getCard().getIdInGame(), originRow);
                removeEventForDecoyAbility();
            });
        }
    }

    public void removeEventForDecoyAbility() {
        for (ObservableList<GameCardView> list : allRowsObservableLists) {
            for (GameCardView gameCardView : list) {
                gameCardView.setOnMouseClicked(null);
                gameCardView.setOnMouseEntered(null);
                gameCardView.setOnMouseExited(null);
                gameCardView.setOnMousePressed(null);
                gameCardView.setOnMouseReleased(null);
            }
        }
    }

    private void disableMouseEventsForHandCards() {
        for (GameCardView gameCardView : currentPlayerHandObservableList) {
            gameCardView.setOnMouseClicked(null);
            gameCardView.setOnMouseEntered(null);
            gameCardView.setOnMouseExited(null);
            gameCardView.setOnMousePressed(null);
            gameCardView.setOnMouseReleased(null);
        }
    }

    private void disableMouseEventsForHandCard(int cardId) {
        GameCardView gameCardView = getGameCardViewWithCardId(cardId);
        gameCardView.setOnMouseClicked(null);
        gameCardView.setOnMouseEntered(null);
        gameCardView.setOnMouseExited(null);
        gameCardView.setOnMousePressed(null);
        gameCardView.setOnMouseReleased(null);
    }

    public GameCardView getGameCardViewWithCardId(int cardId) {
        for (GameCardView cardView : allCardsInGame) {
            if (cardView.getCard().getIdInGame() == cardId) return cardView;
        }
        return null;
    }

    private void setOnMouseClickForDestinationFlowPane(int cardId) {
        GameCardView gameCardView = getGameCardViewWithCardId(cardId);
        AbilityName abilityName = gameCardView.getCard().getAbilityName();
        Place place = gameCardView.getCardData().getPlaceToBe();

        removeStyleClass();

        switch (place) {
            case CLOSE_COMBAT -> handleCloseCombat(cardId, abilityName);
            case RANGED -> handleRanged(cardId, abilityName);
            case SIEGE -> handleSiege(cardId, abilityName);
            case AGILE -> handleAgile(cardId, abilityName);
            case WEATHER -> handleWeather(cardId);
            case SPECIAL -> handleSpecial(cardId, abilityName);
        }
    }

    private void handleCloseCombat(int cardId, AbilityName abilityName) {
        if (abilityName == AbilityName.SPY) {
            highlightAndSetOnClick(opponentCloseCombat, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombat.toString());
        } else {
            highlightAndSetOnClick(currentPlayerCloseCombat, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
        }
    }

    private void handleRanged(int cardId, AbilityName abilityName) {
        if (abilityName == AbilityName.SPY) {
            highlightAndSetOnClick(opponentRanged, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRanged.toString());
        } else {
            highlightAndSetOnClick(currentPlayerRanged, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
        }
    }

    private void handleSiege(int cardId, AbilityName abilityName) {
        if (abilityName == AbilityName.SPY) {
            highlightAndSetOnClick(opponentSiege, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentSiege.toString());
        } else {
            highlightAndSetOnClick(currentPlayerSiege, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
        }
    }

    private void handleAgile(int cardId, AbilityName abilityName) {
        if (abilityName == AbilityName.SPY) {
            highlightAndSetOnClick(opponentCloseCombat, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombat.toString());
            highlightAndSetOnClick(opponentRanged, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRanged.toString());
        } else {
            highlightAndSetOnClick(currentPlayerCloseCombat, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
            highlightAndSetOnClick(currentPlayerRanged, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
        }
    }

    private void handleWeather(int cardId) {
        highlightAndSetOnClick(weather, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.weather.toString());
    }

    private void handleSpecial(int cardId, AbilityName abilityName) {
        if (abilityName == AbilityName.DECOY) {
            handleDecoy(cardId);
        } else if (abilityName == AbilityName.SCORCH) {
            handleScorch(cardId);
        } else {
            handleDefaultSpecial(cardId);
        }
    }

    private void handleDecoy(int cardId) {
        if (!currentPlayerCloseCombatObservableList.isEmpty()) {
            highlightAndSetOnClickWithDecoyAbility(currentPlayerCloseCombat, currentPlayerCloseCombatObservableList, cardId, RowsInGame.currentPlayerCloseCombat.toString());
        }
        if (!currentPlayerRangedObservableList.isEmpty()) {
            highlightAndSetOnClickWithDecoyAbility(currentPlayerRanged, currentPlayerRangedObservableList, cardId, RowsInGame.currentPlayerRanged.toString());
        }
        if (!currentPlayerSiegeObservableList.isEmpty()) {
            highlightAndSetOnClickWithDecoyAbility(currentPlayerSiege, currentPlayerSiegeObservableList, cardId, RowsInGame.currentPlayerSiege.toString());
        }
    }

    private void handleScorch(int cardId) {
        highlightAndSetOnClickIfEmpty(currentPlayerCloseCombatSpecialPlace, cardId, RowsInGame.currentPlayerCloseCombatSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(currentPlayerRangedSpecialPlace, cardId, RowsInGame.currentPlayerRangedSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(currentPlayerSiegeSpecialPlace, cardId, RowsInGame.currentPlayerSiegeSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(opponentCloseCombatSpecialPlace, cardId, RowsInGame.opponentCloseCombatSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(opponentRangedSpecialPlace, cardId, RowsInGame.opponentRangedSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(opponentSiegeSpecialPlace, cardId, RowsInGame.opponentSiegeSpecialPlace.toString());

        highlightAndSetOnClick(currentPlayerCloseCombat, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
        highlightAndSetOnClick(currentPlayerRanged, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
        highlightAndSetOnClick(currentPlayerSiege, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
        highlightAndSetOnClick(opponentCloseCombat, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombat.toString());
        highlightAndSetOnClick(opponentRanged, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRanged.toString());
        highlightAndSetOnClick(opponentSiege, cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentSiege.toString());
    }

    private void handleDefaultSpecial(int cardId) {
        highlightAndSetOnClickIfEmpty(currentPlayerCloseCombatSpecialPlace, cardId, RowsInGame.currentPlayerCloseCombatSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(currentPlayerRangedSpecialPlace, cardId, RowsInGame.currentPlayerRangedSpecialPlace.toString());
        highlightAndSetOnClickIfEmpty(currentPlayerSiegeSpecialPlace, cardId, RowsInGame.currentPlayerSiegeSpecialPlace.toString());
    }

    private void highlightAndSetOnClick(FlowPane flowPane, int cardId, String origin, String destination) {
        flowPane.getStyleClass().add("highlighted-flow-pane");
        flowPane.setOnMouseClicked(e -> {
            controller.moveCardAndDoAbilityForCurrentPlayer(cardId, origin, destination);
            removeStyleClass();
            removeSetOnMouseClickedForAllFlowPanes();
        });
    }

    private void highlightAndSetOnClickIfEmpty(FlowPane flowPane, int cardId, String destination) {
        if (flowPane.getChildren().isEmpty()) {
            highlightAndSetOnClick(flowPane, cardId, RowsInGame.currentPlayerHand.toString(), destination);
        }
    }

    private void highlightAndSetOnClickWithDecoyAbility(FlowPane flowPane, ObservableList<GameCardView> observableList, int cardId, String destination) {
        flowPane.getStyleClass().add("highlighted-flow-pane");
        addMouseEventForDecoyAbility(observableList, cardId, destination);
    }

    private void removeSetOnMouseClickedForAllFlowPanes() {
        currentPlayerCloseCombat.setOnMouseClicked(null);
        currentPlayerRanged.setOnMouseClicked(null);
        currentPlayerSiege.setOnMouseClicked(null);
        opponentCloseCombat.setOnMouseClicked(null);
        opponentRanged.setOnMouseClicked(null);
        opponentSiege.setOnMouseClicked(null);
    }


    public void removeStyleClass() {
        currentPlayerCloseCombat.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerRanged.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerSiege.getStyleClass().remove("highlighted-flow-pane");
        weather.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerCloseCombatSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerRangedSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerSiegeSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        opponentCloseCombat.getStyleClass().remove("highlighted-flow-pane");
        opponentRanged.getStyleClass().remove("highlighted-flow-pane");
        opponentSiege.getStyleClass().remove("highlighted-flow-pane");
        opponentRangedSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        opponentSiegeSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        opponentCloseCombatSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        cardDetailShower.setImage(null);
    }


    private ObservableList<GameCardView> getObservableListWithName(String listName) {
        return switch (listName) {
            case "currentPlayerHand" -> currentPlayerHandObservableList;
            case "currentPlayerDeck" -> currentPlayerDeckObservableList;
            case "opponentHand" -> opponentHandObservableList;
            case "opponentDeck" -> opponentDeckObservableList;
            case "weather" -> weatherObservableList;
            case "currentPlayerCloseCombat" -> currentPlayerCloseCombatObservableList;
            case "currentPlayerRanged" -> currentPlayerRangedObservableList;
            case "currentPlayerSiege" -> currentPlayerSiegeObservableList;
            case "currentPlayerCloseCombatSpecialPlace" -> currentPlayerCloseCombatSpecialPlaceObservableList;
            case "currentPlayerRangedSpecialPlace" -> currentPlayerRangedSpecialPlaceObservableList;
            case "currentPlayerSiegeSpecialPlace" -> currentPlayerSiegeSpecialPlaceObservableList;
            case "opponentCloseCombat" -> opponentCloseCombatObservableList;
            case "opponentRanged" -> opponentRangedObservableList;
            case "opponentSiege" -> opponentSiegeObservableList;
            case "opponentCloseCombatSpecialPlace" -> opponentCloseCombatSpecialPlaceObservableList;
            case "opponentRangedSpecialPlace" -> opponentRangedSpecialPlaceObservableList;
            case "opponentSiegeSpecialPlace" -> opponentSiegeSpecialPlaceObservableList;
            case "currentPlayerDiscardPlace" -> currentPlayerDiscardPlaceObservableList;
            case "opponentDiscardPlace" -> opponentDiscardPlaceObservableList;
            default -> null;
        };
    }

    private FlowPane getFlowPaneWithName(String listName) {
        return switch (listName) {
            case "currentPlayerHand" -> currentPlayerHand;
            case "currentPlayerDeck" -> currentPlayerDeck;
            case "opponentHand" -> opponentHand;
            case "opponentDeck" -> opponentDeck;
            case "weather" -> weather;
            case "currentPlayerCloseCombat" -> currentPlayerCloseCombat;
            case "currentPlayerRanged" -> currentPlayerRanged;
            case "currentPlayerSiege" -> currentPlayerSiege;
            case "currentPlayerCloseCombatSpecialPlace" -> currentPlayerCloseCombatSpecialPlace;
            case "currentPlayerRangedSpecialPlace" -> currentPlayerRangedSpecialPlace;
            case "currentPlayerSiegeSpecialPlace" -> currentPlayerSiegeSpecialPlace;
            case "opponentCloseCombat" -> opponentCloseCombat;
            case "opponentRanged" -> opponentRanged;
            case "opponentSiege" -> opponentSiege;
            case "opponentCloseCombatSpecialPlace" -> opponentCloseCombatSpecialPlace;
            case "opponentRangedSpecialPlace" -> opponentRangedSpecialPlace;
            case "opponentSiegeSpecialPlace" -> opponentSiegeSpecialPlace;
            case "currentPlayerDiscardPlace" -> currentPlayerDiscardPlace;
            case "opponentDiscardPlace" -> opponentDiscardPlace;
            default -> null;
        };
    }

    public void moveCardToDestinationFlowPane(int cardId, String initialObservableListName, String destinationObservableListName) {
        Platform.runLater(() -> {
            GameCardView gameCardView = getGameCardViewWithCardId(cardId);

            ObservableList<GameCardView> initialObservableList = getObservableListWithName(initialObservableListName);
            ObservableList<GameCardView> destinationObservableList = getObservableListWithName(destinationObservableListName);
            initialObservableList.remove(gameCardView);
            if (!destinationObservableList.contains(gameCardView)) {
                destinationObservableList.add(gameCardView);

            }

            FlowPane destinationFLowPane = getFlowPaneWithName(destinationObservableListName);
            FlowPane initialFlowPane = getFlowPaneWithName(initialObservableListName);

            if (!destinationFLowPane.getChildren().contains(gameCardView)) {
                destinationFLowPane.getChildren().add(gameCardView);

            }
            initialFlowPane.getChildren().remove(gameCardView);
            disableMouseEventsForHandCard(cardId);
            destinationFLowPane.setOnMouseClicked(null);
            updateAllLabels();
        });
    }


    public void toggleMusic(MouseEvent mouseEvent) {
    }

    public void changeTurn() {
        if (table.getPlayerInTurn() == table.getCurrentPlayer()) {
            App.getAppView().showNotification(NotificationsData.YOUR_TURN.getMessage(), NotificationsData.YOUR_TURN.getImageAddress(), "");
            disableLockScreen();
        } else {
            App.getAppView().showNotification(NotificationsData.USERNAME_TURN.getMessage(), NotificationsData.USERNAME_TURN.getImageAddress(), table.getOpponent().getUsername());
            lockScreen();
        }
    }

    public void removeWeatherPictures() {
        weatherRow1.setVisible(false);
        weatherRow2.setVisible(false);
        weatherRow3.setVisible(false);
        weatherRow4.setVisible(false);
        weatherRow5.setVisible(false);
        weatherRow6.setVisible(false);
    }


    private void swapObservableLists(ObservableList<GameCardView> list1, ObservableList<GameCardView> list2) {
        ObservableList<GameCardView> temp1 = FXCollections.observableArrayList(list1);
        ObservableList<GameCardView> temp2 = FXCollections.observableArrayList(list2);
        list1.setAll(temp2);
        list2.setAll(temp1);
    }

    private void swapFlowPanes(FlowPane pane1, FlowPane pane2) {
        ArrayList<Node> temp1 = new ArrayList<>(pane1.getChildren());
        ArrayList<Node> temp2 = new ArrayList<>(pane2.getChildren());
        pane1.getChildren().setAll(temp2);
        pane2.getChildren().setAll(temp1);
    }


    public void leaderAbilityApply(MouseEvent mouseEvent) {
        controller.doCurrentPlayerLeaderAbility();
    }

    public void backCardsToDiscardPlaces() {
        setPowerOfCardsDefault();
        moveCardsFromRowToDiscardPlace(currentPlayerCloseCombatObservableList, RowsInGame.currentPlayerCloseCombat);
        moveCardsFromRowToDiscardPlace(currentPlayerRangedObservableList, RowsInGame.currentPlayerRanged);
        moveCardsFromRowToDiscardPlace(currentPlayerSiegeObservableList, RowsInGame.currentPlayerSiege);
        moveCardsFromRowToDiscardPlace(opponentCloseCombatObservableList, RowsInGame.opponentCloseCombat);
        moveCardsFromRowToDiscardPlace(opponentRangedObservableList, RowsInGame.opponentRanged);
        moveCardsFromRowToDiscardPlace(opponentSiegeObservableList, RowsInGame.opponentSiege);
        moveCardsFromRowToDiscardPlace(currentPlayerCloseCombatSpecialPlaceObservableList, RowsInGame.currentPlayerCloseCombatSpecialPlace);
        moveCardsFromRowToDiscardPlace(currentPlayerRangedSpecialPlaceObservableList, RowsInGame.currentPlayerRangedSpecialPlace);
        moveCardsFromRowToDiscardPlace(currentPlayerSiegeSpecialPlaceObservableList, RowsInGame.currentPlayerSiegeSpecialPlace);
        moveCardsFromRowToDiscardPlace(opponentCloseCombatSpecialPlaceObservableList, RowsInGame.opponentCloseCombatSpecialPlace);
        moveCardsFromRowToDiscardPlace(opponentRangedSpecialPlaceObservableList, RowsInGame.opponentRangedSpecialPlace);
        moveCardsFromRowToDiscardPlace(opponentSiegeSpecialPlaceObservableList, RowsInGame.opponentSiegeSpecialPlace);
        updateAllLabels();
    }

    private void moveCardsFromRowToDiscardPlace(ObservableList<GameCardView> rowObservableList, RowsInGame sourceRow) {
        ObservableList<GameCardView> rowObservableListCopy = FXCollections.observableArrayList(rowObservableList);
        for (GameCardView gameCardView : rowObservableListCopy) {
            int dest = getDestByCardId(gameCardView.getCard().getIdInGame());
            if (dest == 1) {
                if (gameCardView.getCard() instanceof UnitCard) {
                    if (!(((UnitCard) gameCardView.getCard()).noRemove())) {
                        controller.moveCardAndDontDoAbilityForCurrentPlayer(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
                    }
                    if ((((UnitCard) gameCardView.getCard()).noRemove()) && gameCardView.getCard().getAbilityName() == AbilityName.TRANSFORMER) {
                        gameCardView.applyTransform();
                        updateAllLabels();
                    }
                } else {
                    controller.moveCardAndDontDoAbilityForCurrentPlayer(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
                }
            } else {
                if (gameCardView.getCard() instanceof UnitCard) {
                    if (!(((UnitCard) gameCardView.getCard()).noRemove())) {
                        controller.moveCardAndDontDoAbilityForCurrentPlayer(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.opponentDiscardPlace.toString());
                    }
                    if ((((UnitCard) gameCardView.getCard()).noRemove()) && gameCardView.getCard().getAbilityName() == AbilityName.TRANSFORMER) {
                        gameCardView.applyTransform();
                        updateAllLabels();
                    }
                } else {
                    controller.moveCardAndDontDoAbilityForCurrentPlayer(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.opponentDiscardPlace.toString());
                }
            }
        }
    }

    private int getDestByCardId(int cardId) {
        if ((cardId / 100) == table.getCurrentPlayer().getPriorityInGame()) return 1;
        else return 0;
    }


    public void setPowerOfCardDefault(int cardId) {
        getGameCardViewWithCardId(cardId).setPowerDefault();
    }

    public void setPowerOfCardsDefault() {
        Platform.runLater(() -> {
            setPowerDefaultForRow(currentPlayerRangedObservableList);
            setPowerDefaultForRow(currentPlayerCloseCombatObservableList);
            setPowerDefaultForRow(currentPlayerSiegeObservableList);
            setPowerDefaultForRow(opponentRangedObservableList);
            setPowerDefaultForRow(opponentCloseCombatObservableList);
            setPowerDefaultForRow(opponentSiegeObservableList);
        });
    }

    private void setPowerDefaultForRow(ObservableList<GameCardView> rowObservableList) {
        Platform.runLater(() -> {
            synchronized (rowObservableList) {
                for (GameCardView gameCardView : rowObservableList) {
                    gameCardView.setPowerDefault();
                }
            }
            updateAllLabels();
        });
    }


    public void updateAllLabels() {
        Platform.runLater(() -> {
            updateScores();
            updateCardCounts();
            updateNames();
            updateCrystals();
            updateMostScorePlayerPicture();
            updateLeaderPictures();
            if (currentPlayerSiegeObservableList.size() > 9) {
                currentPlayerSiege.setHgap(0);
            } else {
                currentPlayerSiege.setHgap(5);
            }
            if (currentPlayerRangedObservableList.size() > 9) {
                currentPlayerRanged.setHgap(0);
            } else {
                currentPlayerRanged.setHgap(5);
            }
            if (currentPlayerCloseCombatObservableList.size() > 9) {
                currentPlayerCloseCombat.setHgap(0);
            } else {
                currentPlayerCloseCombat.setHgap(5);
            }
            if (opponentSiegeObservableList.size() > 9) {
                opponentSiege.setHgap(0);
            } else {
                opponentSiege.setHgap(5);
            }
            if (opponentRangedObservableList.size() > 9) {
                opponentRanged.setHgap(0);
            } else {
                opponentRanged.setHgap(5);
            }
            if (opponentCloseCombatObservableList.size() > 9) {
                opponentCloseCombat.setHgap(0);
            } else {
                opponentCloseCombat.setHgap(5);
            }
        });
    }

    private void updateLeaderPictures() {
        opponentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getOpponent().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
        currentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
    }

    private void updateMostScorePlayerPicture() {
        if (table.getCurrentPlayer().getScore() > table.getOpponent().getScore()) {
            currentPlayerExcellenceShower.setVisible(true);
            opponentPlayerExcellenceShower.setVisible(false);
        } else if (table.getCurrentPlayer().getScore() < table.getOpponent().getScore()) {
            currentPlayerExcellenceShower.setVisible(false);
            opponentPlayerExcellenceShower.setVisible(true);
        } else {
            currentPlayerExcellenceShower.setVisible(false);
            opponentPlayerExcellenceShower.setVisible(false);
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getLeader().canDoAction()) {
            currentPlayerLeaderAbilityEnable.setVisible(true);
        } else {
            currentPlayerLeaderAbilityEnable.setVisible(false);
        }
        if (table.getOpponent().getBoard().getDeck().getLeader().canDoAction()) {
            opponentPlayerLeaderAbilityEnable.setVisible(true);
        } else {
            opponentPlayerLeaderAbilityEnable.setVisible(false);
        }
    }

    private void updateCrystals() {
        if (table.getCurrentPlayer().getNumberOfCrystals() == 0) {
            currentPlayerSecondJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_off.png").toExternalForm()));
            currentPlayerFirstJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_off.png").toExternalForm()));
        } else if (table.getCurrentPlayer().getNumberOfCrystals() == 1) {
            currentPlayerSecondJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_off.png").toExternalForm()));
            currentPlayerFirstJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_on.png").toExternalForm()));
        } else {
            currentPlayerSecondJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_on.png").toExternalForm()));
            currentPlayerFirstJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_on.png").toExternalForm()));
        }
        if (table.getOpponent().getNumberOfCrystals() == 0) {
            opponentPlayerSecondJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_off.png").toExternalForm()));
            opponentPlayerFirstJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_off.png").toExternalForm()));
        } else if (table.getOpponent().getNumberOfCrystals() == 1) {
            opponentPlayerSecondJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_off.png").toExternalForm()));
            opponentPlayerFirstJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_on.png").toExternalForm()));
        } else {
            opponentPlayerSecondJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_on.png").toExternalForm()));
            opponentPlayerFirstJem.setImage(new Image(Main.class.getResource("/images/icons/icon_gem_on.png").toExternalForm()));
        }
    }

    private void updateNames() {
        currentPlayerUsername.setText(table.getCurrentPlayer().getUsername());
        currentPlayerFactionName.setText(table.getCurrentPlayer().getBoard().getDeck().getFaction().toString());
        opponentPlayerUsername.setText(table.getOpponent().getUsername());
        opponentPlayerFactionName.setText(table.getOpponent().getBoard().getDeck().getFaction().toString());
    }

    private void updateCardCounts() {
        currentPlayerSpecialCardCounter.setText(String.valueOf(table.getCurrentPlayer().getSpecialCardCounter()));
        opponentPlayerSpecialCardCounter.setText(String.valueOf(table.getOpponent().getSpecialCardCounter()));
        opponentPlayerDeckCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getDeck().getSize()));
        currentPlayerDeckCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getDeck().getSize()));
    }

    private void updateScores() {
        opponentPlayerSiegePower.setText(String.valueOf(table.getOpponent().getBoard().getSiegeCardPlace().getStrength()));
        opponentPlayerRangedPower.setText(String.valueOf(table.getOpponent().getBoard().getRangedCardPlace().getStrength()));
        opponentPlayerCloseCombatPower.setText(String.valueOf(table.getOpponent().getBoard().getCloseCombatCardPlace().getStrength()));
        currentPlayerSiegePower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getSiegeCardPlace().getStrength()));
        currentPlayerRangedPower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getRangedCardPlace().getStrength()));
        currentPlayerCloseCombatPower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getCloseCombatCardPlace().getStrength()));
        currentPlayerAllScoreCounter.setText(String.valueOf(table.getCurrentPlayer().getScore()));
        opponentPlayerAllScoreCounter.setText(String.valueOf(table.getOpponent().getScore()));
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }


    public void backWeatherCardToDiscardPlaces(WeatherCard weatherCard) {
        if (weatherCard.getName().startsWith("weather_frost")) {
            setPowerDefaultForRow(currentPlayerCloseCombatObservableList);
            setPowerDefaultForRow(opponentCloseCombatObservableList);
            moveWeatherCardToDiscardPlace(weatherCard);
        } else if (weatherCard.getName().startsWith("weather_fog")) {
            setPowerDefaultForRow(currentPlayerRangedObservableList);
            setPowerDefaultForRow(opponentRangedObservableList);
            moveWeatherCardToDiscardPlace(weatherCard);
        } else if (weatherCard.getName().startsWith("weather_rain")) {
            setPowerDefaultForRow(currentPlayerSiegeObservableList);
            setPowerDefaultForRow(opponentSiegeObservableList);
            moveWeatherCardToDiscardPlace(weatherCard);
        } else if (weatherCard.getName().startsWith("weather_storm")) {
            setPowerDefaultForRow(currentPlayerCloseCombatObservableList);
            setPowerDefaultForRow(opponentCloseCombatObservableList);
            setPowerDefaultForRow(currentPlayerRangedObservableList);
            setPowerDefaultForRow(opponentRangedObservableList);
            moveWeatherCardToDiscardPlace(weatherCard);
        } else if (weatherCard.getName().startsWith("weather_clear")) {
            moveWeatherCardToDiscardPlace(weatherCard);
        }
    }

    private void moveWeatherCardToDiscardPlace(WeatherCard weatherCard) {
        if (weatherCard.getCardData() != CardData.weather_clear && weatherCard.getPlayer().getPriorityInGame() == table.getCurrentPlayer().getPriorityInGame()) {
            controller.moveCardAndDontDoAbilityForCurrentPlayer(weatherCard.getIdInGame(), RowsInGame.weather.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
        } else {
            controller.moveCardAndDontDoAbilityForCurrentPlayer(weatherCard.getIdInGame(), RowsInGame.weather.toString(), RowsInGame.opponentDiscardPlace.toString());
        }
    }

    public void disApplyWeatherCards() {
        ObservableList<WeatherCard> weatherCardsCopy = FXCollections.observableArrayList(table.getSpellPlace().getCards());
        for (WeatherCard weatherCard : weatherCardsCopy) {
            backWeatherCardToDiscardPlaces(weatherCard);
        }
    }

    public void setClownImageForOpponentLeaderCard() {
        opponentPlayerLeaderCard.setImage(new Image(GameMenuControllerViewForOnlineGame.class.getResource("/images/crown.jpg").toExternalForm()));
    }

    private int decoyCheat = 0;

    public void addDecoyCard() {
        if (decoyCheat == 0) {
            Card decoy = CardFactory.getCardByName("special_decoy");
            GameCardView gameCardView = new GameCardView(decoy);
            currentPlayerHand.getChildren().add(gameCardView);
            currentPlayerHandObservableList.add(gameCardView);
            if (table.getCurrentPlayer().getPriorityInGame() == 1) {
                decoy.setIdInGame(199);
            } else {
                decoy.setIdInGame(299);
            }
            addMouseEventsForHandCards();
            decoyCheat++;
        } else {
            App.getAppView().showAlert("you can't receive more than one decoy card", AlertType.WARNING.getType());
        }
    }

    public void showVetoCards() {
        vetoCardBox.setVisible(true);
        vetoCardButton.setVisible(true);
        vetoCardBackground.setVisible(true);
        vetoCardPane.setVisible(true);
        if (vetoCardPane.getChildren().size() > 0) {
            vetoCardPane.getChildren().clear();
        }
        vetoCardsToShow.clear();
        allCardsToVeto.clear();
        cardsToVeto.clear();
        addToVetoCardPane();
        addOnMouseClickedEventToVetoCard();
        vetoCardPane.getChildren().addAll(vetoCardsToShow);
    }

    private void addToVetoCardPane() {
        int j = 0;
        for (Card card : table.getCurrentPlayer().getBoard().getDeck().getCards()) {
            for (int i = 0; i < table.getCurrentPlayer().getBoard().getDeck().getCards().size(); i++) {
                if (Objects.equals(card.getId(), table.getCurrentPlayer().getBoard().getHand().getCards().get(i).getId())) {
                    vetoCardsToShow.add(new PreGameCard(card.getName(), CardData.getCardDataByName(card.getName()).getPower(), CardData.getCardDataByName(card.getName()).getAbility(), Main.class.getResource("/images/cards/") + CardData.getCardDataByName(card.getName()).getImageAddress()));
                    allCardsToVeto.add(card);
                    j++;
                    break;
                }
                if (j == 5) {
                    break;
                }
            }
            if (j == 5) {
                break;
            }
        }
    }

    private void addOnMouseClickedEventToVetoCard() {
        for (int i = 0; i < vetoCardsToShow.size(); i++) {
            int finalI = i;
            vetoCardsToShow.get(i).setOnMouseClicked(event -> {
                cardsToVeto.add(allCardsToVeto.get(finalI));
                vetoCardPane.getChildren().remove(vetoCardsToShow.get(finalI));
                if (cardsToVeto.size() == 2) {
                    vetoCardBox.setVisible(false);
                    vetoCardBackground.setVisible(false);
                    vetoCardPane.setVisible(false);
                    vetoCardButton.setVisible(false);
                    controller.vetoCard(table.getCurrentPlayer(), cardsToVeto);
                    if (table.getCurrentPlayer() != table.getPlayerInTurn()) {
                        lockScreen();
                    }
                }
            });
        }
    }

    public void vetoCard(MouseEvent mouseEvent) {
        vetoCardBox.setVisible(false);
        vetoCardBackground.setVisible(false);
        vetoCardPane.setVisible(false);
        vetoCardButton.setVisible(false);
        controller.vetoCard(table.getCurrentPlayer(), cardsToVeto);
        if (table.getCurrentPlayer() != table.getPlayerInTurn()) {
            lockScreen();
        }
    }

    public void showEmoteButtons(MouseEvent mouseEvent) {
        emoteButton1.setVisible(true);
        emoteButton2.setVisible(true);
        emoteButton3.setVisible(true);
        emoteButton4.setVisible(true);
        emoteButton5.setVisible(true);
        emoteButton6.setVisible(true);
        emoteButton7.setVisible(true);
        emoteButton8.setVisible(true);
        hideEmoteButton.setVisible(true);
        showEmoteButton.setVisible(false);
        customEmoteHbox.setVisible(false);
        hideCustomEmoteHboxButton.setVisible(false);
        showCustomEmoteHboxButton.setVisible(false);
    }

    public void haHaEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.HA_HA_HA);
        controller.sendEmote(emote, Emotes.HA_HA_HA);
    }

    public void thanksEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.THANKS);
        controller.sendEmote(emote, Emotes.THANKS);
    }

    public void oopsEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.OOPS);
        controller.sendEmote(emote, Emotes.OOPS);
    }

    public void dirinLaLaLaEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.DIRIN_LALALA);
        controller.sendEmote(emote, Emotes.DIRIN_LALALA);
    }

    public void boringEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.BORING);
        controller.sendEmote(emote, Emotes.BORING);
    }

    public void shhhhEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.SHHHHHH);
        controller.sendEmote(emote, Emotes.SHHHHHH);
    }

    public void anyWayEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.ANY_WAY);
        controller.sendEmote(emote, Emotes.ANY_WAY);
    }

    public void goodOneEmote(MouseEvent mouseEvent) {
        Emote emote = new Emote(Emotes.GOOD_ONE);
        controller.sendEmote(emote, Emotes.GOOD_ONE);
    }

    public void hideEmoteButtons(MouseEvent mouseEvent) {
        emoteButton1.setVisible(false);
        emoteButton2.setVisible(false);
        emoteButton3.setVisible(false);
        emoteButton4.setVisible(false);
        emoteButton5.setVisible(false);
        emoteButton6.setVisible(false);
        emoteButton7.setVisible(false);
        emoteButton8.setVisible(false);
        hideEmoteButton.setVisible(false);
        showEmoteButton.setVisible(true);
        customEmoteHbox.setVisible(false);
        hideCustomEmoteHboxButton.setVisible(false);
        showCustomEmoteHboxButton.setVisible(true);
    }

    public void showCustomEmoteHbox(MouseEvent mouseEvent) {
        emoteButton1.setVisible(false);
        emoteButton2.setVisible(false);
        emoteButton3.setVisible(false);
        emoteButton4.setVisible(false);
        emoteButton5.setVisible(false);
        emoteButton6.setVisible(false);
        emoteButton7.setVisible(false);
        emoteButton8.setVisible(false);
        hideEmoteButton.setVisible(false);
        showEmoteButton.setVisible(false);
        customEmoteHbox.setVisible(true);
        hideCustomEmoteHboxButton.setVisible(true);
        showCustomEmoteHboxButton.setVisible(false);
    }

    public void sendCustomEmote(MouseEvent mouseEvent) {
        String text = emoteTextField.getText();
        TextEmote textEmote = new TextEmote(text);
        controller.sendTextEmote(textEmote, text);
    }

    public void hideCustomEmoteHbox(MouseEvent mouseEvent) {
        emoteButton1.setVisible(false);
        emoteButton2.setVisible(false);
        emoteButton3.setVisible(false);
        emoteButton4.setVisible(false);
        emoteButton5.setVisible(false);
        emoteButton6.setVisible(false);
        emoteButton7.setVisible(false);
        emoteButton8.setVisible(false);
        hideEmoteButton.setVisible(false);
        showEmoteButton.setVisible(true);
        customEmoteHbox.setVisible(false);
        hideCustomEmoteHboxButton.setVisible(false);
        showCustomEmoteHboxButton.setVisible(true);
    }

    private void lockScreen() {
        disableMouseEventsForHandCards();
        leaderAbility.setDisable(true);
        passRoundButton.setDisable(true);
        terminalButton.setDisable(true);
    }

    public void applyWeatherPicture(RowsInGame rowsInGame) {
        System.out.println(rowsInGame.toString());
        switch (rowsInGame) {
            case RowsInGame.currentPlayerCloseCombat -> {
                weatherRow3.setVisible(true);
            }
            case RowsInGame.opponentCloseCombat -> {
                weatherRow4.setVisible(true);
            }
            case RowsInGame.currentPlayerRanged -> {
                weatherRow2.setVisible(true);
            }
            case RowsInGame.opponentRanged -> {
                weatherRow5.setVisible(true);
            }
            case RowsInGame.currentPlayerSiege -> {
                weatherRow1.setVisible(true);
            }
            case RowsInGame.opponentSiege -> {
                weatherRow6.setVisible(true);
            }
        }
    }

    private void disableLockScreen() {
        addMouseEventsForHandCards();
        leaderAbility.setDisable(false);
        passRoundButton.setDisable(false);
        terminalButton.setDisable(false);
    }
}

