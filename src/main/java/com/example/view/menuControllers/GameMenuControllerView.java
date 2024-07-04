package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.App;
import com.example.model.alerts.NotificationsData;
import com.example.model.card.*;
import com.example.model.card.Card;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.RowsInGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

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
    public VBox opponentPlayerLeaderCard;
    public VBox currentPlayerLeaderCard;
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
    private FlowPane currentPlayerSiege;
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
    private FlowPane weatherCardPlace;
    private FlowPane opponentPlayerDeck = new FlowPane();
    @FXML
    private FlowPane opponentPlayerDiscardPlace;
    private FlowPane opponentPlayerHand = new FlowPane();
    private ObservableList<GameCardView> allCardsInGame = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerDeckObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerHandObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerSiegeObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerRangedObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerCloseCombatObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentCloseCombatObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentRangedObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> weatherObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentPlayerHandObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentPlayerDeckObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerCloseCombatSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerRangedSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerSiegeSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentSiegeObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentSiegeSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentCloseCombatSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentRangedSpecialPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerDiscardPlaceObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentPlayerDiscardPlaceObservablePlace = FXCollections.observableArrayList();

    private Table table;
    GameMenuController controller = (GameMenuController) Controller.GAME_MENU_CONTROLLER.getController();

    public void passRound(MouseEvent mouseEvent) {
        controller.passRound();
    }

    private void setObservableLists() {

    }

    @FXML
    public void initialize() {
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

        addCurrentPlayerHandCards(table);

    }

