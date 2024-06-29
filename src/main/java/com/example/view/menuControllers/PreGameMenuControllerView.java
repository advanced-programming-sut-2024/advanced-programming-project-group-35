package com.example.view.menuControllers;

import com.example.Main;
import com.example.model.PreGameCardData;
import com.example.model.App;
import com.example.model.card.PreGameCard;
import com.example.model.card.factions.Factions;
import com.example.model.card.factions.Monsters;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PreGameMenuControllerView {
    private final String srcPath = Main.class.getResource("/images/cards/").toExternalForm();
    public Pane mainPane;
    private Factions faction;
    private ObservableList<PreGameCard> allCards = FXCollections.observableArrayList();
    private ObservableList<PreGameCard> playerDeck = FXCollections.observableArrayList();
    private Label playerNameLabel = new Label("player name: ");
    private Label realmNameLabel = new Label("faction name: ");
    private Label totalCardsLabel = new Label("total cards: ");
    private Label soldiersCountLabel = new Label("soldiers count: 0");
    private Label specialCardsCountLabel = new Label("special cards count: 0");
    private Label heroCardsCountLabel = new Label("hero cards count: 0");
    private Label totalPowerLabel = new Label("total power: 0");
    Stage primaryStage = App.getAppView().getPrimaryStage();
    Scene scene = App.getAppView().getPane().getScene();

    @FXML
    public void initialize () {
        faction = new Monsters();

        resetMenu();

        FlowPane allCardsPane = new FlowPane(700, 800);
        FlowPane playerDeckPane = new FlowPane(700, 800);

        allCardsPane.getChildren().addAll(allCards);

        setSwapCardEventHandlers(allCardsPane, playerDeckPane, allCards, playerDeck);
        setSwapCardEventHandlers(playerDeckPane, allCardsPane, playerDeck, allCards);

        VBox infoColumn = new VBox(10);
        infoColumn.setAlignment(Pos.CENTER);
        infoColumn.setPadding(new Insets(10));
        infoColumn.getChildren().addAll(
                playerNameLabel,
                realmNameLabel,
                totalCardsLabel,
                soldiersCountLabel,
                specialCardsCountLabel,
                heroCardsCountLabel,
                totalPowerLabel
        );

        HBox root = new HBox(20);
        root.getChildren().addAll(allCardsPane, infoColumn, playerDeckPane);
        mainPane.getChildren().add(root);

//        Scene scene = new Scene(root, 1000, 600);
//        primaryStage.setTitle("Pre-Game Menu");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        playerNameLabel.setText("player name: " + App.getLoggedInUser().getUsername());

        updateDeckInfo();

        System.out.println("pre game menu");
    }

    private void setSwapCardEventHandlers(FlowPane list1Pane, FlowPane list2Pane, ObservableList<PreGameCard> list1, ObservableList<PreGameCard> list2) {
        for (PreGameCard card : list1) {
            card.setOnMouseClicked(event -> {
                list1.remove(card);
                list2.add(card);
                list1Pane.getChildren().remove(card);
                list2Pane.getChildren().add(card);
                updateDeckInfo();
            });
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
    }

    private void addAllCardsSkellige() {
        //TODO
    }

    private void addAllCardsScoiaTael() {
        //TODO
    }

    private void addAllCardsNorthernRealms() {
        //TODO
    }

    private void addAllCardsMonsters() {
        System.out.println(srcPath + PreGameCardData.monsters_arachas + ".jpg");
        allCards.addAll(
                new PreGameCard("Arachas", 6, "soldier", srcPath + PreGameCardData.monsters_arachas + ".jpg"),
                new PreGameCard("Arachas 1", 6, "soldier", srcPath + PreGameCardData.monsters_arachas_1 + ".jpg"),
                new PreGameCard("Arachas 2", 6, "soldier", srcPath + PreGameCardData.monsters_arachas_2 + ".jpg"),
                new PreGameCard("Arachas Behemoth", 6, "soldier", srcPath + PreGameCardData.monsters_arachas_behemoth + ".jpg"),
                new PreGameCard("Bruxa", 6, "soldier", srcPath + PreGameCardData.monsters_bruxa + ".jpg"),
                new PreGameCard("Celaeno Harpy", 6, "soldier", srcPath + PreGameCardData.monsters_celaeno_harpy + ".jpg"),
                new PreGameCard("Cockatrice", 6, "soldier", srcPath + PreGameCardData.monsters_cockatrice + ".jpg"),
                new PreGameCard("Draug", 6, "soldier", srcPath + PreGameCardData.monsters_draug + ".jpg"),
                new PreGameCard("Earth Elemental", 6, "soldier", srcPath + PreGameCardData.monsters_earth_elemental + ".jpg"),
                new PreGameCard("Ekkima", 6, "soldier", srcPath + PreGameCardData.monsters_ekkima + ".jpg"),
                new PreGameCard("Endrega", 6, "soldier", srcPath + PreGameCardData.monsters_endrega + ".jpg"),
                new PreGameCard("Eredin Bronze", 6, "soldier", srcPath + PreGameCardData.monsters_eredin_bronze + ".jpg"),
                new PreGameCard("Eredin Gold", 6, "soldier", srcPath + PreGameCardData.monsters_eredin_gold + ".jpg"),
                new PreGameCard("Eredin Silver", 6, "soldier", srcPath + PreGameCardData.monsters_eredin_silver + ".jpg"),
                new PreGameCard("Eredin Copper", 6, "soldier", srcPath + PreGameCardData.monsters_eredin_copper + ".jpg"),
                new PreGameCard("Eredin The Treacherous", 6, "soldier", srcPath + PreGameCardData.monsters_eredin_the_treacherous + ".jpg"),
                new PreGameCard("Fiend", 6, "soldier", srcPath + PreGameCardData.monsters_fiend + ".jpg"),
                new PreGameCard("Fire Elemental", 6, "soldier", srcPath + PreGameCardData.monsters_fire_elemental + ".jpg"),
                new PreGameCard("Fleder", 6, "soldier", srcPath + PreGameCardData.monsters_fleder + ".jpg"),
                new PreGameCard("Fogling", 6, "soldier", srcPath + PreGameCardData.monsters_fogling + ".jpg"),
                new PreGameCard("Forktail", 6, "soldier", srcPath + PreGameCardData.monsters_forktail + ".jpg"),
                new PreGameCard("Frost Giant", 6, "soldier", srcPath + PreGameCardData.monsters_frost_giant + ".jpg"),
                new PreGameCard("Frightener", 6, "soldier", srcPath + PreGameCardData.monsters_frightener + ".jpg"),
                new PreGameCard("Gargoyle", 6, "soldier", srcPath + PreGameCardData.monsters_gargoyle + ".jpg"),
                new PreGameCard("Ghoul", 6, "soldier", srcPath + PreGameCardData.monsters_ghoul + ".jpg"),
                new PreGameCard("Ghoul 1", 6, "soldier", srcPath + PreGameCardData.Monsters_ghoul_1 + ".jpg"),
                new PreGameCard("Ghoul 2", 6, "soldier", srcPath + PreGameCardData.Monsters_ghoul_2 + ".jpg"),
                new PreGameCard("Gravehag", 6, "soldier", srcPath + PreGameCardData.monsters_gravehag + ".jpg"),
                new PreGameCard("Griffin", 6, "soldier", srcPath + PreGameCardData.monsters_griffin + ".jpg"),
                new PreGameCard("Harpy", 6, "soldier", srcPath + PreGameCardData.monsters_harpy + ".jpg"),
                new PreGameCard("Imlerith", 6, "soldier", srcPath + PreGameCardData.monsters_imlerith + ".jpg"),
                new PreGameCard("Katakan", 6, "soldier", srcPath + PreGameCardData.monsters_katakan + ".jpg"),
                new PreGameCard("Kayran", 6, "soldier", srcPath + PreGameCardData.monsters_kayran + ".jpg"),
                new PreGameCard("Leshen", 6, "soldier", srcPath + PreGameCardData.monsters_leshen + ".jpg"),
                new PreGameCard("Mighty Maiden", 6, "soldier", srcPath + PreGameCardData.monsters_mighty_maiden + ".jpg"),
                new PreGameCard("Nekker", 6, "soldier", srcPath + PreGameCardData.monsters_nekker + ".jpg"),
                new PreGameCard("Nekker 1", 6, "soldier", srcPath + PreGameCardData.monsters_nekker_1 + ".jpg"),
                new PreGameCard("Nekker 2", 6, "soldier", srcPath + PreGameCardData.monsters_nekker_2 + ".jpg"),
                new PreGameCard("Poroniec", 6, "soldier", srcPath + PreGameCardData.monsters_poroniec + ".jpg"),
                new PreGameCard("Toad", 6, "soldier", srcPath + PreGameCardData.monsters_toad + ".jpg"),
                new PreGameCard("Werewolf", 6, "soldier", srcPath + PreGameCardData.monsters_werewolf + ".jpg"),
                new PreGameCard("Witch Velen", 6, "soldier", srcPath + PreGameCardData.monsters_witch_velen + ".jpg"),
                new PreGameCard("Witch Velen 1", 6, "soldier", srcPath + PreGameCardData.monsters_witch_velen_1 + ".jpg"),
                new PreGameCard("Witch Velen 2", 6, "soldier", srcPath + PreGameCardData.monsters_witch_velen_2 + ".jpg"),
                new PreGameCard("Wyvern", 6, "soldier", srcPath + PreGameCardData.monster_wyvern + ".jpg"),
                new PreGameCard("Weather Storm", 6, "special", srcPath + PreGameCardData.weather_storm + ".jpg")
        );
    }

    private void addAllCardsNilfgaardian() {
        //TODO
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

}