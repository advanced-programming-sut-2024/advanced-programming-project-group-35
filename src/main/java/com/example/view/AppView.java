package com.example.view;

import com.example.Main;
import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.Terminal;
import com.example.view.menuControllers.ViewController;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppView extends Application {
    private FXMLLoader fxmlLoader;
    private Stage primaryStage;
    private Pane pane;
    private ViewController viewController = null;
    private static Terminal terminal;
    private boolean terminalVisible = false;

    public Terminal getTerminal() {
        return terminal;
    }

    public void showMenu(Menu menu) throws Exception {
        terminal = new Terminal();

        primaryStage.centerOnScreen();

        fxmlLoader = new FXMLLoader(Main.class.getResource(menu.getFxmlFile()));
        pane = fxmlLoader.load();
        viewController = fxmlLoader.getController();

        pane.setBackground(new Background(createBackgroundImage()));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        Image image = new Image(Main.class.getResource("/images/cursor.png").toExternalForm());
        ImageCursor cursor = new ImageCursor(image);

        scene.setCursor(cursor);

        primaryStage.setTitle(menu.getTitle());
        viewController.showTerminalButton();
        primaryStage.show();
    }
    public void showTerminal() {
        pane.getChildren().add(terminal);
        TranslateTransition transition = new TranslateTransition(Duration.millis(700), terminal);
        transition.setFromY(900);
        transition.setToY(630);
        transition.play();
        terminalVisible = true;
    }
    public void removeTerminal() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(700), terminal);
        transition.setFromY(630);
        transition.setToY(900);
        transition.setOnFinished(e -> {
            pane.getChildren().remove(terminal);
            try {
                viewController.showTerminalButton();
            } catch (NullPointerException e1) {}
        });
        transition.play();
        terminalVisible = false;
    }
    private BackgroundImage createBackgroundImage() {
        Image image = new Image(Main.class.getResource(App.getCurrentMenu().getBackGroundImagePath()).toExternalForm(), 1707, 900, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    @Override
    public void start(Stage stage){
        primaryStage = stage;
        primaryStage.setMaximized(true);
        Image icon = new Image(Main.class.getResource("/images/game_icon.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        Controller.LOGIN_MENU_CONTROLLER.run();
    }

    public void hideTerminal() {
        pane.getChildren().remove(terminal);
        try {
            viewController.showTerminalButton();
        } catch (NullPointerException e) {}
    }

    public Pane getPane() {
        return pane;
    }
}
