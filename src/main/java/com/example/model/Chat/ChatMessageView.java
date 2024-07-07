package com.example.model.Chat;

import com.example.model.App;
import com.example.model.Terminal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ChatMessageView extends StackPane {
    private int senderId;
    private int hour;
    private int minute;
    private String content;
    private ChatMessage replyTo;
    private ChatReaction reaction;
    boolean isReply = false;
    private ObservableList<ChatReaction> reactions = FXCollections.observableArrayList();
    private ChatMessage chatMessage;
    private HBox reactionsBox;
    ContextMenu contextMenu = new ContextMenu();

    public ChatMessageView(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
        this.senderId = chatMessage.getSender();
        this.hour = chatMessage.getHour();
        this.minute = chatMessage.getMinute();
        this.content = chatMessage.getContent();
        this.replyTo = chatMessage.getReplyTo();
        if (replyTo != null) {
            isReply = true;
        }
        this.reactionsBox = new HBox(5);
        this.reactionsBox.setAlignment(Pos.CENTER_LEFT);
        init();
    }

    public void init() {
        this.getStylesheets().add(Terminal.class.getResource("/CSS/chatBox.css").toExternalForm());
        this.getStylesheets().add(Terminal.class.getResource("/CSS/allFiles.css").toExternalForm());
        VBox message = new VBox();
        message.getStyleClass().add("message");
        message.setSpacing(5);
        message.setPrefWidth(180);
        message.setMaxWidth(180);
        if (isReply) {
            Label replyLabel = new Label("Replying to " + App.getUserByID(replyTo.getSender()).getUsername() + ": " + replyTo.getContent());
            replyLabel.getStyleClass().add("reply-label");
            replyLabel.setStyle("-fx-font-size: 10px;");
            message.getChildren().add(replyLabel);
        }
        Label senderLabel = new Label(App.getUserByID(senderId).getUsername());
        senderLabel.getStyleClass().add("sender-label");
        senderLabel.setStyle("-fx-font-size: 12px;");
        Text contentLabel = new Text(content);
        contentLabel.setWrappingWidth(160);
        contentLabel.getStyleClass().add("content-label");
        contentLabel.setStyle("-fx-font-size: 14px;");
        Label timeLabel = new Label(hour + ":" + minute);
        timeLabel.getStyleClass().add("time-label");
        timeLabel.setStyle("-fx-font-size: 10px;");
        reactionsBox = new HBox();
        if (reactions.size() > 0) {
            Label reactionsLabel = new Label("Reactions: ");
            reactionsLabel.getStyleClass().add("reactions-label");
            reactionsLabel.setStyle("-fx-font-size: 12px;");
            reactionsBox.getChildren().add(reactionsLabel);
            reactionsBox.setSpacing(5);
            reactionsBox.setPrefHeight(20);
            reactionsBox.setPrefWidth(180);
            reactionsBox.getStyleClass().add("reactions-box");
        }
        for (ChatReaction reaction : reactions) {
            reactionsBox.getChildren().add(reaction);
        }
        message.getChildren().addAll(senderLabel, contentLabel, timeLabel, reactionsBox);
        this.getChildren().add(message);
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                setupContextMenu();
                contextMenu.show(this, event.getScreenX(), event.getScreenY());
            }
        });
    }
    public void setupContextMenu() {
        contextMenu.getItems().clear();
        contextMenu.getStyleClass().add("context-menu");
        MenuItem likeItem = new MenuItem(ChatReactionType.LIKE.getName());
        likeItem.getStyleClass().add("context-menu-item");
        likeItem.setOnAction(event -> addReaction(ChatReactionType.LIKE));
        MenuItem dislikeItem = new MenuItem(ChatReactionType.DISLIKE.getName());
        dislikeItem.getStyleClass().add("context-menu-item");
        dislikeItem.setOnAction(event -> addReaction(ChatReactionType.DISLIKE));
        MenuItem laughItem = new MenuItem(ChatReactionType.LAUGH.getName());
        laughItem.getStyleClass().add("context-menu-item");
        laughItem.setOnAction(event -> addReaction(ChatReactionType.LAUGH));
        MenuItem sadItem = new MenuItem(ChatReactionType.SAD.getName());
        sadItem.getStyleClass().add("context-menu-item");
        sadItem.setOnAction(event -> addReaction(ChatReactionType.SAD));
        MenuItem angryItem = new MenuItem(ChatReactionType.ANGRY.getName());
        angryItem.getStyleClass().add("context-menu-item");
        angryItem.setOnAction(event -> addReaction(ChatReactionType.ANGRY));
        MenuItem replyItem = new MenuItem("Replay");
        replyItem.getStyleClass().add("context-menu-item");
        replyItem.setOnAction(event -> ChatBox.setReplyTo(chatMessage));

        contextMenu.getItems().addAll(likeItem, dislikeItem, laughItem, sadItem, angryItem, replyItem);
    }

    public void setReaction(ChatReaction reaction) {
        this.reaction = reaction;
    }

    public ChatReaction getReaction() {
        return reaction;
    }
    private void addReaction(ChatReactionType type) {
        int reactorId = App.getLoggedInUser().getID();
        ChatReaction reaction = new ChatReaction(reactorId, type);

        if (!reactions.stream().anyMatch(r -> r.getType() == type && r.getReactorId() == reactorId)) {
            reactions.add(reaction);
            reactionsBox.getChildren().add(reaction);
            chatMessage.addReaction(reactorId);
        }
    }
    public void addReaction(ChatReaction reaction) {
        reactions.add(reaction);
        reactionsBox.getChildren().add(reaction);
    }
}
