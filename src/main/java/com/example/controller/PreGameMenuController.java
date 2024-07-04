package com.example.controller;

import com.example.model.App;
import com.example.view.Menu;
import javafx.application.Platform;

public class PreGameMenuController extends AppController {
    @Override
    public void run() {
        try {
            //first show the loading image:
            App.getAppView().showLoading();
            //then show the pregame menu on thread:
            Thread loadDataThread = new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(4000);
                        App.getAppView().showMenu(Menu.PREGAME_MENU);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            });
            loadDataThread.start();
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

//    public void startGame() {
//        App.setCurrentMenu(Menu.GAME_MENU);
//        Controller.GAME_MENU_CONTROLLER.run();
//    }

    public void addRandomCardsToHand() {

    }
}
