package com.example.model.card.factions;

import com.example.model.card.AbilityContext;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;
import com.example.model.game.Table;

public class ScoiaTeal implements Factions {
    FactionsType factionsType = FactionsType.ScoiaTael;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(Table table, Player player) {

    }

}
