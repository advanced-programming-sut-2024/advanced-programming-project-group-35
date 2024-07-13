package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.WeatherCard;
import com.example.model.game.Table;

import java.util.ArrayList;

public class CommnderOfTheRedRidersAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        ArrayList<WeatherCard> weatherCards = new ArrayList<>();
        for (Card card : abilityContext.getTable().getCurrentPlayer().getBoard().getHand().getCards()) {
            if (card instanceof WeatherCard) {
                weatherCards.add((WeatherCard) card);
                break;
            }
        }
        for (Card card : abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getCards()) {
            if (card instanceof WeatherCard) {
                weatherCards.add((WeatherCard) card);
            }
        }
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
