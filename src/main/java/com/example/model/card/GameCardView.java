package com.example.model.card;

import com.example.Main;
import com.example.model.App;
import com.example.model.card.enums.CardData;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class GameCardView extends Pane {
    private final String srcPath = Main.class.getResource("/images/inGameCards/").toExternalForm();
    private final double height = 82;
    private final double width = 57;
    private Card card;
    private CardData cardData;
    private Rectangle cardBase;
    boolean isDragged = false;

    // Static variable to track the currently selected card
    private static GameCardView selectedCard = null;

    public GameCardView(Card card) {
        this.card = card;
        this.cardData = card.getCardData();
        cardBase = new Rectangle(width, height);
        cardBase.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
        cardBase.setFill(Color.DARKGRAY);
        Image image = new Image(srcPath + cardData.getImageAddress());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");

        // Add mouse event handlers for hover effect
        imageView.setOnMouseEntered(event -> onMouseEnter());
        imageView.setOnMouseExited(event -> onMouseExit());
        imageView.setOnMouseClicked(event -> onMouseClicked());

        this.getChildren().addAll(cardBase, imageView);
        setLabelAndAbilitiesLabel();
        addCardViewToCard();
    }

    private void onMouseEnter() {
        // Check if the Y position is less than 600
        if (isDragged) {
            this.setTranslateY(0);
            this.setEffect(null);
            return; // Disable event
        }

        // Move the pane up by 15 pixels if it's not already selected
        if (selectedCard != this) {
            this.setTranslateY(-15);
        }

        // Add drop shadow effect if it's not already selected
        if (selectedCard != this) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.YELLOW);
            dropShadow.setRadius(10);
            this.setEffect(dropShadow);
        }
    }

    private void onMouseExit() {
        // Check if the Y position is less than 600
        if (isDragged) {
            this.setTranslateY(0);
            this.setEffect(null);
            return; // Disable event
        }

        // Move the pane back to original position if it's not selected
        if (selectedCard != this) {
            this.setTranslateY(0);
            this.setEffect(null);
        }
    }

    private void onMouseClicked() {
        // Check if the Y position is less than 600
        if (isDragged) {
            this.setTranslateY(0);
            this.setEffect(null);
            return; // Disable event
        }

        // Deselect the previously selected card
        if (selectedCard != null) {
            selectedCard.setTranslateY(0);
            selectedCard.setEffect(null);
        }

        // Select the current card
        selectedCard = this;
        this.setTranslateY(-15);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.YELLOW);
        dropShadow.setRadius(10);
        this.setEffect(dropShadow);
    }

    private void setLabelAndAbilitiesLabel() {
        if (!(card instanceof SpecialCard)) {
            setLabelAndAbilitiesLabelForNonSpecialCards();
        } else {
            setLabelAndAbilitiesLabelForSpecialCards();
        }
    }

    private void setLabelAndAbilitiesLabelForSpecialCards() {
        ImageView ability = com.example.model.card.enums.CardData.getImageViewAbilitySpecialCards(card.getCardData());
        ability.setLayoutX(2);
        ability.setLayoutY(2);
        ability.setFitWidth(25);
        ability.setFitHeight(25);
        this.getChildren().add(ability);
    }

    private void setLabelAndAbilitiesLabelForNonSpecialCards() {
        Label power = new Label("" + ((UnitCard) card).getCurrentPower());
        power.setFont(Font.font("GWENT", FontWeight.BOLD, 15));
        power.setAlignment(Pos.CENTER);
        power.setTranslateX(4);
        power.setTranslateY(4);
        power.setPrefHeight(20);
        power.setPrefWidth(20);
        ImageView powerBackGround;
        ImageView type = CardData.getPlaceToBeImageAddress(card.getPlace());
        type.setFitHeight(20);
        type.setFitWidth(20);
        type.setTranslateY(height - 20 - 2);
        type.setTranslateX(width - 20 - 2);
        if (card.getAbility() != null) {
            ImageView ability = CardData.getImageAbilityForUnitCards(card.getAbility());
            ability.setFitHeight(20);
            ability.setFitWidth(20);
            ability.setTranslateY(height - 20 - 2);
            ability.setTranslateX(width - 20 - 20 - 2 - 2);
            this.getChildren().add(ability);
        }
        if (((UnitCard) card).isHero()) {
            power.setTextFill(Paint.valueOf("white"));
            powerBackGround = CardData.getPowerBackGroundForHero();
        } else {
            power.setTextFill(Paint.valueOf("black"));
            powerBackGround = CardData.getPowerBackGroundForUnitCard();
        }
        powerBackGround.setLayoutX(0);
        powerBackGround.setLayoutY(0);
        powerBackGround.setFitWidth(45);
        powerBackGround.setFitHeight(45);
        this.getChildren().addAll(powerBackGround, power, type);
    }

    private void addCardViewToCard() {
        card.setGameCardView(this);
    }

    public Card getCard() {
        return card;
    }

    public com.example.model.card.enums.CardData getCardData() {
        return cardData;
    }

    public void setDragged(boolean dragged) {
        isDragged = dragged;
    }
}
