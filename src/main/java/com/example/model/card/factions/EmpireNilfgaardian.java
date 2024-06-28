package com.example.model.card.factions;


import com.example.model.card.AbilityContext;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;

public class EmpireNilfgaardian implements Factions {
    // در آخر هر راند باید کال شه
    FactionsType factionsType = FactionsType.EmpireNilfgaardian;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(AbilityContext abilityContext, Player player) {
        if (abilityContext.getTable().isRoundDraw()) {
            abilityContext.getTable().setRoundWinner(player);
        }
    }
}
