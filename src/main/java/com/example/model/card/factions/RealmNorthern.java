package com.example.model.card.factions;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;
import com.example.model.game.Table;

import java.util.Random;

public class RealmNorthern implements Factions {
    FactionsType factionsType = FactionsType.RealmsNorthern;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(Table table, Player player) {
        if (table.getCurrentRound().isWon() && table.getCurrentRound().getWinner() == player) {
            if (!player.getBoard().getDeck().isEmpty()) {
                Card drawnCard = player.getBoard().getDeck().getCard(new Random().nextInt(player.getBoard().getDeck().getSize()));
                player.getBoard().getDeck().removeCard(drawnCard);
                player.getBoard().getHand().addCard(drawnCard);
                //TODO گرافیک انتفال کارت
            }
        }
    }
}
