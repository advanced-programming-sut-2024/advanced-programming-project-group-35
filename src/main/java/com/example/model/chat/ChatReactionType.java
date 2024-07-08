package com.example.model.chat;

public enum ChatReactionType {
    LIKE("Like", "like-reaction-icon.png"),
    DISLIKE("Dislike", "dislike-reaction-icon.png"),
    LAUGH("Laugh", "laugh-reaction-icon.png"),
    SAD("Sad", "sad-reaction-icon.png"),
    ANGRY("Angry", "angry-reaction-icon.png"),
    ;
    private final String name;
    private final String iconPath;

    ChatReactionType(String name, String iconPath) {
        this.name = name;
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public String getIconPath() {
        return iconPath;
    }
}
