package com.example.model.Chat;

import com.example.Main;
import com.example.model.App;
import com.example.model.Terminal;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ChatBox extends StackPane {
    private final double WIDTH = 260;
    private final double HEIGHT = 600;
    private TextField textField;
    private Button sendButton;
    private VBox allMessagesVbox;
    private ScrollPane scrollPane = new ScrollPane();
    private int editableStartIndex;
    private String title;
    private static ChatMessage replyTo;

    public ChatBox() {
        this.getStylesheets().add(Terminal.class.getResource("/CSS/chatBox.css").toExternalForm());
        this.getStylesheets().add(Terminal.class.getResource("/CSS/allFiles.css").toExternalForm());
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        this.maxWidth(WIDTH);
        this.maxHeight(HEIGHT);
        this.setLayoutX(1125);
        VBox chatBox = new VBox();
        chatBox.setAlignment(Pos.CENTER);
        chatBox.setPrefHeight(HEIGHT - 10);
        chatBox.setPrefWidth(WIDTH - 10);
        chatBox.setSpacing(10);
        chatBox.getStyleClass().add("chat-box");
        Image sendIcon = new Image(Main.class.getResource("/images/icons/send_icon.png").toExternalForm());
        ImageView sendIconImageView = new ImageView(sendIcon);
        sendIconImageView.setFitHeight(20);
        sendIconImageView.setFitWidth(20);
        textField = new TextField();
        sendButton = new Button("", sendIconImageView);
        sendButton.getStyleClass().add("send-button");
        sendButton.setOnMouseClicked(e -> {
            sendMessage();
        });
        textField.getStyleClass().add("text-field");
        textField.setPrefHeight(30);
        textField.setPrefWidth(WIDTH - 10 - 30 - 10);
        HBox hBox = new HBox(textField, sendButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefWidth(WIDTH - 10);
        hBox.setPrefHeight(30);
        hBox.setSpacing(10);
        allMessagesVbox = new VBox();
        allMessagesVbox.prefWidth(WIDTH - 30);
        allMessagesVbox.prefHeight(HEIGHT - 10 - 60 - 30);
        allMessagesVbox.setMinHeight(HEIGHT - 10 - 60 - 30);
        allMessagesVbox.setMinWidth(WIDTH - 30);
        allMessagesVbox.setMaxWidth(WIDTH - 30);
        scrollPane.prefWidth(WIDTH - 10);
        scrollPane.prefHeight(HEIGHT - 10 - 60 - 30);
        scrollPane.setMinHeight(HEIGHT - 10 - 60 - 30);
        scrollPane.setMinWidth(WIDTH - 10);
        scrollPane.setMaxWidth(WIDTH - 10);
        scrollPane.setMaxHeight(HEIGHT - 10 - 60 - 30);
        scrollPane.getStyleClass().add("all-messages");
        allMessagesVbox.getStyleClass().add("all-messages-vbox");
        allMessagesVbox.setSpacing(10);
        HBox closeBox = new HBox();
        closeBox.getStyleClass().add("close-box");
        closeBox.setPrefWidth(WIDTH);
        closeBox.setPrefHeight(30);
        closeBox.setAlignment(Pos.CENTER);
        closeBox.setSpacing(150);
        Image closeIcon = new Image(Main.class.getResource("/images/alert-exit-button.png").toExternalForm());
        ImageView closeIconImageView = new ImageView(closeIcon);
        Button closeButton = new Button("", closeIconImageView);
        closeButton.getStyleClass().add("close-button");
        closeButton.setOnMouseClicked(e -> {
            App.getAppView().removeChatBox();
        });
        Label label = new Label("Chat Box");
        label.getStyleClass().add("chat-box-label");
        closeBox.getChildren().addAll(closeButton, label);
        chatBox.getChildren().addAll(closeBox, scrollPane, hBox);
        this.getChildren().add(chatBox);
        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = textField.getText();
        if (!message.isEmpty()) {
            textField.clear();
            ChatMessage chatMessage = new ChatMessage(App.getLoggedInUser().getID(), message);
            if (replyTo != null) {
                chatMessage.setReplyTo(replyTo);
                replyTo = null;
            }
            allMessagesVbox.getChildren().add(new ChatMessageView(chatMessage));
            scrollPane.setContent(allMessagesVbox);
        }
    }

    public TextField getTextField() {
        return textField;
    }

    public static ChatMessage getReplyTo() {
        return replyTo;
    }

    public static void setReplyTo(ChatMessage replyTo) {
        ChatBox.replyTo = replyTo;
    }
}
