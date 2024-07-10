package com.example.model.alerts;

import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class TextEmote extends Text {
    private final String text;
    public TextEmote(String string) {
        this.text = string;
        this.setText(text);
        this.setWrappingWidth(300);
    }
}