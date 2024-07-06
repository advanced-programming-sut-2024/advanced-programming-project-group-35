package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.App;
import com.example.model.alerts.NotificationsData;
import com.example.model.card.*;
import com.example.model.card.Card;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.RowsInGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class GameMenuControllerView {
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
    GameMenuController controller = (GameMenuController) Controller.GAME_MENU_CONTROLLER.getController();

    public void passRound(MouseEvent mouseEvent) {
        controller.passRound();
    }

    private void setObservableLists() {

    }

    @FXML
    public void initialize() {
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
        table = controller.getTable();

        addGameCardViewsToAllCards();

        currentPlayerFactionName.setText(table.getCurrentPlayer().getBoard().getDeck().getFaction().toString());
        currentPlayerUsername.setText(table.getCurrentPlayer().getUsername());
        currentPlayerCloseCombatPower.setText("0");
        currentPlayerRangedPower.setText("0");
        currentPlayerSiegePower.setText("0");
        currentPlayerAllScoreCounter.setText(String.valueOf(table.getCurrentPlayer().getScore()));
        currentPlayerSpecialCardCounter.setText(String.valueOf(table.getCurrentPlayer().getSpecialCardCounter()));
        currentPlayerDeckCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getDeck().getSize()));
        //currentPlayerLeaderAbilityEnable.setVisible(table.getCurrentPlayer().getBoard().getLeader().isAbilityEnable());
        //currentPlayerLeaderCard.getChildren().add(new PreGameCard(table.getCurrentPlayer().getBoard().getLeader().getName(), table.getCurrentPlayer().getBoard().getLeader().getPower(), table.getCurrentPlayer().getBoard().getLeader().getAbility(), table.getCurrentPlayer().getBoard().getLeader().getImageAddress()));
        Image currentFactionIcon = new Image(FactionsType.getFactionDeckShieldImageAddress(table.getCurrentPlayer().getBoard().getDeck().getFaction()));
        currentPlayerFactionIcon.setImage(currentFactionIcon);
        currentPlayerFirstJem.setVisible(true);
        currentPlayerSecondJem.setVisible(true);
        currentPlayerExcellenceShower.setVisible(false);
        Image currentDeckPlace = new Image(FactionsType.getFactionBackDeckImageAddress(table.getCurrentPlayer().getBoard().getDeck().getFaction()));
        currentPlayerDeckPlace.setImage(currentDeckPlace);

        opponentPlayerFactionName.setText(table.getOpponent().getBoard().getDeck().getFaction().toString());
        opponentPlayerUsername.setText(table.getOpponent().getUsername());
        opponentPlayerCloseCombatPower.setText("0");
        opponentPlayerRangedPower.setText("0");
        opponentPlayerSiegePower.setText("0");
        opponentPlayerAllScoreCounter.setText(String.valueOf(table.getOpponent().getScore()));
        opponentPlayerSpecialCardCounter.setText(String.valueOf(table.getOpponent().getSpecialCardCounter()));
        opponentPlayerDeckCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getDeck().getSize()));
        //opponentPlayerLeaderAbilityEnable.setVisible(table.getOpponent().getBoard().getLeader().isAbilityEnable());
        //opponentPlayerLeaderCard.getChildren().add(new PreGameCard(table.getOpponent().getBoard().getLeader().getName(), table.getOpponent().getBoard().getLeader().getPower(), table.getOpponent().getBoard().getLeader().getAbility(), table.getOpponent().getBoard().getLeader().getImageAddress()));
        Image opponentFactionIcon = new Image(FactionsType.getFactionDeckShieldImageAddress(table.getOpponent().getBoard().getDeck().getFaction()));
        opponentPlayerFactionIcon.setImage(opponentFactionIcon);
        opponentPlayerFirstJem.setVisible(true);
        opponentPlayerSecondJem.setVisible(true);
        opponentPlayerExcellenceShower.setVisible(false);
        Image opponentDeckPlace = new Image(FactionsType.getFactionBackDeckImageAddress(table.getCurrentPlayer().getBoard().getDeck().getFaction()));
        opponentPlayerDeckPlace.setImage(opponentDeckPlace);

        if (!currentPlayerHand.getChildren().isEmpty()) {
            currentPlayerHand.getChildren().clear();
        }

        opponentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getOpponent().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
        currentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));

        addCurrentPlayerHandCards(table);

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
        switch (gameCardView.getCardData().getPlaceToBe()) {
            case CLOSE_COMBAT -> {
                removeStyleClass();
                if (gameCardView.getCard().getAbilityName() == AbilityName.SPY) {
                    opponentCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    opponentCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombat.toString());
                        removeStyleClass();
                    });
                } else {
                    currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                        removeStyleClass();
                    });
                }
            }
            case RANGED -> {
                removeStyleClass();
                if (gameCardView.getCard().getAbilityName() == AbilityName.SPY) {
                    opponentCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    opponentCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRanged.toString());
                        removeStyleClass();
                    });
                } else {
                    currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
                        removeStyleClass();
                    });
                }
            }
            case SIEGE -> {
                removeStyleClass();
                if (gameCardView.getCard().getAbilityName() == AbilityName.SPY) {
                    opponentSiege.getStyleClass().add("highlighted-flow-pane");
                    opponentSiege.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentSiege.toString());
                        removeStyleClass();
                    });
                } else {
                    currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
                        removeStyleClass();
                    });
                }
            }
            case AGILE -> {
                removeStyleClass();
                currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                if (gameCardView.getCard().getAbilityName() == AbilityName.SPY) {
                    opponentCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombat.toString());
                        removeStyleClass();
                        opponentRanged.setOnMouseClicked(null);
                    });
                    opponentRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRanged.toString());
                        removeStyleClass();
                        opponentCloseCombat.setOnMouseClicked(null);
                    });
                } else {
                    currentPlayerCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                        removeStyleClass();
                        currentPlayerRanged.setOnMouseClicked(null);
                    });
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
                        removeStyleClass();
                        currentPlayerCloseCombat.setOnMouseClicked(null);
                    });
                }
            }
            case WEATHER -> {
                removeStyleClass();
                weather.getStyleClass().add("highlighted-flow-pane");
                weather.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.weather.toString());
                    removeStyleClass();
                });
            }
            case SPECIAL -> {
                if (gameCardView.getCard().getAbilityName() == AbilityName.DECOY) {
                    removeStyleClass();
                    if (!currentPlayerCloseCombatObservableList.isEmpty()) {
                        currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                        addMouseEventForDecoyAbility(currentPlayerCloseCombatObservableList, gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerCloseCombat.toString());
                    }
                    if (!currentPlayerRangedObservableList.isEmpty()) {
                        currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                        addMouseEventForDecoyAbility(currentPlayerRangedObservableList, gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerRanged.toString());
                    }
                    if (!currentPlayerSiegeObservableList.isEmpty()) {
                        currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                        addMouseEventForDecoyAbility(currentPlayerSiegeObservableList, gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerSiege.toString());
                    }
                } else if (gameCardView.getCard().getAbilityName() == AbilityName.SCORCH) {
                    removeStyleClass();
                    if (currentPlayerCloseCombatSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerCloseCombatSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerCloseCombatSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombatSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (currentPlayerRangedSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerRangedSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerRangedSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRangedSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (currentPlayerSiegeSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerSiegeSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerSiegeSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiegeSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (opponentCloseCombatSpecialPlace.getChildren().isEmpty()) {
                        opponentCloseCombatSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        opponentCloseCombatSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombatSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (opponentRangedSpecialPlace.getChildren().isEmpty()) {
                        opponentRangedSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        opponentRangedSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRangedSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (opponentSiegeSpecialPlace.getChildren().isEmpty()) {
                        opponentSiegeSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        opponentSiegeSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentSiegeSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    opponentCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    opponentCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentCloseCombat.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    opponentRanged.getStyleClass().add("highlighted-flow-pane");
                    opponentRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentRanged.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    opponentSiege.getStyleClass().add("highlighted-flow-pane");
                    opponentSiege.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentSiege.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                } else {
                    removeStyleClass();
                    if (currentPlayerCloseCombatSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerCloseCombatSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerCloseCombatSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombatSpecialPlace.toString());
                            removeStyleClass();
                        });
                    }
                    if (currentPlayerRangedSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerRangedSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerRangedSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRangedSpecialPlace.toString());
                            removeStyleClass();
                        });
                    }
                    if (currentPlayerSiegeSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerSiegeSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerSiegeSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiegeSpecialPlace.toString());
                            removeStyleClass();
                        });
                    }
                }
            }
        }
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

    private void removeSetOnMouseClickedForAllFlowPanes() {
        for (ObservableList<GameCardView> list : allRowsObservableLists) {
            for (GameCardView gameCardView : list) {
                gameCardView.setOnMouseClicked(null);
            }
        }
        weather.setOnMouseClicked(null);
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
    }


    public void toggleMusic(MouseEvent mouseEvent) {
    }

    public void changeTurn() {
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event1 -> {
            App.getAppView().showNotification(NotificationsData.USERNAME_TURN.getMessage(), NotificationsData.USERNAME_TURN.getImageAddress(), table.getCurrentPlayer().getUsername());
        }));
        timeline1.setCycleCount(1);
        timeline1.play();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), event -> {
            swapContents(currentPlayerDeckObservableList, opponentDeckObservableList, currentPlayerDeck, opponentDeck);
            swapContents(currentPlayerHandObservableList, opponentHandObservableList, currentPlayerHand, opponentHand);
            swapContents(currentPlayerDiscardPlaceObservableList, opponentDiscardPlaceObservablePlace, currentPlayerDiscardPlace, opponentDiscardPlace);
            swapContents(currentPlayerSiegeObservableList, opponentSiegeObservableList, currentPlayerRanged, opponentSiege);
            swapContents(currentPlayerRangedObservableList, opponentRangedObservableList, currentPlayerRanged, opponentRanged);
            swapContents(currentPlayerCloseCombatObservableList, opponentCloseCombatObservableList, currentPlayerCloseCombat, opponentCloseCombat);
            swapContents(currentPlayerRangedSpecialPlaceObservableList, opponentRangedSpecialPlaceObservableList, currentPlayerRangedSpecialPlace, opponentRangedSpecialPlace);
            swapContents(currentPlayerCloseCombatObservableList, opponentCloseCombatObservableList, currentPlayerCloseCombatSpecialPlace, opponentCloseCombatSpecialPlace);
            swapContents(currentPlayerSiegeSpecialPlaceObservableList, opponentSiegeSpecialPlaceObservableList, currentPlayerSiegeSpecialPlace, opponentSiegeSpecialPlace);
            table.swapPlayers();
            addMouseEventsForHandCards();
            updateAllLabels();
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void swapContents(ObservableList<GameCardView> list1, ObservableList<GameCardView> list2, FlowPane pane1, FlowPane pane2) {
        swapObservableLists(list1, list2);
        swapFlowPanes(pane1, pane2);
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
                        controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
                    }
                } else {
                    controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
                }
            } else {
                if (gameCardView.getCard() instanceof UnitCard) {
                    if (!(((UnitCard) gameCardView.getCard()).noRemove())) {
                        controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.opponentDiscardPlace.toString());
                    }
                } else {
                    controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(gameCardView.getCard().getIdInGame(), sourceRow.toString(), RowsInGame.opponentDiscardPlace.toString());
                }
            }
        }
    }

    private int getDestByCardId(int cardId) {
        if ((cardId / 100) == table.getCurrentPlayer().getPriorityInGame()) return 1;
        else return 0;
    }

    private void moveCardsToDiscardPile(GameCardView gameCardView, String origin, String destination) {
        if (gameCardView.getCard() instanceof UnitCard) {
            UnitCard unitCard = (UnitCard) gameCardView.getCard();
            if (!unitCard.noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(unitCard.getIdInGame(), origin, destination);
            }
        } else {
            controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(gameCardView.getCard().getIdInGame(), origin, destination);
        }
    }

    public void setPowerOfCardDefault(int cardId) {
        for (GameCardView gameCardView : currentPlayerRangedObservableList) {
            if (gameCardView.getCard().getIdInGame() == cardId) {
                gameCardView.setPowerDefault();
            }
        }
        for (GameCardView gameCardView : currentPlayerCloseCombatObservableList) {
            if (gameCardView.getCard().getIdInGame() == cardId) {
                gameCardView.setPowerDefault();
            }
        }
        for (GameCardView gameCardView : currentPlayerSiegeObservableList) {
            if (gameCardView.getCard().getIdInGame() == cardId) {
                gameCardView.setPowerDefault();
            }
        }
        for (GameCardView gameCardView : opponentRangedObservableList) {
            if (gameCardView.getCard().getIdInGame() == cardId) {
                gameCardView.setPowerDefault();
            }
        }
        for (GameCardView gameCardView : opponentRangedObservableList) {
            if (gameCardView.getCard().getIdInGame() == cardId) {
                gameCardView.setPowerDefault();
            }
        }
        for (GameCardView gameCardView : opponentRangedObservableList) {
            if (gameCardView.getCard().getIdInGame() == cardId) {
                gameCardView.setPowerDefault();
            }
        }
    }

    public void setPowerOfCardsDefault() {
        setPowerDefaultForRow(currentPlayerRangedObservableList);
        setPowerDefaultForRow(currentPlayerCloseCombatObservableList);
        setPowerDefaultForRow(currentPlayerSiegeObservableList);
        setPowerDefaultForRow(opponentRangedObservableList);
        setPowerDefaultForRow(opponentCloseCombatObservableList);
        setPowerDefaultForRow(opponentSiegeObservableList);
    }

    private void setPowerDefaultForRow(ObservableList<GameCardView> rowObservableList) {
        synchronized (rowObservableList) {
            for (GameCardView gameCardView : rowObservableList) {
                gameCardView.setPowerDefault();
            }
        }
    }


    public void updateAllLabels() {
        opponentPlayerSiegePower.setText(String.valueOf(table.getOpponent().getBoard().getSiegeCardPlace().getStrength()));
        opponentPlayerRangedPower.setText(String.valueOf(table.getOpponent().getBoard().getRangedCardPlace().getStrength()));
        opponentPlayerCloseCombatPower.setText(String.valueOf(table.getOpponent().getBoard().getCloseCombatCardPlace().getStrength()));
        currentPlayerSiegePower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getSiegeCardPlace().getStrength()));
        currentPlayerRangedPower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getRangedCardPlace().getStrength()));
        currentPlayerCloseCombatPower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getCloseCombatCardPlace().getStrength()));
        currentPlayerAllScoreCounter.setText(String.valueOf(table.getCurrentPlayer().getScore()));
        opponentPlayerAllScoreCounter.setText(String.valueOf(table.getOpponent().getScore()));
        currentPlayerSpecialCardCounter.setText(String.valueOf(table.getCurrentPlayer().getSpecialCardCounter()));
        opponentPlayerSpecialCardCounter.setText(String.valueOf(table.getOpponent().getSpecialCardCounter()));
        opponentPlayerDeckCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getDeck().getSize()));
        currentPlayerDeckCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getDeck().getSize()));
        currentPlayerUsername.setText(table.getCurrentPlayer().getUsername());
        currentPlayerFactionName.setText(table.getCurrentPlayer().getBoard().getDeck().getFaction().toString());
        opponentPlayerUsername.setText(table.getOpponent().getUsername());
        opponentPlayerFactionName.setText(table.getOpponent().getBoard().getDeck().getFaction().toString());
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
        opponentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getOpponent().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
        currentPlayerLeaderCard.setImage(new Image(Main.class.getResource("/images/inGameCards/" + table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName().getImageAddress()).toExternalForm()));
        if (currentPlayerSiegeObservableList.size() > 9) {
            currentPlayerRanged.setHgap(0);
        } else {
            currentPlayerRanged.setHgap(5);
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
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }


    public void backWeatherCardToDiscardPlaces(WeatherCard weatherCard) {
        switch (weatherCard.getCardName()) {
            case CardData.weather_frost -> {
                setPowerDefaultForRow(currentPlayerCloseCombatObservableList);
                setPowerDefaultForRow(opponentCloseCombatObservableList);
                moveWeatherCardToDiscardPlace(weatherCard);
            }
            case CardData.weather_fog -> {
                setPowerDefaultForRow(currentPlayerRangedObservableList);
                setPowerDefaultForRow(opponentRangedObservableList);
                moveWeatherCardToDiscardPlace(weatherCard);}
            case CardData.weather_rain -> {
                setPowerDefaultForRow(currentPlayerSiegeObservableList);
                setPowerDefaultForRow(opponentSiegeObservableList);
                moveWeatherCardToDiscardPlace(weatherCard);
            }
            case CardData.weather_storm -> {
                setPowerDefaultForRow(currentPlayerCloseCombatObservableList);
                setPowerDefaultForRow(opponentCloseCombatObservableList);
                setPowerDefaultForRow(currentPlayerRangedObservableList);
                setPowerDefaultForRow(opponentRangedObservableList);
                moveWeatherCardToDiscardPlace(weatherCard);
            }
            case CardData.weather_clear -> {
               moveWeatherCardToDiscardPlace(weatherCard);
            }
        }
    }
    private void moveWeatherCardToDiscardPlace(WeatherCard weatherCard) {
        if (weatherCard.getCardData() != CardData.weather_clear && weatherCard.getPlayer().getPriorityInGame() == table.getCurrentPlayer().getPriorityInGame()) {
            controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(weatherCard.getIdInGame(), RowsInGame.weather.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
        } else {
            controller.moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(weatherCard.getIdInGame(), RowsInGame.weather.toString(), RowsInGame.opponentDiscardPlace.toString());
        }
    }

    public void disApplyWeatherCards() {
        ObservableList<WeatherCard> weatherCardsCopy = FXCollections.observableArrayList(table.getSpellPlace().getCards());
        for (WeatherCard weatherCard : weatherCardsCopy) {
            backWeatherCardToDiscardPlaces(weatherCard);
        }
    }
}

