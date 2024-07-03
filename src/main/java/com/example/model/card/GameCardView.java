package com.example.model.card;

import com.example.Main;
import com.example.model.App;
import com.example.model.card.enums.Abilities;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
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
    private Label power;
    private ImageView powerBackGround;

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
        ability.setLayoutX(2);
        ability.setLayoutY(2);
        ability.setFitWidth(25);
        ability.setFitHeight(25);
        this.getChildren().add(ability);
    }

    private void setLabelAndAbilitiesLabelForNonSpecialCards() {
        power = new Label("" + ((UnitCard) card).getCurrentPower());
        power.setFont(Font.font("GWENT", FontWeight.BOLD, 15));
        power.setAlignment(Pos.CENTER);
        power.setTranslateX(4);
        power.setTranslateY(4);
        power.setPrefHeight(20);
        power.setPrefWidth(20);
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

    public CardData getCardData() {
        return cardData;
    }

    public void setDragged(boolean dragged) {
        isDragged = dragged;
    }

    public void doAbilityAnimation(AbilityName abilityName) {
        Image musterImage = new Image(getSrcPathForAnimImagesWithAbilityName(abilityName));
        ImageView musterImageView = new ImageView(musterImage);

        musterImageView.setFitWidth(40);
        musterImageView.setFitHeight(40);
        musterImageView.setTranslateX((width - 40) / 2);
        musterImageView.setTranslateY((height - 40) / 2);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event1 -> {
                this.getChildren().remove(musterImageView);
            }));
            this.getChildren().add(musterImageView);
            timeline1.setCycleCount(1);
            timeline1.play();
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private String getSrcPathForAnimImagesWithAbilityName(AbilityName abilityName) {
        switch (abilityName) {
            case MUSTER -> {
                return GameCardView.class.getResource("/images/icons/anim_muster.png").toExternalForm();
            }
            case MORALE_BOOST -> {
                return GameCardView.class.getResource("/images/icons/anim_morale.png").toExternalForm();
            }
            case SPY -> {
                return GameCardView.class.getResource("/images/icons/anim_spy.png").toExternalForm();
            }
            default -> {
                return null;
            }
        }
    }

    public void updatePowerLabel() {
        if (((UnitCard) card).isHero()) {
            power.setText("" + ((UnitCard) card).getCurrentPower());
            power.setTextFill(Paint.valueOf(Color.rgb(219, 136, 40).toString()));
        } else if (!((UnitCard) card).isHero()) {
            power.setText("" + ((UnitCard) card).getCurrentPower());
            power.setTextFill(Paint.valueOf(Color.rgb(219, 136, 40).toString()));
        }
    }

    public void setPowerDefault() {
        if (((UnitCard) card).isHero()) {
            power.setText("" + ((UnitCard) card).getPower());
            power.setTextFill(Paint.valueOf("white"));
        } else if (!((UnitCard) card).isHero()) {
            power.setText("" + ((UnitCard) card).getPower());
            power.setTextFill(Paint.valueOf("black"));
        }
    }
}
