package com.example.model;

import com.example.controller.server.PlayerHandler;
import com.example.model.game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Log {
    ArrayList<String> commands = new ArrayList<String>();
    LinkedHashMap<String, String> deck1 = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> deck2 = new LinkedHashMap<String, String>();
    String player1;
    int player1ID = 1;
    String player2;
    int player2ID = 2;

    public Log(Player player1, Player player2) {
        this.player1 = player1.getName();
        this.player2 = player2.getName();
        deck1 = player1.getBoard().getDeck().toHash(player1ID);
        deck2 = player2.getBoard().getDeck().toHash(player2ID);
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void sendInitial(PlayerHandler spectator) {
        spectator.getOut().println("GAME_STARTED|" + player1 + "|" + player2 + "|" + deck1 + "|" + deck2);
    }
}
