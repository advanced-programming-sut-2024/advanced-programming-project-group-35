package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
    private Place place;
    private Ability ability;
    private CardData cardData;
    private GameCardView gameCardView;
    private AbilityName abilityName;
    private int idInGame;
    public Card(Place place, Ability ability, AbilityName abilityName, CardData cardData) {
        this.abilityName = abilityName;
        this.cardData = cardData;
        this.place = place;
        this.ability = ability;
        this.setArcHeight(20);
        this.setArcWidth(20);
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public void setCardData(CardData cardData) {
        this.cardData = cardData;
    }

    public void setAbilityName(AbilityName abilityName) {
        this.abilityName = abilityName;
    }

    public int getIdInGame() {
        return idInGame;
    }

    public void setIdInGame(int idInGame) {
        this.idInGame = idInGame;
    }

    public AbilityName getAbilityName() {
        return abilityName;
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
