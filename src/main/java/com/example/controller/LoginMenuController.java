package com.example.controller;

import com.example.model.App;
import com.example.model.IO.Steps.LoginMenuStep;
import com.example.model.IO.errors.Errors;
import com.example.model.IO.patterns.Patterns;
import com.example.model.user.User;
import com.example.view.OutputView;
import com.example.view.Menu;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

public class LoginMenuController extends AppController {
    private LoginMenuStep currentStep = LoginMenuStep.NOTHING;
    private User registeringUser;
    private User forgotenPasswordUser;

    @Override
    public void run() {
        try {
            App.getAppView().showMenu(Menu.LOGIN_MENU);
            App.setCurrentController(Controller.LOGIN_MENU_CONTROLLER);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private Errors runCommandSetPassword(String input) {
        Matcher matcher;
        if ((matcher = Patterns.SET_PASSWORD.getMather(input)) == null) {
            return Errors.INVALID_COMMAND;
        }
        Errors error = handlePassword(matcher.group("password"), matcher.group("confirmPassword"));
        if (error != null) {
            return error;
        }
        String newPassword = matcher.group("password");
        forgotenPasswordUser.setPassword(newPassword);
        currentStep = LoginMenuStep.NOTHING;
        return Errors.PASSWORD_CHANGED;
    }

//    private Errors runCommandForgotPassword(String input) {
//        Matcher matcher;
//        if ((matcher = Patterns.ANSWER_QUESTION.getMather(input)) == null) {
//            return Errors.INVALID_COMMAND;
//        }
//        String securityQuestionAnswer = matcher.group("answer");
//        if (!forgotenPasswordUser.getSecurityQuestionAnswer().equals(securityQuestionAnswer)) {
//            return Errors.WRONG_ANSWER_CONFIRMATION;
//        }
//        currentStep = LoginMenuStep.SET_PASSWORD;
//        return Errors.SET_PASSWORD;
//    }


//    private Errors runCommandRegisterFirstStep(String input) {
//        Matcher matcher;
//        if ((matcher = Patterns.PICK_QUESTION.getMather(input)) == null) {
//            return Errors.INVALID_COMMAND;
//        }
//        int securityQuestionNumber = Integer.parseInt(matcher.group("questionNumber"));
//        String securityQuestionAnswer = matcher.group("answer");
//        String securityQuestionAnswerConfirmation = matcher.group("confirmAnswer");
//        return setSecurityQuestion(securityQuestionAnswer, securityQuestionAnswerConfirmation, securityQuestionNumber);
//    }

    public Errors finalizeRegisterUser(String securityQuestionAnswer, String securityQuestionAnswerConfirmation, int securityQuestionNumber) {
        if (!securityQuestionAnswer.equals(securityQuestionAnswerConfirmation)) {
            return OutputView.showOutputAlert(Errors.WRONG_ANSWER_CONFIRMATION);
        }
        registeringUser.setSecurityQuestion(App.getSecurityQuestions().get(securityQuestionNumber));
        registeringUser.setSecurityQuestionAnswer(securityQuestionAnswer);
        App.addNewUser(registeringUser);
        currentStep = LoginMenuStep.NOTHING;
        return OutputView.showOutputAlert(Errors.REGISTER_SUCCESSFUL);
    }

    private Errors runCommandNothingStep(String input) {
        Matcher matcher;
        Errors error = null;
        if ((matcher = Patterns.REGISTER_USER.getMather(input)) != null) {
//            error = terminalRegisterUser(matcher);
        } else if ((matcher = Patterns.LOGIN_USER.getMather(input)) != null) {
//            error = terminalLoginUser(matcher);
        } else if ((matcher = Patterns.FORGOT_PASSWORD.getMather(input)) != null) {
//            error = terminalForgotPassword(matcher);
        } else if ((matcher = Patterns.EXIT.getMather(input)) != null) {
//            App.getAppView().getTerminal().printMessage("Goodbye!");
//            App.getAppView().getTerminal().close();
            return Errors.NO_ERROR;
        } else {
            error = Errors.INVALID_COMMAND;
        }
        return error;
    }

    public Errors loginUser(String username, String password, Boolean stayLoggedIn) {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return OutputView.showOutputAlert(Errors.USER_DOESNT_EXIST);
        }
        if (!user.getPassword().equals(password)) {
            return OutputView.showOutputAlert(Errors.PASSWORD_DOESNT_MATCH);
        }
        App.setLoggedInUser(user);
//        goToMainMenu();
        return OutputView.showOutputAlert(Errors.LOGIN_SUCCESSFUL);
    }

//    private void goToMainMenu() {
//        App.setCurrentMenu(Menu.MAIN_MENU);
//        Controller.MAIN_MENU_CONTROLLER.run();
//    }

//    private Errors terminalForgotPassword(Matcher matcher) {
//        String username = matcher.group("username");
//        forgotenPasswordUser = App.getUserByUsername(username);
//        if (forgotenPasswordUser == null) {
//            return Errors.USER_DOESNT_EXIST;
//        }
//        //showSecurityQuestion(forgotenPasswordUser);
//        currentStep = LoginMenuStep.FORGOT_PASSWORD;
//        return Errors.NO_ERROR;
//    }

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

//    private void showSecurityQuestion(User user) {
////        App.getAppView().getTerminal().printMessage(user.getSecurityQuestion());
//    }

//    private Errors terminalRegisterUser(Matcher matcher) {
//        String username = matcher.group("username");
//        String password = matcher.group("password");
//        String confirmPassword = matcher.group("confirmPassword");
//        String nickname = matcher.group("nickname");
//        String email = matcher.group("email");
//
//
//        return registerUser(username, password, confirmPassword, nickname, email, false);
//    }

    public Errors registerUser(String username, String password, String confirmPassword, String nickname, String email, Boolean stayLoggedIn) {
        if (username == null || !isValidUsername(username)) {
            return OutputView.showOutputAlert(Errors.INVALID_USERNAME_FORMAT);
        }
        if (App.getUserByUsername(username) != null) {
            return OutputView.showOutputAlert(Errors.USER_ALREADY_EXISTS);
        }
        Errors error = handlePassword(password, confirmPassword);
        if (error != null) {
            return OutputView.showOutputAlert(error);
        }
        if (!isValidEmail(email)) {
            return OutputView.showOutputAlert(Errors.INVALID_EMAIL);
        }
        registeringUser = new User(username, password, nickname, email);
        currentStep = LoginMenuStep.REGISTER_FIRST_STEP;
        return OutputView.showOutputAlert(Errors.REGISTER_FIRST_STEP_SUCCESSFUL);
    }

//    private void showSecurityQuestions() {
//        App.getAppView().getTerminal().printMessage("Choose a security question:");
//        for (int i = 1; i <= App.getSecurityQuestions().size(); i++) {
//            App.getAppView().getTerminal().printMessage(i + ". " + App.getSecurityQuestions().get(i - 1));
//        }
//    }

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

//    private void printInvalidCommand() {
//        App.getAppView().getTerminal().printError("invalid command");
//    }

    public void menuEnter(Menu menu) {
    }

    public void menuExit() {

    }

    public void showCurrentMenu() {
    }

    public void registerNewUser(String username, String password, String nickname, String email, int securityQuestionNumber, String securityQuestionAnswer) {
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
            return OutputView.showOutputAlert(Errors.USER_DOESNT_EXIST);
        }
        if (!forgotenPasswordUser.getSecurityQuestion().equals(securityQuestion)) {
            return OutputView.showOutputAlert(Errors.WRONG_SECURITY_QUESTION);
        }
        if (!forgotenPasswordUser.getSecurityQuestionAnswer().equals(securityAnswer)) {
            return OutputView.showOutputAlert(Errors.WRONG_ANSWER_CONFIRMATION);
        }
        currentStep = LoginMenuStep.SET_PASSWORD;
        return OutputView.showOutputAlert(Errors.NO_ERROR);
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
}
