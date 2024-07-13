package com.example.model;

import com.example.Main;

import java.sql.*;
import java.util.ArrayList;
import java.io.File;

public class DatabaseManager {
    //private static final String DB_PATH = Main.class.getResource("/DB/users.db").toExternalForm();

    private static final String DB_PATH = System.getProperty("user.home") + "/Desktop/users.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public static void createNewDatabase() {
        try (Connection conn = connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                File dbFile = new File(DB_PATH);
//                System.out.println("Database file exists: " + dbFile.exists());
//                System.out.println("Database file path: " + dbFile.getAbsolutePath());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL,"
                + "nickname TEXT,"
                + "email TEXT,"
                + "securityQuestion TEXT,"
                + "securityQuestionAnswer TEXT,"
                + "currentFactionType TEXT,"
                + "phoneNumber TEXT,"
                + "name TEXT,"
                + "lastName TEXT,"
                + "profilePicture TEXT,"
                + "wins INTEGER DEFAULT 0,"
                + "losses INTEGER DEFAULT 0,"
                + "score INTEGER DEFAULT 0,"
                + "bestScore INTEGER DEFAULT 0,"
                + "money INTEGER DEFAULT 0,"
                + "numberOfFlags INTEGER DEFAULT 0,"
                + "numberOfKills INTEGER DEFAULT 0,"
                + "numberOfDeaths INTEGER DEFAULT 0,"
                + "numberOfDraws INTEGER DEFAULT 0,"
                + "numberOfPlayedGames INTEGER DEFAULT 0,"
                + "numberOfWonGames INTEGER DEFAULT 0,"
                + "numberOfLostGames INTEGER DEFAULT 0,"
                + "numberOfSpellCards INTEGER DEFAULT 0,"
                + "numberOfMinionCards INTEGER DEFAULT 0,"
                + "gameData TEXT,"
                + "decksAddresses TEXT"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Users table has been created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, nickname = ?, email = ?, " +
                "securityQuestion = ?, securityQuestionAnswer = ?, currentFactionType = ?, " +
                "phoneNumber = ?, name = ?, lastName = ?, profilePicture = ?, wins = ?, " +
                "losses = ?, score = ?, bestScore = ?, money = ?, numberOfFlags = ?, " +
                "numberOfKills = ?, numberOfDeaths = ?, numberOfDraws = ?, " +
                "numberOfPlayedGames = ?, numberOfWonGames = ?, numberOfLostGames = ?, " +
                "numberOfSpellCards = ?, numberOfMinionCards = ?, gameData = ?, decksAddresses = ? " +
                "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getID());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getNickname());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getSecurityQuestion());
            pstmt.setString(7, user.getSecurityQuestionAnswer());
            pstmt.setString(8, user.getCurrentFactionType() != null ? user.getCurrentFactionType().toString() : "DEFAULT");
            pstmt.setString(9, user.getPhoneNumber());
            pstmt.setString(10, user.getName());
            pstmt.setString(11, user.getLastName());
            pstmt.setString(12, user.getProfilePicture());
            pstmt.setInt(13, user.getWins());
            pstmt.setInt(14, user.getLosses());
            pstmt.setInt(15, user.getScore());
            pstmt.setInt(16, user.getBestScore());
            pstmt.setInt(17, user.getMoney());
            pstmt.setInt(18, user.getNumberOfFlags());
            pstmt.setInt(19, user.getNumberOfKills());
            pstmt.setInt(20, user.getNumberOfDeaths());
            pstmt.setInt(21, user.getNumberOfDraws());
            pstmt.setInt(22, user.getNumberOfPlayedGames());
            pstmt.setInt(23, user.getNumberOfWonGames());
            pstmt.setInt(24, user.getNumberOfLostGames());
            pstmt.setInt(25, user.getNumberOfSpellCards());
            pstmt.setInt(26, user.getNumberOfMinionCards());
            pstmt.setString(27, user.getGameData() != null ? user.getGameData().toString() : "");
            pstmt.setString(28, user.getDeckNames() != null ? user.getDeckNames().toString() : "");

            int affectedRows = pstmt.executeUpdate();
//            System.out.println("User " + user.getUsername() + " has been updated. Rows affected: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void insertOrUpdateUser(User user) {
        if (userExists(user.getID())) {
            updateUser(user);
        } else {
            insertUser(user);
        }
    }

    public static boolean userExists(int userId) {
        String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insertUser(User user) {
        String sql = "INSERT INTO users(id, username, password, nickname, email, securityQuestion, securityQuestionAnswer, currentFactionType, phoneNumber, name, lastName, profilePicture, wins, losses, score, bestScore, money, numberOfFlags, numberOfKills, numberOfDeaths, numberOfDraws, numberOfPlayedGames, numberOfWonGames, numberOfLostGames, numberOfSpellCards, numberOfMinionCards, gameData, decksAddresses) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, user.getID());
                pstmt.setString(2, user.getUsername());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getNickname());
                pstmt.setString(5, user.getEmail());
                pstmt.setString(6, user.getSecurityQuestion());
                pstmt.setString(7, user.getSecurityQuestionAnswer());
                pstmt.setString(8, user.getCurrentFactionType() != null ? user.getCurrentFactionType().toString() : "DEFAULT");
                pstmt.setString(9, user.getPhoneNumber());
                pstmt.setString(10, user.getName());
                pstmt.setString(11, user.getLastName());
                pstmt.setString(12, user.getProfilePicture());
                pstmt.setInt(13, user.getWins());
                pstmt.setInt(14, user.getLosses());
                pstmt.setInt(15, user.getScore());
                pstmt.setInt(16, user.getBestScore());
                pstmt.setInt(17, user.getMoney());
                pstmt.setInt(18, user.getNumberOfFlags());
                pstmt.setInt(19, user.getNumberOfKills());
                pstmt.setInt(20, user.getNumberOfDeaths());
                pstmt.setInt(21, user.getNumberOfDraws());
                pstmt.setInt(22, user.getNumberOfPlayedGames());
                pstmt.setInt(23, user.getNumberOfWonGames());
                pstmt.setInt(24, user.getNumberOfLostGames());
                pstmt.setInt(25, user.getNumberOfSpellCards());
                pstmt.setInt(26, user.getNumberOfMinionCards());
                pstmt.setString(27, user.getGameData() != null ? user.getGameData().toString() : "");
                pstmt.setString(28, user.getDeckNames() != null ? user.getDeckNames().toString() : "");

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
            }
            conn.commit();
//            System.out.println("User " + user.getUsername() + " has been inserted.");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nickname"),
                        rs.getString("email")
                );
                // Set other properties of the user here
                user.setId(rs.getInt("id"));
                user.setSecurityQuestionAnswer(rs.getString("securityQuestionAnswer"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void clearUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate(sql);
            System.out.println("Users table has been cleared. Rows affected: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getUserCount() {
        String sql = "SELECT COUNT(*) FROM users";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}