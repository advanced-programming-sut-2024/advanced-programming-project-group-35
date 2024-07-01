package com.example.controller;

import com.example.model.App;
import com.example.model.User;
import com.example.view.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreTableController extends AppController {
    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.SCORE_TABLE_MENU);
            App.setCurrentController(Controller.SCORE_TABLE_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void setScoreboardTable(TableView<User> scoreboardTable, ArrayList<User> allUsers) {
        allUsers.sort(Comparator.comparing(User::getScore).reversed());

        // Limit the list to the top 10 users
        List<User> topUsers = allUsers.stream().limit(10).collect(Collectors.toList());

        // Create an ObservableList of users and add the top 10 users to it
        ObservableList<User> users = FXCollections.observableArrayList(topUsers);

        // Create TableColumn objects for each column in the table
        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        TableColumn<User, Integer> bestScoreColumn = new TableColumn<>("Best Score");
        TableColumn<User, Integer> scoreColumn = new TableColumn<>("Score");
        TableColumn<User, Integer> gamesWonColumn = new TableColumn<>("Games Won");

        // Set the cell value factory for each column to display the appropriate property of the User object
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        bestScoreColumn.setCellValueFactory(new PropertyValueFactory<>("bestScore"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        gamesWonColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfWonGames"));

        usernameColumn.setMinWidth(200);
        bestScoreColumn.setMinWidth(200);
        scoreColumn.setMinWidth(200);
        gamesWonColumn.setMinWidth(200);

        // Add the TableColumn objects to the TableView
        scoreboardTable.getColumns().setAll(usernameColumn, bestScoreColumn, scoreColumn, gamesWonColumn);

        // Set the items of the TableView to the ObservableList of users
        scoreboardTable.setItems(users);

        // Apply different styles to the first three rows to represent gold, silver, and bronze
        scoreboardTable.setRowFactory(tv -> new TableRow<User>() {
            @Override
            public void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    if (getIndex() == 0) {
                        setStyle("-fx-background-color: linear-gradient(to bottom, #FFD700, #B8860B);");
                    } else if (getIndex() == 1) {
                        setStyle("-fx-background-color: linear-gradient(to bottom, #D3D3D3, #A9A9A9);");
                    } else if (getIndex() == 2) {
                        setStyle("-fx-background-color: linear-gradient(to bottom, #CD7F32, #8C7853);");
                    } else {
                        setStyle("");
                    }
                }
                setMinHeight(35);
            }
        });
    }

    public void backToMainMenu() throws Exception {
        App.getAppView().showMenu(Menu.MAIN_MENU);
        App.setCurrentController(Controller.MAIN_MENU_CONTROLLER);
    }
}
