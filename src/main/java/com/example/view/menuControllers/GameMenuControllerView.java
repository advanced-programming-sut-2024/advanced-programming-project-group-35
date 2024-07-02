package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.*;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Table;
import com.example.model.game.place.RowsInGame;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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


    private Table table;
    GameMenuController controller = (GameMenuController) Controller.GAME_MENU_CONTROLLER.getController();

    public void passRound(MouseEvent mouseEvent) {

    }

    @FXML
    public void initialize() {
        table = controller.getTable();

        addGameCardViewsToAllCards();


        currentPlayerFactionName.setText(table.getCurrentPlayer().getBoard().getDeck().getFaction().toString());
        currentPlayerUsername.setText(table.getCurrentPlayer().getUsername());
        //currentPlayerCloseCombatPower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getCloseCombatPower()));
        //currentPlayerRangedPower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getRangedPower()));
        //currentPlayerSiegePower.setText(String.valueOf(table.getCurrentPlayer().getBoard().getSiegePower()));
        currentPlayerAllScoreCounter.setText(String.valueOf(table.getCurrentPlayer().getScore()));
        //currentPlayerSpecialCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getSpecialCardCounter()));
        currentPlayerDeckCardCounter.setText(String.valueOf(table.getCurrentPlayer().getBoard().getDeck().getSize()));
        //currentPlayerLeaderAbilityEnable.setVisible(table.getCurrentPlayer().getBoard().getLeader().isAbilityEnable());
        //currentPlayerLeaderCard.getChildren().add(new PreGameCard(table.getCurrentPlayer().getBoard().getLeader().getName(), table.getCurrentPlayer().getBoard().getLeader().getPower(), table.getCurrentPlayer().getBoard().getLeader().getAbility(), table.getCurrentPlayer().getBoard().getLeader().getImageAddress()));
        //currentPlayerFactionIcon.setImage(table.getCurrentPlayer().getFactionIcon());
        currentPlayerFirstJem.setVisible(true);
        currentPlayerSecondJem.setVisible(true);
        currentPlayerExcellenceShower.setVisible(false);
        //currentPlayerProfilePic.setImage(table.getCurrentPlayer().getProfilePic());
        //currentPlayerProfilePicBorder.setImage(table.getCurrentPlayer().getProfilePicBorder());

        opponentPlayerFactionName.setText(table.getOpponent().getBoard().getDeck().getFaction().toString());
        opponentPlayerUsername.setText(table.getOpponent().getUsername());
        //opponentPlayerCloseCombatPower.setText(String.valueOf(table.getOpponent().getBoard().getCloseCombatPower()));
        //opponentPlayerRangedPower.setText(String.valueOf(table.getOpponent().getBoard().getRangedPower()));
        //opponentPlayerSiegePower.setText(String.valueOf(table.getOpponent().getBoard().getSiegePower()));
        opponentPlayerAllScoreCounter.setText(String.valueOf(table.getOpponent().getScore()));
        //opponentPlayerSpecialCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getSpecialCardCounter()));
        opponentPlayerDeckCardCounter.setText(String.valueOf(table.getOpponent().getBoard().getDeck().getSize()));
        //opponentPlayerLeaderAbilityEnable.setVisible(table.getOpponent().getBoard().getLeader().isAbilityEnable());
        //opponentPlayerLeaderCard.getChildren().add(new PreGameCard(table.getOpponent().getBoard().getLeader().getName(), table.getOpponent().getBoard().getLeader().getPower(), table.getOpponent().getBoard().getLeader().getAbility(), table.getOpponent().getBoard().getLeader().getImageAddress()));
        //opponentPlayerFactionIcon.setImage(table.getOpponent().getFactionIcon());
        opponentPlayerFirstJem.setVisible(true);
        opponentPlayerSecondJem.setVisible(true);
        opponentPlayerExcellenceShower.setVisible(false);
        //opponentPlayerProfilePic.setImage(table.getOpponent().getProfilePic());
        //opponentPlayerProfilePicBorder.setImage(table.getOpponent().getProfilePicBorder());


//        if (!currentPlayerHand.getChildren().isEmpty()) {
//            currentPlayerHand.getChildren().clear();
//        }
//        playerDeck.clear();
//        addToCurrentPlayerHand();
//        addOnMouseClickedEventToLeaderCards();
//        currentPlayerHand.getChildren().addAll(playerDeck);
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
        for (Card card : table.getOpponent().getBoard().getHand().getCards()) {
            allCardsInGame.add(new GameCardView(card));
        }
    }

