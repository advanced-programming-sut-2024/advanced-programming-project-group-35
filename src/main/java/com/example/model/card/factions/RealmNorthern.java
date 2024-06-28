package com.example.model.card.factions;

import com.example.model.card.AbilityContext;
import com.example.model.card.Card;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;

import java.util.Random;

public class RealmNorthern implements Factions {
    // در آخر هر راند باید کال شه

    FactionsType factionsType = FactionsType.RealmsNorthern;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(AbilityContext abilityContext, Player player) {
        if (abilityContext.getTable().isRoundWon()) {
            if (!player.getBoard().getDeck().isEmpty()) {
                Card drawnCard = player.getBoard().getDeck().getCard(new Random().nextInt(player.getBoard().getDeck().getSize()));
                player.getBoard().getDeck().removeCard(drawnCard);
                player.getBoard().getHand().addCard(drawnCard);
            }
        }
    }
}
