package Model.User;

import Model.Card.Card;
import Model.Card.Enum.FactionsType;
import Model.Card.Factions.Factions;
import Model.Game.GameData;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int securityQuestion;
    private String securityQuestionAnswer;
    private FactionsType currentFactionType;
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
    private ArrayList<Card> storageCards;
    private ArrayList<GameData> gameData; // mitoonim az queue estefade konim (vali ta hala kar nakardam bahash)
    private Deck deck;

    public User(String username, String password, String nickname, String email, int securityQuestionNumber, String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestionNumber;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.currentFactionType = generateRandomFactionType();
        this.storageCards = new ArrayList<Card>();
        this.gameData = new ArrayList<>();
        this.deck = new Deck();
    }
    private FactionsType generateRandomFactionType() {
        return null;
    }

    public FactionsType getCurrentFactionType() {
        return currentFactionType;
    }

    public void setCurrentFactionType(FactionsType currentFactionType) {
        this.currentFactionType = currentFactionType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }
    public int calculateRank() {
        return 0;
    }
}
