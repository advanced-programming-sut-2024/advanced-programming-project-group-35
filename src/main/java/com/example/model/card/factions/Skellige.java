package com.example.model.card.factions;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Card;
import com.example.model.card.SpecialCard;
import com.example.model.card.WeatherCard;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.DiscardPile;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.Place;
import com.example.model.game.place.RowsInGame;

import java.util.Random;

public class Skellige implements Factions {
    FactionsType factionsType = FactionsType.Skellige;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(Table table, Player player) {
        if (table.getRoundNumber() == 3) {
            DiscardPile discardPile = player.getBoard().getDiscardPile();
            if (discardPile.getSize() > 1) {
                int index1 = new Random().nextInt(discardPile.getSize());
                int index2 = index1 - 1;
                if (index2 == -1)
                    index2 = 1;
                Card selectedCard1 = discardPile.getCard(index1);
                Card selectedCard2 = discardPile.getCard(index2);
                if (selectedCard1 != null) {
                    moveCardToDestination(selectedCard1, table, player);
                }
                if (selectedCard2 != null) {
                    moveCardToDestination(selectedCard2, table, player);
                }
            } else if (player.getBoard().getDiscardPile().getSize() == 1) {
                Card selectedCard = discardPile.getCard(0);
                if (selectedCard != null) {
                    moveCardToDestination(selectedCard, table, player);
                }
            }
        }
    }

    private void moveCardToDestination(Card selectedCard, Table table, Player player) {
        if (selectedCard instanceof SpecialCard) {
            if (selectedCard instanceof WeatherCard) {
                if (table.getCurrentPlayer() == player) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.weather.toString());
                } else {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.weather.toString());
                }
            } else {
                if (selectedCard.getPlace() == Place.CLOSE_COMBAT) {
                    if (table.getCurrentPlayer() == player) {
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerSiegeSpecialPlace.toString());
                    } else {
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentCloseCombatSpecialPlace.toString());
                    }
                } else if (selectedCard.getPlace() == Place.SIEGE) {
                    if (table.getCurrentPlayer() == player) {
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerSiegeSpecialPlace.toString());
                    } else {
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentSiegeSpecialPlace.toString());
                    }
                } else {
                    if (table.getCurrentPlayer() == player) {
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerRangedSpecialPlace.toString());
                    } else {
                        ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentRangedSpecialPlace.toString());
                    }
                }
            }
        } else {
            if (selectedCard.getPlace() == Place.CLOSE_COMBAT) {
                if (table.getCurrentPlayer() == player) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerCloseCombat.toString());
                } else {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentCloseCombat.toString());
                }
            } else if (selectedCard.getPlace() == Place.SIEGE) {
                if (table.getCurrentPlayer() == player) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerSiege.toString());
                } else {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentSiege.toString());
                }
            } else {
                if (table.getCurrentPlayer() == player) {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.currentPlayerDiscardPlace.toString(), RowsInGame.currentPlayerRanged.toString());
                } else {
                    ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDoAbilityForCurrentPlayer(selectedCard.getIdInGame(), RowsInGame.opponentDiscardPlace.toString(), RowsInGame.opponentRanged.toString());
                }
            }
        }
    }
}
