package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.WeatherCard;
import com.example.model.card.enums.CardData;
import com.example.model.game.Hand;
import com.example.model.game.Table;

import java.util.ArrayList;

public class TheWhiteFlameAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        Hand hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand();
        ArrayList<Card> handCopy = new ArrayList<>(hand.getCards());
        for (Card card : handCopy) {
            if (card.getCardName() == CardData.weather_rain) {
                if (!abilityContext.getTable().getSpellPlace().isSpellPlaceFull()) {
                    hand.removeCard(card);
                    abilityContext.getTable().getSpellPlace().addCard((WeatherCard) card);
                    card.getAbility().apply(new AbilityContext(abilityContext.getTable(), null, null));
                }
            }
        }
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
