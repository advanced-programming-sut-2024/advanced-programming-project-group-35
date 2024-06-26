package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.LoginMenuController;
import com.example.model.App;
import com.example.model.alerts.AlertType;
import com.example.view.AppView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class LoginMenuControllerView extends ViewController {
    private static Pane currentPane = App.getAppView().getPane();

    @FXML
    private Button terminalButton;

    public void blurringImage(MouseEvent mouseEvent) {
        BoxBlur blur = new BoxBlur();
        blur.setWidth(5);
        blur.setHeight(5);
        blur.setIterations(1);
        terminalButton.setEffect(blur);
    }

    public void UnBlurringImage(MouseEvent mouseEvent) {
        terminalButton.setEffect(null);
    }

    public void showTerminal() {
        App.getAppView().showTerminal();
    }

    @Override
    public void hideTerminalButton() {
        terminalButton.setVisible(false);
    }

    @Override
    public void showTerminalButton() {
        terminalButton.setVisible(true);
        terminalButton.requestFocus();
        GaussianBlur blur = new GaussianBlur(30);
        terminalButton.setEffect(blur);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(blur.radiusProperty(), 30)),
                new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 0))
        );
        timeline.play();
    }

    public void keyPressed(KeyEvent event) {
        if (event.getCode().toString().equals("TAB")) {
            App.getAppView().showTerminal();
        } else if (event.getCode().toString().equals("ESCAPE")) {
            App.getAppView().removeTerminal();
        } else if (event.getCode().toString().equals("PAGE_UP")) {
            //App.getAppView().getTerminal().scrollUp();
        } else if (event.getCode().toString().equals("PAGE_DOWN")) {
            // App.getAppView().getTerminal().scrollDown();
        }
    }

    public void openLoginStage(MouseEvent mouseEvent) throws IOException {
        stageCreator("Login", "login.fxml");
    }

    public void openRegisterStage(MouseEvent mouseEvent) throws IOException {
        stageCreator("Register", "register.fxml");
    }

    public void openResetPasswordStage(MouseEvent mouseEvent) throws IOException {
        stageCreator("Reset Password", "register.fxml");
    }

    public void generateRandomPassword(MouseEvent mouseEvent) {
    }

    private static void stageCreator(String stageTitle, String fxmlFileName) throws IOException {
        Stage stage = new Stage();
        stage.setTitle(stageTitle);
        String fxmlFilePath = "/FXML/";
        fxmlFilePath += fxmlFileName;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenuControllerView.class.getResource(fxmlFilePath)));
        currentPane = pane;
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void loginUser(MouseEvent mouseEvent) {
        App.getAppView().showAlert("login successful", AlertType.INFO.getType(), currentPane);
    }
}
