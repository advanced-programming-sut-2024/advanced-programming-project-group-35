package com.example.model.card;

import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;

public class Card {
    private Place place;
    private Ability ability;
    private CardData cardData;
    public Card(Place place, Ability ability, CardData cardData) {
        this.cardData = cardData;
        this.place = place;
        this.ability = ability;
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
