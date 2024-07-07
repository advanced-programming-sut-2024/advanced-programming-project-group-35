package com.example.model.Chat;

import javafx.scene.shape.Rectangle;

public class ChatReaction extends Rectangle {
    private int reactorId;
    private ChatReactionType type;

    public ChatReaction(int reactorId, ChatReactionType type) {
        this.reactorId = reactorId;
        this.type = type;
    }

    public int getReactorId() {
        return reactorId;
    }

    public ChatReactionType getType() {
        return type;
    }
}
