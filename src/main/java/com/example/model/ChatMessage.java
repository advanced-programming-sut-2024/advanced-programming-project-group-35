package com.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatMessage {
    private int senderId;
    private int hour;
    private int minute;
    private String content;
    private List<Integer> reactions;
    private ChatMessage replyTo;

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public ChatMessage(int senderId, String content) {
        this.senderId = senderId;
        this.content = content;
        this.reactions = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        this.hour = date.getHour();
        this.minute = date.getMinute();
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

    public ChatMessage getReplyTo() {
        return replyTo;
    }
}