package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class ChatMessage {
    private int senderId;
    private String content;
    private List<Integer> reactions;
    private ChatMessage replyTo;

    public ChatMessage(int senderId, String content) {
        this.senderId = senderId;
        this.content = content;
        this.reactions = new ArrayList<>();
    }

    // Getters and setters

    public void addReaction(int reactorId) {
        reactions.add(reactorId);
    }

    public void setReplyTo(ChatMessage replyTo) {
        this.replyTo = replyTo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CHAT|").append(senderId).append("|").append(content);
        if (replyTo != null) {
            sb.append("|REPLY_TO|").append(replyTo.senderId).append("|").append(replyTo.content);
        }
        return sb.toString();
    }

    public int getSender() {
        return senderId;
    }

    public String getContent() {
        return content;
    }
}