package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.Card;
import com.example.model.game.Board;
import com.example.model.game.Table;

import java.util.Random;

public class InvaderOfTheNorthAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        removeRandomCardFromDiscardPileAndAddToHand(table.getCurrentPlayer().getBoard());
        removeRandomCardFromDiscardPileAndAddToHand(table.getOpponent().getBoard());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void removeRandomCardFromDiscardPileAndAddToHand(Board board) {
        Card randomCard = board.getDiscardPile().getCard(new Random().nextInt(board.getDiscardPile().getSize()));
        board.getHand().addCard(randomCard);
        board.getDiscardPile().removeCard(randomCard);
    }
}