//    private void handleChanges(ListChangeListener.Change<? extends Card> change, FlowPane fromPane, FlowPane toPane) {
//        while (change.next()) {
//            if (change.wasAdded()) {
//                for (Card card : change.getAddedSubList()) {
////                    cardMoveAnimation(card, fromPane, toPane);
//                }
//            }
//        }
//    }

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
                opponentPlayerHandObservableList.add(gameCardView);
            }
        }
        updateCurrentPlayerHand();
    }

    private void updateCurrentPlayerHand() {
        addMouseEventsForHandCards();
        currentPlayerHand.getChildren().addAll(currentPlayerHandObservableList);
        opponentPlayerHand.getChildren().addAll(opponentPlayerHandObservableList);
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
                    removeEventForDecoyAbility(currentPlayerSiegeObservableList);
                    removeEventForDecoyAbility(currentPlayerRangedObservableList);
                    removeEventForDecoyAbility(currentPlayerCloseCombatObservableList);
                    removeEventForDecoyAbility(currentPlayerSiegeSpecialPlaceObservableList);
                    removeEventForDecoyAbility(currentPlayerRangedSpecialPlaceObservableList);
                    removeEventForDecoyAbility(currentPlayerCloseCombatSpecialPlaceObservableList);
                    removeEventForDecoyAbility(opponentSiegeObservableList);
                    removeEventForDecoyAbility(opponentRangedObservableList);
                    removeEventForDecoyAbility(opponentCloseCombatObservableList);
                    removeEventForDecoyAbility(opponentSiegeSpecialPlaceObservableList);
                    removeEventForDecoyAbility(opponentRangedSpecialPlaceObservableList);
                    removeEventForDecoyAbility(opponentCloseCombatSpecialPlaceObservableList);
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
                removeEventForDecoyAbility(currentPlayerSiegeObservableList);
                removeEventForDecoyAbility(currentPlayerRangedObservableList);
                removeEventForDecoyAbility(currentPlayerCloseCombatObservableList);
                removeEventForDecoyAbility(currentPlayerSiegeSpecialPlaceObservableList);
                removeEventForDecoyAbility(currentPlayerRangedSpecialPlaceObservableList);
                removeEventForDecoyAbility(currentPlayerCloseCombatSpecialPlaceObservableList);
                removeEventForDecoyAbility(opponentSiegeObservableList);
                removeEventForDecoyAbility(opponentRangedObservableList);
                removeEventForDecoyAbility(opponentCloseCombatObservableList);
                removeEventForDecoyAbility(opponentSiegeSpecialPlaceObservableList);
                removeEventForDecoyAbility(opponentRangedSpecialPlaceObservableList);
                removeEventForDecoyAbility(opponentCloseCombatSpecialPlaceObservableList);
            });
        }
    }

    public void removeEventForDecoyAbility(ObservableList<GameCardView> gameCardViews) {
        for (GameCardView gameCardView : gameCardViews) {
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
        switch (gameCardView.getCardData().getPlaceToBe()) {
            case CLOSE_COMBAT -> {
                removeStyleClass();
                if (gameCardView.getCard().getAbilityName() == AbilityName.SPY) {
                    opponentCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    opponentCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerCloseCombat.toString());
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
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerRanged.toString());
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
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerSiege.toString());
                        removeStyleClass();
                    });
                } else {
                    currentPlayerSiege.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerSiege.setOnMouseClicked(e -> {
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
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerCloseCombat.toString());
                        removeStyleClass();
                        opponentRanged.setOnMouseClicked(null);
                    });
                    opponentRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerRanged.toString());
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
                weatherCardPlace.getStyleClass().add("highlighted-flow-pane");
                weatherCardPlace.setOnMouseClicked(e -> {
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
                        currentPlayerSiege.getStyleClass().add("highlighted-flow-pane");
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
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerCloseCombatSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (opponentRangedSpecialPlace.getChildren().isEmpty()) {
                        opponentRangedSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        opponentRangedSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerRangedSpecialPlace.toString());
                            removeStyleClass();
                            removeSetOnMouseClickedForAllFlowPanes();
                        });
                    }
                    if (opponentSiegeSpecialPlace.getChildren().isEmpty()) {
                        opponentSiegeSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        opponentSiegeSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerSiegeSpecialPlace.toString());
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
                    currentPlayerSiege.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerSiege.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    opponentCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    opponentCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerCloseCombat.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    opponentRanged.getStyleClass().add("highlighted-flow-pane");
                    opponentRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerRanged.toString());
                        removeStyleClass();
                        removeSetOnMouseClickedForAllFlowPanes();
                    });
                    opponentSiege.getStyleClass().add("highlighted-flow-pane");
                    opponentSiege.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.opponentPlayerSiege.toString());
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
        weatherCardPlace.getStyleClass().remove("highlighted-flow-pane");
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
        currentPlayerCloseCombat.setOnMouseClicked(null);
        currentPlayerRanged.setOnMouseClicked(null);
        currentPlayerSiege.setOnMouseClicked(null);
        weatherCardPlace.setOnMouseClicked(null);
        currentPlayerCloseCombatSpecialPlace.setOnMouseClicked(null);
        currentPlayerRangedSpecialPlace.setOnMouseClicked(null);
        currentPlayerSiegeSpecialPlace.setOnMouseClicked(null);
        opponentCloseCombat.setOnMouseClicked(null);
        opponentRanged.setOnMouseClicked(null);
        opponentSiege.setOnMouseClicked(null);
        opponentRangedSpecialPlace.setOnMouseClicked(null);
        opponentSiegeSpecialPlace.setOnMouseClicked(null);
        opponentCloseCombatSpecialPlace.setOnMouseClicked(null);
    }

    private ObservableList<GameCardView> getObservableListWithName(String listName) {
        switch (listName) {
            case "currentPlayerDiscardPlace" -> {
                return currentPlayerDiscardPlaceObservableList;
            }
            case "currentPlayerDeckObservableList" -> {
                return currentPlayerDeckObservableList;
            }
            case "opponentPlayerDeckObservableList" -> {
                return opponentPlayerDeckObservableList;
            }
            case "currentPlayerHandObservableList" -> {
                return currentPlayerHandObservableList;
            }
            case "opponentPlayerHandObservableList" -> {
                return opponentPlayerHandObservableList;
            }
            case "currentPlayerSiegeObservableList" -> {
                return currentPlayerSiegeObservableList;
            }
            case "currentPlayerRangedObservableList" -> {
                return currentPlayerRangedObservableList;
            }
            case "currentPlayerCloseCombatObservableList" -> {
                return currentPlayerCloseCombatObservableList;
            }
            case "currentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return currentPlayerCloseCombatSpecialPlaceObservableList;
            }
            case "currentPlayerRangedSpecialPlaceObservableList" -> {
                return currentPlayerRangedSpecialPlaceObservableList;
            }
            case "currentPlayerSiegeSpecialPlaceObservableList" -> {
                return currentPlayerSiegeSpecialPlaceObservableList;
            }
            case "opponentPlayerSiegeObservableList" -> {
                return opponentSiegeObservableList;
            }
            case "opponentPlayerCloseCombatObservableList" -> {
                return opponentCloseCombatObservableList;
            }
            case "opponentPlayerRangedObservableList" -> {
                return opponentRangedObservableList;
            }
            case "opponentPlayerSiegeSpecialPlaceObservableList" -> {
                return opponentSiegeSpecialPlaceObservableList;
            }
            case "opponentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return opponentCloseCombatSpecialPlaceObservableList;
            }
            case "opponentPlayerRangedSpecialPlaceObservableList" -> {
                return opponentRangedSpecialPlaceObservableList;
            }
            case "weatherObservableList" -> {
                return weatherObservableList;
            }
            default -> {
                return null;
            }
        }
    }

    private FlowPane getFlowPaneWithName(String listName) {
        switch (listName) {
            case "currentPlayerDiscardPlace" -> {
                return currentPlayerDiscardPlace;
            }
            case "currentPlayerHandObservableList" -> {
                return currentPlayerHand;
            }
            case "currentPlayerDeckObservableList" -> {
                return currentPlayerDeck;
            }
            case "currentPlayerSiegeObservableList" -> {
                return currentPlayerSiege;
            }
            case "currentPlayerRangedObservableList" -> {
                return currentPlayerRanged;
            }
            case "currentPlayerCloseCombatObservableList" -> {
                return currentPlayerCloseCombat;
            }
            case "currentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return currentPlayerCloseCombatSpecialPlace;
            }
            case "currentPlayerRangedSpecialPlaceObservableList" -> {
                return currentPlayerRangedSpecialPlace;
            }
            case "currentPlayerSiegeSpecialPlaceObservableList" -> {
                return currentPlayerSiegeSpecialPlace;
            }
            case "opponentPlayerSiegeObservableList" -> {
                return opponentSiege;
            }
            case "opponentPlayerCloseCombatObservableList" -> {
                return opponentCloseCombat;
            }
            case "opponentPlayerRangedObservableList" -> {
                return opponentRanged;
            }
            case "opponentPlayerSiegeSpecialPlaceObservableList" -> {
                return opponentSiegeSpecialPlace;
            }
            case "opponentPlayerCloseCombatSpecialPlaceObservableList" -> {
                return opponentCloseCombatSpecialPlace;
            }
            case "opponentPlayerRangedSpecialPlaceObservableList" -> {
                return opponentRangedSpecialPlace;
            }
            case "weatherObservableList" -> {
                return weatherCardPlace;
            }
            default -> {
                return null;
            }
        }
    }

    public void moveCardToDestinationFlowPane(int cardId, String initialObservableListName, String destinationObservableListName) {
        GameCardView gameCardView = getGameCardViewWithCardId(cardId);

        ObservableList<GameCardView> initialObservableList = getObservableListWithName(initialObservableListName);
        ObservableList<GameCardView> destinationObservableList = getObservableListWithName(destinationObservableListName);
        initialObservableList.remove(gameCardView);
        destinationObservableList.add(gameCardView);

        FlowPane destinationFLowPane = getFlowPaneWithName(destinationObservableListName);
        FlowPane initialFlowPane = getFlowPaneWithName(initialObservableListName);

        destinationFLowPane.getChildren().add(gameCardView);
        initialFlowPane.getChildren().remove(gameCardView);
        disableMouseEventsForHandCard(cardId);
        destinationFLowPane.setOnMouseClicked(null);
        updateAllLabels();
    }

    private static void cardMoveAnimation(GameCardView gameCardView, double startX, double startY, double endX, double endY, FlowPane initialFlowPane, FlowPane destinationFLowPane) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), gameCardView);
        transition.setFromX(startX);
        transition.setFromY(startY);
        transition.setToX(endX);
        transition.setToY(endY);

        transition.setOnFinished(event -> {
            initialFlowPane.getChildren().remove(gameCardView);

            destinationFLowPane.getChildren().add(gameCardView);

            gameCardView.setTranslateX(0);
            gameCardView.setTranslateY(0);
        });

        transition.play();
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
            swapContents(currentPlayerDeckObservableList, opponentPlayerDeckObservableList, currentPlayerDeck, opponentPlayerDeck);
            swapContents(currentPlayerHandObservableList, opponentPlayerHandObservableList, currentPlayerHand, opponentPlayerHand);
            swapContents(currentPlayerDiscardPlaceObservableList, opponentPlayerDiscardPlaceObservablePlace, currentPlayerDiscardPlace, opponentPlayerDiscardPlace);
            swapContents(currentPlayerSiegeObservableList, opponentSiegeObservableList, currentPlayerSiege, opponentSiege);
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

    public void backCardsToDiscardPiles() {
        synchronized (currentPlayerRangedObservableList) {
            ObservableList<GameCardView> currentPlayerRangedCopy = FXCollections.observableArrayList(currentPlayerRangedObservableList);
            for (GameCardView gameCardView : currentPlayerRangedCopy) {
                System.out.println("1");
                if (gameCardView.getCard() instanceof UnitCard) {
                    System.out.println(((UnitCard) gameCardView.getCard()).noRemove());
                    controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerRanged.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
                }
            }
        }

        synchronized (currentPlayerSiegeObservableList) {
            ObservableList<GameCardView> currentPlayerSiegeCopy = FXCollections.observableArrayList(currentPlayerSiegeObservableList);
            for (GameCardView gameCardView : currentPlayerSiegeCopy) {
                if (gameCardView.getCard() instanceof UnitCard) {
                    System.out.println(((UnitCard) gameCardView.getCard()).noRemove());
                    controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerSiege.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
                }
            }
        }

        ObservableList<GameCardView> currentPlayerCloseCombatCopy = FXCollections.observableArrayList(currentPlayerCloseCombatObservableList);
        for (GameCardView gameCardView : currentPlayerCloseCombatCopy) {
            System.out.println("3");
            if (gameCardView.getCard() instanceof UnitCard) {
                System.out.println(((UnitCard) gameCardView.getCard()).noRemove());
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerCloseCombat.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> currentPlayerRangedSpecialPlaceCopy = FXCollections.observableArrayList(currentPlayerRangedSpecialPlaceObservableList);
        for (GameCardView gameCardView : currentPlayerRangedSpecialPlaceCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerRangedSpecialPlace.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> currentPlayerSiegeSpecialPlaceCopy = FXCollections.observableArrayList(currentPlayerSiegeSpecialPlaceObservableList);
        for (GameCardView gameCardView : currentPlayerSiegeSpecialPlaceCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerSiegeSpecialPlace.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> currentPlayerCloseCombatSpecialPlaceCopy = FXCollections.observableArrayList(currentPlayerCloseCombatSpecialPlaceObservableList);
        for (GameCardView gameCardView : currentPlayerCloseCombatSpecialPlaceCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerCloseCombatSpecialPlace.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> opponentRangedCopy = FXCollections.observableArrayList(opponentRangedObservableList);
        for (GameCardView gameCardView : opponentRangedCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.currentPlayerRanged.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> opponentSiegeCopy = FXCollections.observableArrayList(opponentSiegeObservableList);
        for (GameCardView gameCardView : opponentSiegeCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.opponentPlayerSiege.toString(), RowsInGame.opponentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> opponentCloseCombatCopy = FXCollections.observableArrayList(opponentCloseCombatObservableList);
        for (GameCardView gameCardView : opponentCloseCombatCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.opponentPlayerCloseCombat.toString(), RowsInGame.opponentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> opponentRangedSpecialPlaceCopy = FXCollections.observableArrayList(opponentRangedSpecialPlaceObservableList);
        for (GameCardView gameCardView : opponentRangedSpecialPlaceCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.opponentPlayerRangedSpecialPlace.toString(), RowsInGame.opponentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> opponentSiegeSpecialPlaceCopy = FXCollections.observableArrayList(opponentSiegeSpecialPlaceObservableList);
        for (GameCardView gameCardView : opponentSiegeSpecialPlaceCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.opponentPlayerSiegeSpecialPlace.toString(), RowsInGame.opponentPlayerDiscardPlace.toString());
            }
        }

        ObservableList<GameCardView> opponentCloseCombatSpecialPlaceCopy = FXCollections.observableArrayList(opponentCloseCombatSpecialPlaceObservableList);
        for (GameCardView gameCardView : opponentCloseCombatSpecialPlaceCopy) {
            if (gameCardView.getCard() instanceof UnitCard && ((UnitCard) gameCardView.getCard()).noRemove()) {
                controller.moveCardFromOriginToDestinationAndDontDoAbility(gameCardView.getCard().getIdInGame(), RowsInGame.opponentPlayerCloseCombatSpecialPlace.toString(), RowsInGame.opponentPlayerDiscardPlace.toString());
            }
        }
    }

    public void setPowerOfCardsDefault() {
        for (GameCardView gameCardView : currentPlayerRangedObservableList) {
            gameCardView.setPowerDefault();
        }
        for (GameCardView gameCardView : currentPlayerCloseCombatObservableList) {
            gameCardView.setPowerDefault();
        }
        for (GameCardView gameCardView : currentPlayerSiegeObservableList) {
            gameCardView.setPowerDefault();
        }
        for (GameCardView gameCardView : opponentRangedObservableList) {
            gameCardView.setPowerDefault();
        }
        for (GameCardView gameCardView : opponentRangedObservableList) {
            gameCardView.setPowerDefault();
        }
        for (GameCardView gameCardView : opponentRangedObservableList) {
            gameCardView.setPowerDefault();
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
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}

