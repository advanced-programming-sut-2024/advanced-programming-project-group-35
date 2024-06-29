package com.example.controller;

import com.example.model.App;
import com.example.view.Menu;

public class PreGameMenuController extends AppController {
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.PREGAME_MENU);
            App.setCurrentController(Controller.PRE_GAME_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void showFactions() {

    }

    public void selectFaction(String factionName) {

    }

    public void showCards() {

    }

    public void showDeck() {

    }

    public void showCurrentUserInformation() {

    }

    public void saveDeckByName() {

    }

    // in do ta tabe mitoonan yeki shan to piadesazi bayad bebinim kodoom behtare
    public void saveDeckByAddress() {

    }

    public void loadDeckByName() {

    }

    // in do ta tabe mitoonan yeki shan to piadesazi bayad bebinim kodoom behtare
    public void loadDeckByAddress() {

    }

    public void showLeaders() {

    }

    public void selectLeader(int leaderNumber) {

    }

    public void addToDeck(String cardName, int number) {

    }

    public void deleteFromDeck(String cardName, int number) {

    }

    public void changeTurn() {

    }

    public void startGame() {
        
    }

    public void addRandomCardsToHand() {

    }
}
