package com.example.controller;

import com.example.model.App;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Deck;
import com.example.model.game.Hand;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.view.Menu;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

public class GameMenuController extends AppController {
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
        //TODO  گذاشتن دکمه وتو کارت رو صفحه
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
        } else {
            //TODO نمایش خطا در صفحه که نمیتونی کارت وتو کنی
        }
    }
    public void doCardAction(Card card, AbilityContext abilityContext) {
        if (card.getAbility() != null) {
            card.getAbility().apply(abilityContext);
        }
    }
    public void startNewGame(String player1Name, String player2Name, Deck player1Deck, Deck plyer2Deck) {
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        player1.getBoard().setHandForStartGame();
        player1.getBoard().setHandForStartGame();
        Table table = new Table(player1, player2);
    }
    private void startRound() {

    }
    public void endRound() {

    }
    public void changeRound() {

    }
    public void changeTurn() {

    }
    public void disApplyWeatherCards() {
    }
}
