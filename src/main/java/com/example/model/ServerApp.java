package com.example.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerApp {
    private static ArrayList<User> allUsers = new ArrayList<User>();
    public static void saveUsers(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(allUsers, writer);
            System.out.println("Users data saved successfully.");
        } catch (IOException e) {
        }
    }

    public static void loadUsers(String filename) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(filename)) {
            Type userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            allUsers = gson.fromJson(reader, userListType);
            System.out.println("Users data loaded successfully.");
        } catch (IOException e) {
        }
    }
}
