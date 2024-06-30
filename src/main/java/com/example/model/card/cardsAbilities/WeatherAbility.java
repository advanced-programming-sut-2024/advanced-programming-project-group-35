package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.CardName;
import com.example.model.game.place.Row;

public class WeatherAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
       //TODO اول چک کنیم میتونیم اجرا کنیم توانایی رو یا نه
        switch (abilityContext.getCard().getCardName()) {
            case CardName.BITING_FROST -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
                abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace().updateStrength();
                abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace().updateStrength();
            }
            case CardName.IMPENETRABLE_FOG -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
            }
            case CardName.TORRENTIAL_RAIN -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
            }
            case CardName.SKELLIGE_STORM -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
            }
            case CardName.CLEAR_WEATHER -> {
                ((GameMenuController)Controller.GAME_MENU_CONTROLLER.getController()).disApplyWeatherCards();
            }
        }
    }
    private void setPowerOne(Row row) {
        row.setApplyWeather(true);
        for (UnitCard card : row.getCards()) {
            card.setCurrentPower(1);
        }
    }
}
