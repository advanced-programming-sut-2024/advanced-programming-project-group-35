package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.AbilityContext;
import com.example.model.game.Deck;
import com.example.model.game.Player;

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
