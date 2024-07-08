package com.example.controller.server;// Server.java
import com.example.model.game.OnlineTable;
import com.example.model.game.Player;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 8080;
    public ConcurrentHashMap<Integer, PlayerHandler> players = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, OnlineTable> games = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, PlayerHandler> clientConnectors = new ConcurrentHashMap<>();

    public void start() {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3");

        ServerApp.setServer(this);
        ServerApp.loadUsers("user.json");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ServerApp.saveUsers("user.json");
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

    public void prepareGame(int player1ID, int player2ID) {
        createGame(new Player(player1ID), new Player(player2ID));
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
        System.out.println("all client connectors: " + clientConnectors);
        return clientConnectors.get(receiverID);
    }
}

// PlayerHandler.java


