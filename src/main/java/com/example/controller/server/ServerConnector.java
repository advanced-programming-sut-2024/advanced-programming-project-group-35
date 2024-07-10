package com.example.controller.server;

import com.example.Main;
import com.example.model.App;
import com.example.model.FriendRequest;
import com.example.model.User;
import com.example.model.card.Card;
import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.deckmanager.DeckManager;
import com.example.model.deckmanager.DeckToJson;
import com.example.model.game.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnector {
    private static final String SERVER_IP = "localhost"; // آدرس IP سرور
    private static final int SERVER_PORT = 8080; // پورت سرور

    public void saveUsers(ArrayList<User> allUsers) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUsers = gson.toJson(allUsers);

        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("SYSTEM|SAVE_USERS");
            out.println(jsonUsers);
            out.println("END_JSON");

            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> loadUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("SYSTEM|LOAD_USERS");

            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while (!(line = in.readLine()).equals("END_JSON")) {
                jsonBuilder.append(line).append("\n");
            }
            //System.out.println(jsonBuilder.toString());
            Type userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            allUsers = gson.fromJson(jsonBuilder.toString(), userListType);
            System.out.println("Users data loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    public ArrayList<User> getAllUsers() {
        return loadUsers();
    }

    public void acceptFriendRequest(FriendRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|ACCEPT_FRIEND_REQUEST|");
            out.print(request.getSender().getID());
            out.print("|");
            out.println(request.getReceiver().getID());
            System.out.println(request.getSender().getID() + " sending to  " + request.getReceiver().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptFriendRequest(int sender, int receiver) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|ACCEPT_FRIEND_REQUEST|");
            out.print(sender);
            out.print("|");
            out.println(receiver);
            System.out.println(sender + " sending to  " + receiver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectFriendRequest(FriendRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|REJECT_FRIEND_REQUEST|");
            out.print(request.getSender().getID());
            out.print("|");
            out.println(request.getReceiver().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectFriendRequest(int sender, int receiver) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|REJECT_FRIEND_REQUEST|");
            out.print(sender);
            out.print("|");
            out.println(receiver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFriendRequest(FriendRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|SEND_FRIEND_REQUEST|");
            out.print(request.getSender().getID());
            out.print("|");
            out.println(request.getReceiver().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserOnline(User loggedInUser) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|SET_USER_ONLINE|");
            out.println(loggedInUser.getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserOffline(User loggedInUser) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("SYSTEM|SET_USER_OFFLINE|");
            out.println(loggedInUser.getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(int senderID, int receiverID, String message) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("Message|");
            out.print(senderID);
            out.print("|");
            out.print(receiverID);
            out.print("|");
            out.println(message);
            System.out.println("-message sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGameRequest(int senderID, int receiverID) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("GameRequest|");
            out.print(senderID);
            out.print("|");
            out.println(receiverID);
            System.out.println("-game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRandomGameRequest(int id) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            DeckToJson deck = DeckManager.loadDeck("E:\\uni\\AP\\decks\\monsters.json");

            setHand(deck);

            out.print("RandomGameRequest|");
            out.print(id);
            out.println("|" + DeckManager.getDeckString(deck));
            System.out.println("-random game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHand(DeckToJson deck) {
        //TODO for test
        ArrayList<String> names = new ArrayList<>(deck.getCards());
        for (String name: names) {
            if (CardData.getCardDataByName(name).getAbilityName().equals("tight_bound")){
                deck.getHand().add(name);
                deck.getCards().remove(name);
            }
        }
        for (int i = 0 ; i < 15 ; i++) {
            String cardName = deck.getCards().get(i);
            deck.getHand().add(cardName);
            deck.getCards().remove(cardName);
        }
        ArrayList<String> restOfCards = new ArrayList<>(deck.getCards());
        deck.setRestOfCards(restOfCards);
        deck.getCards().clear();
    }

    public void sendTournamentGameRequest(int id) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("TournamentGameRequest|");
            out.println(id);
            System.out.println("-tournament game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}