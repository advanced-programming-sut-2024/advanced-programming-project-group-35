package com.example.model;

import com.example.model.card.enums.FactionsType;
import com.example.model.deckmanager.DeckManager;
import com.example.model.deckmanager.DeckToJson;
import com.example.model.game.Table;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class User {
    boolean stayLoggedIn = false;
    private boolean isOnline;
    private boolean isInGame = true;
    private boolean privateGame = false;
    private int gameID;
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
    private DeckToJson temporaryDeck = DeckManager.loadDeck("/Users/ali/Downloads/random.json");
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
    private ArrayList<Log> logs;
    private ArrayList<String> decksAddresses;
    private ArrayList<Integer> friends = new ArrayList<>();
    private ArrayList<GameRequest> gameRequests = new ArrayList<>();
    private ArrayList<FriendRequest> friendRequests = new ArrayList<>();

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    public void setFriendRequests(ArrayList<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public ArrayList<FriendRequest> getFriendRequests() {
        return friendRequests;
    }


    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.currentFactionType = generateRandomFactionType();
        this.gameData = new ArrayList<>();
        this.decksAddresses = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
    }

    public static User getUserByUsername(String name) {
        for (User user : App.getAllUsers()) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }
    public void setNewID() {
        LocalDateTime now = LocalDateTime.now();
        id = now.hashCode();
    }

    public DeckToJson getTemporaryDeck() {
        return temporaryDeck;
    }

    public void setTemporaryDeck(DeckToJson temporaryDeck) {
        this.temporaryDeck = temporaryDeck;
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
        return FactionsType.Monsters;
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

    public void addFriend(User friend) {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        if (friends.contains(friend.getID())){
            return;
        }
        friends.add(friend.getID());
        for (FriendRequest request : friendRequests){
            System.out.println(request.getSender().getID() + " " + request.getReceiver().getID() + friend.getID());
            if (request.getSender().getID() == friend.getID() || request.getReceiver().getID() == friend.getID()){
                System.out.println("accepted");
                request.accept();
            }
        }
    }

    public FriendRequest getFriendRequest(User friend) {
        for (FriendRequest friendRequest : friendRequests) {
            if (friendRequest.getSender().equals(friend)) {
                return friendRequest;
            }
        }
        return null;
    }

    public void removeFriendRequest(FriendRequest friendRequest) {
        friendRequests.remove(friendRequest);
    }


    public void addFriendRequest(FriendRequest friendRequest) {
        if (friendRequests == null) {
            friendRequests = new ArrayList<>();
        }
        if (friends == null){
            friends = new ArrayList<>();
        }
        if (friends.contains(friendRequest.getSender().getID()) || friends.contains(friendRequest.getReceiver().getID())) return;
        if (friendRequests.contains(friendRequest)) return;
        FriendRequest duplicate = new FriendRequest(friendRequest.getReceiver().getID(), friendRequest.getSender().getID());
        if (friendRequests.contains(duplicate)) return;
        friendRequests.add(friendRequest);
    }

    public void setOnline(boolean b) {
        isOnline = b;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public int getMoney() {
        return money;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public int getNumberOfDeaths() {
        return numberOfDeaths;
    }

    public int getNumberOfSpellCards() {
        return numberOfSpellCards;
    }

    public int getNumberOfMinionCards() {
        return numberOfMinionCards;
    }

    public ArrayList<GameData> getGameData() {
        return gameData;
    }

    public ArrayList<String> getDeckNames() {
        return decksAddresses;
    }

//    public int getID() {
//        return id;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isADeckExistWithThisName(String deckName) {
        if (decksAddresses.contains(deckName)) {
            return true;
        } else {
            return false;
        }
    }

    public void addDeckNameToDeckAddresses(String deckName) {
        decksAddresses.add(deckName);
    }

    public boolean isFriend(User receiver) {
        for (int friend : friends) {
            if (friend == receiver.id) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getDeckName() {
        return decksAddresses;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        System.out.println("setInGame called with value: " + inGame);
//        isInGame.set(inGame);
//        System.out.println("isInGame is now: " + isInGame.get());
    }

    public boolean stayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public void rejectFriendRequest(User friend) {
        for (FriendRequest friendRequest : friendRequests) {
            if (friendRequest.getSender().getID() == friend.getID() || friendRequest.getReceiver().getID() == friend.getID()) {
                friendRequest.reject();
            }
        }
    }

    public String getDeckString() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String deckJson = objectMapper.writeValueAsString(temporaryDeck);
        return deckJson;
    }

    public boolean isOnline() {
        return isOnline;
    }
    public int getId() {
        return id;
    }

    public void addGameRequest(int senderID) {
        GameRequest gameRequest = new GameRequest(senderID, id);
        if (gameRequests == null) {
            gameRequests = new ArrayList<>();
        }
        gameRequests.add(gameRequest);
    }

    public void addGameRequest(GameRequest gameRequest) {
        if (gameRequests == null) {
            gameRequests = new ArrayList<>();
        }
        gameRequests.add(gameRequest);
    }

    public ArrayList<GameRequest> getGameRequests() {
        return gameRequests;
    }

    public boolean isPrivate() {
        return privateGame;
    }

    public void setPrivacy(boolean isPrivate) {
        this.privateGame = isPrivate;
    }

    public void acceptGameRequest(int friendUserID) {
        for (GameRequest gameRequest : gameRequests) {
            if (gameRequest.getSenderID() == friendUserID) {
                gameRequest.accept();
                return;
            }
        }
    }

    public void rejectGameRequest(int friendUserID) {
        for (GameRequest gameRequest : gameRequests) {
            if (gameRequest.getSenderID() == friendUserID) {
                gameRequest.reject();
                return;
            }
        }
    }

    public boolean hasFriendRequest(User friend) {
        for (FriendRequest friendRequest : friendRequests) {
            if (friendRequest.getSender().getID() == friend.getID() || friendRequest.getReceiver().getID() == friend.getID()) {
                return true;
            }
        }
        return false;
    }

    public int getGameID() {
        return gameID;
    }
}
