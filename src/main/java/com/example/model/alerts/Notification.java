package com.example.model.alerts;

import com.example.model.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Notification extends VBox {
    private String message;
    String imageAddress;
    public final double width = 1400;
    public final double height = 100;

    public Notification(String message, String imageAddress) {
        this.imageAddress = imageAddress;
        this.message = message;
        this.setPrefHeight(800);
        this.setPrefWidth(1400);
        this.setAlignment(javafx.geometry.Pos.CENTER);
        init();
    }
    private void init() {
        this.getStylesheets().add(Alert.class.getResource("/CSS/notificationStyle.css").toExternalForm());

        ImageView imageView = new ImageView(new Image(imageAddress, 200, 200, true, true));
        Text label = new Text(message);
        label.setWrappingWidth(300);
        label.getStyleClass().add("label-message");
        HBox container = new HBox(imageView, label);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        container.prefWidth(width);
        container.prefHeight(height);
        container.setSpacing(30);
        container.getStyleClass().add("container");

        this.getChildren().add(container);
    }
}
