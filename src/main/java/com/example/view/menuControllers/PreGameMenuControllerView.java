package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.model.PreGameCardData;
import com.example.model.App;
import com.example.model.card.PreGameCard;
import com.example.model.card.factions.Factions;
import com.example.model.card.factions.Monsters;
import com.example.view.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
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
    public FlowPane allCardsPane;
    public FlowPane playerDeckPane;

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
        faction = new Monsters();

        resetMenu();

        leftScrollPane.setBackground(Background.EMPTY);
        rightScrollPane.setBackground(Background.EMPTY);
        allCardsPane.setBackground(Background.EMPTY);
        playerDeckPane.setBackground(Background.EMPTY);

        leftScrollPane.setContent(allCardsPane);
        leftScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        rightScrollPane.setContent(playerDeckPane);
        rightScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        allCardsPane.getChildren().addAll(allCards);

        setSwapCardEventHandlers(true);
        setSwapCardEventHandlers(false);

        HBox.setHgrow(leftScrollPane, Priority.ALWAYS);
        HBox.setHgrow(rightScrollPane, Priority.ALWAYS);

        playerNameLabel.setText("player name: " + App.getLoggedInUser().getUsername());

        updateDeckInfo();

        System.out.println("pre game menu");

        Node flowPaneBackground = playerDeckPane.lookup(".flow-pane-pre-game-menu");
        if (flowPaneBackground != null) {
            flowPaneBackground.setStyle("-fx-background-color: transparent;");
        }
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
                new PreGameCard(PreGameCardData.monsters_arachas.getName(), PreGameCardData.monsters_arachas.getPower(), PreGameCardData.monsters_arachas.getAbility(), srcPath + PreGameCardData.monsters_arachas.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_arachas_1.getName(), PreGameCardData.monsters_arachas_1.getPower(), PreGameCardData.monsters_arachas_1.getAbility(), srcPath + PreGameCardData.monsters_arachas_1.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_arachas_2.getName(), PreGameCardData.monsters_arachas_2.getPower(), PreGameCardData.monsters_arachas_2.getAbility(), srcPath + PreGameCardData.monsters_arachas_2.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_arachas_behemoth.getName(), PreGameCardData.monsters_arachas_behemoth.getPower(), PreGameCardData.monsters_arachas_behemoth.getAbility(), srcPath + PreGameCardData.monsters_arachas_behemoth.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_bruxa.getName(), PreGameCardData.monsters_bruxa.getPower(), PreGameCardData.monsters_bruxa.getAbility(), srcPath + PreGameCardData.monsters_bruxa.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_celaeno_harpy.getName(), PreGameCardData.monsters_celaeno_harpy.getPower(), PreGameCardData.monsters_celaeno_harpy.getAbility(), srcPath + PreGameCardData.monsters_celaeno_harpy.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_cockatrice.getName(), PreGameCardData.monsters_cockatrice.getPower(), PreGameCardData.monsters_cockatrice.getAbility(), srcPath + PreGameCardData.monsters_cockatrice.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_draug.getName(), PreGameCardData.monsters_draug.getPower(), PreGameCardData.monsters_draug.getAbility(), srcPath + PreGameCardData.monsters_draug.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_earth_elemental.getName(), PreGameCardData.monsters_earth_elemental.getPower(), PreGameCardData.monsters_earth_elemental.getAbility(), srcPath + PreGameCardData.monsters_earth_elemental.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_ekkima.getName(), PreGameCardData.monsters_ekkima.getPower(), PreGameCardData.monsters_ekkima.getAbility(), srcPath + PreGameCardData.monsters_ekkima.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_endrega.getName(), PreGameCardData.monsters_endrega.getPower(), PreGameCardData.monsters_endrega.getAbility(), srcPath + PreGameCardData.monsters_endrega.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_fiend.getName(), PreGameCardData.monsters_fiend.getPower(), PreGameCardData.monsters_fiend.getAbility(), srcPath + PreGameCardData.monsters_fiend.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_fire_elemental.getName(), PreGameCardData.monsters_fire_elemental.getPower(), PreGameCardData.monsters_fire_elemental.getAbility(), srcPath + PreGameCardData.monsters_fire_elemental.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_fleder.getName(), PreGameCardData.monsters_fleder.getPower(), PreGameCardData.monsters_fleder.getAbility(), srcPath + PreGameCardData.monsters_fleder.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_fogling.getName(), PreGameCardData.monsters_fogling.getPower(), PreGameCardData.monsters_fogling.getAbility(), srcPath + PreGameCardData.monsters_fogling.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_forktail.getName(), PreGameCardData.monsters_forktail.getPower(), PreGameCardData.monsters_forktail.getAbility(), srcPath + PreGameCardData.monsters_forktail.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_frost_giant.getName(), PreGameCardData.monsters_frost_giant.getPower(), PreGameCardData.monsters_frost_giant.getAbility(), srcPath + PreGameCardData.monsters_frost_giant.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_frightener.getName(), PreGameCardData.monsters_frightener.getPower(), PreGameCardData.monsters_frightener.getAbility(), srcPath + PreGameCardData.monsters_frightener.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_gargoyle.getName(), PreGameCardData.monsters_gargoyle.getPower(), PreGameCardData.monsters_gargoyle.getAbility(), srcPath + PreGameCardData.monsters_gargoyle.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_ghoul.getName(), PreGameCardData.monsters_ghoul.getPower(), PreGameCardData.monsters_ghoul.getAbility(), srcPath + PreGameCardData.monsters_ghoul.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_ghoul_1.getName(), PreGameCardData.monsters_ghoul_1.getPower(), PreGameCardData.monsters_ghoul_1.getAbility(), srcPath + PreGameCardData.monsters_ghoul_1.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_ghoul_2.getName(), PreGameCardData.monsters_ghoul_2.getPower(), PreGameCardData.monsters_ghoul_2.getAbility(), srcPath + PreGameCardData.monsters_ghoul_2.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_gravehag.getName(), PreGameCardData.monsters_gravehag.getPower(), PreGameCardData.monsters_gravehag.getAbility(), srcPath + PreGameCardData.monsters_gravehag.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_gryffin.getName(), PreGameCardData.monsters_gryffin.getPower(), PreGameCardData.monsters_gryffin.getAbility(), srcPath + PreGameCardData.monsters_gryffin.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_harpy.getName(), PreGameCardData.monsters_harpy.getPower(), PreGameCardData.monsters_harpy.getAbility(), srcPath + PreGameCardData.monsters_harpy.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_imlerith.getName(), PreGameCardData.monsters_imlerith.getPower(), PreGameCardData.monsters_imlerith.getAbility(), srcPath + PreGameCardData.monsters_imlerith.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_katakan.getName(), PreGameCardData.monsters_katakan.getPower(), PreGameCardData.monsters_katakan.getAbility(), srcPath + PreGameCardData.monsters_katakan.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_kayran.getName(), PreGameCardData.monsters_kayran.getPower(), PreGameCardData.monsters_kayran.getAbility(), srcPath + PreGameCardData.monsters_kayran.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_leshen.getName(), PreGameCardData.monsters_leshen.getPower(), PreGameCardData.monsters_leshen.getAbility(), srcPath + PreGameCardData.monsters_leshen.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_mighty_maiden.getName(), PreGameCardData.monsters_mighty_maiden.getPower(), PreGameCardData.monsters_mighty_maiden.getAbility(), srcPath + PreGameCardData.monsters_mighty_maiden.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_nekker.getName(), PreGameCardData.monsters_nekker.getPower(), PreGameCardData.monsters_nekker.getAbility(), srcPath + PreGameCardData.monsters_nekker.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_nekker_1.getName(), PreGameCardData.monsters_nekker_1.getPower(), PreGameCardData.monsters_nekker_1.getAbility(), srcPath + PreGameCardData.monsters_nekker_1.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_nekker_2.getName(), PreGameCardData.monsters_nekker_2.getPower(), PreGameCardData.monsters_nekker_2.getAbility(), srcPath + PreGameCardData.monsters_nekker_2.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_poroniec.getName(), PreGameCardData.monsters_poroniec.getPower(), PreGameCardData.monsters_poroniec.getAbility(), srcPath + PreGameCardData.monsters_poroniec.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_toad.getName(), PreGameCardData.monsters_toad.getPower(), PreGameCardData.monsters_toad.getAbility(), srcPath + PreGameCardData.monsters_toad.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_werewolf.getName(), PreGameCardData.monsters_werewolf.getPower(), PreGameCardData.monsters_werewolf.getAbility(), srcPath + PreGameCardData.monsters_werewolf.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_witch_velen.getName(), PreGameCardData.monsters_witch_velen.getPower(), PreGameCardData.monsters_witch_velen.getAbility(), srcPath + PreGameCardData.monsters_witch_velen.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_witch_velen_1.getName(), PreGameCardData.monsters_witch_velen_1.getPower(), PreGameCardData.monsters_witch_velen_1.getAbility(), srcPath + PreGameCardData.monsters_witch_velen_1.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_witch_velen_2.getName(), PreGameCardData.monsters_witch_velen_2.getPower(), PreGameCardData.monsters_witch_velen_2.getAbility(), srcPath + PreGameCardData.monsters_witch_velen_2.getImageAddress()),
                new PreGameCard(PreGameCardData.monsters_wyvern.getName(), PreGameCardData.monsters_wyvern.getPower(), PreGameCardData.monsters_wyvern.getAbility(), srcPath + PreGameCardData.monsters_wyvern.getImageAddress())
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

    public void startGameButtonAction(ActionEvent actionEvent) {
    }

    public void backToMainMenu(ActionEvent actionEvent) {
        App.setCurrentMenu(Menu.MAIN_MENU);
        Controller.MAIN_MENU_CONTROLLER.run();
    }
}