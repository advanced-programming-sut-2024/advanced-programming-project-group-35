package com.example.model;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Terminal extends StackPane {
    private TextArea textArea;
    private int editableStartIndex;
    private String title;

    public Terminal() {
        this.getStylesheets().add(Terminal.class.getResource("/CSS/terminalStyle.css").toExternalForm());
        textArea = new TextArea();
        textArea.setPrefHeight(180);
        textArea.setWrapText(true);
        textArea.setPrefWidth(900);

        if (App.getLoggedInUser() != null) {
            title = "Gwent\\" + App.getLoggedInUser().getUsername() + " :\n";
            textArea.appendText(title);
        } else {
            title = "Gwent :\n";
            textArea.appendText(title);
        }
        editableStartIndex = textArea.getCaretPosition() + 1;

        textArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String command = getCurrentCommand();
                if (command.equals("clear")) {
                    textArea.clear();
                    textArea.appendText(title.substring(0, title.length() - 1));
                } else {
                    App.getCurrentController().runCommand(command);
                }
                editableStartIndex = textArea.getCaretPosition() + 2;
            } else if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                if (textArea.getCaretPosition() < editableStartIndex) {
                    event.consume();
                }
            } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                event.consume();
            }
        });
        textArea.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!textArea.isFocused()) {
                textArea.requestFocus();
                mouseEvent.consume();
            } else {
                mouseEvent.consume();
            }
        });

        ImageView imageView = new ImageView(new Image(Terminal.class.getResource("/images/terminal-exit-button.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> App.getAppView().removeTerminal());

        VBox buttonContainer = new VBox(imageView);
        buttonContainer.prefWidth(900);
        buttonContainer.getStyleClass().add("vbox-button-container");

        VBox textAreaContainer = new VBox(textArea);
        textAreaContainer.getStyleClass().add("vbox-textArea-container");

        textArea.requestFocus();
        VBox container = new VBox(buttonContainer, textAreaContainer);
        container.getStyleClass().add("vbox-container");

        this.getChildren().add(container);

        this.setLayoutX(50);
    }

    public void printError(String text) {
        textArea.appendText("\n   " + text);
        editableStartIndex = textArea.getCaretPosition() + 2;
    }

    public void printMessage(String text) {
        textArea.appendText("\n   " + text);
        editableStartIndex = textArea.getCaretPosition() + 2;
    }

    private String getCurrentCommand() {
        String text = textArea.getText();
        int lastPromptIndex = text.lastIndexOf("\n");
        return text.substring(lastPromptIndex + 1).trim();
    }
}