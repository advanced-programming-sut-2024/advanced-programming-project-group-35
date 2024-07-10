package com.example.controller;

import com.example.model.App;
import com.example.model.IO.Steps.LoginMenuStep;
import com.example.model.IO.errors.Errors;
import com.example.model.User;
import com.example.view.OutputView;
import com.example.view.Menu;
import javafx.application.Platform;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginMenuController extends AppController {
    private LoginMenuStep currentStep = LoginMenuStep.NOTHING;
    private User registeringUser;
    private User forgotenPasswordUser;

    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.LOGIN_MENU);
            App.setCurrentController(Controller.LOGIN_MENU_CONTROLLER);
            Thread stayLoggedInThread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (User user : App.getAllUsers()) {
                    if (user.stayLoggedIn()) {
                        try {
                            loginUserForce(user.getUsername(), user.getPassword(), true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Platform.runLater(() -> {
                                App.setCurrentMenu(Menu.MAIN_MENU);
                                Controller.MAIN_MENU_CONTROLLER.run();
                            });

                    }
                }

            });
            stayLoggedInThread.start();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Errors finalizeRegisterUser(String securityQuestionAnswer, String securityQuestionAnswerConfirmation, int securityQuestionNumber) throws IOException {
        if (!securityQuestionAnswer.equals(securityQuestionAnswerConfirmation)) {
            return OutputView.showOutputAlert(Errors.WRONG_ANSWER_CONFIRMATION);
        }
        registeringUser.setSecurityQuestion(App.getSecurityQuestions().get(securityQuestionNumber));
        registeringUser.setSecurityQuestionAnswer(securityQuestionAnswer);
        registeringUser.setNewID();
//        registeringUser.setId(10);
        App.addNewUser(registeringUser);
        App.setLoggedInUser(registeringUser);
        currentStep = LoginMenuStep.NOTHING;
        return OutputView.showOutputAlert(Errors.REGISTER_SUCCESSFUL);
    }

    public Errors loginUser(String username, String password, Boolean stayLoggedIn) throws IOException {
        User user = App.getUserByUsername(username);
        if (user == null) {
            OutputView.showOutputAlert(Errors.USER_DOESNT_EXIST);
            return Errors.USER_DOESNT_EXIST;
        }
        if (!user.getPassword().equals(password)) {
            OutputView.showOutputAlert(Errors.PASSWORD_DOESNT_MATCH);
            return Errors.PASSWORD_DOESNT_MATCH;
        }
        user.setStayLoggedIn(stayLoggedIn);
        App.setLoggedInUser(user);
        OutputView.showOutputAlert(Errors.LOGIN_SUCCESSFUL);
        return Errors.LOGIN_SUCCESSFUL;
    }

    public void loginUserForce(String username, String password, Boolean stayLoggedIn) throws IOException {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return;
        }
        if (!user.getPassword().equals(password)) {
            return;
        }
        user.setStayLoggedIn(stayLoggedIn);
        App.setLoggedInUser(user);
    }

    private Errors handlePassword(String newPassword, String confirmPassword) {
        if (!isValidPassword(newPassword)) {
            return Errors.INVALID_PASSWORD;
        }
        if (isWeakPassword(newPassword)) {
            return Errors.WEAK_PASSWORD;
        }
        if (!newPassword.equals(confirmPassword)) {
            return Errors.PASSWORD_DOESNT_MATCH;
        }
        return null;
    }

    public Errors registerUser(String username, String password, String confirmPassword, String nickname, String email, Boolean stayLoggedIn) {
        if (username == null || !isValidUsername(username)) {
            OutputView.showOutputAlert(Errors.INVALID_USERNAME_FORMAT);
            return Errors.INVALID_USERNAME_FORMAT;
        }
        if (App.getUserByUsername(username) != null) {
            OutputView.showOutputAlert(Errors.USER_ALREADY_EXISTS);
            return Errors.USER_ALREADY_EXISTS;
        }
        Errors error = handlePassword(password, confirmPassword);
        if (error != null) {
            OutputView.showOutputAlert(error);
            return error;
        }
        if (!isValidEmail(email)) {
            OutputView.showOutputAlert(Errors.INVALID_EMAIL);
            return Errors.INVALID_EMAIL;
        }
        registeringUser = new User(username, password, nickname, email);
        currentStep = LoginMenuStep.REGISTER_FIRST_STEP;
        OutputView.showOutputAlert(Errors.REGISTER_FIRST_STEP_SUCCESSFUL);
        return Errors.REGISTER_FIRST_STEP_SUCCESSFUL;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isWeakPassword(String password) {
        return !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^\\S{8,}$");
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9-]*$");
    }

    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        final String DIGITS = "0123456789";
        final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String SPECIAL_CHARACTERS = "@#$%^&+=";
        final int length = 10;
        List<Character> password = new ArrayList<>();
        password.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.add(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
        String allCharacters = DIGITS + LOWERCASE + UPPERCASE + SPECIAL_CHARACTERS;
        for (int i = 4; i < length; i++) {
            password.add(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        Collections.shuffle(password, random);
        StringBuilder finalPassword = new StringBuilder();
        for (char c : password) {
            finalPassword.append(c);
        }
        return finalPassword.toString();
    }

    public Errors checkSecurityQuestions(String username, String securityQuestion, String securityAnswer) {
        forgotenPasswordUser = App.getUserByUsername(username);
        if (forgotenPasswordUser == null) {
            OutputView.showOutputAlert(Errors.USER_DOESNT_EXIST);
            return Errors.USER_DOESNT_EXIST;
        }
        if (!forgotenPasswordUser.getSecurityQuestion().equals(securityQuestion)) {
            System.out.println(forgotenPasswordUser.getSecurityQuestion() + " " + securityQuestion);
            OutputView.showOutputAlert(Errors.WRONG_SECURITY_QUESTION);
            return Errors.WRONG_SECURITY_QUESTION;
        }
        if (!forgotenPasswordUser.getSecurityQuestionAnswer().equals(securityAnswer)) {
            OutputView.showOutputAlert(Errors.WRONG_ANSWER_CONFIRMATION);
            return Errors.WRONG_ANSWER_CONFIRMATION;
        }
        currentStep = LoginMenuStep.SET_PASSWORD;
        OutputView.showOutputAlert(Errors.NO_ERROR);
        return Errors.NO_ERROR;
    }

    public Errors setNewPassword(String password, String confirmPassword) {
        Errors error = handlePassword(password, confirmPassword);
        if (error != null) {
            return OutputView.showOutputAlert(error);
        }
        forgotenPasswordUser.setPassword(password);
        currentStep = LoginMenuStep.NOTHING;
        return OutputView.showOutputAlert(Errors.PASSWORD_CHANGED);
    }

    public int getEmailVerificationCode() throws IOException {
        int code = new SecureRandom().nextInt(9000) + 1000;
        System.out.println(code);
        String emailText = "GWENT\n\nYour verification code is: " + code;
        EmailSender.sendEmail(registeringUser.getEmail(), emailText);
        System.out.println("Code sent");
        System.out.println("registeringUser.username = " + registeringUser.getUsername());
        EmailSender.sendLinkEmail(registeringUser.getEmail(), EmailVerification.createVerificationLink(registeringUser.getUsername()));
        System.out.println("Link sent");
        return code;
    }
}
