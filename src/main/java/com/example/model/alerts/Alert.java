package com.example.model.alerts;

import com.example.model.App;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Alert extends StackPane {
    private String alertType;
    private String message;
    public final double width = 200;
    public final double height = 60;
    private final Pane pane;

    public Alert(String message, String alertType, Pane pane) {
        this.alertType = alertType;
        this.message = message;
        this.pane = pane;
        init();
    }
    private void init() {
        this.getStylesheets().add(Alert.class.getResource("/CSS/alertStyle.css").toExternalForm());
        ImageView imageView = new ImageView(new Image(Alert.class.getResource("/images/terminal-exit-button.png").toExternalForm(), 20, 20, true, true));
        imageView.setOnMouseClicked(e -> App.getAppView().removeAlert(this.pane));

        String containerStyleClassName = alertType + "-vbox-button-container";

        VBox buttonContainer = new VBox(imageView);
        buttonContainer.getStyleClass().add(containerStyleClassName);
        buttonContainer.prefWidth(width);

        String labelContainerStyleClassName = alertType + "-vbox-label-container";
        String labelStyleClassName = alertType + "-label-message";

        Label label = new Label(message);
        VBox labelContainer = new VBox(label);
        label.getStyleClass().add(labelStyleClassName);
        labelContainer.getStyleClass().add(labelContainerStyleClassName);
        labelContainer.setPrefWidth(width);
        labelContainer.setPrefHeight(height);

        VBox container = new VBox(buttonContainer, labelContainer);

        this.getChildren().add(container);
    }
}
