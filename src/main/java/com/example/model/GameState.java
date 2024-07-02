package com.example.model;

import com.example.model.game.Player;

import java.util.List;

public class GameState {
    List<Player> players;
    // Add other game state variables (board state, current turn, etc.)

    public GameState(List<Player> players) {
        this.players = players;
    }

    public void updateFromAction(int playerId, String action) {
        // Implement game logic to update state based on player action
    }
}