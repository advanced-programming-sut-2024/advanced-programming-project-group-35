package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.*;
import com.example.model.card.cardsAbilities.DecoyAbility;
import com.example.model.card.enums.CardData;
import com.example.model.card.Card;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Table;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
    public ImageView opponentPlayerDeckPlace;
    public VBox opponentPlayerDiscardPlace;
    public ImageView currentPlayerDeckPlace;
    public VBox currentPlayerDiscardPlace;
    public VBox opponentPlayerLeaderCard;
    public VBox currentPlayerLeaderCard;
    public Label opponentPlayerDeckCardCounter;
    public Label currentPlayerDeckCardCounter;
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
    public FlowPane weatherCardPlace;
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
    private ObservableList<Card> playerDeck = FXCollections.observableArrayList();
    private Table table;
    GameMenuController controller = (GameMenuController) Controller.GAME_MENU_CONTROLLER.getController();
    public void passRound(MouseEvent mouseEvent) {
    }

    @FXML
    public void initialize() {
        table = controller.getTable();
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
        //playerDeck.clear();
        //addToCurrentPlayerHand();
        //addOnMouseClickedEventToCards();
        //currentPlayerHand.getChildren().addAll((Node) playerDeck);

//        if (!currentPlayerHand.getChildren().isEmpty()) {
//            currentPlayerHand.getChildren().clear();
//        }
//        playerDeck.clear();
//        addToCurrentPlayerHand();
//        addOnMouseClickedEventToLeaderCards();
//        currentPlayerHand.getChildren().addAll(playerDeck);
        addCurrentPlayerHandCards(table);

    }
//    private void addToCurrentPlayerHand() {
//        for (int i = 0; i < table.getCurrentPlayer().getBoard().getDeck().getSize(); i++) {
//            playerDeck.add(table.getCurrentPlayer().getBoard().getDeck().getCard(i));
//        }
//    }
//    private void addOnMouseClickedEventToCards() {
//        for (Card card : playerDeck) {
//            card.setOnMouseClicked(event -> {
//                leaderCard = card;
//            });
//        }
//    }

    private void addCurrentPlayerHandCards(Table table) {
        for (Card card : table.getCurrentPlayer().getBoard().getHand().getCards()) {
            currentPlayerHandObservableList.add(new GameCardView(card));
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
                //TODO
            });
            gameCardView.setOnMouseExited(e -> {
                //TODO
            });
            gameCardView.setOnMouseClicked(e -> {
                setOnMouseClickForDestinationFlowPane(gameCardView);
            });
        }
    }

    private void setOnMouseClickForDestinationFlowPane(GameCardView gameCardView) {
        switch (gameCardView.getCardData().getPlaceToBe()) {
            //TODO زرد شدن مقصد
            case CLOSE_COMBAT -> {
                currentPlayerCloseCombat.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerCloseCombatObservableList, currentPlayerHand, currentPlayerCloseCombat);

                });
            }
            case RANGED -> {
                currentPlayerRanged.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerRangedObservableList, currentPlayerHand, currentPlayerRanged);
                });
            }
            case SIEGE -> {
                currentPlayerSiege.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerSiegeObservableList, currentPlayerHand, currentPlayerSiege);
                });
            }
            case AGILE -> {
                currentPlayerCloseCombat.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerCloseCombatObservableList, currentPlayerHand, currentPlayerCloseCombat);
                });
                currentPlayerRanged.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerRangedObservableList, currentPlayerHand, currentPlayerRanged);
                });
            }
            case WEATHER -> {
                weatherCardPlace.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, weatherObservableList, currentPlayerHand, weatherCardPlace);
                });
            }
            case SPECIAL -> {
                if (gameCardView.getCard().getAbility() instanceof DecoyAbility) {
                    currentPlayerCloseCombat.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerCloseCombatObservableList, currentPlayerHand, currentPlayerCloseCombat);
                    });
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerRangedObservableList, currentPlayerHand, currentPlayerRanged);
                    });
                    currentPlayerSiege.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, currentPlayerSiegeObservableList, currentPlayerHand, currentPlayerSiege);
                    });
                } else {
                    //TODO اول چک بشه که محل کارت خاص خالیه یا نه
                    currentPlayerCloseCombatSpecialPlace.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, null, currentPlayerHand, currentPlayerCloseCombatSpecialPlace);
                    });
                    currentPlayerRangedSpecialPlace.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, null, currentPlayerHand, currentPlayerRangedSpecialPlace);
                    });
                    currentPlayerSiegeSpecialPlace.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(gameCardView, currentPlayerHandObservableList, null, currentPlayerHand, currentPlayerSiegeSpecialPlace);
                    });
                }
            }
        }
    }

    private void moveCardToDestinationFlowPane(GameCardView gameCardView, ObservableList<GameCardView> initialObservableList, ObservableList<GameCardView> destinationObservableList, FlowPane initialFlowPane, FlowPane destinationFLowPane) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), gameCardView);
//        initialObservableList.remove(gameCardView);
//        destinationObservableList.add(gameCardView);
        transition.setToX(destinationFLowPane.getLayoutX());
        transition.setToY(destinationFLowPane.getLayoutY());
        transition.setOnFinished(event -> {
            initialFlowPane.getChildren().remove(gameCardView);
            destinationFLowPane.getChildren().addAll(gameCardView);
            gameCardView.setTranslateX(0);
            gameCardView.setTranslateY(0);
            nonLeaderCardsDoAbility(gameCardView);
        });
        transition.play();
        destinationFLowPane.setOnMouseClicked(null);
        gameCardView.setOnMouseClicked(null);
        gameCardView.setOnMouseEntered(null);
        gameCardView.setOnMouseExited(null);
        gameCardView.setOnMousePressed(null);
        gameCardView.setOnMouseReleased(null);
    }

    private void nonLeaderCardsDoAbility(GameCardView gameCardView) {

    }

    private ObservableList<GameCardView> currentPlayerHandObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerSiegeObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerRangedObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> currentPlayerCloseCombatObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentCloseCombatObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> opponentRangedObservableList = FXCollections.observableArrayList();
    private ObservableList<GameCardView> weatherObservableList = FXCollections.observableArrayList();
    public void toggleMusic(MouseEvent mouseEvent) {
    }
}

