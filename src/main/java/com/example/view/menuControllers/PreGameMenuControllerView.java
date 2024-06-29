package com.example.view.menuControllers;

import com.example.Main;
import com.example.model.PreGameCardData;
import com.example.model.App;
import com.example.model.card.PreGameCard;
import com.example.model.card.factions.Factions;
import com.example.model.card.factions.Monsters;
import com.example.model.card.factions.RealmNorthern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PreGameMenuControllerView {
    private final String srcPath = Main.class.getResource("/images/cards/").toExternalForm();
    public Pane mainPane;
    public ScrollPane leftScrollPane;
    public ScrollPane rightScrollPane;
    public HBox mainHBox;
    public VBox infoVBox;
    public Button startGameButton;
    public Button backButton;
    private Factions faction;
    private ObservableList<PreGameCard> allCards = FXCollections.observableArrayList();
    private ObservableList<PreGameCard> playerDeck = FXCollections.observableArrayList();
    private FlowPane allCardsPane;
    private FlowPane playerDeckPane;

    // برچسب‌های اطلاعات
    @FXML
    private Label playerNameLabel = new Label("player name: ");
    @FXML
    private Label realmNameLabel = new Label("faction name: ");
    @FXML
    private Label totalCardsLabel = new Label("total cards: ");
    @FXML
    private Label soldiersCountLabel = new Label("soldiers count: 0");
    @FXML
    private Label specialCardsCountLabel = new Label("special cards count: 0");
    @FXML
    private Label heroCardsCountLabel = new Label("hero cards count: 0");
    @FXML
    private Label totalPowerLabel = new Label("total power: 0");
    Stage primaryStage = App.getAppView().getPrimaryStage();
    Scene scene = App.getAppView().getPane().getScene();

    @FXML
    public void initialize() {
        faction = new RealmNorthern();

        resetMenu();

        allCardsPane = new FlowPane(20, 10);
        playerDeckPane = new FlowPane(20, 10);

        // ایجاد ScrollPane برای allCardsPane
        leftScrollPane.setContent(allCardsPane);
        leftScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // ایجاد ScrollPane برای playerDeckPane
        rightScrollPane.setContent(playerDeckPane);
        rightScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        allCardsPane.getChildren().addAll(allCards);

        setSwapCardEventHandlers(true);
        setSwapCardEventHandlers(false);


        System.out.println("pre game menu");
        //mainHBox.getChildren().addAll(leftScrollPane, infoColumn, rightScrollPane);
        System.out.println("pre game menu");
        // تنظیم اولویت رشد برای ScrollPane ها
        HBox.setHgrow(leftScrollPane, Priority.ALWAYS);
        HBox.setHgrow(rightScrollPane, Priority.ALWAYS);

//        mainPane.getChildren().add(mainHBox);
        //set root on the center
//        mainHBox.setLayoutX((scene.getWidth() - mainHBox.getPrefWidth()) / 2);
//        mainHBox.setLayoutY((scene.getHeight() - mainHBox.getPrefHeight()) / 2);

//        Scene scene = new Scene(root, 1000, 600);
//        primaryStage.setTitle("Pre-Game Menu");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        playerNameLabel.setText("player name: " + App.getLoggedInUser().getUsername());

        updateDeckInfo();

        System.out.println("pre game menu");
    }

    private void setSwapCardEventHandlers(boolean fromAllPane) {
        for (PreGameCard card : allCards) {
            card.setOnMouseClicked(event -> {
                if (allCards.contains(card)) {
                    playerDeck.add(card);
                    allCards.remove(card);
                } else {
                    allCards.add(card);
                    playerDeck.remove(card);
                }
                updateDeckCardsPane();
                updateAllCardsPane();
                updateDeckInfo();
            });
        }
    }

    private void updateDeckCardsPane() {
        playerDeckPane.getChildren().clear();
        for (PreGameCard card : playerDeck) {
            playerDeckPane.getChildren().add(card);
        }
    }

    private void updateAllCardsPane() {
        allCardsPane.getChildren().clear();
        for (PreGameCard card : allCards) {
            allCardsPane.getChildren().add(card);
        }
    }

    private void resetMenu() {
        if (allCards.size() > 0) {
            allCards.clear();
        }
        if (playerDeck.size() > 0) {
            playerDeck.clear();
        }
        addAllCards(faction);

        // تنظیم اطلاعات اولیه
        realmNameLabel.setText("faction name: " + faction.getFaction().toString());

        updateDeckInfo();
    }

    private void addAllCards(Factions faction) {
        switch (faction.getFaction()) {
            case EmpireNilfgaardian:
                addAllCardsNilfgaardian();
                break;
            case Monsters:
                addAllCardsMonsters();
                break;
            case RealmsNorthern:
                addAllCardsNorthernRealms();
                break;
            case ScoiaTael:
                addAllCardsScoiaTael();
                break;
            case Skellige:
                addAllCardsSkellige();
                break;
        }
        addAllCardsNeutral();
        addAllCardsSpecial();
    }

    private void addAllCardsSkellige() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("skellige"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsScoiaTael() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("scoiatael"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }


    private void addAllCardsNorthernRealms() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("realms"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsMonsters() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("monsters"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsNilfgaardian() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("nilfgaard"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsNeutral() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("neutral"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsSpecial() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("special"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void updateDeckInfo() {
        int totalCards = playerDeck.size();
        int soldiersCount = 0;
        int specialCardsCount = 0;
        int heroCardsCount = 0;
        int totalPower = 0;

        for (PreGameCard card : playerDeck) {
            totalPower += card.getPower();
            switch (card.getAbility()) {
                case "soldier":
                    soldiersCount++;
                    break;
                case "special":
                    specialCardsCount++;
                    break;
                case "hero":
                    heroCardsCount++;
                    break;
            }
        }

        totalCardsLabel.setText("total cards: " + totalCards);
        soldiersCountLabel.setText("soldiers count: " + soldiersCount);
        specialCardsCountLabel.setText("special cards count: " + specialCardsCount);
        heroCardsCountLabel.setText("hero cards count: " + heroCardsCount);
        totalPowerLabel.setText("total power: " + totalPower);
    }

    public void startGameButtonAction(ActionEvent actionEvent) {
    }

    public void backButtonAction(ActionEvent actionEvent) {
    }
}