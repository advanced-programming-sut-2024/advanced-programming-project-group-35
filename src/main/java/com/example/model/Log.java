package com.example.model;

import com.example.controller.server.PlayerHandler;
import com.example.model.deckmanager.DeckToJson;
import com.example.model.game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Log {
    ArrayList<String> commands = new ArrayList<String>();
    public DeckToJson deck1 ;
    public DeckToJson deck2 ;
    String player1;
    int player1ID = 1;
    String player2;
    int player2ID = 2;


    public Log(int id1, int id2, DeckToJson deck1, DeckToJson deck2) {
        this.player1ID = id1;
        this.player2ID = id2;
        this.deck1 = deck1;
        this.deck2 = deck2;
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
