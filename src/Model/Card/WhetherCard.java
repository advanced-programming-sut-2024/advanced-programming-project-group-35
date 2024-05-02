package Model.Card;

import Model.Card.Enum.WhetherTypes;
import Model.Card.UnitCards.Action;
import Model.Game.Board;

public class WhetherCard extends Card implements Action, Observer {
    private WhetherTypes type;
    @Override
    public void update(Board board) {
        //
    }

    @Override
    public void doAction() {
        
    }
}
