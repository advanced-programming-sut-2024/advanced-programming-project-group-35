package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;

import java.util.ArrayList;

public class HopeOfTheAenSeidheAbility implements LeaderAbility {
    @Override
    public void apply(AbilityContext abilityContext) {
        Row close = abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        Row ranged = abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace();
        transferSiegeCards(close, ranged);
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void transferSiegeCards(Row close, Row ranged) {
        ArrayList<Card> closeCopy = new ArrayList<>(close.getCards());
        ArrayList<Card> rangedCopy = new ArrayList<>(ranged.getCards());
        for (Card card : rangedCopy) {
            if (ranged.getSpecialCard() == null && close.getSpecialCard() != null && (close.getSpecialCard().getAbilityName() == AbilityName.COMMANDER_HORN)) {
                ((UnitCard) card).duplicatePower();
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card.getIdInGame(), RowsInGame.currentPlayerRanged.toString(), RowsInGame.currentPlayerCloseCombat.toString());
            }
        }
        for (Card card : closeCopy) {
            if (close.getSpecialCard() == null && ranged.getSpecialCard() != null && (ranged.getSpecialCard().getAbilityName() == AbilityName.COMMANDER_HORN)) {
                ((UnitCard) card).duplicatePower();
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card.getIdInGame(), RowsInGame.currentPlayerCloseCombat.toString(), RowsInGame.currentPlayerRanged.toString());

            }
        }
    }
}
