package com.example.model.card.factions;

import com.example.model.card.AbilityContext;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;

public interface Factions {
    FactionsType getFaction();
    void apply(AbilityContext abilityContext, Player player);

    // بعد از اتمام هر دور باید اینا فراخوانی شن
}