//    private void addToCurrentPlayerHand() {
//        for (CardData cardData : CardData.values()) {
//            if (cardData.getType().equals("leader") && cardData.getFaction().equals(faction.getFaction())) {
//                leaderCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
//            }
//        }
//    }
//    private void addOnMouseClickedEventToLeaderCards() {
//        for (PreGameCard card : leaderCards) {
//            card.setOnMouseClicked(event -> {
//                leaderCard = card;
//                backToPreGameMenu(null);
//                updateDeckInfo();
//            });
//        }
//    }

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
                //TODO
            });
            gameCardView.setOnMouseExited(e -> {
                //TODO
            });
            gameCardView.setOnMouseClicked(e -> {
                setOnMouseClickForDestinationFlowPane(gameCardView.getCard().getIdInGame());
            });
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

    private GameCardView getGameCardViewWithCardId(int cardId) {
        for (GameCardView cardView : allCardsInGame) {
            if (cardView.getCard().getIdInGame() == cardId) return cardView;
        }
        return null;
    }

    private void setOnMouseClickForDestinationFlowPane(int cardId) {
        GameCardView gameCardView = getGameCardViewWithCardId(cardId);
        switch (gameCardView.getCardData().getPlaceToBe()) {
            //TODO زرد شدن مقصد
            case CLOSE_COMBAT -> {
                currentPlayerCloseCombat.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerCloseCombatObservableList.toString());
                });
            }
            case RANGED -> {
                currentPlayerRanged.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerRangedObservableList.toString());
                });
            }
            case SIEGE -> {
                currentPlayerSiege.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerSiegeObservableList.toString());
                });
            }
            case AGILE -> {
                currentPlayerCloseCombat.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerCloseCombatObservableList.toString());
                });
                currentPlayerRanged.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerRangedObservableList.toString());
                });
            }
            case WEATHER -> {
                weatherCardPlace.setOnMouseClicked(e -> {
                    moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.weatherObservableList.toString());
                });
            }
            case SPECIAL -> {
                if (gameCardView.getCard().getAbilityName() == AbilityName.DECOY) {
                    currentPlayerCloseCombat.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerCloseCombatObservableList.toString());
                    });
                    currentPlayerRanged.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerRangedObservableList.toString());
                    });
                    currentPlayerSiege.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerSiegeObservableList.toString());
                    });
                } else {
                    //TODO اول چک بشه که محل کارت خاص خالیه یا نه
                    currentPlayerCloseCombatSpecialPlace.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerCloseCombatSpecialPlace.toString());
                    });
                    currentPlayerRangedSpecialPlace.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerRangedSpecialPlace.toString());
                    });
                    currentPlayerSiegeSpecialPlace.setOnMouseClicked(e -> {
                        moveCardToDestinationFlowPane(cardId, RowsInGame.currentPlayerHandObservableList.toString(), RowsInGame.currentPlayerSiegeSpecialPlace.toString());
                    });
                }
            }
        }
    }

    private ObservableList<GameCardView> getObservableListWithName(String listName) {
        switch (listName) {
            case "currentPlayerDeck" -> {
                return currentPlayerDeckObservableList;
            }
            case "currentPlayerHandObservableList" -> {
                return currentPlayerHandObservableList;
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
            case "opponentCloseCombatObservableList" -> {
                return opponentCloseCombatObservableList;
            }
            case "opponentRangedObservableList" -> {
                return opponentRangedObservableList;
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
            case "currentPlayerDeck" -> {
                return null;
            }
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
            case "opponentCloseCombatObservableList" -> {
                return null;
            }
            case "opponentRangedObservableList" -> {
                return null;
            }
            case "weatherObservableList" -> {
                return null;
            }
            default -> {
                return null;
            }
        }
    }

    private void moveCardToDestinationFlowPane(int cardId, String initialObservableListName, String destinationObservableListName) {
        GameCardView gameCardView = getGameCardViewWithCardId(cardId);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), gameCardView);
        ObservableList<GameCardView> initialObservableList = getObservableListWithName(initialObservableListName);
        ObservableList<GameCardView> destinationObservableList = getObservableListWithName(destinationObservableListName);
        initialObservableList.remove(gameCardView);
        destinationObservableList.add(gameCardView);
        FlowPane destinationFLowPane = getFlowPaneWithName(destinationObservableListName);
        FlowPane initialFlowPane = getFlowPaneWithName(initialObservableListName);
        transition.setToX(destinationFLowPane.getTranslateX());
        transition.setToY(destinationFLowPane.getTranslateY());
        transition.setOnFinished(event -> {
            initialFlowPane.getChildren().remove(gameCardView);
            destinationFLowPane.getChildren().addAll(gameCardView);
            gameCardView.setTranslateX(0);
            gameCardView.setTranslateY(0);
            nonLeaderCardsDoAbility(gameCardView);
        });
        transition.play();
        destinationFLowPane.setOnMouseClicked(null);
        controller.saveLog("cardWithId: " + gameCardView.getCard().getIdInGame() + " for PlayerWithId: " + table.getCurrentPlayer().getUsername() + " movedFrom: " + initialObservableListName + " to: " + destinationObservableListName);
    }

    private void nonLeaderCardsDoAbility(GameCardView gameCardView) {

    }
}

