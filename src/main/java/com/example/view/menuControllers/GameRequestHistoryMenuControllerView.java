package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.GameRequest;
import com.example.model.User;
import com.example.model.alerts.AlertType;
import com.example.view.Menu;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameRequestHistoryMenuControllerView {
    public ScrollPane mainScrollPane = new ScrollPane();

    @FXML
    public void initialize() {
        updateGameRequestHistory();
    }

    public void updateGameRequestHistory() {
        //TODO: update game request history
        mainScrollPane.setStyle("-fx-alignment: center;");
        VBox rows = new VBox();
        rows.setSpacing(20);
        rows.setAlignment(Pos.TOP_CENTER);
        rows.setPrefWidth(500);
        if (App.getLoggedInUser().getFriendRequests() == null) {
            return;
        }
        for (int i = 0; i < App.getLoggedInUser().getGameRequests().size(); i++) {
            HBox row = new HBox();
            row.setPrefWidth(500);
            Label username = setUsernameLabel(i);
            username.setTranslateX(20);
            username.setPrefWidth(180);
            username.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-cursor: hand;");
            row.setSpacing(150);
            row.setAlignment(Pos.CENTER_LEFT);
            int finalI = i;
            username.setOnMouseClicked(e -> {
                App.getAppView().showAlert("Friend request from " + App.getLoggedInUser().getFriendRequests().get(finalI).getSender().getUsername(), "info");
            });
            HBox rightSection = new HBox();
            rightSection.setSpacing(15);
            rightSection.setAlignment(Pos.CENTER_RIGHT);
            rightSection.setPrefWidth(170);
            if (App.getLoggedInUser().getGameRequests().get(i).getSenderID() == App.getLoggedInUser().getID()) {
                Label status = new Label();
                if (!App.getLoggedInUser().getGameRequests().get(i).isAccepted() && !App.getLoggedInUser().getGameRequests().get(i).isRejected()) {
                    status.setText("Pending");
                    status.setStyle("-fx-text-fill: #396b84;");
                } else if (App.getLoggedInUser().getGameRequests().get(i).isAccepted()) {
                    status.setText("Accepted");
                    status.setStyle("-fx-text-fill: #3c7d52;");
                } else {
                    status.setText("Rejected");
                    status.setStyle("-fx-text-fill: #a93d3a;");
                }
                rightSection.getChildren().add(status);
                row.getChildren().addAll(username, rightSection);
            } else {
                if (App.getLoggedInUser().getGameRequests().get(i).isAccepted()) {
                    Label status = new Label("You Accepted");
                    status.setStyle("-fx-text-fill: #3c7d52;");
                    rightSection.getChildren().add(status);
                    row.getChildren().addAll(username, rightSection);
                    rows.getChildren().add(row);
                    continue;
                } else if (App.getLoggedInUser().getGameRequests().get(i).isRejected()) {
                    Label status = new Label("You Rejected");
                    status.setStyle("-fx-text-fill: #a93d3a;");
                    rightSection.getChildren().add(status);
                    row.getChildren().addAll(username, rightSection);
                    rows.getChildren().add(row);
                    continue;
                } else {
                    Label accept = new Label("Accept");
                    accept.setStyle("-fx-text-fill: #3c7d52; -fx-padding: 10px; -fx-background-radius: 10px; -fx-background-color: #def0d8; -fx-cursor: hand;");
                    int finalI1 = i;
                    accept.setOnMouseClicked(e -> {
                        App.getServerConnector().acceptGameRequest(App.getLoggedInUser().getGameRequests().get(finalI1));
                        App.getAppView().showAlert("Game request accepted", AlertType.INFO.getType());
                        //updateFriendRequestList();
                    });
                    Label reject = new Label("Reject");
                    reject.setStyle("-fx-text-fill: #a93d3a; -fx-padding: 10px; -fx-background-radius: 10px; -fx-background-color: #f2dedf; -fx-cursor: hand;");
                    reject.setOnMouseClicked(e -> {
                        App.getServerConnector().rejectGameRequest(App.getLoggedInUser().getGameRequests().get(finalI1));
                        App.getAppView().showAlert("Game request rejected", AlertType.INFO.getType());
                        //updateFriendRequestList();
                    });
                    rightSection.getChildren().addAll(accept, reject);
                    row.getChildren().addAll(username, rightSection);
                }
            }
            rows.getChildren().add(row);
        }
        mainScrollPane.setContent(rows);
    }

    private Label setUsernameLabel(int i) {
        GameRequest gameRequest = App.getLoggedInUser().getGameRequests().get(i);
        String usernameText = User.getUserByID(gameRequest.getSenderID()).getUsername();
        if (usernameText.equals(App.getLoggedInUser().getUsername())) {
            usernameText = User.getUserByID(gameRequest.getReceiverID()).getUsername();
        }
        String out = usernameText + " " + gameRequest.getHour() + ":" + gameRequest.getMinute() + ":" + gameRequest.getSecond();
        return new Label(out);
    }

    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}
