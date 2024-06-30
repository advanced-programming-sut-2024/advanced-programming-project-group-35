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
    public void apply(Table table) {
        Hand hand = table.getCurrentPlayer().getBoard().getHand();
        ArrayList<Card> handCopy = new ArrayList<>(hand.getCards());
        for (Card card : handCopy) {
            if (card.getCardName() == CardData.weather_rain) {
                if (!table.getSpellPlace().isSpellPlaceFull()) {
                    hand.removeCard(card);
                    table.getSpellPlace().addCard((WeatherCard) card);
                    card.getAbility().apply(new AbilityContext(table, null, null));
                }
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
