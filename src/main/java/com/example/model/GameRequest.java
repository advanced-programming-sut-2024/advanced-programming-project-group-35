package com.example.model;

import java.time.LocalDateTime;

public class GameRequest {
    private int senderID;
    private int receiverID;
    private boolean isAccepted;
    private boolean isPending;
    private int second;
    private int minute;
    private int hour;

    private int gameHandlerID = 0;

    public int getGameHandlerID() {
        return gameHandlerID;
    }

    public void setGameHandlerID(int gameHandlerID) {
        this.gameHandlerID = gameHandlerID;
    }

    public GameRequest(int senderID, int receiverID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.isAccepted = false;
        this.isPending = true;
        LocalDateTime now = LocalDateTime.now();
        this.second = now.getSecond();
        this.minute = now.getMinute();
        this.hour = now.getHour();
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
    public boolean isRejected() {
        return !isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public void accept() {
        isAccepted = true;
        isPending = false;
    }

    public void reject() {
        isAccepted = false;
        isPending = false;
    }
}

