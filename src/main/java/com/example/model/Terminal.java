package com.example.model;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.IO.patterns.CheatCodes;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Terminal extends StackPane {
    private final double WIDTH = 500;
    private final double HEIGHT = 180;
    private TextArea textArea;
    private int editableStartIndex;
    private String title;

    public Terminal() {
        this.getStylesheets().add(Terminal.class.getResource("/CSS/terminalStyle.css").toExternalForm());
        textArea = new TextArea();
        textArea.setPrefHeight(HEIGHT);
        textArea.setWrapText(true);
        textArea.setPrefWidth(WIDTH);

        if (App.getLoggedInUser() != null) {
            title = "Gwent\\" + App.getLoggedInUser().getUsername() + " :\n";
            textArea.appendText(title);
        } else {
            title = "Gwent :\n";
            textArea.appendText(title);
        }
        editableStartIndex = textArea.getCaretPosition() + 1;

        textArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String command = getCurrentCommand();
                if (command.equals("clear")) {
                    textArea.clear();
                    textArea.appendText(title.substring(0, title.length() - 1));
                } else {
                   if (CheatCodes.ADD_CARD_TO_HAND.matched(command)) {
                       addCardTODeck();
                   } else if (CheatCodes.RECOVER_CRYSTALS.matched(command)) {
                       recverCrystals();
                   } else if (CheatCodes.RECOVER_LEADER_ABILITY.matched(command)) {
                       recoverLeaderAbility();
                   } else if (CheatCodes.LUCK_OPPONENT_LEADER_ABILITY.matched(command)) {
                       luckOpponentLeaderAbility();
                   } else if (CheatCodes.LUCK_OPPONENT_EMOTES.matched(command)) {
                       luckOpponentEmotes();
                   } else if (CheatCodes.SET_CLOWN_PICTURE_FOR_OPPONENT_LEADER_CARD.matched(command)) {
                       setClownForOpponent();
                   } else if (CheatCodes.ADD_DECOY_CARD.matched(command)) {
                       addDecoyCard();
                   }
                }
                editableStartIndex = textArea.getCaretPosition() + 2;
            } else if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                if (textArea.getCaretPosition() < editableStartIndex) {
                    event.consume();
                }
            } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                event.consume();
            }
        });
        textArea.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!textArea.isFocused()) {
                textArea.requestFocus();
                mouseEvent.consume();
            } else {
                mouseEvent.consume();
            }
        });

        ImageView imageView = new ImageView(new Image(Terminal.class.getResource("/images/terminal-exit-button.png").toExternalForm(), 20, 20, true, true));
        imageView.setOnMouseClicked(e -> App.getAppView().removeTerminal());

        VBox buttonContainer = new VBox(imageView);
        buttonContainer.prefWidth(900);
        buttonContainer.getStyleClass().add("vbox-button-container");

        VBox textAreaContainer = new VBox(textArea);
        textAreaContainer.getStyleClass().add("vbox-textArea-container");

        textArea.requestFocus();
        VBox container = new VBox(buttonContainer, textAreaContainer);
        container.getStyleClass().add("vbox-container");

        this.getChildren().add(container);

        this.setLayoutX(35);
    }

    private void addDecoyCard() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.addDecoyCard();
    }

    private void setClownForOpponent() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.setClownForOpponent();
    }

    private void luckOpponentEmotes() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.luckOpponentEmotes();
    }

    private void luckOpponentLeaderAbility() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.luckOpponentLeaderAbility();
    }

    private void recverCrystals() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.recoverCrystals();
    }

    private void recoverLeaderAbility() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.recoverLeaderAbility();
    }

    private void addCardTODeck() {
        GameMenuControllerForOnlineGame gameMenuControllerForOnlineGame = (GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController();
        gameMenuControllerForOnlineGame.addRandomCardToDeck();
    }

    public String getCurrentCommand() {
        String text = textArea.getText();
        int lastPromptIndex = text.lastIndexOf("\n");
        return text.substring(lastPromptIndex + 1).trim();
    }
}