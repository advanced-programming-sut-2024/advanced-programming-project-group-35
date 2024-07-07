package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.model.User;
import com.example.model.alerts.AlertType;
import com.example.view.Menu;
import com.example.view.OutputView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FriendsMenuControllerView {
    public ScrollPane mainScrollPane = new ScrollPane();

    @FXML
    public void initialize() {
        updateFriendRequestList();
    }

    private void updateFriendRequestList() {
        mainScrollPane.setStyle("-fx-alignment: center;");
        VBox rows = new VBox();
        rows.setSpacing(20);
        rows.setAlignment(Pos.TOP_CENTER);
        rows.setPrefWidth(500);
        for (int i = 0; i < App.getLoggedInUser().getFriendRequests().size(); i++) {
            HBox row = new HBox();
            row.setPrefWidth(500);
            Label username = new Label(App.getLoggedInUser().getFriendRequests().get(i).getSender().getUsername());
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
                if (!App.getLoggedInUser().getFriendRequests().get(i).isAccepted() && !App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
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
                    accept.setOnMouseClicked(e -> {
                        App.getLoggedInUser().getFriendRequests().get(finalI).accept();
                        App.getLoggedInUser().addFriend(App.getLoggedInUser().getFriendRequests().get(finalI).getSender());
                        App.getAppView().showAlert("Friend request accepted", AlertType.INFO.getType());
                        updateFriendRequestList();
                    });
                    Label reject = new Label("Reject");
                    reject.setStyle("-fx-text-fill: #a93d3a; -fx-padding: 10px; -fx-background-radius: 10px; -fx-background-color: #f2dedf; -fx-cursor: hand;");
                    reject.setOnMouseClicked(e -> {
                        App.getLoggedInUser().getFriendRequests().get(finalI).reject();
                        App.getAppView().showAlert("Friend request rejected", AlertType.INFO.getType());
                        updateFriendRequestList();
                    });
                    rightSection.getChildren().addAll(accept, reject);
                    row.getChildren().addAll(username, rightSection);
                }
            }
            rows.getChildren().add(row);
        }
        mainScrollPane.setContent(rows);
    }

    //    if (App.getLoggedInUser().getFriendRequests().size() == 0) {
//                    OutputView.showOutputAlert(Errors.NO_FRIEND_REQUESTS);
//                } else {
//                    Label status = new Label();
//                    if (!App.getLoggedInUser().getFriendRequests().get(i).isAccepted() && !App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
//                        status.setText("Pending");
//                    } else if (App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
//                        status.setText("Accepted");
//                    } else {
//                        status.setText("Rejected");
//                    }
//                    اینجا یه دونه ایف بذار که اگر فرستنده خودش بود رو چک کنه
//                    Button accept = new Button("Accept");
//                    accept.setOnMouseClicked(e -> {
//                        نمیدونم
//                   });
//                    Button reject = new Button("Reject");
//                    reject.setOnMouseClicked(e -> {
//                        نمیدونم
//                    });
//                    rightSection.getChildren().addAll(status, accept, reject);
//                    row.getChildren().addAll(label, rightSection);
//                    mainScrollPane.getChildren().addAll(row);
//                }
    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }

    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }
}
