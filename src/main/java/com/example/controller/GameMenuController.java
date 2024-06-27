package com.example.controller;

import com.example.model.IO.errors.Errors;
import com.example.model.card.Card;

import java.util.regex.Matcher;

public class GameMenuController extends AppController {
    @Override
    public void run() {
    }


    public void menuEnter(Matcher matcher) {
    }

    public void menuExit() {
    }

    public void showCurrentMenu() {
    }

    public void vetoCard(int cardNumber) {
        // veto card <card_number>
    }

    public void showAllCardsInHand() {
        // in hand deck
    }

    public void showCardInHand(int cardNumber) {
        // in hand deck –option <card_number>
    }

    public void showRemainingCards() {
        // remaining cards to play
    }

    public void showOutOfPlayCards() {
        // out of play cards
    }

    public void showCardsInRow(int rowNumber) {
        // cards in rou <row_number>
    }

    public void showSpellCards() {
        // spells in play
    }

    public void putCard(int cardNumber, int rowNumber) {
        // place card <card_number> in row <row_number>
    }

    public void showCommander() {
        // show commander
    }

    public void commanderPowerPlay() {
        // commander power play
    }

    public void showPlayersInfo() {
        // show players info
    }

    public void showPlayersLives() {
        // show players lives
    }

    public void showNumberOfCardsInHand() {
        // show number of cards in hand
    }

    public void showTurnInfo() {
        // show turn info
    }

    public void showTotalScore() {
        // show total score
    }

    public void showTotalScoreOfRow(int rowNumber) {
        // show total score of row <row_number>
    }

    public void passRound() {
        // pass round
    }

    public void endRound() {
        // end round
    }

    public void endGame() {
        // end game
    }

    public void showWinnerData() {
    }

    // maybe this method should be deleted
    private Card getRandomCard() {
        return null;
    }

}
