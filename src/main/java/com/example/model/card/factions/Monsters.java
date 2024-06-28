package com.example.model.card.factions;


import com.example.model.card.AbilityContext;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;
import com.example.model.game.place.Row;

import java.util.Random;

public class Monsters implements Factions {
    // در آخر هر راند باید کال شه

    FactionsType factionsType = FactionsType.Monsters;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(AbilityContext abilityContext, Player player) {
        for (Row row : player.getBoard().getRows()) {
            if (!row.isEmpty()) {
                row.getCards().get(new Random().nextInt(row.getCards().size())).setNoRemove(true);
                break;
            }
        }
    }
}
