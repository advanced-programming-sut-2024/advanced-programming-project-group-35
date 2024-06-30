package com.example.model.game.place;

import com.example.model.card.SpecialCard;
import com.example.model.card.UnitCard;

import java.util.ArrayList;

public class Row {

    private SpecialCard specialPlace;
    private UnitPlace place;
    private boolean applyWeather = false;
    private int strength = 0;

    public boolean isApplyWeather() {
        return applyWeather;
    }

    public void setApplyWeather(boolean applyWeather) {
        this.applyWeather = applyWeather;
    }

    public SpecialCard getSpecialPlace() {
        return specialPlace;
    }

    public void setSpecialPlace(SpecialCard specialPlace) {
        this.specialPlace = specialPlace;
    }

    private ArrayList<UnitCard> cards = new ArrayList<>();

    public ArrayList<UnitCard> getCards() {
        return cards;
    }

    public UnitPlace getPlace() {
        return place;
    }

    public void setPlace(UnitPlace place) {
        this.place = place;
    }

    public void addCard(UnitCard card) {
        cards.add(card);
    }

    public void removeCard(UnitCard card) {
        cards.remove(card);
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
            result += card.getCurrentPower();
        }
        strength = result;
    }
}
