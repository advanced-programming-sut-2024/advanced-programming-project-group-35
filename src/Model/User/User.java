package Model.User;

import Model.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String lastName;
    private String profilePicture;
    private int wins;
    private int losses;
    private int score;
    private int money;
    private int numberOfFlags;
    private int numberOfKills;
    private int numberOfDeaths;
    private int numberOfDraws;
    private int numberOfPlayedGames;
    private int numberOfWonGames;
    private int numberOfLostGames;
    private int numberOfSpellCards;
    private int numberOfMinionCards;
    private HashMap<String, String> QandA;
    private ArrayList<Card> storageCards = new ArrayList<Card>();
    private Deck deck = new Deck();

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
