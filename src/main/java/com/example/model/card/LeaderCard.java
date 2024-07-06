package com.example.model.card;

import com.example.model.card.enums.CardData;
import com.example.model.card.leaderCardsAbilities.LeaderAbility;

public class LeaderCard {
    private boolean canDoAction;
    private LeaderAbility ability;
    private CardData cardData;

    public LeaderCard(CardData cardData, LeaderAbility ability) {
        this.ability = ability;
        this.cardData = cardData;
        if (cardData.getName().equals("leaders_skellige_king_bran")) {
            this.canDoAction = false;
        } else {
            this.canDoAction = true;
        }
    }

    public LeaderAbility getAbility() {
        return ability;
    }

    public CardData getLeaderName() {
        return cardData;
    }
    public String getName() {
        return cardData.getName();
    }

    public boolean canDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }
}
