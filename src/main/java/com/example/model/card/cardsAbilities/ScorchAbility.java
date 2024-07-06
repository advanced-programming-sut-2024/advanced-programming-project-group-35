package com.example.model.card.cardsAbilities;

import com.example.controller.Controller;
import com.example.controller.GameMenuController;
import com.example.model.card.Ability;
import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.CardData;
import com.example.model.game.place.Place;
import com.example.model.game.place.Row;
import com.example.model.game.place.RowsInGame;

import java.util.ArrayList;

public class ScorchAbility implements Ability {
    @Override
    public void apply(AbilityContext abilityContext) {
//        if (abilityContext.getCard() != null) {
//            applyAbilityForScorchCard(abilityContext);
//        } else {
//            applyAbilityForNonScorchCards(abilityContext);
//        }
    }

    private void applyAbilityForScorchCard(AbilityContext abilityContext) {
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

//                ((GameMenuController) Controller.GAME_MENU_CONTROLLER.getController()).moveCardFromOriginToDestinationAndDontDoAbility(card.getIdInGame(), abilityContext.getTable().getCurrentPlayer().getBoard().getRowByName(card.getPlace()), RowsInGame.currentPlayerDiscardPlace.toString());
            }
            for (UnitCard card : maximumPoweredCardsForOpponentPlayer) {
                abilityContext.getTable().getOpponent().getBoard().getRowByName(card.getPlace()).removeCard(card);
                abilityContext.getTable().getOpponent().getBoard().getDiscardPile().addCard(card);
                //TODO گرافیک انتقال کارت
            }
        } else if (maximumPowerForCurrentPlayer > maximumPowerForOpponentPlayer) {
            for (UnitCard card : maximumPoweredCardsForCurrentPlayer) {
                abilityContext.getTable().getCurrentPlayer().getBoard().getRowByName(card.getPlace()).removeCard(card);
                abilityContext.getTable().getCurrentPlayer().getBoard().getDiscardPile().addCard(card);
                //TODO گرافیک انتقال کارت
            }
        } else {
            for (UnitCard card : maximumPoweredCardsForOpponentPlayer) {
                abilityContext.getTable().getOpponent().getBoard().getRowByName(card.getPlace()).removeCard(card);
                abilityContext.getTable().getOpponent().getBoard().getDiscardPile().addCard(card);
                //TODO گرافیک انتقال کارت
            }
        }
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
        for (Row row : abilityContext.getTable().getOpponent().getBoard().getRows()) {
            if (row.getNonHeroStrength() >= 10) {
                removeMaximumPoweredCardsInARow(row, abilityContext);
            }
        }
    }

    private void removeMaximumPoweredCardsInARow(Row row, AbilityContext abilityContext) {
        ArrayList<UnitCard> maximumPoweredCards = new ArrayList<>();
        int s = getMaximumPoweredCards(maximumPoweredCards, row);
        for (UnitCard card : maximumPoweredCards) {
            row.removeCard(card);
            abilityContext.getTable().getOpponent().getBoard().getDiscardPile().addCard(card);
            //TODO گرافیک انتقال کارت
        }
    }
}
