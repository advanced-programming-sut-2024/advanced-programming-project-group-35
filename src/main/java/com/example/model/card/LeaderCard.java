package com.example.model.card;

import com.example.model.card.enums.CardName;

public class LeaderCard {
    private boolean canDoAction;
    private LeaderAbility ability;
    private CardName cardName;
    private String imageAddress;

    public LeaderCard(String imageAddress,CardName cardName, LeaderAbility ability) {
        this.imageAddress = imageAddress;
        this.ability = ability;
    }

    public CardName getCardName() {
        return cardName;
    }
    public String getName() {
        return cardName.getName();
    }

    public boolean CanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }
}
