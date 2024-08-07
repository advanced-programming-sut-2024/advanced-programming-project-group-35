package com.example.controller.server;

import com.example.Main;
import com.example.model.App;
import com.example.model.FriendRequest;
import com.example.model.GameRequest;
import com.example.model.User;
import com.example.model.alerts.Emote;
import com.example.model.alerts.TextEmote;
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
import java.util.Collections;
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

    private ArrayList<GameRequest> loadGames() {
        ArrayList<GameRequest> allGames = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("SYSTEM|LOAD_GAMES");

            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while (!(line = in.readLine()).equals("END_JSON")) {
                jsonBuilder.append(line).append("\n");
            }
            //System.out.println(jsonBuilder.toString());
            Type userListType = new TypeToken<ArrayList<GameRequest>>() {
            }.getType();
            allGames = gson.fromJson(jsonBuilder.toString(), userListType);
            System.out.println("Users data loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allGames;
    }

    public ArrayList<User> getAllUsers() {
        return loadUsers();
    }

    public ArrayList<GameRequest> getAllGames() {
        return loadGames();
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

    public void sendGameRequest(int senderID, int receiverID, DeckToJson deck) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("GameRequest|");
            out.print(senderID);
            out.print("|");
            out.print(receiverID);
            out.print("|");
            out.println(DeckManager.getDeckString(deck));
            System.out.println("-game request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRandomGameRequest(int id, DeckToJson deck){
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            //DeckToJson deck = DeckManager.loadDeck("C:\\Projects\\JavaProjs\\new-repo\\src\\main\\resources\\decksData\\monsters.json");

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
        Collections.shuffle(deck.getCards());
        for (int i = 0 ; i < 10 ; i++) {
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

    public void acceptGameRequest() {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("AcceptGameRequest");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptGameRequest(GameRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("AcceptGameRequest|");
            out.print(request.getSenderID());
            out.print("|");
            out.println(request.getReceiverID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectGameRequest() {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("RejectGameRequest");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectGameRequest(GameRequest request) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("RejectGameRequest|");
            out.print(request.getSenderID());
            out.print("|");
            out.println(request.getReceiverID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChat(String s) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmote(Emote emote, int sender) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("EMOTE|");
            out.print(sender);
            out.print("|");
            switch (emote.emotes) {
                case HA_HA_HA:
                    out.println("1");
                    break;
                case THANKS:
                    out.println("2");
                    break;
                case OOPS:
                    out.println("3");
                    break;
                case GOOD_ONE:
                    out.println("4");
                    break;
                case DIRIN_LALALA:
                    out.println("5");
                    break;
                case BORING:
                    out.println("6");
                    break;
                case SHHHHHH:
                    out.println("7");
                    break;
                case ANY_WAY:
                    out.println("8");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTextEmote(TextEmote textEmote, String text, int id) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.print("EMOTE|");
            out.print(id);
            out.print("|");
            out.println(text);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}