package com.example.model.user;

import com.example.model.App;
import com.example.model.card.Card;
import com.example.model.card.enums.FactionsType;
import com.example.model.game.GameData;

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

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.currentFactionType = generateRandomFactionType();
        this.storageCards = new ArrayList<Card>();
        this.gameData = new ArrayList<>();
        this.deck = new Deck();
        App.addNewUser(this);
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

    public String getSecurityQuestion() {
        return App.getSecurityQuestions().get(securityQuestion);
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }
    public int calculateRank() {
        return 0;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestionAnswer = securityQuestion;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
