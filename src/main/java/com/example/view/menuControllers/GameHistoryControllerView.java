package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.model.User;
import com.example.model.alerts.AlertType;
import com.example.view.Menu;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameHistoryControllerView {
    public ScrollPane mainScrollPane = new ScrollPane();
    public TextField searchField;
    private User loggedInUser = App.getLoggedInUser();
    private int maxGameHistory = loggedInUser.getGameData().size();

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }

    @FXML
    public void initialize() {
        updateGameHistoryTable();
    }

    private void updateGameHistoryTable() {
        VBox gameDataVbox = new VBox();
        gameDataVbox.setSpacing(20);
        gameDataVbox.setAlignment(Pos.CENTER);
        gameDataVbox.setPrefWidth(1100);
        gameDataVbox.setPrefHeight(450);
        for (int i = loggedInUser.getGameData().size() - 1; i >= loggedInUser.getGameData().size() - maxGameHistory ; i--) {
            HBox eachGameDataRow = new HBox();
            eachGameDataRow.setSpacing(20);
            eachGameDataRow.setPrefHeight(60);
            eachGameDataRow.setPrefWidth(1100);
            eachGameDataRow.setAlignment(Pos.CENTER);
            Label opponentName = new Label(loggedInUser.getGameData().get(i).getOpponentName());
            opponentName.setMaxWidth(150);
            opponentName.setMinWidth(150);
            opponentName.setPrefWidth(150);
            opponentName.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            Label date = new Label(loggedInUser.getGameData().get(i).getDate());
            date.setMaxWidth(200);
            date.setMinWidth(200);
            date.setPrefWidth(200);
            date.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            VBox scores = new VBox();
            scores.setMaxWidth(310);
            scores.setMinWidth(310);
            scores.setPrefWidth(310);
            scores.setMaxHeight(60);
            scores.setMinHeight(60);
            scores.setPrefHeight(60);
            scores.setSpacing(10);
            scores.setAlignment(Pos.CENTER_LEFT);
            HBox starterPlayerScores = new HBox();
            starterPlayerScores.setMaxWidth(310);
            starterPlayerScores.setMinWidth(310);
            starterPlayerScores.setPrefWidth(310);
            starterPlayerScores.setMaxHeight(25);
            starterPlayerScores.setMinHeight(25);
            starterPlayerScores.setPrefHeight(25);
            starterPlayerScores.setSpacing(5);
            starterPlayerScores.setAlignment(Pos.CENTER_LEFT);
            HBox opponentPlayerScores = new HBox();
            opponentPlayerScores.setMaxWidth(310);
            opponentPlayerScores.setMinWidth(310);
            opponentPlayerScores.setPrefWidth(310);
            opponentPlayerScores.setMaxHeight(25);
            opponentPlayerScores.setMinHeight(25);
            opponentPlayerScores.setPrefHeight(25);
            opponentPlayerScores.setSpacing(5);
            opponentPlayerScores.setAlignment(Pos.CENTER_LEFT);
            for (int j = 0; j < loggedInUser.getGameData().get(i).getStarterPlayerScores().length; j++) {
                Label starterPlayerScore = new Label(String.valueOf(loggedInUser.getGameData().get(i).getStarterPlayerScores()[j]));
                starterPlayerScore.setPrefWidth(25);
                starterPlayerScore.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");
                Label opponentPlayerScore = new Label(String.valueOf(loggedInUser.getGameData().get(i).getOpponentPlayerScores()[j]));
                opponentPlayerScore.setPrefWidth(25);
                opponentPlayerScore.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");
                starterPlayerScores.getChildren().add(starterPlayerScore);
                opponentPlayerScores.getChildren().add(opponentPlayerScore);
            }
            scores.getChildren().addAll(starterPlayerScores, opponentPlayerScores);
            VBox finalScores = new VBox();
            finalScores.setMaxWidth(70);
            finalScores.setMinWidth(70);
            finalScores.setPrefWidth(70);
            finalScores.setMaxHeight(60);
            finalScores.setMinHeight(60);
            finalScores.setPrefHeight(60);
            finalScores.setSpacing(10);
            finalScores.setAlignment(Pos.CENTER_LEFT);
            Label currentPlayerFinalScore = new Label(String.valueOf(loggedInUser.getGameData().get(i).getCurrentPlayerFinalScore()));
            currentPlayerFinalScore.setPrefWidth(70);
            currentPlayerFinalScore.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            Label opponentFinalScore = new Label(String.valueOf(loggedInUser.getGameData().get(i).getOpponentFinalScore()));
            opponentFinalScore.setPrefWidth(70);
            opponentFinalScore.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            finalScores.getChildren().addAll(currentPlayerFinalScore, opponentFinalScore);
            Label winnerName = new Label(loggedInUser.getGameData().get(i).getWinnerName());
            winnerName.setMaxWidth(150);
            winnerName.setMinWidth(150);
            winnerName.setPrefWidth(150);
            winnerName.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            Button viewGameButton = new Button("Watch");
            viewGameButton.getStyleClass().add("brown-button");
            eachGameDataRow.getChildren().addAll(opponentName, date, scores, finalScores, winnerName, viewGameButton);
            gameDataVbox.getChildren().add(eachGameDataRow);
        }
        mainScrollPane.setContent(gameDataVbox);
        mainScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }

    public void updateGameHistory(MouseEvent mouseEvent) {
        String numberOfHistory = searchField.getText();
        if (numberOfHistory == null || numberOfHistory.equals("")) {
            return;
        } else if (!numberOfHistory.matches("[0-9]+")) {
            App.getAppView().showAlert("Please enter a valid number", AlertType.ERROR.getType());
            return;
        } else if (Integer.parseInt(numberOfHistory) > loggedInUser.getGameData().size()) {
            App.getAppView().showAlert("Not enough game history", AlertType.ERROR.getType());
            return;
        } else if (Integer.parseInt(numberOfHistory) < 1) {
            App.getAppView().showAlert("Invalid number", AlertType.ERROR.getType());
            return;
        }
        maxGameHistory = Integer.parseInt(numberOfHistory);
        updateGameHistoryTable();
    }
}