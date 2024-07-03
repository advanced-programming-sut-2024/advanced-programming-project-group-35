package com.example.controller.server;// Server.java
import com.example.model.ServerApp;
import com.example.model.game.OnlineTable;
import com.example.model.game.Player;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 8080;
    private ConcurrentHashMap<Integer, PlayerHandler> players = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, OnlineTable> games = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, PlayerHandler> clientConnectors = new ConcurrentHashMap<>();

    public void start() {
        ServerApp.loadUsers("users.json");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ServerApp.saveUsers("users.json");
        }));
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                new Thread(new PlayerHandler(clientSocket, this)).start();
                System.out.println("New player connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(int ID, PlayerHandler handler) {
        players.put(ID, handler);
    }

    public void removePlayer(int ID) {
        players.remove(ID);
    }

    public void createGame(Player player1, Player player2) {
        OnlineTable table = new OnlineTable(player1, player2);
        games.put(table.getId(), table);
        players.get(player1.getId()).setCurrentGame(table);
        players.get(player2.getId()).setCurrentGame(table);
    }

//    public ServerApp getDataManager() {
//        return dataManager;
//    }

    // Other methods for game management

    public static void main(String[] args) {
        new Server().start();
    }

    public void addClientConnector(int id, PlayerHandler playerHandler) {
        clientConnectors.put(id, playerHandler);
    }

    public PlayerHandler getClientConnector(int receiverID) {
        return clientConnectors.get(receiverID);
    }
}

// PlayerHandler.java


