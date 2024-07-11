package com.example.controller;

import com.example.model.App;
import com.example.model.card.Card;
import com.example.model.card.LeaderCard;
import com.example.model.card.SpecialCard;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
import com.example.view.AppView;
import com.example.view.Menu;
import com.example.view.menuControllers.GameMenuControllerView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameMenuControllerForOnlineGameTest {

    private GameMenuControllerForOnlineGame controller;
    private static Stage primaryStage;
    private static CountDownLatch latch;
    private AppView mockAppView;
    private GameMenuControllerView mockGameMenuControllerView;
    private Table mockTable;

    @BeforeAll
    public static void setupJavaFX() throws InterruptedException {
        latch = new CountDownLatch(1);
        Platform.startup(() -> {
            new Stage(); // Dummy stage to initialize JavaFX
            latch.countDown();
        });

        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new ExceptionInInitializerError("JavaFX initialization timed out.");
        }
    }

    @BeforeEach
    void setUp() {
        mockTable = Mockito.mock(Table.class);
        mockAppView = Mockito.mock(AppView.class);
        App.setCurrentController(Controller.GAME_MENU_CONTROLLER);

        App.setAppView(mockAppView);
        controller = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        controller.setTable(mockTable);
    }

    @Test
    void testRun() throws Exception {
        assertDoesNotThrow(() -> {
            Platform.runLater(() -> controller.run());
        });

        verify(mockAppView).showMenu(Menu.GAME_MENU);
    }

    @Test
    void testVetoCard() {
        Player mockPlayer = Mockito.mock(Player.class);
        ObservableList<Card> selectedCards = FXCollections.observableArrayList();
        Card mockCard = Mockito.mock(Card.class);
        selectedCards.add(mockCard);

        when(mockPlayer.canVetoCard()).thenReturn(true);

        controller.vetoCard(mockPlayer, selectedCards);

        verify(mockPlayer).decreaseNumberOfVetoCards();
    }

    @Test
    void testMoveCardFromOriginToDestinationAndDoAbility() {
        int cardId = 1;
        String origin = "currentPlayerHand";
        String destination = "currentPlayerCloseCombat";

        UnitCard mockCard = Mockito.mock(UnitCard.class);
        ObservableList<Card> originList = FXCollections.observableArrayList(mockCard);
        ObservableList<Card> destList = FXCollections.observableArrayList();

        when(mockTable.getCurrentPlayer()).thenReturn(Mockito.mock(Player.class));
        when(mockTable.getOpponent()).thenReturn(Mockito.mock(Player.class));
        when(mockCard.getIdInGame()).thenReturn(cardId);
        when(mockCard.getAbilityName()).thenReturn(AbilityName.MUSTER);

        // Mocking the getRowListByName method
        doReturn(originList).when(controller).getRowListByName(origin);
        doReturn(destList).when(controller).getRowListByName(destination);

        controller.moveCardFromOriginToDestinationAndDoAbility(cardId, origin, destination);

        assertFalse(originList.contains(mockCard));
        assertTrue(destList.contains(mockCard));
        verify(mockGameMenuControllerView).moveCardToDestinationFlowPane(cardId, origin, destination);
    }

    @Test
    void testPassRound() {
        Player mockCurrentPlayer = Mockito.mock(Player.class);
        Player mockOpponent = Mockito.mock(Player.class);

        when(mockTable.getCurrentPlayer()).thenReturn(mockCurrentPlayer);
        when(mockTable.getOpponent()).thenReturn(mockOpponent);
        when(mockOpponent.isPassRound()).thenReturn(false);

        controller.passRound();

        verify(mockCurrentPlayer).setPassRound(true);
    }

    @Test
    void testDoDecoyAbility() {
        int decoyCardId = 1;
        int selectedCardId = 2;
        String dest = "currentPlayerCloseCombat";

        Card mockDecoyCard = Mockito.mock(Card.class);
        Card mockSelectedCard = Mockito.mock(Card.class);
        Player mockCurrentPlayer = Mockito.mock(Player.class);

        when(mockTable.getCurrentPlayer()).thenReturn(mockCurrentPlayer);
        when(mockDecoyCard.getIdInGame()).thenReturn(decoyCardId);
        when(mockSelectedCard.getIdInGame()).thenReturn(selectedCardId);

        // Mocking necessary methods
        doReturn(mockDecoyCard).when(controller).getCardById(decoyCardId, FXCollections.observableArrayList());
        doReturn(mockSelectedCard).when(controller).getCardById(selectedCardId, FXCollections.observableArrayList());

        controller.doDecoyAbility(decoyCardId, selectedCardId, dest);

        verify(mockGameMenuControllerView).setPowerOfCardDefault(selectedCardId);
        verify(mockGameMenuControllerView).addMouseEventsForHandCards();
    }

    @Test
    void testStartNewGame() {
        String player1Name = "1";
        String player2Name = "2";
        String player1DeckNames = "{\"cards\":[\"Card1\",\"Card2\"]}";
        String player2DeckNames = "{\"cards\":[\"Card3\",\"Card4\"]}";

        controller.startNewGame(player1Name, player2Name, player1DeckNames, player2DeckNames);

        // Verify that a new Table is created and set
        assertNotNull(controller.getTable());
    }

    @Test
    void testDoCurrentPlayerLeaderAbility() {
        Player mockCurrentPlayer = Mockito.mock(Player.class);
        LeaderCard mockLeaderCard = Mockito.mock(LeaderCard.class);

        when(mockTable.getCurrentPlayer()).thenReturn(mockCurrentPlayer);
        when(mockCurrentPlayer.getBoard().getDeck().getLeader()).thenReturn(mockLeaderCard);
        when(mockLeaderCard.canDoAction()).thenReturn(true);

        controller.doCurrentPlayerLeaderAbility();

        verify(mockLeaderCard).getAbility();
    }

    @Test
    void testAddRandomCardToDeck() {
        Player mockCurrentPlayer = Mockito.mock(Player.class);
        when(mockTable.getCurrentPlayer()).thenReturn(mockCurrentPlayer);

        controller.addRandomCardToDeck();

        // Verify that a card is moved from deck to hand
        verify(controller).moveCardFromOriginToDestinationAndDontDoAbilityWithNoLog(
                anyInt(), eq(RowsInGame.currentPlayerDeck.toString()), eq(RowsInGame.currentPlayerHand.toString())
        );
    }

    @Test
    void testRecoverLeaderAbility() {
        Player mockCurrentPlayer = Mockito.mock(Player.class);
        LeaderCard mockLeaderCard = Mockito.mock(LeaderCard.class);

        when(mockTable.getCurrentPlayer()).thenReturn(mockCurrentPlayer);
        when(mockCurrentPlayer.getBoard().getDeck().getLeader()).thenReturn(mockLeaderCard);

        controller.recoverLeaderAbility();

        verify(mockLeaderCard).setCanDoAction(true);
        verify(mockGameMenuControllerView).updateAllLabels();
    }

    @Test
    void testRecoverCrystals() {
        Player mockCurrentPlayer = Mockito.mock(Player.class);
        when(mockTable.getCurrentPlayer()).thenReturn(mockCurrentPlayer);

        controller.recoverCrystals();

        verify(mockCurrentPlayer).setNumberOfCrystals(2);
        verify(mockGameMenuControllerView).updateAllLabels();
    }

    @AfterEach
    void tearDown() {
        Platform.runLater(() -> {
            if (primaryStage != null) {
                primaryStage.close();
            }
        });
    }
}