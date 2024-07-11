package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.User;
import com.example.view.Menu;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TVMenuControllerView {

    public ScrollPane pastGamesScrollPane;
    public ScrollPane onlineGamesScrollPane;

    @FXML
    public void initialize() {
        User loggedInUser = App.getLoggedInUser();
        VBox pastGames = new VBox();
        pastGames.setSpacing(20);
        pastGames.setAlignment(Pos.CENTER);
        pastGames.setPrefWidth(370);
        pastGames.setPrefHeight(330);
        for (int i = loggedInUser.getGameData().size() - 1; i >= 0; i--) {
            HBox eachGameDataRow = new HBox();
            eachGameDataRow.setSpacing(20);
            eachGameDataRow.setPrefHeight(60);
            eachGameDataRow.setPrefWidth(1100);
            eachGameDataRow.setAlignment(Pos.CENTER);
            ImageView tvImage = new ImageView(new Image(Main.class.getResource("/images/screenshot1.png").toExternalForm(), 60, 60, true, true));
            VBox data = new VBox();
            data.setSpacing(5);
            data.setAlignment(Pos.CENTER_LEFT);
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
            Label winnerName = new Label("Winner:" + loggedInUser.getGameData().get(i).getWinnerName());
            winnerName.setMaxWidth(150);
            winnerName.setMinWidth(150);
            winnerName.setPrefWidth(150);
            winnerName.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            Button viewGameButton = new Button("Watch");
            viewGameButton.getStyleClass().add("brown-button");
            viewGameButton.setOnMouseClicked(event -> {
                // Watch the game
            });
            data.getChildren().addAll(opponentName, date, winnerName, viewGameButton);
            eachGameDataRow.getChildren().addAll(tvImage, data);
            pastGames.getChildren().add(eachGameDataRow);
        }

        pastGamesScrollPane.setContent(pastGames);

        VBox onlineGames = new VBox();
        onlineGames.setSpacing(20);
        onlineGames.setAlignment(Pos.CENTER);
        onlineGames.setPrefWidth(370);
        onlineGames.setPrefHeight(330);

        for (int i = loggedInUser.getGameData().size() - 2; i >= 0; i--) {
            HBox eachGameDataRow = new HBox();
            eachGameDataRow.setSpacing(20);
            eachGameDataRow.setPrefHeight(60);
            eachGameDataRow.setPrefWidth(1100);
            eachGameDataRow.setAlignment(Pos.CENTER);
            ImageView tvImage = new ImageView(new Image(Main.class.getResource("/images/screenshot2.png").toExternalForm(), 60, 60, true, true));
            VBox data = new VBox();
            data.setSpacing(5);
            data.setAlignment(Pos.CENTER_LEFT);
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
            Label winnerName = new Label("Winner:" + loggedInUser.getGameData().get(i).getWinnerName());
            winnerName.setMaxWidth(150);
            winnerName.setMinWidth(150);
            winnerName.setPrefWidth(150);
            winnerName.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            Button viewGameButton = new Button("Watch");
            viewGameButton.getStyleClass().add("brown-button");
            viewGameButton.setOnMouseClicked(event -> {
                // Watch the game
            });
            data.getChildren().addAll(opponentName, date, winnerName, viewGameButton);
            eachGameDataRow.getChildren().addAll(tvImage, data);
            onlineGames.getChildren().add(eachGameDataRow);
        }


        onlineGamesScrollPane.setContent(onlineGames);
    }
    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}
