package com.example.model.game.place;

import com.example.model.card.Card;
import com.example.model.card.SpecialCard;
import com.example.model.card.UnitCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Row {
    private ObservableList<SpecialCard> specialPlace = FXCollections.observableArrayList();
    private Place place;
    private boolean applyWeather = false;
    private int strength = 0;

    public boolean isApplyWeather() {
        return applyWeather;
    }

    public void setApplyWeather(boolean applyWeather) {
        this.applyWeather = applyWeather;
    }

    public ObservableList<SpecialCard> getSpecialPlace() {
        return specialPlace;
    }

    public SpecialCard getSpecialCard() {
        return specialPlace.get(0);
    }

    public void setSpecialCard(SpecialCard specialCard) {
        this.specialPlace.add(specialCard);
    }

    private ObservableList<Card> cards = FXCollections.observableArrayList();

    public ObservableList<Card> getCards() {
        return cards;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void addCard(Card card) {
        cards.add(card);
        updateStrength();
    }

    public void removeCard(Card card) {
        cards.remove(card);
        updateStrength();
    }

    public void clear() {
        cards.clear();
    }

    public int getStrength() {
        return strength;
    }

    public int getNonHeroStrength() {
        int result = 0;
        for (Card card : cards) {
            if (card != null && (card instanceof UnitCard) && !((UnitCard)card).isHero()) {
                result += ((UnitCard)card).getCurrentPower();
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void updateStrength() {
        int result = 0;
        for (Card card : cards) {
            if (card != null && (card instanceof UnitCard)) {
                result += ((UnitCard)card).getCurrentPower();
            }
        }
        strength = result;
    }
}
