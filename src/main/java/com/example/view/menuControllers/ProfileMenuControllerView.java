package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.LoginMenuController;
import com.example.controller.ProfileMenuController;
import com.example.controller.server.ServerConnector;
import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.model.User;
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
import java.util.SplittableRandom;

public class ProfileMenuControllerView {
    private final Stage stage = App.getAppView().getPrimaryStage();
    public TextField newUsernameField;
    public TextField newNicknameField;
    public Label usernameShower = new Label();
    public Label nicknameShower = new Label();
    public TextField newEmailField;
    public PasswordField oldPasswordField;
    public PasswordField newPasswordField;
    public PasswordField confirmNewPasswordField;
    public Label usernameDataMenu = new Label();
    public Label nicknameDataMenu = new Label();
    public Label bestScoreDataMenu = new Label();
    public Label rankDataMenu = new Label();
    public Label gamesPlayedDataMenu = new Label();
    public Label wonGamesDataMenu = new Label();
    public Label drawGamesDataMenu = new Label();
    public Label lostGamesDataMenu = new Label();
    private Pane pane = App.getAppView().getPane();
    ProfileMenuController controller = (ProfileMenuController) Controller.PROFILE_MENU_CONTROLLER.getController();

    @FXML
    public void initialize() {
        usernameShower.setText(App.getLoggedInUser().getUsername());
        nicknameShower.setText(App.getLoggedInUser().getNickname());
        usernameDataMenu.setText("Username: " + App.getLoggedInUser().getUsername());
        nicknameDataMenu.setText("Nickname: " + App.getLoggedInUser().getNickname());
        bestScoreDataMenu.setText("Best Score: " + App.getLoggedInUser().getBestScore());
        rankDataMenu.setText("Rank: " + App.getRankByUsername(App.getLoggedInUser().getUsername()));
        gamesPlayedDataMenu.setText("Games Played: " + App.getLoggedInUser().getNumberOfPlayedGames());
        wonGamesDataMenu.setText("Won Games: " + App.getLoggedInUser().getNumberOfWonGames());
        drawGamesDataMenu.setText("Draw Games: " + App.getLoggedInUser().getNumberOfDraws());
        lostGamesDataMenu.setText("Lost Games: " + App.getLoggedInUser().getNumberOfLostGames());
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.MAIN_MENU);
        Controller.MAIN_MENU_CONTROLLER.run();

        sendTestMessage("ali|Ha ha ha ridi");
    }

    private void sendTestMessage(String message) {
        String[] split = message.split("\\|");
        String receiverUsername = split[0];
        //send message
        ServerConnector serverConnector = new ServerConnector();
        User sender = App.getLoggedInUser();
        User receiver = App.getUserByUsername(receiverUsername);
        serverConnector.sendMessage(sender.getID(), receiver.getID(), split[1]);
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
            newUsernameField.clear();
        }
    }

    public void editNickname(MouseEvent mouseEvent) {
        controller.editNickname(newNicknameField.getText());
        if (OutputView.getLastError() == Errors.NICKNAME_CHANGED) {
            nicknameShower.setText(newNicknameField.getText());
            newNicknameField.clear();
        }
    }

    public void openUserDataMenu(MouseEvent mouseEvent) throws IOException {
        paneChanger("User Data Menu", "userDataMenu.fxml");
    }

    public void openGameHistoryMenu(MouseEvent mouseEvent) throws IOException {
        controller.showGameHistory();
    }

    public void editEmail(MouseEvent mouseEvent) {
        controller.editEmail(newEmailField.getText());
        if (OutputView.getLastError() == Errors.EMAIL_CHANGED) {
            newEmailField.clear();
        }
    }

    public void editPassword(MouseEvent mouseEvent) {
        controller.editPassword(oldPasswordField.getText(), newPasswordField.getText(), confirmNewPasswordField.getText());
        if (OutputView.getLastError() == Errors.PASSWORD_CHANGED) {
            oldPasswordField.clear();
            newPasswordField.clear();
            confirmNewPasswordField.clear();
        }
    }

    public void backToProfileMenu(MouseEvent mouseEvent) throws IOException {
        paneChanger("Profile Menu", "ProfileMenu.fxml");
    }

    public void openFriendsListMenu(MouseEvent mouseEvent) {
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}
