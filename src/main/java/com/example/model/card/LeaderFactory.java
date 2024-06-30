package com.example.model.card;

import com.example.model.card.enums.CardData;
import com.example.model.card.enums.LeaderCardsAbilities;

public class LeaderFactory {
    public static LeaderCard getLeaderCardByName(String cardName) {
        CardData cardData = CardData.getCardDataByName(cardName);
        return new LeaderCard(cardData, LeaderCardsAbilities.getLeaderAbilityByLeaderCardName(cardData.getName()));
    }
}
