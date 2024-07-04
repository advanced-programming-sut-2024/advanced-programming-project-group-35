package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Board;
import com.example.model.game.Deck;
import com.example.model.game.DiscardPile;
import com.example.model.game.Table;

import java.util.ArrayList;

public class CrachAnCraiteAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        moveCardsFromDiscardPileToDeck(abilityContext.getTable().getCurrentPlayer().getBoard());
        moveCardsFromDiscardPileToDeck(abilityContext.getTable().getOpponent().getBoard());
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void moveCardsFromDiscardPileToDeck(Board board) {
        Deck deck = board.getDeck();
        DiscardPile discardPile = board.getDiscardPile();
        ArrayList<Card> cardsToTransfer = new ArrayList<>(discardPile.getCards());
        for (Card card : cardsToTransfer) {
            deck.addCard(card);
            discardPile.removeCard(card);
        }
        deck.shuffle();
    }
}
