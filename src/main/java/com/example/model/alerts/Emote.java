package com.example.model.alerts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Emote extends ImageView {
    public final Emotes emotes;

    public Emote(Emotes emotes) {
        this.emotes = emotes;
        this.setImage(new Image(Emote.class.getResource("/images/emotes/" + emotes.getAddress()).toExternalForm()));
        this.setFitWidth(300);
        this.setFitHeight(300);
    }

    public Emote(int index) {
        this(Emotes.values()[index - 1]);
    }
}