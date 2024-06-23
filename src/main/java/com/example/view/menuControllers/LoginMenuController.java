package com.example.view.menuControllers;

import com.example.model.App;
import com.example.view.AppView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class LoginMenuController extends ViewController {

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

    public void showCommandLine(MouseEvent mouseEvent) {
        terminalButton.setVisible(false);
        App.getAppView().showTerminal();
    }

    @Override
    public void showTerminalButton() {
        terminalButton.setVisible(true);
        GaussianBlur blur = new GaussianBlur(30);
        terminalButton.setEffect(blur);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(blur.radiusProperty(), 30)),
                new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 0))
        );
        timeline.play();
    }
}
