package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardName;
import com.example.model.game.place.UnitPlace;

public class UnitCard extends Card {
    private final int power;
    private int currentPower;
    private boolean isHero;
    private boolean noRemove; // برای وقتیه که ما میخوایم دور رو عوض کنیم و اگه یه کارتی اینش ترو باشه نباید از صفحه حدف شه (برای ابیلیتی مانستر)

    public UnitCard(int power, String imageAddress, Ability ability, UnitPlace place, boolean isHero, CardName cardName, boolean noRemove) {
        super(imageAddress, place, ability, cardName);
        this.power = power;
        this.isHero = isHero;
        this.noRemove = noRemove;
    }

    public boolean isNoRemove() {
        return noRemove;
    }

    public void setNoRemove(boolean noRemove) {
        this.noRemove = noRemove;
    }

    public boolean isHero() {
        return isHero;
    }

    public void applyCommanderHorn() {
        currentPower *= 2;
    }

    public void disApplyCommanderHorn() {
        currentPower = power;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void applyMoralBoost() {
        currentPower++;
    }

    public void disApplyMoralBoost() {
        currentPower = power;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }

    public void applyMardroeme() {
        //TODO
    }

    public void disApplyMardroeme() {

    }
}
