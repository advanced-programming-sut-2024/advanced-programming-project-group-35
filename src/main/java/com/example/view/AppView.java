package com.example.view;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.ProfileMenuController;
import com.example.controller.ScoreTableController;
import com.example.model.App;
import com.example.model.Chat.ChatBox;
import com.example.model.FriendRequest;
import com.example.model.User;
import com.example.model.alerts.Alert;
import com.example.model.Terminal;
import com.example.model.alerts.ConfirmationAlert;
import com.example.model.alerts.Notification;
import com.example.view.menuControllers.FriendsMenuControllerView;
import com.example.view.menuControllers.GameMenuControllerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppView extends Application {
    private FXMLLoader fxmlLoader;
    private Stage primaryStage;
    private Pane pane;
    private Terminal terminal = new Terminal();
    private ChatBox chatBox = new ChatBox();
    private boolean isAlert = false;
    private Alert alert;
    private ConfirmationAlert confirmationAlert;
    private Notification notification;
    private boolean isNotification = false;
    private boolean terminalVisible = false;
    private Terminal getTerminal() {
        return terminal;
    }
    private GameMenuControllerView gameMenuControllerView;
    private FriendsMenuControllerView friendsMenuControllerView;
    private Menu currentMenu;
    public void showMenu(Menu menu) throws Exception {
        primaryStage.centerOnScreen();

        fxmlLoader = new FXMLLoader(Main.class.getResource(menu.getFxmlFile()));
        pane = fxmlLoader.load();
        if (menu.getTitle().equals("Game Menu")) {
            gameMenuControllerView = fxmlLoader.getController();
        }
        if (menu.getTitle().equals("Friends Menu")) {
            friendsMenuControllerView = fxmlLoader.getController();
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        Image image = new Image(Main.class.getResource("/images/cursor.png").toExternalForm(), 32, 32, true, true);
        ImageCursor cursor = new ImageCursor(image);

        scene.setCursor(cursor);

        primaryStage.setTitle(menu.getTitle());
        primaryStage.show();
        currentMenu = menu;
    }

    public void showAlert(String message, String alertType){
        if (!isAlert) {
            alert = new Alert(message, alertType);
            alert.setLayoutX(pane.getWidth() - alert.width - 35);
            alert.setLayoutY(50);
            pane.getChildren().add(alert);
            isAlert = true;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5)));
            timeline.play();
            timeline.setOnFinished(actionEvent ->  {
                isAlert = false;
                removeAlert(pane);
            });
        }
    }
    public void removeAlert(Pane currentPane) {
        currentPane.getChildren().remove(alert);
        isAlert = false;
    }

    public void showConfirmationAlert(String message, String alertType) {
        if (!isAlert) {
            confirmationAlert = new ConfirmationAlert(message, alertType);
            confirmationAlert.setLayoutX(pane.getWidth() - confirmationAlert.width - 35);
            confirmationAlert.setLayoutY(50);
            pane.getChildren().add(confirmationAlert);
            isAlert = true;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5)));
            timeline.play();
            timeline.setOnFinished(actionEvent ->  {
                isAlert = false;
                removeAlert(pane);
            });
        }
    }

    public void showConfirmationAlert(String message, String alertType, FriendRequest friendRequest) {
        if (!isAlert) {
            confirmationAlert = new ConfirmationAlert(message, alertType, friendRequest);
            confirmationAlert.setLayoutX(pane.getWidth() - confirmationAlert.width - 35);
            confirmationAlert.setLayoutY(50);
            pane.getChildren().add(confirmationAlert);
            isAlert = true;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5)));
            timeline.play();
            timeline.setOnFinished(actionEvent ->  {
                isAlert = false;
                removeAlert(pane);
            });
        }
    }

    public void removeConfirmationAlert(Pane currentPane) {
        currentPane.getChildren().remove(confirmationAlert);
        isAlert = false;
    }
    public void showNotification(String message, String imageAddress, String username) {
        if (!isNotification) {
            notification = new Notification(message, imageAddress, username);
            notification.setLayoutX(0);
            notification.setLayoutY(0);
            pane.getChildren().add(notification);
            isNotification = true;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2)));
            timeline.play();
            timeline.setOnFinished(actionEvent ->  {
                isNotification = false;
                removeNotification(pane);
            });
        }
    }
    public void removeNotification(Pane currentPane) {
        currentPane.getChildren().remove(notification);
        isNotification = false;
    }

    public GameMenuControllerView getGameMenuControllerView() {
        return gameMenuControllerView;
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        Image icon = new Image(Main.class.getResource("/images/game_icon.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        Controller.LOGIN_MENU_CONTROLLER.run();
    }

    public Pane getPane() {
        return pane;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void showTerminal() {
        pane.getChildren().add(terminal);
        TranslateTransition transition = new TranslateTransition(Duration.millis(700), terminal);
        transition.setFromY(900);
        transition.setToY(550);
        transition.play();
    }

    public void removeTerminal() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(700), terminal);
        transition.setFromY(550);
        transition.setToY(900);
        transition.setOnFinished(e -> {
            pane.getChildren().remove(terminal);
            try {

            } catch (NullPointerException e1) {
            }
        });
        transition.play();
    }

    public void showChatBox() {
        pane.getChildren().add(chatBox);
        chatBox.getTextField().requestFocus();
        TranslateTransition transition = new TranslateTransition(Duration.millis(700), chatBox);
        transition.setFromY(900);
        transition.setToY(185);
        transition.play();
    }

    public void removeChatBox() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(700), chatBox);
        transition.setFromY(185);
        transition.setToY(900);
        transition.setOnFinished(e -> {
            pane.getChildren().remove(chatBox);
            try {

            } catch (NullPointerException e1) {
            }
        });
        transition.play();
    }

    public void showLoading() {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/FXML/loadingPage.fxml"));
            pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserInfo() { //check if current menu is profile or score board
        if (currentMenu.getTitle().equals("Friends Menu")) { //reset the menu to show the new data
            App.getAppView().getFriendsMenuControllerView().updateFriendRequestList();
        } else if (currentMenu.getTitle().equals("Score Table")) {
            Controller.SCORE_TABLE_MENU_CONTROLLER.run();
        }
    }

    public void showMessage(String part) {
        Platform.runLater(() -> {
            showAlert(part, "info");
        });
    }

    public void showRequest(String senderName, int senderID) {
        Platform.runLater(() -> {
            showAlert(senderName + " wants to be your friend", "request");
        });
    }

    public FriendsMenuControllerView getFriendsMenuControllerView() {
        return friendsMenuControllerView;
    }
}
