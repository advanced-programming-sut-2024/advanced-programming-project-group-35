package com.example.model.card;

import com.example.Main;
import com.example.model.card.enums.CardData;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameCardView extends Pane {
    private final String srcPath = Main.class.getResource("/images/inGameCards/").toExternalForm();
    private final double height = 95;
    private final double width = 65;
    private Card card;
    private CardData cardData;

    public GameCardView(Card card) {
        this.card = card;
        this.cardData = card.getCardData();
        Rectangle cardBase = new Rectangle(width, height);
        cardBase.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
        cardBase.setFill(Color.DARKGRAY);
        Image image = new Image(srcPath + cardData.getImageAddress());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: transparent;");
        this.getChildren().addAll(cardBase, imageView);
        setLabelAndAbilitiesLabel();
        addCardViewToCard();
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
        ability.setLayoutX(-3);
        ability.setLayoutY(-3);
        ability.setFitWidth(50);
        ability.setFitHeight(50);
        this.getChildren().add(ability);
    }

    private void setLabelAndAbilitiesLabelForNonSpecialCards() {
        Label power = new Label("" + ((UnitCard) card).getCurrentPower());
        power.setFont(Font.font("GWENT", FontWeight.BOLD, 15));
        power.setAlignment(Pos.CENTER);
        power.setLayoutX(0);
        power.setLayoutY(0);
        power.setPrefHeight(15);
        power.setPrefWidth(20);
        ImageView powerBackGround;
        ImageView type = CardData.getPlaceToBeImageAddress(card.getPlace());
        type.setFitHeight(25);
        type.setFitWidth(25);
        type.setLayoutY(73);
        type.setLayoutX(43);
        if (card.getAbility() != null) {
            ImageView ability = CardData.getImageAbilityForUnitCards(card.getAbility());
            ability.setFitHeight(25);
            ability.setFitWidth(25);
            ability.setLayoutY(73);
            ability.setLayoutX(16);
            this.getChildren().add(ability);
        }
        if (((UnitCard) card).isHero()) {
            power.setTextFill(Paint.valueOf("white"));
            powerBackGround = CardData.getPowerBackGroundForHero();
        } else {
            power.setTextFill(Paint.valueOf("black"));
            powerBackGround = CardData.getPowerBackGroundForUnitCard();
        }
        powerBackGround.setLayoutX(-3);
        powerBackGround.setLayoutY(-3);
        powerBackGround.setFitWidth(50);
        powerBackGround.setFitHeight(50);
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
}
