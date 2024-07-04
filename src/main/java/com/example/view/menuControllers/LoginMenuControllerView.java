package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.LoginMenuController;
import com.example.model.App;
import com.example.model.IO.errors.Errors;
import com.example.model.alerts.AlertType;
import com.example.view.Menu;
import com.example.view.OutputView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginMenuControllerView {
    private final Stage stage = App.getAppView().getPrimaryStage();
    private Pane pane = App.getAppView().getPane();
    public TextField usernameFieldLogin;
    public PasswordField passwordFieldLogin;
    public CheckBox stayLoggedInCheckBoxLogin;
    public TextField securityAnswerConfirmationRegister;
    public VBox questionVBox;
    public VBox passwordVBox;
    public TextField usernameFieldResetPassword;
    public ComboBox securityQuestionResetPassword;
    public TextField securityAnswerResetPassword;
    public PasswordField passwordFieldResetPassword;
    public PasswordField confirmPasswordFieldResetPassword;
    @FXML
    private TextField usernameFieldRegister;
    @FXML
    private PasswordField passwordFieldRegister;
    @FXML
    private PasswordField confirmPasswordFieldRegister;
    @FXML
    private TextField emailFieldRegister;
    @FXML
    private TextField nicknameFieldRegister;
    @FXML
    private CheckBox stayLoggedInCheckBoxRegister;
    @FXML
    private ComboBox securityQuestionRegister;
    @FXML
    private TextField securityAnswerRegister;
    private TextField emailVerificationCodeField;
    private int emailVerificationCode;

    LoginMenuController controller = (LoginMenuController) Controller.LOGIN_MENU_CONTROLLER.getController();

    public void openLoginStage(MouseEvent mouseEvent) throws IOException {
        paneChanger("Login", "login.fxml");
    }

    public void openRegisterStage(MouseEvent mouseEvent) throws IOException {
        paneChanger("Register", "register.fxml");
    }

    public void openResetPasswordStage(MouseEvent mouseEvent) throws IOException {
        paneChanger("Reset Password", "resetPassword.fxml");
    }

    public void generateRandomPassword(MouseEvent mouseEvent) {
        String randomPassword = controller.generateRandomPassword();
        passwordFieldRegister.setText(randomPassword);
        confirmPasswordFieldRegister.setText(randomPassword);
        App.getAppView().showAlert("Your random password is: " + randomPassword, AlertType.INFO.getType());
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

    public void loginUser(MouseEvent mouseEvent) {
        String username = usernameFieldLogin.getText();
        String password = passwordFieldLogin.getText();
        boolean stayLoggedIn = stayLoggedInCheckBoxLogin.isSelected();
        controller.loginUser(username, password, stayLoggedIn);
        if (OutputView.getLastError() == Errors.LOGIN_SUCCESSFUL) {
            App.setCurrentMenu(Menu.MAIN_MENU);
            Controller.MAIN_MENU_CONTROLLER.run();
        }
    }

    public void registerUser(MouseEvent mouseEvent) throws IOException {
        String username = usernameFieldRegister.getText();
        String password = passwordFieldRegister.getText();
        String confirmPassword = confirmPasswordFieldRegister.getText();
        String email = emailFieldRegister.getText();
        String nickname = nicknameFieldRegister.getText();
        boolean stayLoggedIn = stayLoggedInCheckBoxRegister.isSelected();
        controller.registerUser(username, password, confirmPassword, nickname, email, stayLoggedIn);
        setEmailVerificationCode();
        if (OutputView.getLastError() == Errors.REGISTER_FIRST_STEP_SUCCESSFUL) {
            paneChanger("Security Question", "securityQuestion.fxml");
            OutputView.showOutputAlert(Errors.SENT_CODE);
        }
    }

    private void setEmailVerificationCode() throws IOException {
        emailVerificationCode = controller.getEmailVerificationCode();
    }

    public void finalizeRegisterUser(MouseEvent mouseEvent) {
        int securityQuestionIndex = securityQuestionRegister.getSelectionModel().getSelectedIndex();
        String securityAnswer = securityAnswerRegister.getText();
        String securityAnswerConfirmation = securityAnswerConfirmationRegister.getText();
        int emailVerificationCode = Integer.parseInt(emailVerificationCodeField.getText());
        if (emailVerificationCode != this.emailVerificationCode) {
            App.getAppView().showAlert("Wrong email verification code", AlertType.ERROR.getType());
            return;
        }
        controller.finalizeRegisterUser(securityAnswer, securityAnswerConfirmation, securityQuestionIndex);
        if (OutputView.getLastError() == Errors.REGISTER_SUCCESSFUL) {
            App.setCurrentMenu(Menu.MAIN_MENU);
            Controller.MAIN_MENU_CONTROLLER.run();
        } else {
            App.getAppView().showAlert("Error", AlertType.ERROR.getType());
        }
    }

    public void backToLoginMenu(MouseEvent mouseEvent) {
        try {
            paneChanger("Gwent", "LoginMenu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkSecurityQuestions(MouseEvent mouseEvent) {
        String username = usernameFieldResetPassword.getText();
        securityQuestionResetPassword.getSelectionModel().select(0);
        String securityQuestion = securityQuestionResetPassword.getSelectionModel().getSelectedItem().toString();
        String securityAnswer = securityAnswerResetPassword.getText();
        controller.checkSecurityQuestions(username, securityQuestion, securityAnswer);
        if (OutputView.getLastError() == Errors.NO_ERROR) {
            questionVBox.setVisible(false);
            passwordVBox.setVisible(true);
        }
    }

    public void setNewPassword(MouseEvent mouseEvent) {
        String password = passwordFieldResetPassword.getText();
        String confirmPassword = confirmPasswordFieldResetPassword.getText();
        controller.setNewPassword(password, confirmPassword);
        if (OutputView.getLastError() == Errors.PASSWORD_CHANGED) {
            App.setCurrentMenu(Menu.LOGIN_MENU);
            Controller.LOGIN_MENU_CONTROLLER.run();
        }
    }

    public void testApp1(MouseEvent mouseEvent) {
        String username = "ali";
        String password = "@li0083Moi";
        boolean stayLoggedIn = false;
        controller.loginUser(username, password, stayLoggedIn);
        if (OutputView.getLastError() == Errors.LOGIN_SUCCESSFUL) {
            App.setCurrentMenu(Menu.MAIN_MENU);
            Controller.MAIN_MENU_CONTROLLER.run();
        }
    }

    public void testApp2(MouseEvent mouseEvent) {
        String username = "parsa";
        String password = "endn=20&Y+";
        boolean stayLoggedIn = false;
        controller.loginUser(username, password, stayLoggedIn);
        if (OutputView.getLastError() == Errors.LOGIN_SUCCESSFUL) {
            App.setCurrentMenu(Menu.MAIN_MENU);
            Controller.MAIN_MENU_CONTROLLER.run();
        }
    }
}
