package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.Card;
import com.example.model.card.WeatherCard;
import com.example.model.game.Table;

import java.util.ArrayList;

public class CommnderOfTheRedRidersAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        ArrayList<WeatherCard> weatherCards = new ArrayList<>();
        for (Card card : table.getCurrentPlayer().getBoard().getHand().getCards()) {
            if (card instanceof WeatherCard) {
                weatherCards.add((WeatherCard) card);
            }
        }
        for (Card card : table.getCurrentPlayer().getBoard().getDeck().getCards()) {
            if (card instanceof WeatherCard) {
                weatherCards.add((WeatherCard) card);
            }
        }
        //TODO باید اینکارت ها نمایش داده بشه یدونش انتخاب شه و بازی شه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
