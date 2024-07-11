package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.model.User;
import com.example.model.alerts.AlertType;
import com.example.model.FriendRequest;
import com.example.view.Menu;
import com.example.view.OutputView;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FriendsMenuControllerView {
    public TextField friendUsername;
    public ScrollPane mainScrollPane = new ScrollPane();
    public ScrollPane newFriendScrollPane;
    public Pane findNewFriendPane;

    @FXML
    public void initialize() {
        updateFriendRequestList();
    }

    public void updateFriendRequestList() {
        mainScrollPane.setStyle("-fx-alignment: center;");
        VBox rows = new VBox();
        rows.setSpacing(20);
        rows.setAlignment(Pos.TOP_CENTER);
        rows.setPrefWidth(500);
        if (App.getLoggedInUser().getFriendRequests() == null) {
            return;
        }
        for (int i = 0; i < App.getLoggedInUser().getFriendRequests().size(); i++) {
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
            if (App.getLoggedInUser().getFriendRequests().get(i).getSender().getID() == App.getLoggedInUser().getID()) {
                Label status = new Label();
                if (!App.getLoggedInUser().getFriendRequests().get(i).isAccepted() && !App.getLoggedInUser().getFriendRequests().get(i).isRejected()) {
                    status.setText("Pending");
                    status.setStyle("-fx-text-fill: #396b84;");
                } else if (App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
                    status.setText("Accepted");
                    status.setStyle("-fx-text-fill: #3c7d52;");
                } else {
                    status.setText("Rejected");
                    status.setStyle("-fx-text-fill: #a93d3a;");
                }
                rightSection.getChildren().add(status);
                row.getChildren().addAll(username, rightSection);
            } else {
                if (App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
                    Label status = new Label("You Accepted");
                    status.setStyle("-fx-text-fill: #3c7d52;");
                    rightSection.getChildren().add(status);
                    row.getChildren().addAll(username, rightSection);
                    rows.getChildren().add(row);
                    continue;
                } else if (App.getLoggedInUser().getFriendRequests().get(i).isRejected()) {
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
                        App.getServerConnector().acceptFriendRequest(App.getLoggedInUser().getFriendRequests().get(finalI1));
                        App.getAppView().showAlert("Friend request accepted", AlertType.INFO.getType());
                        //updateFriendRequestList();
                    });
                    Label reject = new Label("Reject");
                    reject.setStyle("-fx-text-fill: #a93d3a; -fx-padding: 10px; -fx-background-radius: 10px; -fx-background-color: #f2dedf; -fx-cursor: hand;");
                    reject.setOnMouseClicked(e -> {
                        App.getServerConnector().rejectFriendRequest(App.getLoggedInUser().getFriendRequests().get(finalI1));
                        App.getAppView().showAlert("Friend request rejected", AlertType.INFO.getType());
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
        String usernameText = App.getLoggedInUser().getFriendRequests().get(i).getSender().getUsername();
        if (usernameText.equals(App.getLoggedInUser().getUsername())) {
            usernameText = App.getLoggedInUser().getFriendRequests().get(i).getReceiver().getUsername();
        }
        return new Label(usernameText);
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }

    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }

    public void sendFriendRequest(String username) {
        FriendRequest friendRequest = new FriendRequest(App.getLoggedInUser(), App.getUserByUsername(username));
        App.getServerConnector().sendFriendRequest(friendRequest);
        //App.getAppView().showAlert("Friend request sent to " + username, AlertType.SUCCESS.getType());
    }

    public void findUsers(MouseEvent mouseEvent) {
        if (friendUsername.getText().equals("")) {
            App.getAppView().showAlert("Please enter a username.", AlertType.ERROR.getType());
            return;
        }

        VBox rows = new VBox();
        rows.setSpacing(20);
        rows.setAlignment(Pos.TOP_CENTER);
        rows.setPrefWidth(980);
        // i need a search system on allUsers and show the results here
        String searchText = friendUsername.getText().toLowerCase();
        boolean flag = false;
        for (int i = 0; i < App.getAllUsers().size(); i++) {
            String username = App.getAllUsers().get(i).getUsername().toLowerCase();
            if (username.matches(".*" + searchText + ".*")) {
                flag = true;
                Label usernameLabel = new Label(App.getAllUsers().get(i).getUsername());
                usernameLabel.setTranslateX(20);
                usernameLabel.setPrefWidth(180);
                usernameLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-cursor: hand;");

                HBox row = new HBox();
                row.setPrefWidth(980);
                row.setAlignment(Pos.CENTER);
                row.setSpacing(150);
                row.setAlignment(Pos.CENTER_LEFT);

                Label score = new Label("Score: " + App.getAllUsers().get(i).getScore());
                score.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-cursor: hand;");
                score.setPrefWidth(180);

                Label nickname = new Label(App.getAllUsers().get(i).getNickname());
                nickname.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-cursor: hand;");
                nickname.setPrefWidth(180);

                HBox rightSection = new HBox();
                rightSection.setSpacing(15);
                rightSection.setAlignment(Pos.CENTER_RIGHT);
                rightSection.setPrefWidth(170);

                Label sendRequest = new Label("Send Request");
                sendRequest.setStyle("-fx-text-fill: #3c7d52; -fx-padding: 10px; -fx-background-radius: 10px; -fx-background-color: #def0d8; -fx-cursor: hand;");
                int finalI = i;
                sendRequest.setOnMouseClicked(e -> {
                    sendFriendRequest(App.getAllUsers().get(finalI).getUsername());
                });

                rightSection.getChildren().add(sendRequest);
                row.getChildren().addAll(usernameLabel, nickname, score, rightSection);
                rows.getChildren().add(row);
            }
        }
        if (!flag) {
            App.getAppView().showAlert("No user found with this username.", AlertType.ERROR.getType());
        }
        newFriendScrollPane.setContent(rows);
    }

    public void openFindFriendMenu(MouseEvent mouseEvent) {
        findNewFriendPane.setVisible(true);
    }

    public void backToFriendListMenu(MouseEvent mouseEvent) {
        findNewFriendPane.setVisible(false);
    }
}
