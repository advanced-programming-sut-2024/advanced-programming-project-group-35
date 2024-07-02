package com.example.controller;

import com.example.model.App;
import com.example.model.DeckManager;
import com.example.model.card.*;
import com.example.model.card.enums.CardData;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.*;
import com.example.view.Menu;

import java.util.ArrayList;
import java.util.Random;

public class GameMenuController extends AppController {
    private Deck player1Deck;
    private Deck player2Deck;
    private Table table;
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.GAME_MENU);
            App.setCurrentController(Controller.GAME_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void vetoCard(Player player, Card selectedCard) {
        //TODO
        if (player.canVetoCard()) {
            Deck deck = player.getBoard().getDeck();
            Hand hand = player.getBoard().getHand();
            Card ranomCard = deck.getCard(new Random().nextInt(deck.getSize()));
            hand.removeCard(selectedCard);
            hand.addCard(ranomCard);
            deck.removeCard(ranomCard);
            deck.addCard(selectedCard);
            player.decreaseNumberOfVetoCards();
            //TODO گرافیک جابه جایی کارت
        }
    }

    public void doUnitCardAction(Card card, AbilityContext abilityContext) {
        if (card.getAbility() != null) {
            card.getAbility().apply(abilityContext);
        }
        if (abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
            passRound(abilityContext.getTable());
        } else if (!abilityContext.getTable().getOpponent().isPassRound()) {
            changeTurn(abilityContext.getTable());
        }
    }

    public void doLeaderCardAction(LeaderCard leaderCard, Table table) {
        if (leaderCard.getAbility() != null && leaderCard.canDoAction()) {
            leaderCard.getAbility().apply(table);
        }
        if (table.getCurrentPlayer().getBoard().getHand().getCards().isEmpty()) {
            passRound(table);
        } else if (!table.getOpponent().isPassRound()) {
            changeTurn(table);
        }
    }

    public void startNewGame(String player1Name, String player2Name, ArrayList<String> player1DeckNames, ArrayList<String> player2DeckNames) {
        Deck player1Deck = DeckManager.loadDeck(player1DeckNames);
        Deck player2Deck = DeckManager.loadDeck(player2DeckNames);
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        player1.getBoard().setDeck(player1Deck);
        player2.getBoard().setDeck(player2Deck);
        player1.getBoard().setHandForStartGame(player1Deck);
        player2.getBoard().setHandForStartGame(player2Deck);
        table = new Table(player1, player2);
        Round round1 = new Round(1);
        table.addRound(round1);
        table.setCurrentRound(round1);
//        //TODO لود عکس های صفحه
        startRound(table);
    }

    public void passRound(Table table) {
        table.getCurrentPlayer().setPassRound(true);
        if (table.getOpponent().isPassRound()) {
            changeRound(table);
        } else {
            changeTurn(table);
        }
    }

    private void startRound(Table table) {
        if (table.getRoundNumber() == 1) {
            LeaderCard leaderCard1 = table.getCurrentPlayer().getBoard().getDeck().getLeader();
            LeaderCard leaderCard2 = table.getOpponent().getBoard().getDeck().getLeader();
            if (leaderCard1.canDoAction() && leaderCard1.getLeaderName() == CardData.leaders_scoiatael_francesca_copper) {
                leaderCard1.getAbility().apply(table);
            }
            if (leaderCard2.canDoAction() && leaderCard2.getLeaderName() == CardData.leaders_scoiatael_francesca_copper) {
                leaderCard2.getAbility().apply(table);
            }
        } else if (table.getRoundNumber() == 3) {
            FactionsType factionsType1 = table.getCurrentPlayer().getBoard().getDeck().getFaction();
            FactionsType factionsType2 = table.getOpponent().getBoard().getDeck().getFaction();
            if (factionsType1 == FactionsType.Skellige) {
                table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
            }
            if (factionsType2 == FactionsType.Skellige) {
                table.getOpponent().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
            }
        }
    }

    private void endRound(Table table) {
        backCardsToDiscardPiles(table);
        disApplyWeatherCards(table);
        Player player1 = table.getCurrentPlayer();
        Player player2 = table.getOpponent();
        table.getCurrentRound().addScore(player1, player1.getScore());
        table.getCurrentRound().addScore(player2, player2.getScore());
        if (player1.getScore() == player2.getScore()) {
            table.getCurrentRound().setDraw(true);
            table.getCurrentRound().setWon(false);
            table.getCurrentRound().setWinner(null);
        } else if (player1.getScore() > player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player1);
        } else if (player1.getScore() < player2.getScore()) {
            table.getCurrentRound().setDraw(false);
            table.getCurrentRound().setWon(true);
            table.getCurrentRound().setWinner(player2);
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getFaction() == FactionsType.Monsters) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
        }
        if (table.getOpponent().getBoard().getDeck().getFaction() == FactionsType.Monsters) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getFaction() == FactionsType.EmpireNilfgaardian) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
        }
        if (table.getOpponent().getBoard().getDeck().getFaction() == FactionsType.EmpireNilfgaardian) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
        }
        if (table.getCurrentPlayer().getBoard().getDeck().getFaction() == FactionsType.RealmsNorthern) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getCurrentPlayer());
        }
        if (table.getOpponent().getBoard().getDeck().getFaction() == FactionsType.RealmsNorthern) {
            table.getCurrentPlayer().getBoard().getDeck().getFactionAbility().apply(table, table.getOpponent());
        }
    }

    private void backCardsToDiscardPiles(Table table) {
        //TODO(گرافیک) باید چک شه اگه ریموو کارت ترو بود حذف کنیم
    }

    private void changeRound(Table table) {
        endRound(table);
        if (table.getCurrentPlayer().getNumberOfCrystals() == 0) {
            endGame(table, table.getOpponent());
        } else if (table.getOpponent().getNumberOfCrystals() == 0) {
            endGame(table, table.getCurrentPlayer());
        } else {
            Round round = new Round(table.getRoundNumber() + 1);
            table.getCurrentPlayer().setPassRound(false);
            table.getOpponent().setPassRound(false);
            table.addRound(round);
            table.setCurrentRound(round);
            table.setRoundNumber(table.getRoundNumber() + 1);
            startRound(table);
        }
    }

    private void changeTurn(Table table) {
        table.swapPlayers();
        //TODO گرافیک
    }

    private void endGame(Table table, Player winner) {
        //TODO نمایش برنده
        //TODO نمایش مجموع امتیاز های هر فرد در تمام راند ها
        //TODO اضافه کردن گیم دیتا
    }

    public void disApplyWeatherCards(Table table) {
        //TODO
    }

    public Deck getPlayer1Deck() {
        return player1Deck;
    }

    public Deck getPlayer2Deck() {
        return player2Deck;
    }

    public Table getTable() {
        return table;
    }
}
