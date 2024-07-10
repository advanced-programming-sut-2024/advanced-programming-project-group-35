package com.example.controller.server;

import com.example.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TournamentHandler {
    private HashMap<Integer, PlayerHandler> participants;
    private List<Integer> players;
    private List<Integer> winners;
    private List<Integer> lossers;
    private int winnersState = 0;
    private List<Integer> losers;
    private int losersState = 0;
    private List<GameHandler> currentGames;
    private int round;

    private int stPlayer;
    private int ndPlayer;
    private int rdPlayer;

    public TournamentHandler(HashMap<Integer, PlayerHandler> participants) {
        this.participants = participants;
        this.players = new ArrayList<>(participants.keySet());
        this.currentGames = new ArrayList<>();
        this.round = 1;
    }

    public void startTournament() throws InterruptedException {
        while (players.size() > 1) {
            playRound(players);
            round++;
        }
        while (winners.size() > 1){
            playRound(winners);
            winnersState++;
        }
        while (losers.size() > 1){
            playRound(losers);
            losersState++;
        }
        playRound(List.of(winners.get(0), losers.get(0)));
        // اعلام برنده نهایی
        announceWinner(stPlayer, ndPlayer, rdPlayer);
    }

    private void playRound(List<Integer> players) throws InterruptedException {
        Collections.shuffle(players);
        currentGames.clear();
        CountDownLatch latch = new CountDownLatch(players.size()/2);

        for (int i = 0; i < players.size() - 1; i += 2) {
            int id1 = players.get(i);
            int id2 = players.get(i + 1);

            GameHandler game = new GameHandler(id1, id2, false);
            currentGames.add(game);
            new Thread(() ->{
               game.run();
               latch.countDown();
            });
        }

        // انتظار برای پایان همه بازی‌های این دور
        if (!latch.await(30, TimeUnit.MINUTES)){
            System.out.println("Round " + round + " timed out!");
        }

        updateParticipants(players);
    }

    private void updateParticipants(List<Integer> players) {
        //HashMap<Integer, PlayerHandler> winners = new HashMap<>();
        if (losersState == 0 && winnersState == 0){
            for (GameHandler game : currentGames) {
                int winnerId = game.getWinnerID();
                winners.add(winnerId);
                int loserID = game.getLoserID();
                losers.add(loserID);
            }
        } else if(winnersState == 2 && losersState == 2) {
            stPlayer = currentGames.get(0).getWinnerID();
            ndPlayer = currentGames.get(0).getLoserID();
        } else {
            players.clear();
            for (GameHandler game : currentGames) {
                int winnerId = game.getWinnerID();
                players.add(winnerId);
            }
            if (winnersState == 1 && players.size() == 1){
                rdPlayer = players.get(0);
            }
        }
    }

    private void announceWinner(int id1, int id2, int id3) {
        for (PlayerHandler player : ServerApp.getServer().players.values()) {
            player.getOut().println("TOURNAMENT_WINNER|" + id1 + "|" + id2 + "|" + id3);
        }
    }
}