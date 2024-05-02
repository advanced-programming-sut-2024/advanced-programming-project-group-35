package Model.Card;

import Model.Card.Card;
import Model.Card.Enum.UnitAbility;
import Model.Card.Enum.UnitNames;
import Model.Card.Enum.UnitPlace;
import Model.Game.Board;

public abstract class UnitCard extends Card {
    private UnitPlace unitPlace;
    private UnitAbility ability;
    private int strength;
    @Override
    public void update(Board board){
        //
    }

    public int getStrength() {
        return strength;
    }
}
