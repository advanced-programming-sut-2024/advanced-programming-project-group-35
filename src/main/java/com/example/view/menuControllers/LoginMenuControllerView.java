package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.LoginMenuController;
import com.example.model.App;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class LoginMenuControllerView extends ViewController {

    @FXML
    private ImageView terminalButton;

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
        }
        else if (event.getCode().toString().equals("ESCAPE")) {
            App.getAppView().removeTerminal();
        }
        else if (event.getCode().toString().equals("PAGE_UP")) {
            //App.getAppView().getTerminal().scrollUp();
        }
        else if (event.getCode().toString().equals("PAGE_DOWN")) {
           // App.getAppView().getTerminal().scrollDown();
        }
    }
}
