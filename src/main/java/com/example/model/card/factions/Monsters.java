package com.example.model.card.factions;

import com.example.model.card.Card;
import com.example.model.card.UnitCard;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.Player;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

import java.util.Random;

public class Monsters implements Factions {
    FactionsType factionsType = FactionsType.Monsters;

    @Override
    public FactionsType getFaction() {
        return factionsType;
    }

    @Override
    public void apply(Table table, Player player) {
        for (Row row : player.getBoard().getRows()) {
            if (!row.isEmpty()) {
                Card randomCard = row.getCards().get(new Random().nextInt(row.getCards().size()));
                if (randomCard instanceof UnitCard) {
                    ((UnitCard)randomCard).setNoRemove(true);
                    break;
                }
            }
        }
    }
}
