package com.example.controller.server;// Server.java
import com.example.Main;
import com.example.controller.EmailVerification;
import com.example.model.DatabaseManager;
import com.example.model.User;
import com.example.model.game.OnlineTable;
import com.example.model.game.Player;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 8080;
    private static final int HTTP_SERVER_PORT = 8000;
    public ConcurrentHashMap<Integer, PlayerHandler> players = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, OnlineTable> games = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, PlayerHandler> clientConnectors = new ConcurrentHashMap<>();

    public void start() throws IOException {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3");

        startHttp();

        ServerApp.setServer(this);
        ServerApp.loadUsers("users.json");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ServerApp.saveUsers("users.json");
            System.out.println("Data Base is running...");
            DatabaseManager.createNewDatabase();
            DatabaseManager.createUsersTable();
            DatabaseManager.clearUsersTable();
            saveUsersToDatabase();
            System.out.println("Total number of users in database: " + DatabaseManager.getUserCount());
            try {
                Thread.sleep(1000); // 1 second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Data Base completed.");
            for (int i = 0; i < ServerApp.getAllUsers().size(); i++) {
                System.out.println(ServerApp.getAllUsers().get(i).getID());
                System.out.println(ServerApp.getAllUsers().get(i).getUsername());
                System.out.println("###");
            }
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

    public static void saveUsersToDatabase() {
        ArrayList<User> users = ServerApp.getAllUsers();
        System.out.println("Number of users to save: " + users.size());
        for (User user : users) {
//            System.out.println("Saving user: " + user.getUsername());
            DatabaseManager.insertOrUpdateUser(user);
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

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void addClientConnector(int id, PlayerHandler playerHandler) {
        clientConnectors.put(id, playerHandler);
    }

    public PlayerHandler getClientConnector(int receiverID) {
        System.out.println("all client connectors: " + clientConnectors);
        return clientConnectors.get(receiverID);
    }



    private static void startHttp() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(HTTP_SERVER_PORT), 0);
        server.createContext("/", Server::handleRequest);
        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port " + HTTP_SERVER_PORT);
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        System.out.println("Received request: " + exchange.getRequestURI());
        String path = exchange.getRequestURI().getPath();
        if (path.startsWith("/verify/")) {
            handleVerification(exchange);
        } else {
            sendResponse(exchange, 404, "صفحه مورد نظر یافت نشد.");
        }
    }

    private static void handleVerification(HttpExchange exchange) throws IOException {
        String uuid = exchange.getRequestURI().getPath().substring("/verify/".length());
        System.out.println("Received verification request for: " + uuid);
        boolean verified = EmailVerification.isVerified(uuid);
        if (verified) {
            // ثبت‌نام کاربر
            registerUser();
            sendResponse(exchange, 200, "ایمیل شما با موفقیت تأیید شد!");
        } else {
            sendResponse(exchange, 400, "لینک تأیید نامعتبر است.");
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        String response = String.format("<html><body><h1>%s</h1></body></html>", message);
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private static void registerUser() {
        // کد ثبت‌نام نهایی کاربر
        System.out.println("Registering user successfully!");
    }
}

// PlayerHandler.java


