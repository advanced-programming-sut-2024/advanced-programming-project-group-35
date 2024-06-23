package com.example.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Alert extends StackPane {
    private String message;
    private final double width = 250;
    private final double height = 80;

    public Alert(String message) {
        this.message = message;
        init();
    }
    private void init() {
        this.getStylesheets().add(Alert.class.getResource("/CSS/alertStyle.css").toExternalForm());
        ImageView imageView = new ImageView(new Image(Alert.class.getResource("/images/terminal-exit-button.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> App.getAppView().removeAlert());

        VBox buttonContainer = new VBox(imageView);
        buttonContainer.getStyleClass().add("vbox-button-container");
        buttonContainer.prefWidth(width);

        VBox labelContainer = new VBox(new Label(message));
        labelContainer.getStyleClass().add("vbox-label-container");
        labelContainer.setPrefWidth(width);
        labelContainer.setPrefHeight(height);

        VBox container = new VBox(buttonContainer, labelContainer);

        this.getChildren().add(container);
    }
}
