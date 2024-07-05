package com.example.view;

import com.example.Main;
import com.example.controller.Controller;
import com.example.controller.ScoreTableController;
import com.example.model.App;
import com.example.model.alerts.Alert;
import com.example.model.Terminal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppView extends Application {
    private FXMLLoader fxmlLoader;
    private Stage primaryStage;
    private Pane pane;
    private Terminal terminal;
    private boolean isAlert = false;
    private Alert alert;
    private boolean terminalVisible = false;
    public Terminal getTerminal() {
        return terminal;
    }
    public void showMenu(Menu menu) throws Exception {
        primaryStage.centerOnScreen();

        fxmlLoader = new FXMLLoader(Main.class.getResource(menu.getFxmlFile()));
        pane = fxmlLoader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        Image image = new Image(Main.class.getResource("/images/cursor.png").toExternalForm(), 32, 32, true, true);
        ImageCursor cursor = new ImageCursor(image);

        scene.setCursor(cursor);

        primaryStage.setTitle(menu.getTitle());
        primaryStage.show();
    }

    public void showAlert(String message, String alertType) {
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

    public void updateUserInfo() {
        ((ScoreTableController) Controller.SCORE_TABLE_MENU_CONTROLLER.getController()).makeScoreboardTable(App.getAllUsers());
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
}
