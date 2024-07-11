package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.Row;

import java.util.ArrayList;

public class TheTreacherousAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        duplicateSpyCardsInRows(abilityContext.getTable().getCurrentPlayer().getBoard().getRows());
        duplicateSpyCardsInRows(abilityContext.getTable().getOpponent().getBoard().getRows());
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void duplicateSpyCardsInRows(ArrayList<Row> rows) {
        for (Row row : rows) {
            for (Card card : row.getCards()) {
                if (card != null && card.getAbilityName() == AbilityName.SPY) {
                    ((UnitCard)card).duplicatePower();
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                }
            }
        }
    }
}
