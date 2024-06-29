package com.example.model.card;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PreGameCard extends StackPane {
    private String name;
    private int power;
    private String ability;
    private Image image;

    public PreGameCard(String name, int power, String ability, String imagePath) {
        this.name = name;
        this.power = power;
        this.ability = ability;
        this.image = new Image(imagePath);

        Rectangle cardBase = new Rectangle(120, 200);
        cardBase.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
        //cardBase.setArcWidth(15);
        //cardBase.setArcHeight(15);
        cardBase.setFill(Color.DARKGRAY);

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(200);
        imageView.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");

        //Label nameLabel = new Label(name);
//        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        nameLabel.setTextFill(Color.WHITE);
//
//        Label powerLabel = new Label(String.valueOf(power));
//        powerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//        powerLabel.setTextFill(Color.GOLD);
//
//        Label abilityLabel = new Label(ability);
//        abilityLabel.setFont(Font.font("Arial", 10));
//        abilityLabel.setTextFill(Color.LIGHTGRAY);
//        abilityLabel.setWrapText(true);
//
//        VBox cardInfo = new VBox(5);
//        cardInfo.getChildren().addAll(nameLabel, powerLabel, abilityLabel);

        this.getChildren().addAll(cardBase, imageView);
//        imageView.setTranslateY(-40);
//        cardInfo.setTranslateY(40);

        this.setOnMouseEntered(e -> {this.setScaleX(1.1); this.setScaleY(1.1);});
        this.setOnMouseExited(e -> {this.setScaleX(1); this.setScaleY(1);});
        this.setStyle("-fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0, 0, 0); -fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getAbility() {
        return ability;
    }
}