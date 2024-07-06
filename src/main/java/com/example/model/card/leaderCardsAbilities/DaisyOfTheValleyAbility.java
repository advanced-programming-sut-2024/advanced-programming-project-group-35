package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.game.Deck;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.RowsInGame;

import java.util.Random;

public class DaisyOfTheValleyAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        Deck deck = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck();
        if (!deck.isEmpty()) {
//            Card card = deck.getCard(new Random().nextInt(deck.getSize()));
//            deck.removeCard(card);
//            abilityContext.getTable().getCurrentPlayer().getBoard().getHand().addCard(card);
//            String origin = (String) abilityContext.getParam("origin");
//            String dest = (String) abilityContext.getParam("dest");
//            ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card.getIdInGame(), origin, dest);
            Player player = (Player) abilityContext.getParam("player");
            player.getBoard().getDeck().getLeader().setCanDoAction(false);
        }
    }
}
