package com.example.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private int id;
    private String name;
    private List<ChatMessage> messages;
    private List<Integer> participants;

    public ChatRoom(int id, String name) {
        this.id = id;
        this.name = name;
        this.messages = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public void addParticipant(int participantId) {
        participants.add(participantId);
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public List<Integer> getParticipants() {
        return participants;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
