package com.example.model.card.leaderCardsAbilities;

import com.example.model.card.UnitCard;
import com.example.model.card.cardsAbilities.SpyAbility;
import com.example.model.game.Table;
import com.example.model.game.place.Row;

import java.util.ArrayList;

public class TheTreacherousAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        duplicateSpyCardsInRows(table.getCurrentPlayer().getBoard().getRows());
        duplicateSpyCardsInRows(table.getOpponent().getBoard().getRows());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void duplicateSpyCardsInRows(ArrayList<Row> rows) {
        for (Row row : rows) {
            for (UnitCard card : row.getCards()) {
                if (card.getAbility() instanceof SpyAbility)
                    card.duplicatePower();
            }
        }
    }
}