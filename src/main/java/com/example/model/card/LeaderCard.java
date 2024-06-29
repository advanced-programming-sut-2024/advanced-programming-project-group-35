package com.example.model.card;

import com.example.model.card.enums.CardName;
import com.example.model.card.enums.LeaderName;

public class LeaderCard {
    private boolean canDoAction; //TODO باید چک شه وفتی میخوایم توانایی لیدر استفاده کنیم این ترو باشه
    private LeaderAbility ability;
    private LeaderName leaderName;
    private String imageAddress;

    public LeaderCard(String imageAddress,LeaderName leaderName, LeaderAbility ability) {
        this.imageAddress = imageAddress;
        this.ability = ability;
        this.leaderName = leaderName;
    }

    public LeaderName getLeaderName() {
        return leaderName;
    }
    public String getName() {
        return leaderName.getName();
    }

    public boolean CanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }
}
