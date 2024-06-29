package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.game.place.UnitPlace;
import javafx.scene.shape.Rectangle;

public class Card {
    private UnitPlace place;
    private Ability ability;
    private CardName cardName;
    public Card(UnitPlace place, Ability ability, CardName cardName) {
        this.cardName = cardName;
        this.place = place;
        this.ability = ability;
    }

    public CardName getCardName() {
        return cardName;
    }
    public String getName() {
        return cardName.getName();
    }

    public UnitPlace getPlace() {
        return place;
    }

    public Ability getAbility() {
        return ability;
    }
}
