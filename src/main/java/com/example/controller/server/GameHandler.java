package com.example.controller.server;

import com.example.model.Log;
import com.example.model.ServerApp;
import com.example.model.game.OnlineTable;
import com.example.model.game.Player;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameHandler implements Runnable{
    private ArrayList<PlayerHandler> spectators = new ArrayList<>();

    private PlayerHandler player1Handler;
    private PlayerHandler player2Handler;
    private BufferedReader player1In;
    private BufferedReader player2In;
    private PrintWriter player1Out;
    private PrintWriter player2Out;
    private boolean isPlayer1Turn = true;
    private Log gameHistory;

    public GameHandler(int player1ID, int player2ID) throws InterruptedException {
        player1Handler = ServerApp.getServer().players.get(player1ID);
        player2Handler = ServerApp.getServer().players.get(player2ID);
        this.player1In = player1Handler.getIn();
        this.player2In = player2Handler.getIn();
        this.player1Out = player1Handler.getOut();
        this.player2Out = player2Handler.getOut();
        Player player1 = new Player(player1ID);
        Player player2 = new Player(player2ID);
        player1Handler.wait();
        player2Handler.wait();
        gameHistory = new Log(player1, player2);
    }

    private void endGame() {
        notifyAll();
    }

    @Override
    public void run() {
        try {
            player1Out.println("GAME_STARTED|");
            player2Out.println("GAME_STARTED|");
            String inputLine;
            while(true){
                if(isPlayer1Turn){
                    inputLine = player1In.readLine();
                    player2Out.println(inputLine);
                    gameHistory.addCommand(inputLine);
                    if(inputLine.equals("END_GAME|")){
                        endGame();
                        break;
                    }
                    isPlayer1Turn = false;
                }else{
                    inputLine = player2In.readLine();
                    player1Out.println(inputLine);
                    gameHistory.addCommand(inputLine);
                    if(inputLine.equals("END_GAME|")){
                        endGame();
                        break;
                    }
                    isPlayer1Turn = true;
                }
                for (PlayerHandler spectator : spectators) {
                    spectator.getOut().println(inputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSpectator(PlayerHandler spectator) throws InterruptedException {
        //send game history until now:
        gameHistory.sendInitial(spectator);
        for (String command : gameHistory.getCommands()) {
            spectator.getOut().println(command);
        }
        spectators.add(spectator);
        spectator.wait();
    }
}
