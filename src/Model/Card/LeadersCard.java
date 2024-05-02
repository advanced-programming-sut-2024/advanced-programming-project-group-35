package Model.Card;

import Model.Card.Enum.LeadersAbility;
import Model.Card.UnitCards.Action;
import Model.Game.Board;

public class LeadersCard implements Observer, Action {
    private LeadersAbility ability;

    @Override
    public void update(Board board) {
    }

    @Override
    public void doAction() {

    }
}
