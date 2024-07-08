package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.*;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Row;

public class WeatherAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        LeaderCard leaderCard1 = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader();
        LeaderCard leaderCard2 = abilityContext.getTable().getOpponent().getBoard().getDeck().getLeader();
        WeatherCard weatherCard = (WeatherCard) abilityContext.getParam("card");

        if (leaderCard1.getLeaderName().equals("realms_foltest_gold") || leaderCard2.getLeaderName().equals("realms_foltest_gold")) {
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().backWeatherCardToDiscardPlaces(weatherCard);
            abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
            abilityContext.getTable().getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        }

        switch (weatherCard.getCardName()) {
            case CardData.weather_frost -> {
                applyWeatherEffect(abilityContext, leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace(), abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
            }
            case CardData.weather_fog -> {
                applyWeatherEffect(abilityContext, leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace(), abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
            }
            case CardData.weather_rain -> {
                applyWeatherEffect(abilityContext, leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace(), abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
            }
            case CardData.weather_storm -> {
                applyWeatherEffect(abilityContext, leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace(), abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
                applyWeatherEffect(abilityContext, leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace(), abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
            }
            case CardData.weather_clear -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).disApplyWeatherCards();
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().backWeatherCardToDiscardPlaces(weatherCard);
            }
        }
    }

    private void applyWeatherEffect(AbilityContext abilityContext, LeaderCard leaderCard1, LeaderCard leaderCard2, Row currentPlayerRow, Row opponentPlayerRow) {
        boolean isKingBran1 = leaderCard1.getLeaderName().equals("skellige_king_bran");
        boolean isKingBran2 = leaderCard2.getLeaderName().equals("skellige_king_bran");

        setPowerOne(currentPlayerRow, isKingBran1);
        setPowerOne(opponentPlayerRow, isKingBran2);
    }

    private void setPowerOne(Row row, boolean kingBran) {
        for (Card card : row.getCards()) {
            if (card != null && (card instanceof UnitCard)) {
                if (kingBran) {
                    ((UnitCard) card).setPowerHalf();
                } else {
                    ((UnitCard) card).setPowerOne();
                }
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabelAfterWeather();
            }
        }
    }
}
