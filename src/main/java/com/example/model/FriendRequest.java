package com.example.model;

public class FriendRequest {
    private User sender;
    private User receiver;
    private boolean isAccepted;
    private boolean isRejected;

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.isAccepted = false;
        this.isRejected = false;
    }

    public FriendRequest(int id, int receiverId) {
        this.sender = User.getUserByID(id);
        this.receiver = User.getUserByID(receiverId);
        this.isAccepted = false;
        this.isRejected = false;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
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
