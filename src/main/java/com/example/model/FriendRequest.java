package com.example.model;

public class FriendRequest {
    private int senderID;
    private int receiverID;
    private boolean isAccepted;
    private boolean isRejected;

    public FriendRequest(User sender, User receiver) {
        this.senderID = sender.getID();
        this.receiverID = receiver.getID();
        this.isAccepted = false;
        this.isRejected = false;
    }

    public FriendRequest(int id, int receiverId) {
        this.senderID = id;
        this.receiverID = receiverId;
        this.isAccepted = false;
        this.isRejected = false;
    }

    public User getSender() {
        return User.getUserByID(senderID);
    }

    public User getReceiver() {
        return User.getUserByID(receiverID);
    }

    public boolean isAccepted() {
        return isAccepted;
    }
    public boolean isRejected() {
        return isRejected;
    }

    public void accept() {
        isAccepted = true;
    }

    public void reject() {
        isRejected = true;
    }

}
