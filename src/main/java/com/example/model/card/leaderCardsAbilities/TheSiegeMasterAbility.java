package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.WeatherCard;
import com.example.model.card.enums.CardData;
import com.example.model.game.Hand;
import com.example.model.game.Table;
import com.example.model.game.place.SpellPlace;

public class TheSiegeMasterAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        Hand hand = abilityContext.getTable().getCurrentPlayer().getBoard().getHand();
        SpellPlace spellPlace = abilityContext.getTable().getSpellPlace();
        Card weatherCard = hand.getCardByName(CardData.weather_fog);
        if (weatherCard != null) {
            if (!spellPlace.isSpellPlaceFull()) {
                hand.removeCard(weatherCard);
                spellPlace.addCard((WeatherCard) weatherCard);
                //TODO گرافیک انتقال کارت
                if (abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName() == CardData.leaders_realms_foltest_gold) {
                    hand.addCard(weatherCard);
                    spellPlace.removeCard((WeatherCard) weatherCard);
                } else {
                    weatherCard.getAbility().apply(new AbilityContext(abilityContext.getTable(), null, null));
                }
            }
        }
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
