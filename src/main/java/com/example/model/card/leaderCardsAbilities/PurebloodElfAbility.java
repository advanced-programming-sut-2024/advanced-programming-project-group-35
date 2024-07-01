package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.WeatherCard;
import com.example.model.card.enums.CardData;
import com.example.model.game.Hand;
import com.example.model.game.Table;
import com.example.model.game.place.SpellPlace;

public class PurebloodElfAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        Hand hand = table.getCurrentPlayer().getBoard().getHand();
        SpellPlace spellPlace = table.getSpellPlace();
        Card weatherCard = hand.getCardByName(CardData.weather_frost);
        if (weatherCard != null) {
            if (!spellPlace.isSpellPlaceFull()) {
                hand.removeCard(weatherCard);
                spellPlace.addCard((WeatherCard) weatherCard);
                //TODO گرافیک انتقال کارت
                if (table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName() == CardData.leaders_realms_foltest_gold) {
                    hand.addCard(weatherCard);
                    spellPlace.removeCard((WeatherCard) weatherCard);
                } else {
                    weatherCard.getAbility().apply(new AbilityContext(table, null, null));
                    // TODO تو ابیلیتی ودر کارت ها باید چک شه که لیدر کینگ برن نباشه
                }
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
