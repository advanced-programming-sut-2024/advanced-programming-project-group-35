package com.example.controller;

import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmailVerification {
    private static final String VERIFICATION_BASE_URL = "http://localhost:8000/verify/";
    private static final Map<String, String> registerQueue = new ConcurrentHashMap<>();

    public static String createVerificationLink(String username){
        String uuid = UUID.randomUUID().toString();
        registerQueue.put(uuid, username);
        System.out.println("Verification link created for: " + username);
        System.out.println("Verification link: " + uuid);
        return VERIFICATION_BASE_URL + uuid;
    }

    public static String verifyUser(String uuid) {
        String username = registerQueue.get(uuid);
        System.out.println("Verifying user: " + username);
        return registerQueue.remove(uuid);
    }

    public static boolean isVerified(String uuid) {
        return true;
    }
}