package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Board;
import com.example.model.game.Table;

import java.util.Random;

public class InvaderOfTheNorthAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        removeRandomCardFromDiscardPileAndAddToHand(abilityContext.getTable().getCurrentPlayer().getBoard());
        removeRandomCardFromDiscardPileAndAddToHand(abilityContext.getTable().getOpponent().getBoard());
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void removeRandomCardFromDiscardPileAndAddToHand(Board board) {
        Card randomCard = board.getDiscardPile().getCard(new Random().nextInt(board.getDiscardPile().getSize()));
        board.getHand().addCard(randomCard);
        board.getDiscardPile().removeCard(randomCard);
    }
}
