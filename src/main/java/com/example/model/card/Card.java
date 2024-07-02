package com.example.model.card;

import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
    private Place place;
    private Ability ability;
    private CardData cardData;
    private GameCardView gameCardView;
    public Card(Place place, Ability ability, CardData cardData) {
        this.cardData = cardData;
        this.place = place;
        this.ability = ability;
    }


    public void setGameCardView(GameCardView gameCardView) {
        this.gameCardView = gameCardView;
    }

    public CardData getCardData() {
        return cardData;
    }

    public CardData getCardName() {
        return cardData;
    }
    public String getName() {
        return cardData.getName();
    }

    public Place getPlace() {
        return place;
    }

    public Ability getAbility() {
        return ability;
    }
}
