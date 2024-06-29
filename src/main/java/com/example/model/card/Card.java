package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.game.place.UnitPlace;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
    private final String imageAddress;
    private UnitPlace place;
    private AbilityName abilityName;
    private Ability ability;
    private CardName cardName;
    public Card(String imageAddress, UnitPlace place, AbilityName abilityName, Ability ability, CardName cardName) {
        this.cardName = cardName;
        this.imageAddress = imageAddress;
        this.place = place;
        this.abilityName = abilityName;
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

    public String getImageAddress() {
        return imageAddress;
    }

    public AbilityName getAbilityName() {
        return abilityName;
    }

    public Ability getAbility() {
        return ability;
    }
}
