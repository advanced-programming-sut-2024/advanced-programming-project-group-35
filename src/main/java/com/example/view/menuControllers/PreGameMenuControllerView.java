package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.model.IO.errors.Errors;
import com.example.model.PreGameCardData;
import com.example.model.App;
import com.example.model.card.PreGameCard;
import com.example.model.card.factions.EmpireNilfgaardian;
import com.example.model.card.factions.Factions;
import com.example.model.card.factions.Monsters;
import com.example.model.card.factions.Skellige;
import com.example.model.card.enums.FactionsType;
import com.example.model.card.factions.*;
import com.example.view.Menu;
import com.example.view.OutputView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Objects;

import java.util.List;

public class PreGameMenuControllerView {
    private final Stage stage = App.getAppView().getPrimaryStage();
    public AnchorPane changeFactionPane;
    public ImageView factionCard1;
    public ImageView factionCard2;
    public ImageView factionCard3;
    public ImageView factionCard4;
    public ImageView factionCard5;
    private Pane pane = App.getAppView().getPane();
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
    private PreGameCard leaderCard;
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
        leaderCard = defaultLeaderCard();

        leftScrollPane.setBackground(Background.EMPTY);
        rightScrollPane.setBackground(Background.EMPTY);
        allCardsPane.setBackground(Background.EMPTY);
        playerDeckPane.setBackground(Background.EMPTY);

        leftScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


        rightScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        resetMenu();//everytime faction changes, reset menu function should be called


        HBox.setHgrow(leftScrollPane, Priority.ALWAYS);
        HBox.setHgrow(rightScrollPane, Priority.ALWAYS);

        playerNameLabel.setText("player name: " + App.getLoggedInUser().getUsername());

        updateDeckInfo();

        System.out.println("pre game menu");

