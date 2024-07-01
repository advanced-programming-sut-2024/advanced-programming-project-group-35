package com.example.model.card;

import com.example.Main;
import com.example.model.card.enums.CardData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameCardView extends StackPane {
    private final String srcPath = Main.class.getResource("/images/inGameCards/").toExternalForm();
    private final double height = 95;
    private final double width = 65;
    private Card card;
    private CardData cardData;

    public GameCardView(Card card) {
        this.card = card;
        this.cardData = card.getCardData();
        Rectangle cardBase = new Rectangle(height, width);
        cardBase.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
        cardBase.setFill(Color.DARKGRAY);
        Image image = new Image(srcPath + cardData.getImageAddress());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
        this.getChildren().addAll(cardBase, imageView);
    }

    public Card getCard() {
        return card;
    }

    public CardData getCardData() {
        return cardData;
    }
}
