package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuControllerForOnlineGame;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;
<<<<<<< HEAD
=======
import com.sun.mail.imap.protocol.INTERNALDATE;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
>>>>>>> 139076ed4e7c020cc78d59eb7cf935f77fe2cfd2

import java.util.ArrayList;
import java.util.HashMap;

public class ScorchAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
        if (abilityContext.getCard() == null) {
            applyAbilityForScorchCard(abilityContext);
        } else {
            applyAbilityForNonScorchCards(abilityContext);
        }
    }

    private void applyAbilityForScorchCard(AbilityContext abilityContext) {
<<<<<<< HEAD
        ArrayList<UnitCard> maximumPoweredCardsForCurrentPlayer = new ArrayList<>();
        ArrayList<UnitCard> maximumPoweredCardsForOpponentPlayer = new ArrayList<>();
        int maximumPowerForCurrentPlayer = 0;
        int maximumPowerForOpponentPlayer = 0;
        for (Row row : abilityContext.getTable().getCurrentPlayer().getBoard().getRows()) {
            maximumPowerForCurrentPlayer = getMaximumPoweredCards(maximumPoweredCardsForCurrentPlayer, row);
        }
        for (Row row : abilityContext.getTable().getOpponent().getBoard().getRows()) {
            maximumPowerForOpponentPlayer = getMaximumPoweredCards(maximumPoweredCardsForOpponentPlayer, row);
        }
        if (maximumPowerForCurrentPlayer == maximumPowerForOpponentPlayer) {
            for (UnitCard card : maximumPoweredCardsForCurrentPlayer) {
                //((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), abilityContext.getTable().getCurrentPlayer().getBoard().getRowByName(card.getPlace()), RowsInGame.currentPlayerDiscardPlace.toString());
            }
            for (UnitCard card : maximumPoweredCardsForOpponentPlayer) {
                //((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), abilityContext.getTable().getCurrentPlayer().getBoard().getRowByName(card.getPlace()), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        } else if (maximumPowerForCurrentPlayer > maximumPowerForOpponentPlayer) {
            for (UnitCard card : maximumPoweredCardsForCurrentPlayer) {
              //  ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), abilityContext.getTable().getCurrentPlayer().getBoard().getRowByName(card.getPlace()), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        } else {
            for (UnitCard card : maximumPoweredCardsForOpponentPlayer) {
             //   ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), abilityContext.getTable().getCurrentPlayer().getBoard().getRowByName(card.getPlace()), RowsInGame.currentPlayerDiscardPlace.toString());
            }
        }
=======
        removeMaximumPoweredCardsInClose(abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
        removeMaximumPoweredCardsInSiege(abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
        removeMaximumPoweredCardsInRanged(abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
>>>>>>> 139076ed4e7c020cc78d59eb7cf935f77fe2cfd2
    }

    private int getMaximumPoweredCards(ArrayList<UnitCard> maximumPoweredCardsForCurrentPlayer, Row row) {
        int maximumPowerForCurrentPlayer = 0;
        for (Card card : row.getCards()) {
            if (card instanceof UnitCard && !((UnitCard) card).isHero()) {
                if (((UnitCard) card).getCurrentPower() > maximumPowerForCurrentPlayer) {
                    maximumPoweredCardsForCurrentPlayer.clear();
                    maximumPoweredCardsForCurrentPlayer.add(((UnitCard) card));
                    maximumPowerForCurrentPlayer = ((UnitCard) card).getCurrentPower();
                } else if (((UnitCard) card).getCurrentPower() == maximumPowerForCurrentPlayer) {
                    maximumPoweredCardsForCurrentPlayer.add(((UnitCard) card));
                }
            }
        }
        return maximumPowerForCurrentPlayer;
    }

    private void applyAbilityForNonScorchCards(AbilityContext abilityContext) {
        removeMaximumPoweredCardsInClose(abilityContext.getTable().getOpponent().getBoard().getCloseCombatCardPlace());
        removeMaximumPoweredCardsInSiege(abilityContext.getTable().getOpponent().getBoard().getSiegeCardPlace());
        removeMaximumPoweredCardsInRanged(abilityContext.getTable().getOpponent().getBoard().getRangedCardPlace());
        removeMaximumPoweredCardsInCloseForCurrent(abilityContext.getTable().getCurrentPlayer().getBoard().getCloseCombatCardPlace());
        removeMaximumPoweredCardsInSiegeForCurrent(abilityContext.getTable().getCurrentPlayer().getBoard().getSiegeCardPlace());
        removeMaximumPoweredCardsInRangedForCurrent(abilityContext.getTable().getCurrentPlayer().getBoard().getRangedCardPlace());
    }

    private void removeMaximumPoweredCardsInClose(Row row) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
            timeline.setOnFinished(e -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().setPowerOfCardDefault(card.getIdInGame());
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), RowsInGame.opponentCloseCombat.toString(), RowsInGame.opponentDiscardPlace.toString());
            });
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void removeMaximumPoweredCardsInRanged(Row row) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
            timeline.setOnFinished(e -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().setPowerOfCardDefault(card.getIdInGame());
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), RowsInGame.opponentRanged.toString(), RowsInGame.opponentDiscardPlace.toString());
            });
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void removeMaximumPoweredCardsInSiege(Row row) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
            timeline.setOnFinished(e -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().setPowerOfCardDefault(card.getIdInGame());
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), RowsInGame.opponentSiege.toString(), RowsInGame.opponentDiscardPlace.toString());
            });
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
    private void removeMaximumPoweredCardsInCloseForCurrent(Row row) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
            timeline.setOnFinished(e -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().setPowerOfCardDefault(card.getIdInGame());
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), RowsInGame.currentPlayerCloseCombat.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            });
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void removeMaximumPoweredCardsInRangedForCurrent(Row row) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
            timeline.setOnFinished(e -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().setPowerOfCardDefault(card.getIdInGame());
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), RowsInGame.currentPlayerRanged.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            });
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void removeMaximumPoweredCardsInSiegeForCurrent(Row row) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5)));
            timeline.setOnFinished(e -> {
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).getGameMenuControllerView().setPowerOfCardDefault(card.getIdInGame());
                ((GameMenuControllerForOnlineGame) Controller.GAME_MENU_CONTROLLER_FOR_ONLINE_GAME.getController()).moveCardAndDontDoAbilityForCurrentPlayer(card.getIdInGame(), RowsInGame.currentPlayerSiege.toString(), RowsInGame.currentPlayerDiscardPlace.toString());
            });
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
}
