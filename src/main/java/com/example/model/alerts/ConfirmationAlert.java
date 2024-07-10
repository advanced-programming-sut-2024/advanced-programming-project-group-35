package com.example.model.alerts;

import com.example.model.App;
import com.example.model.FriendRequest;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConfirmationAlert extends StackPane {
    private String alertType;
    private String message;
    public final double width = 250;
    public final double height = 80;
    private FriendRequest friendRequest;
    private boolean result;
    private boolean pending = true;

    public ConfirmationAlert(String message, String alertType, FriendRequest friendRequest) {
        this.alertType = alertType;
        this.message = message;
        this.friendRequest = friendRequest;
        init();
    }

    public ConfirmationAlert(String message, String alertType) {
        this.alertType = alertType;
        this.message = message;
        init();
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    private void init() {
        this.getStylesheets().add(Alert.class.getResource("/CSS/alertStyle.css").toExternalForm());
        String labelContainerStyleClassName = alertType + "-vbox-label-container";
        String labelStyleClassName = alertType + "-label-message";

        Text label = new Text(message);
        VBox labelContainer = new VBox(label);
        label.setWrappingWidth(230);
        label.getStyleClass().add(labelStyleClassName);
        labelContainer.getStyleClass().add(labelContainerStyleClassName);
        labelContainer.setPrefWidth(width);
        labelContainer.setPrefHeight(height);
        Button rejectButton = new Button("Reject");
        rejectButton.setStyle("-fx-background-color: #a93d3a;-fx-text-fill: #f2dedf;-fx-font-size: 16px;-fx-padding: 8;-fx-border-color: #f2dedf;-fx-border-width: 2px;-fx-border-insets: 2;-fx-background-radius: 10px; -fx-border-radius: 10px;-fx-cursor: hand;");
        rejectButton.setOnMouseClicked(e -> {
            result = false;
            pending = false;
            System.out.println("Friend request rejected");
        });
        Button acceptButton = new Button("Accept");
        acceptButton.setStyle("-fx-background-color: #3c7d52;-fx-text-fill: #def0d8;-fx-font-size: 16px;-fx-padding: 8;-fx-border-color: #def0d8;-fx-border-width: 2px;-fx-border-insets: 2;-fx-background-radius: 10px; -fx-border-radius: 10px;-fx-cursor: hand;");
        acceptButton.setOnMouseClicked(e -> {
            result = true;
            pending = false;
            System.out.println("Friend request accepted");
        });
        HBox buttonsHbox = new HBox(rejectButton, acceptButton);
        buttonsHbox.setSpacing(20);
        buttonsHbox.setAlignment(Pos.CENTER);


        VBox container = new VBox(labelContainer, buttonsHbox);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        container.setPrefHeight(80);
        container.setPrefWidth(250);
        this.setStyle("-fx-background-color: #d7eef6;-fx-border-color: #6994A2;-fx-border-width: 2px;-fx-border-insets: 2px;-fx-background-radius: 10px; -fx-border-radius: 10px; -fx-padding: 10px");
        this.getChildren().add(container);
    }
}
