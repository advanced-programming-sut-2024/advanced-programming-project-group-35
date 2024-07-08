package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.MainMenuController;
import com.example.model.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuControllerView {
    private final Stage stage = App.getAppView().getPrimaryStage();
    public TextField friendsName;
    private Pane pane = App.getAppView().getPane();
    MainMenuController controller = (MainMenuController) Controller.MAIN_MENU_CONTROLLER.getController();
    private void paneChanger(String stageTitle, String fxmlFileName) throws IOException {
        stage.setTitle(stageTitle);
        String fxmlFilePath = "/FXML/";
        fxmlFilePath += fxmlFileName;
        pane = FXMLLoader.load(Objects.requireNonNull(LoginMenuControllerView.class.getResource(fxmlFilePath)));
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.centerOnScreen();
        App.getAppView().setPane(pane);
    }

    public void startGame(MouseEvent mouseEvent) {
        controller.openPreGameMenu();
    }

    public void logout(MouseEvent mouseEvent) {
        controller.logout();
    }

    public void openProfileMenu(MouseEvent mouseEvent) {
        controller.openProfileMenu();
    }

    public void openScoreTable(MouseEvent mouseEvent) {
        controller.openScoreTable();
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }

    public void testNetwork(MouseEvent mouseEvent) throws Exception {
        PreGameMenuControllerView gameMenuControllerView = new PreGameMenuControllerView();
        gameMenuControllerView.sendRequestToFriend(friendsName.getText());
    }

    public void openChatBox(MouseEvent mouseEvent) {
        App.getAppView().showChatBox();
    }
}