        Node flowPaneBackground = playerDeckPane.lookup(".flow-pane-pre-game-menu");
        if (flowPaneBackground != null) {
            flowPaneBackground.setStyle("-fx-background-color: transparent;");
        }
        addMouseHoverEffect(factionCard1);
        addMouseHoverEffect(factionCard2);
        addMouseHoverEffect(factionCard3);
        addMouseHoverEffect(factionCard4);
        addMouseHoverEffect(factionCard5);
    }
    private void addMouseHoverEffect(ImageView imageView) {
        imageView.setOnMouseEntered(event -> enlargeImage(imageView));
        imageView.setOnMouseExited(event -> resetImageSize(imageView));
    }

    private PreGameCard defaultLeaderCard() {
        switch (faction.getFaction()) {
            case EmpireNilfgaardian:
                return new PreGameCard(PreGameCardData.nilfgaard_leader.getName(), PreGameCardData.nilfgaard_leader.getPower(), PreGameCardData.nilfgaard_leader.getAbility(), srcPath + PreGameCardData.nilfgaard_leader.getImageAddress());
            case Monsters:
                return new PreGameCard(PreGameCardData.monsters_leader.getName(), PreGameCardData.monsters_leader.getPower(), PreGameCardData.monsters_leader.getAbility(), srcPath + PreGameCardData.monsters_leader.getImageAddress());
            case RealmsNorthern:
                return new PreGameCard(PreGameCardData.realms_leader.getName(), PreGameCardData.realms_leader.getPower(), PreGameCardData.realms_leader.getAbility(), srcPath + PreGameCardData.realms_leader.getImageAddress());
            case ScoiaTael:
                return new PreGameCard(PreGameCardData.scoiatael_leader.getName(), PreGameCardData.scoiatael_leader.getPower(), PreGameCardData.scoiatael_leader.getAbility(), srcPath + PreGameCardData.scoiatael_leader.getImageAddress());
            case Skellige:
                return new PreGameCard(PreGameCardData.skellige_leader.getName(), PreGameCardData.skellige_leader.getPower(), PreGameCardData.skellige_leader.getAbility(), srcPath + PreGameCardData.skellige_leader.getImageAddress());
        }
        return null;
    }

    private void enlargeImage(ImageView imageView) {
        imageView.setFitHeight(imageView.getFitHeight() * 1.2);
        imageView.setFitWidth(imageView.getFitWidth() * 1.2);
    }

    private void resetImageSize(ImageView imageView) {
        imageView.setFitHeight(imageView.getFitHeight() / 1.2);
        imageView.setFitWidth(imageView.getFitWidth() / 1.2);
    }

    private void setSwapCardEventHandlers(boolean fromAllPane) {
        for (PreGameCard card : allCards) {
            card.setOnMouseClicked(event -> {
                if (allCards.contains(card)) {
                    addCardToDeck(card);
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

    private void addCardToDeck(PreGameCard card) {
        if (specialCardsCountLabel.getText().equals("special cards count: 10") && card.getAbility().equals("special")) {
            specialCardsCountLabel.setTextFill(Color.RED);
            OutputView.showOutputAlert(Errors.SPECIAL_CARD_ERROR);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            specialCardsCountLabel.setTextFill(Color.WHITE);
                        }
                    },
                    3000
            );
            return;
        }
        playerDeck.add(card);
        allCards.remove(card);
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
        if (allCardsPane.getChildren().size() > 0){
            allCardsPane.getChildren().clear();
        }
        if (playerDeckPane.getChildren().size() > 0){
            playerDeckPane.getChildren().clear();
        }
        if (allCards.size() > 0) {
            allCards.clear();
        }
        if (playerDeck.size() > 0) {
            playerDeck.clear();
        }
        addAllCards(faction);
        leaderCard = defaultLeaderCard();

        realmNameLabel.setText("faction name: " + faction.getFaction().toString());

        leftScrollPane.setContent(allCardsPane);
        rightScrollPane.setContent(playerDeckPane);
        allCardsPane.getChildren().addAll(allCards);

        setSwapCardEventHandlers(true);
        setSwapCardEventHandlers(false);

        updateDeckInfo();
    }

    private void addAllCards(Factions faction) {
        addAllCardsSpecial();
        addAllWeatherCards();
        addAllCardsNeutral();
        switch (faction.getFaction()) {
            case EmpireNilfgaardian:
                addAllCardsNilfgaardian();
                System.out.println("nilfgaardian");
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

    private void addAllWeatherCards() {
        for (PreGameCardData cardData : PreGameCardData.values()) {
            if (cardData.getName().startsWith("weather"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
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
        if (playerDeck.size() < 22) {
            totalCardsLabel.setTextFill(Color.RED);
            OutputView.showOutputAlert(Errors.NOT_ENOUGH_CARDS);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            totalCardsLabel.setTextFill(Color.WHITE);
                        }
                    },
                    3000
            );
            return;
        }
        List<String> playerDeckNames = getPreGameCardNames(playerDeck);
        String leaderName = getLeaderName(leaderCard);
    }

    private String getLeaderName(PreGameCard leaderCard) {
        return leaderCard.getName();
    }

    private List<String> getPreGameCardNames(ObservableList<PreGameCard> playerDeck) {
        List<String> playerDeckNames = new java.util.ArrayList<>();
        for (PreGameCard card : playerDeck) {
            playerDeckNames.add(card.getName());
        }
        return playerDeckNames;
    }

    public void backToMainMenu(ActionEvent actionEvent) {
        App.setCurrentMenu(Menu.MAIN_MENU);
        Controller.MAIN_MENU_CONTROLLER.run();
    }

    public void openChangeFactionMenu(ActionEvent actionEvent) {
        mainPane.setVisible(true);
        changeFactionPane.setVisible(true);
    }

    private void paneChanger(String stageTitle, String fxmlFileName) throws IOException {
        stage.setTitle(stageTitle);
        String fxmlFilePath = "/FXML/";
        fxmlFilePath += fxmlFileName;
        pane = FXMLLoader.load(Objects.requireNonNull(LoginMenuControllerView.class.getResource(fxmlFilePath)));
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.centerOnScreen();
        App.getAppView().setPane(pane);
    }

    public void backToPreGameMenu(MouseEvent mouseEvent) {
        mainPane.setVisible(true);
        changeFactionPane.setVisible(false);
    }

    public void changeFactionToSkellige(MouseEvent mouseEvent) {
        faction = new Skellige();
        resetMenu();
        backToPreGameMenu(null);
    }

    public void changeFactionToScoiatael(MouseEvent mouseEvent) {
        faction = new ScoiaTeal();
        resetMenu();
        backToPreGameMenu(null);
    }
    public void changeFactionToRealms(MouseEvent mouseEvent) {
        faction = new RealmNorthern();
        resetMenu();
        backToPreGameMenu(null);
    }

    public void changeFactionToNilfgaard(MouseEvent mouseEvent) {
        faction = new EmpireNilfgaardian();
        resetMenu();
        backToPreGameMenu(null);
    }
    public void changeFactionToMonsters(MouseEvent mouseEvent) {
        faction = new Monsters();
        resetMenu();
        backToPreGameMenu(null);
    }
}