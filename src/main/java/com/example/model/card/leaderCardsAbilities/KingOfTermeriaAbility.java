package com.example.model.card.leaderCardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.AbilityName;
import com.example.model.game.place.Row;

public class KingOfTermeriaAbility implements LeaderAbility {

    @Override
    public void apply(AbilityContext abilityContext) {
        Row siege = abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace();
        if (siege.getSpecialCard() != null && !(siege.getSpecialCard().getAbilityName() == AbilityName.COMMANDER_HORN)) {
            for (Card card : siege.getCards()) {
                if (card instanceof UnitCard) {
                    ((UnitCard) card).duplicatePower();
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                }
            }
        } else if (siege.getSpecialCard() == null) {
            int flag = 0;
            for (Card card : siege.getCards()) {
                if (card instanceof UnitCard) {
                    if (card.getAbilityName() == AbilityName.COMMANDER_HORN) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0) {
                for (Card card : siege.getCards()) {
                    if (card instanceof UnitCard) {
                        ((UnitCard) card).duplicatePower();
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabel();
                    }
                }
            }
        }
        abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
