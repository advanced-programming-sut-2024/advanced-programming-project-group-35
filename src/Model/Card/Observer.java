package Model.Card;

import Model.Game.Board;

public interface Observer {
    public void update(Board board);
}
