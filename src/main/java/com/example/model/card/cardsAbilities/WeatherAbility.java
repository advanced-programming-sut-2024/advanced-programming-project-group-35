package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.*;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;

public class WeatherAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        LeaderCard leaderCard1 = abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader();
        LeaderCard leaderCard2 = abilityContext.getTable().getOpponent().getBoard().getDeck().getLeader();
        WeatherCard weatherCard = (WeatherCard) abilityContext.getParam("card");

        if (leaderCard1.getLeaderName().equals("realms_foltest_gold") || leaderCard2.getLeaderName().equals("realms_foltest_gold")) {
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().backWeatherCardToDiscardPlaces(weatherCard);
            abilityContext.getTable().getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
            abilityContext.getTable().getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        }
        if (weatherCard.getName().startsWith("weather_frost")) {
            applyWeatherEffect(leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace(), abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.currentPlayerCloseCombat);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.opponentCloseCombat);
            abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace().setApplyWeather(true);
            abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace().setApplyWeather(true);
        } else if (weatherCard.getName().startsWith("weather_fog")) {
            applyWeatherEffect(leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace(), abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.currentPlayerRanged);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.opponentRanged);
            abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace().setApplyWeather(true);
            abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace().setApplyWeather(true);
        } else if (weatherCard.getName().startsWith("weather_rain")) {
            applyWeatherEffect(leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace(), abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.currentPlayerSiege);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.opponentSiege);
            abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace().setApplyWeather(true);
            abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace().setApplyWeather(true);
        } else if (weatherCard.getName().startsWith("weather_storm")) {
            applyWeatherEffect(leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace(), abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
            applyWeatherEffect(leaderCard1, leaderCard2, abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace(), abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.currentPlayerRanged);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.opponentRanged);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.currentPlayerCloseCombat);
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().applyWeatherPicture(RowsInGame.opponentCloseCombat);
            abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace().setApplyWeather(true);
            abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace().setApplyWeather(true);
            abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace().setApplyWeather(true);
            abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace().setApplyWeather(true);
        } else if (weatherCard.getName().startsWith("weather_clear")) {
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).disApplyWeatherCards();
            ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().backWeatherCardToDiscardPlaces(weatherCard);
        }
    }

    private void applyWeatherEffect(LeaderCard leaderCard1, LeaderCard leaderCard2, Row currentPlayerRow, Row opponentPlayerRow) {
        boolean isKingBran1 = leaderCard1.getLeaderName().getName().equals("leaders_skellige_king_bran");
        boolean isKingBran2 = leaderCard2.getLeaderName().getName().equals("leaders_skellige_king_bran");

        setPowerOne(currentPlayerRow, isKingBran1);
        setPowerOne(opponentPlayerRow, isKingBran2);
    }

    private void setPowerOne(Row row, boolean kingBran) {
        for (Card card : row.getCards()) {
            if (card != null && (card instanceof UnitCard) && !((UnitCard) card).isHero()) {
                if (kingBran) {
                    ((UnitCard) card).setPowerHalf();
                } else {
                    ((UnitCard) card).setPowerOne();
                }
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().getGameCardViewWithCardId(card.getIdInGame()).updatePowerLabelAfterWeather();
            }
        }
    }
}





