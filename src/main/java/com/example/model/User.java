package com.example.model;

import com.example.model.App;
import com.example.model.card.enums.FactionsType;
import com.example.model.GameData;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private int id;
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
    private int wins = 0;
    private int losses = 0;
    private int score = 0;
    private int bestScore = 0;
    private int money;
    private int numberOfFlags;
    private int numberOfKills = 0;
    private int numberOfDeaths = 0;
    private int numberOfDraws;
    private int numberOfPlayedGames = 0;
    private int numberOfWonGames = 0;
    private int numberOfLostGames = 0;
    private int numberOfSpellCards;
    private int numberOfMinionCards;
    private ArrayList<GameData> gameData; // mitoonim az queue estefade konim (vali ta hala kar nakardam bahash)
    private ArrayList<String> decksAddresses;

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.currentFactionType = generateRandomFactionType();
        this.gameData = new ArrayList<>();
        this.decksAddresses = new ArrayList<>();
        setID();
    }

    private void setID() {
        Date date = new Date();
        this.id = date.hashCode();
    }

    public int getID() {
        return id;
    }

    public static User getUserByID(int id) {
        for (User user : App.getAllUsers()) {
            if (user.getID() == id) {
                return user;
            }
        }
        return null;
    }

    public void addGameData(GameData gameData) {
        this.gameData.add(gameData);
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

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getScore() {
        return score;
    }

    public int getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getNumberOfWonGames() {
        return numberOfWonGames;
    }

    public int getNumberOfLostGames() {
        return numberOfLostGames;
    }

}
