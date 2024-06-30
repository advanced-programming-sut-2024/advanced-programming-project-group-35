package com.example.model.card.factions;


import com.example.model.card.AbilityContext;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;
import com.example.model.game.Table;

public class EmpireNilfgaardian implements Factions {
    FactionsType factionsType = FactionsType.EmpireNilfgaardian;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(Table table, Player player) {
        if (table.getCurrentRound().isDraw()) {
            table.getCurrentRound().setWinner(player);
        }
    }
}
