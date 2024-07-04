package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.App;
import com.example.model.card.*;
import com.example.model.card.cardsAbilities.DecoyAbility;
import com.example.model.card.enums.CardData;
import com.example.model.card.Card;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.RowsInGame;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
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

import java.util.concurrent.Flow;

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
    @FXML
    private FlowPane opponentPlayerDiscardPlace;
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
    private Table table;
    GameMenuController controller = (GameMenuController) Controller.GAME_MENU_CONTROLLER.getController();

    public void passRound(MouseEvent mouseEvent) {

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
        //currentPlayerSpecialCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getSpecialCardCounter()));
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
        //opponentPlayerSpecialCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getSpecialCardCounter()));
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

    private void handleChanges(ListChangeListener.Change<? extends Card> change, FlowPane fromPane, FlowPane toPane) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Card card : change.getAddedSubList()) {
//                    cardMoveAnimation(card, fromPane, toPane);
                }
            }
        }
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
        for (Card card : table.getOpponent().getBoard().getHand().getCards()) {
            allCardsInGame.add(new GameCardView(card));
        }
    }


    private void addCurrentPlayerHandCards(Table table) {
        for (GameCardView gameCardView : allCardsInGame) {
            if (table.getCurrentPlayer().getBoard().getHand().getCards().contains(gameCardView.getCard())) {
                currentPlayerHandObservableList.add(gameCardView);
            }
        }
        updateCurrentPlayerHand();
    }

    private void updateCurrentPlayerHand() {
        addMouseEventsForHandCards();
        currentPlayerHand.getChildren().addAll(currentPlayerHandObservableList);
    }

    private void addMouseEventsForHandCards() {
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
            });
        }
    }

    private void setOnMouseClickForShowCardDetails(Card card) {
        String imageAddress = Main.class.getResource("/images/cards/" + card.getCardData().getImageAddress()).toExternalForm();
        cardDetailShower.setImage(new Image(imageAddress));
    }

    private void disableMouseEventsForHandCard(int cardId) {
        GameCardView gameCardView = getGameCardViewWithCardId(cardId);
        gameCardView.setOnMouseClicked(null);
        gameCardView.setOnMouseEntered(null);
        gameCardView.setOnMouseExited(null);
        gameCardView.setOnMousePressed(null);
        gameCardView.setOnMouseReleased(null);
    }

    private GameCardView getGameCardViewWithCardId(int cardId) {
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
                currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                currentPlayerCloseCombat.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                    removeStyleClass();
                });
            }
            case RANGED -> {
                removeStyleClass();
                currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                currentPlayerRanged.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
                    removeStyleClass();
                });
            }
            case SIEGE -> {
                removeStyleClass();
                currentPlayerSiege.getStyleClass().add("highlighted-flow-pane");
                currentPlayerSiege.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
                    removeStyleClass();
                });
            }
            case AGILE -> {
                removeStyleClass();
                currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                currentPlayerCloseCombat.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                    removeStyleClass();
                });
                currentPlayerRanged.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
                    removeStyleClass();
                });
            }
            case WEATHER -> {
                removeStyleClass();
                weatherCardPlace.getStyleClass().add("highlighted-flow-pane");
                weatherCardPlace.setOnMouseClicked(e -> {
                    controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.weather.toString());
                    removeStyleClass();
                });
            }
            case SPECIAL -> {
                if (gameCardView.getCard().getAbilityName() == AbilityName.DECOY) {
                    removeStyleClass();
                    currentPlayerCloseCombat.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerRanged.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerSiege.getStyleClass().add("highlighted-flow-pane");
                    currentPlayerCloseCombat.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                        removeStyleClass();
                    });
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRanged.toString());
                        removeStyleClass();
                    });
                    currentPlayerSiege.setOnMouseClicked(e -> {
                        controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiege.toString());
                        removeStyleClass();
                    });
                } else {
                    if (currentPlayerCloseCombatSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerCloseCombatSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerCloseCombatSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerCloseCombatSpecialPlace.toString());
                            removeStyleClass();
                        });
                    }
                    if (currentPlayerRangedSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerRangedSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerRangedSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerRangedSpecialPlace.toString());
                            removeStyleClass();
                        });
                    }
                    if (currentPlayerSiegeSpecialPlace.getChildren().isEmpty()) {
                        currentPlayerSiegeSpecialPlace.getStyleClass().add("highlighted-flow-pane");
                        currentPlayerSiegeSpecialPlace.setOnMouseClicked(e -> {
                            controller.moveCardFromOriginToDestination(cardId, RowsInGame.currentPlayerHand.toString(), RowsInGame.currentPlayerSiegeSpecialPlace.toString());
                            removeStyleClass();
                        });
                    }
                }
            }
        }
    }

    private void removeStyleClass() {
        currentPlayerCloseCombat.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerRanged.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerSiege.getStyleClass().remove("highlighted-flow-pane");
        weatherCardPlace.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerCloseCombatSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerRangedSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        currentPlayerSiegeSpecialPlace.getStyleClass().remove("highlighted-flow-pane");
        cardDetailShower.setImage(null);
    }

    private ObservableList<GameCardView> getObservableListWithName(String listName) {
        switch (listName) {
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
            case "opponentSiegeObservableList" -> {
                return opponentSiegeObservableList;
            }
            case "opponentCloseCombatObservableList" -> {
                return opponentCloseCombatObservableList;
            }
            case "opponentRangedObservableList" -> {
                return opponentRangedObservableList;
            }
            case "opponentSiegeSpecialPlaceObservableList" -> {
                return opponentSiegeSpecialPlaceObservableList;
            }
            case "opponentCloseCombatSpecialPlaceObservableList" -> {
                return opponentCloseCombatSpecialPlaceObservableList;
            }
            case "opponentRangedSpecialPlaceObservableList" -> {
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
            case "currentPlayerHandObservableList" -> {
                return currentPlayerHand;
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
            case "opponentSiegeObservableList" -> {
                return opponentSiege;
            }
            case "opponentCloseCombatObservableList" -> {
                return opponentCloseCombat;
            }
            case "opponentRangedObservableList" -> {
                return opponentRanged;
            }
            case "opponentSiegeSpecialPlaceObservableList" -> {
                return opponentSiegeSpecialPlace;
            }
            case "opponentCloseCombatSpecialPlaceObservableList" -> {
                return opponentCloseCombatSpecialPlace;
            }
            case "opponentRangedSpecialPlaceObservableList" -> {
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
        FlowPane destinationFLowPane = getFlowPaneWithName(destinationObservableListName);
        FlowPane initialFlowPane = getFlowPaneWithName(initialObservableListName);
//        double startX = gameCardView.getLayoutX();
//        double startY = gameCardView.getLayoutY();
//        double endX = destinationFLowPane.getLayoutX() - initialFlowPane.getLayoutX();
//        double endY = destinationFLowPane.getLayoutY() - initialFlowPane.getLayoutY();
        destinationFLowPane.getChildren().add(gameCardView);
        initialFlowPane.getChildren().remove(gameCardView);
        disableMouseEventsForHandCard(cardId);
        destinationFLowPane.setOnMouseClicked(null);
    }

//    private static void cardMoveAnimation(GameCardView gameCardView, double startX, double startY, double endX, double endY, ObservableList<GameCardView> initialObservableList, FlowPane initialFlowPane, ObservableList<GameCardView> destinationObservableList, FlowPane destinationFLowPane) {
//        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), gameCardView);
//        transition.setFromX(startX);
//        transition.setFromY(startY);
//        transition.setToX(endX);
//        transition.setToY(endY);
//
//        transition.setOnFinished(event -> {
//            initialObservableList.remove(gameCardView);
//            initialFlowPane.getChildren().remove(gameCardView);
//
//            destinationObservableList.add(gameCardView);
//            destinationFLowPane.getChildren().add(gameCardView);
//
//            gameCardView.setTranslateX(0);
//            gameCardView.setTranslateY(0);
//        });
//
//        transition.play();
//    }

    private void nonLeaderCardsDoAbility(GameCardView gameCardView) {

    }

    public void toggleMusic(MouseEvent mouseEvent) {
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
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}

