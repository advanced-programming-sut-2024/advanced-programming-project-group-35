package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.LoginMenuController;
import com.example.controller.ProfileMenuController;
import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.view.Menu;
import com.example.view.OutputView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProfileMenuControllerView {
    private final Stage stage = App.getAppView().getPrimaryStage();
    public TextField newUsernameField;
    public TextField newNicknameField;
    public Label usernameShower;
    public Label nicknameShower;
    public TextField newEmailField;
    public PasswordField oldPasswordField;
    public PasswordField newPasswordField;
    public PasswordField confirmNewPasswordField;
    private Pane pane = App.getAppView().getPane();
    ProfileMenuController controller = (ProfileMenuController) Controller.PROFILE_MENU_CONTROLLER.getController();
    @FXML
    public void initialize() {
        usernameShower.setText(App.getLoggedInUser().getUsername());
        nicknameShower.setText(App.getLoggedInUser().getNickname());
    }
    public void backToMainMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.MAIN_MENU);
        Controller.MAIN_MENU_CONTROLLER.run();
    }
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

    public void editUsername(MouseEvent mouseEvent) {
        controller.editUsername(newUsernameField.getText());
        if (OutputView.getLastError() == Errors.USERNAME_CHANGED) {
            usernameShower.setText(newUsernameField.getText());
        }
    }

    public void editNickname(MouseEvent mouseEvent) {
        controller.editNickname(newNicknameField.getText());
        if (OutputView.getLastError() == Errors.NICKNAME_CHANGED) {
            nicknameShower.setText(newNicknameField.getText());
        }
    }

    public void openUserDataMenu(MouseEvent mouseEvent) throws IOException {
        paneChanger("User Data Menu", "resetPassword.fxml");
    }

    public void openGameHistoryMenu(MouseEvent mouseEvent) throws IOException {
        paneChanger("Game History Menu", "resetPassword.fxml");
    }

    public void editEmail(MouseEvent mouseEvent) {
        controller.editEmail(newEmailField.getText());
    }

    public void editPassword(MouseEvent mouseEvent) {
        controller.editPassword(oldPasswordField.getText(), newPasswordField.getText(), confirmNewPasswordField.getText());
    }
}
