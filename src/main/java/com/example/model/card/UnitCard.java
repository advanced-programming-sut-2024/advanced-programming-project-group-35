package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;

public class UnitCard extends Card {
    private int power;
    private int currentPower;
    private boolean isHero;
    private boolean noRemove;

    public UnitCard(int power, Ability ability, AbilityName abilityName, Place place, boolean isHero, CardData cardData, boolean noRemove) {
        super(place, ability, abilityName,cardData);
        this.power = power;
        this.currentPower = power;
        this.isHero = isHero;
        this.noRemove = noRemove;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean noRemove() {
        return noRemove;
    }

    public void setNoRemove(boolean noRemove) {
        this.noRemove = noRemove;
    }

    public boolean isHero() {
        return isHero;
    }

    public void duplicatePower() {
        currentPower *= 2;
    }

    public void setPowerToDefault() {
        currentPower = power;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void applyMoralBoost() {
        currentPower++;
    }

    public int getPower() {
        return power;
    }

    public void disApplyMoralBoost() {
        currentPower = power;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }


    public void setPowerOne() {
        currentPower = 1;
    }
    public void setPowerHalf() {
        currentPower = (currentPower / 2);
    }
}
