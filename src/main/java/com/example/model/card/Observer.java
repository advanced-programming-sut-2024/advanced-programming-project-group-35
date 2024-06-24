package com.example.model.card;


import com.example.model.game.Board;

public interface Observer {
    public void update(Board board);
}
