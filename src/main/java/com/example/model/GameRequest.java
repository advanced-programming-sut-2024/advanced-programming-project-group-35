package com.example.model;

public class GameRequest {
    private int senderID;
    private int receiverID;
    private boolean isAccepted;
    private boolean isPending;

    public GameRequest(int senderID, int receiverID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.isAccepted = false;
        this.isPending = true;
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

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }
}

