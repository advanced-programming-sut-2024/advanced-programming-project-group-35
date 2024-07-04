package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Row;

public class WeatherAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
       //TODO اول چک کنیم میتونیم اجرا کنیم توانایی رو یا نه stellFOrged
        switch (abilityContext.getCard().getCardName()) {
            case CardData.weather_frost -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
                abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace().updateStrength();
                abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace().updateStrength();
            }
            case CardData.weather_fog -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
            }
            case CardData.weather_rain -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
            }
            case CardData.weather_storm -> {
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
                setPowerOne(abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace());
                setPowerOne(abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
            }
            case CardData.weather_clear -> {
                ((GameMenuController)Controller.GAME_MENU_CONTROLLER.getController()).disApplyWeatherCards(abilityContext.getTable());
            }
        }
    }
    private void setPowerOne(Row row) {
        row.setApplyWeather(true);
        for (Card card : row.getCards()) {
            ((UnitCard)card).setCurrentPower(1);
        }
    }
}
