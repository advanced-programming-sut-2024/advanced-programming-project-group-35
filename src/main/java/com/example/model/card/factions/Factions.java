package com.example.model.card.factions;

import com.example.model.card.AbilityContext;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;
import com.example.model.game.Table;

public interface Factions {
    FactionsType getFaction();
    void apply(Table table, Player player);
}
