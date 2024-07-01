package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.Card;
import com.example.model.game.Deck;
import com.example.model.game.Table;

import java.util.Random;

public class DaisyOfTheValleyAbility implements LeaderAbility {
    //TODO باید در اول راند چک شه و این توانایی انجام بشه
    @Override
    public void apply(Table table) {
        Deck deck = table.getCurrentPlayer().getBoard().getDeck();
        Card card = deck.getCard(new Random().nextInt(deck.getSize()));
        deck.removeCard(card);
        table.getCurrentPlayer().getBoard().getHand().addCard(card);
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
