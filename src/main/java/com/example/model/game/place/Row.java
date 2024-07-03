package com.example.model.game.place;

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

    private ObservableList<UnitCard> cards = FXCollections.observableArrayList();

    public ObservableList<UnitCard> getCards() {
        return cards;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void addCard(UnitCard card) {
        cards.add(card);
        updateStrength();
    }

    public void removeCard(UnitCard card) {
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
        for (UnitCard card : cards) {
            if (!card.isHero()) {
                result += card.getCurrentPower();
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void updateStrength() {
        int result = 0;
        for (UnitCard card : cards) {
            if (card != null) {
                result += card.getCurrentPower();
            }
        }
        strength = result;
    }
}
