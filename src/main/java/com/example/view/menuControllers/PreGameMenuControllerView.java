package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.controller.PreGameMenuController;
import com.example.model.User;
import com.example.model.card.enums.FactionsType;
import com.example.model.deckmanager.DeckManager;
import com.example.model.IO.errors.Errors;
import com.example.model.alerts.AlertType;
import com.example.model.card.enums.CardData;
import com.example.model.App;
import com.example.model.card.PreGameCard;
import com.example.model.card.factions.EmpireNilfgaardian;
import com.example.model.card.factions.Factions;
import com.example.model.card.factions.Monsters;
import com.example.model.card.factions.Skellige;
import com.example.model.card.factions.*;
import com.example.model.deckmanager.DeckToJson;
import com.example.view.Menu;
import com.example.view.OutputView;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class PreGameMenuControllerView {
    private final Stage stage = App.getAppView().getPrimaryStage();
    public AnchorPane changeFactionPane;
    public FlowPane leadersCardsPane;
    public ImageView factionCard1;
    public ImageView factionCard2;
    public ImageView factionCard3;
    public ImageView factionCard4;
    public ImageView factionCard5;
    public FlowPane leaderCardPane;
    public AnchorPane chooseLeaderAnchorPane;
    public TextField saveOrLoadDeckNameField;
    public Button saveDeckButton;
    public Button loadDeckButton;
    public Button chooseDirectoryButton;
    public Button fileChooserButton;
    private Pane pane = App.getAppView().getPane();
    private final String srcPath = Main.class.getResource("/images/cards/").toExternalForm();
    public Pane mainPane;
    public ScrollPane leftScrollPane;
    public ScrollPane rightScrollPane;
    public HBox mainHBox;
    public VBox infoVBox;
    private int saveState = 0;
    private int loadState = 0;
    public Button startGameButton;
    public Button backButton;
    private Factions faction;
    private int totalCards = 0;
    private int soldiersCount = 0;
    private int specialCardsCount = 0;
    private int heroCardsCount = 0;
    private int totalPower = 0;
    private ObservableList<PreGameCard> allCards = FXCollections.observableArrayList();
    private ObservableList<PreGameCard> playerDeck = FXCollections.observableArrayList();
    private ObservableList<PreGameCard> leaderCards = FXCollections.observableArrayList();
    private PreGameCard leaderCard;
    public FlowPane allCardsPane;
    public FlowPane playerDeckPane;
    public static int player1OfflineID = -1;
    public static int player2OfflineID = -1;
    private DeckToJson player1OfflineDeck;
    private DeckToJson player2OfflineDeck;

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
    PreGameMenuController controller = (PreGameMenuController) Controller.PRE_GAME_MENU_CONTROLLER.getController();

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

        Node flowPaneBackground = playerDeckPane.lookup(".flow-pane-pre-game-menu");
        if (flowPaneBackground != null) {
            flowPaneBackground.setStyle("-fx-background-color: transparent;");
        }

        addMouseHoverOnCard();
    }

    private void addMouseHoverOnCard() {
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
                return new PreGameCard(CardData.nilfgaard_leader.getName(), CardData.nilfgaard_leader.getPower(), CardData.nilfgaard_leader.getAbility(), srcPath + CardData.nilfgaard_leader.getImageAddress());
            case Monsters:
                return new PreGameCard(CardData.monsters_leader.getName(), CardData.monsters_leader.getPower(), CardData.monsters_leader.getAbility(), srcPath + CardData.monsters_leader.getImageAddress());
            case RealmsNorthern:
                return new PreGameCard(CardData.realms_leader.getName(), CardData.realms_leader.getPower(), CardData.realms_leader.getAbility(), srcPath + CardData.realms_leader.getImageAddress());
            case ScoiaTael:
                return new PreGameCard(CardData.scoiatael_leader.getName(), CardData.scoiatael_leader.getPower(), CardData.scoiatael_leader.getAbility(), srcPath + CardData.scoiatael_leader.getImageAddress());
            case Skellige:
                return new PreGameCard(CardData.skellige_leader.getName(), CardData.skellige_leader.getPower(), CardData.skellige_leader.getAbility(), srcPath + CardData.skellige_leader.getImageAddress());
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
        if (allCardsPane.getChildren().size() > 0) {
            allCardsPane.getChildren().clear();
        }
        if (playerDeckPane.getChildren().size() > 0) {
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

        if (leadersCardsPane.getChildren().size() > 0) {
            leadersCardsPane.getChildren().clear();
        }
        leaderCards.clear();
        addToLeaderCardsPane();
        addOnMouseClickedEventToLeaderCards();
        leadersCardsPane.getChildren().addAll(leaderCards);

        updateDeckInfo();
    }

    private void addOnMouseClickedEventToLeaderCards() {
        for (PreGameCard card : leaderCards) {
            card.setOnMouseClicked(event -> {
                leaderCard = card;
                backToPreGameMenu(null);
                updateDeckInfo();
            });
        }
    }

    private void addToLeaderCardsPane() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getType().equals("leader") && cardData.getFaction().equals(faction.getFaction())) {
                leaderCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
            }
        }
    }

    private void addAllCards(Factions faction) {
        addAllCardsSpecial();
        addAllWeatherCards();
        addAllCardsNeutral();
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

    private void addAllWeatherCards() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("weather"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsSkellige() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("skellige"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsScoiaTael() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("scoiatael"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }


    private void addAllCardsNorthernRealms() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("realms"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsMonsters() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("monsters"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsNilfgaardian() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("nilfgaard"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsNeutral() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("neutral"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void addAllCardsSpecial() {
        for (CardData cardData : CardData.values()) {
            if (cardData.getName().startsWith("special"))
                allCards.add(new PreGameCard(cardData.getName(), cardData.getPower(), cardData.getAbility(), srcPath + cardData.getImageAddress()));
        }
    }

    private void updateDeckInfo() {

        updateNumbers();

        totalCardsLabel.setText("total cards: " + totalCards);
        soldiersCountLabel.setText("soldiers count: " + soldiersCount);
        specialCardsCountLabel.setText("special cards count: " + specialCardsCount);
        heroCardsCountLabel.setText("hero cards count: " + heroCardsCount);
        totalPowerLabel.setText("total power: " + totalPower);
    }

    public void startGameButtonAction(ActionEvent actionEvent) throws Exception {
        System.out.println(App.getLoggedInUser().getDeckName());
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

        if (App.temporaryUserID == -1) {
            player1OfflineID = App.getLoggedInUser().getID();
            //App.loadUsers();
            App.temporaryUserID = player1OfflineID;
            player1OfflineDeck = new DeckToJson(faction.getFaction().name(), leaderCard.getName());
            App.getLoggedInUser().setTemporaryDeck(player1OfflineDeck);
            //App.saveUsers();
            App.setCurrentMenu(Menu.LOGIN_MENU);
            Controller.LOGIN_MENU_CONTROLLER.run();
        } else if (App.temporaryUserID == App.getLoggedInUser().getID() && player2OfflineID == -1) {
            App.setCurrentMenu(Menu.LOGIN_MENU);
            Controller.LOGIN_MENU_CONTROLLER.run();
            OutputView.showOutputAlert(Errors.YOU_CANT_PLAY_WITH_YOURSELF);
        } else {
            player1OfflineID = App.temporaryUserID;
            player2OfflineID = App.getLoggedInUser().getID();
            player2OfflineDeck = new DeckToJson(faction.getFaction().name(), leaderCard.getName());
            player1OfflineDeck = App.getLoggedInUser().getTemporaryDeck();
            App.getLoggedInUser().setTemporaryDeck(player2OfflineDeck);
            GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
            gameMenuControllerForOnlineGame.startNewGame(App.getLoggedInUser().getUsername(), User.getUserByID(App.temporaryUserID).getUsername(), getDeckString(player1OfflineDeck), getDeckString(player2OfflineDeck));
            gameMenuControllerForOnlineGame.run();
            player1OfflineID = player2OfflineID = -1;
        }
    }
    public String getDeckString(DeckToJson deckToJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String deckJson = objectMapper.writeValueAsString(deckToJson);
        return deckJson;
    }

    private void startGameWithFriend(String friendUsername) {
        //send request to server
    }

    public void sendRequestToFriend(String name) {
        int senderID = App.getLoggedInUser().getID();
        User receiver = App.getUserByUsername(name);
        if (receiver == null) {
            OutputView.showOutputAlert(Errors.USER_NOT_FOUND);
            return;
        }
        int receiverID = receiver.getID();
        App.getServerConnector().sendGameRequest(senderID, receiverID);
    }

    public void sendRandomGameRequest(ActionEvent actionEvent) throws Exception {
        App.getServerConnector().sendRandomGameRequest(App.getLoggedInUser().getID());
        //wait for answer:
//        App.getAppView().showLoading();
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(2000);
//                    System.out.println("waiting for game to start");
//                    if (App.getLoggedInUser().isInGame()) {
//                        //go to game Controller menu TODO
//                        break;
//                    }
//
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        ).start();
//        System.out.println("game started");
    }

    public void sendTournamentGameRequest(ActionEvent actionEvent) {
        //wait for answer:
        App.getAppView().showLoading();
        Thread waitForAnswer = new Thread(() -> {
            App.getServerConnector().sendTournamentGameRequest(App.getLoggedInUser().getID());

            while (true) {
                try {
                    Thread.sleep(1000);
                    if (App.getLoggedInUser().isInGame()) {
                        //go to game Controller menu TODO
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        waitForAnswer.start();
    }


    private ArrayList<String> getPreGameCardNames(ObservableList<PreGameCard> playerDeck) {
        ArrayList<String> playerDeckNames = new ArrayList<>();

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
        chooseLeaderAnchorPane.setVisible(false);
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

    public void openChooseLeader(ActionEvent actionEvent) {
        mainPane.setVisible(true);
        chooseLeaderAnchorPane.setVisible(true);
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }

    public void loadDeck(MouseEvent mouseEvent) {
        saveState = 0;
        if (loadState == 0) {
            loadState = 1;
        } else if (loadState == 1) {
            if (saveOrLoadDeckNameField.getText().equals("")) {
                App.getAppView().showAlert("Please enter a name from your saved decks", AlertType.ERROR.getType());
            } else if (!App.getLoggedInUser().isADeckExistWithThisName(saveOrLoadDeckNameField.getText())) {
                App.getAppView().showAlert("You don't have a deck with this name", AlertType.ERROR.getType());
                saveOrLoadDeckNameField.setText("");
                loadState = 0;
            } else {
                DeckToJson deckToJson = DeckManager.loadDeck(saveOrLoadDeckNameField.getText(), App.getLoggedInUser().getID());
                if (deckToJson == null) {
                    App.getAppView().showAlert("Unable to load deck", AlertType.ERROR.getType());
                } else {
                    loadDeckPictures(deckToJson);
                }
                saveOrLoadDeckNameField.setText("");
                loadState = 0;
            }
        }
    }

    private void loadDeckPictures(DeckToJson deckToJson) {
        faction = FactionsType.getAbilityByName(deckToJson.getFaction());
        resetMenu();
        Thread loadDataThread = new Thread(() -> {
            Platform.runLater(() -> {
                try {
                    CardData leaderCardData = CardData.getCardDataByName(deckToJson.getLeader());
                    System.out.println(CardData.getCardDataByName(deckToJson.getLeader()));
                    leaderCard = new PreGameCard(leaderCardData.getName(), leaderCardData.getPower(), leaderCardData.getAbilityName(), srcPath + leaderCardData.getImageAddress());
                    System.out.println(leaderCard.getName());
                    ArrayList<PreGameCard> copyOfAllCards = new ArrayList<>(allCards);
                    Timeline timeline = new Timeline();
                    AtomicInteger i = new AtomicInteger(0);
                    for (PreGameCard card : copyOfAllCards) {
                        System.out.println(card.getName());
                        if (deckToJson.getCards().contains(card.getName())) {
                            KeyFrame keyFrame = new KeyFrame(Duration.millis(100 * i.get()), event -> {
                                addCardToDeck(card);
                                updateDeckCardsPane();
                                updateAllCardsPane();
                                updateDeckInfo();
                            });
                            timeline.getKeyFrames().add(keyFrame);
                            i.incrementAndGet();
                        }
                    }
                    timeline.play();

//                    App.setCurrentController(Controller.PRE_GAME_MENU_CONTROLLER);
//                    App.getAppView().showMenu(Menu.PREGAME_MENU);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        loadDataThread.start();
    }

    public void saveDeck(MouseEvent mouseEvent) {
        loadState = 0;
        if (saveState == 0) {
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
            } else {
                saveState = 1;
            }
        } else if (saveState == 1) {
            if (saveOrLoadDeckNameField.getText().equals("")) {
                App.getAppView().showAlert("Please enter a name for your deck", AlertType.ERROR.getType());
            } else if (App.getLoggedInUser().isADeckExistWithThisName(saveOrLoadDeckNameField.getText())) {
                App.getAppView().showAlert("You already have a Deck with this name", AlertType.ERROR.getType());
                saveOrLoadDeckNameField.setText("");
                saveState = 0;
            } else {

                updateNumbers();

                DeckManager.saveDeck(new DeckToJson(faction.getFaction().name(), leaderCard.getName()), App.getLoggedInUser().getID(), saveOrLoadDeckNameField.getText());
                App.getLoggedInUser().addDeckNameToDeckAddresses(saveOrLoadDeckNameField.getText());
                App.loadUsers();
                App.getLoggedInUser().addDeckNameToDeckAddresses(saveOrLoadDeckNameField.getText());
                App.saveUsers();
                saveOrLoadDeckNameField.setText("");
                saveState = 0;
                App.getAppView().showAlert("Deck saved successfully", AlertType.SUCCESS.getType());
            }
        }
    }

    public void chooseDirectoryToSaveDeckData(MouseEvent mouseEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");

        updateNumbers();

        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            if (saveOrLoadDeckNameField.getText().equals("")) {
                App.getAppView().showAlert("Please enter a name for your deck", AlertType.ERROR.getType());
            } else {
                DeckManager.saveDeck(new DeckToJson(faction.getFaction().name(), leaderCard.getName()), selectedDirectory.getAbsolutePath(), saveOrLoadDeckNameField.getText());
                App.getAppView().showAlert("Deck saved successfully", AlertType.SUCCESS.getType());
                saveOrLoadDeckNameField.setText("");
            }
        } else {
            App.getAppView().showAlert("Unable to Save Deck", AlertType.ERROR.getType());
            saveOrLoadDeckNameField.setText("");
        }

    }

    private void updateNumbers() {
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0;
        for (PreGameCard card : playerDeck) {
            i1 += card.getPower();
            switch (card.getAbility()) {
                case "soldier":
                    i2++;
                    break;
                case "special":
                    i3++;
                    break;
                case "hero":
                    i4++;
                    break;
            }
        }
        totalCards = playerDeck.size();
        totalPower = i1;
        soldiersCount = i2;
        specialCardsCount = i3;
        heroCardsCount = i4;
    }

    public void chooseDeckFile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open JSON File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) {
            return;
        }
        DeckToJson deck = DeckManager.loadDeck(selectedFile.getAbsolutePath());
        if (deck == null) {
            App.getAppView().showAlert("Unable to load Deck", AlertType.ERROR.getType());
        } else {
            loadDeckPictures(deck);
        }
        loadState = 0;
    }

    public void RandomGameButtonAction(ActionEvent actionEvent) {

    }
}