package com.example.model.chat;

import com.example.Main;
import com.example.model.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ChatReaction extends StackPane {
    private int reactorId;
    private ChatReactionType type;

    public ChatReaction(int reactorId, ChatReactionType type) {
        this.reactorId = reactorId;
        this.type = type;

        Image reactionImage = new Image(Main.class.getResource("/images/icons/" + type.getIconPath()).toExternalForm());
        ImageView imageView = new ImageView(reactionImage);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        this.getChildren().add(imageView);
    }

    public int getReactorId() {
        return reactorId;
    }

    public ChatReactionType getType() {
        return type;
    }
}