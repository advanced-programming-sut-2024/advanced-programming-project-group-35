package com.example.controller;

import com.example.model.App;
import com.example.model.IO.Steps.LoginMenuStep;
import com.example.model.IO.errors.LoginMenuErrors;
import com.example.model.IO.patterns.LoginMenuPatterns;
import com.example.model.user.User;
import com.example.view.LoginMenuView;
import com.example.view.Menu;

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

    @Override
    public void runCommand(String input) {
        try {
            LoginMenuErrors error = null;
            if (currentStep == LoginMenuStep.NOTHING) {
                error = runCommandNothingStep(input);
            } else if (currentStep == LoginMenuStep.REGISTER_FIRST_STEP) {
                error = runCommandRegisterFirstStep(input);
            } else if (currentStep == LoginMenuStep.FORGOT_PASSWORD) {
                error = runCommandForgotPassword(input);
            } else if (currentStep == LoginMenuStep.SET_PASSWORD) {
                error = runCommandSetPassword(input);
            }
            if (error != null) {
                App.getAppView().getTerminal().printError(LoginMenuView.toString(error));
                //App.getAppView().showAlert(LoginMenuView.toString(error), AlertType.ERROR.getType());
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    private LoginMenuErrors runCommandSetPassword(String input) {
        Matcher matcher;
        if ((matcher = LoginMenuPatterns.SET_PASSWORD.getMather(input)) == null) {
            return LoginMenuErrors.INVALID_COMMAND;
        }
        LoginMenuErrors error = handlePassword(matcher.group("password"), matcher.group("confirmPassword"));
        if (error != null) {
            return error;
        }
        String newPassword = matcher.group("password");
        forgotenPasswordUser.setPassword(newPassword);
        currentStep = LoginMenuStep.NOTHING;
        return LoginMenuErrors.PASSWORD_CHANGED;
    }

    private LoginMenuErrors runCommandForgotPassword(String input) {
        Matcher matcher;
        if ((matcher = LoginMenuPatterns.ANSWER_QUESTION.getMather(input)) == null) {
            return LoginMenuErrors.INVALID_COMMAND;
        }
        String securityQuestionAnswer = matcher.group("answer");
        if (!forgotenPasswordUser.getSecurityQuestionAnswer().equals(securityQuestionAnswer)) {
            return LoginMenuErrors.WRONG_ANSWER_CONFIRMATION;
        }
        currentStep = LoginMenuStep.SET_PASSWORD;
        return LoginMenuErrors.SET_PASSWORD;
    }


    private LoginMenuErrors runCommandRegisterFirstStep(String input) {
        Matcher matcher;
        if ((matcher = LoginMenuPatterns.PICK_QUESTION.getMather(input)) == null) {
            return LoginMenuErrors.INVALID_COMMAND;
        }
        int securityQuestionNumber = Integer.parseInt(matcher.group("questionNumber"));
        String securityQuestionAnswer = matcher.group("answer");
        String securityQuestionAnswerConfirmation = matcher.group("confirmAnswer");
        return setSecurityQuestion(securityQuestionAnswer, securityQuestionAnswerConfirmation, securityQuestionNumber);
    }

    private LoginMenuErrors setSecurityQuestion(String securityQuestionAnswer, String securityQuestionAnswerConfirmation, int securityQuestionNumber) {
        if (!securityQuestionAnswer.equals(securityQuestionAnswerConfirmation)) {
            return LoginMenuErrors.WRONG_ANSWER_CONFIRMATION;
        }
        registeringUser.setSecurityQuestion(App.getSecurityQuestions().get(securityQuestionNumber));
        registeringUser.setSecurityQuestionAnswer(securityQuestionAnswer);
        App.addNewUser(registeringUser);
        currentStep = LoginMenuStep.NOTHING;
        return LoginMenuErrors.REGISTER_SUCCESSFUL;
    }

    private LoginMenuErrors runCommandNothingStep(String input) {
        Matcher matcher;
        LoginMenuErrors error;
        if ((matcher = LoginMenuPatterns.REGISTER_USER.getMather(input)) != null) {
            error = terminalRegisterUser(matcher);
        } else if ((matcher = LoginMenuPatterns.LOGIN_USER.getMather(input)) != null) {
            error = terminalLoginUser(matcher);
        } else if ((matcher = LoginMenuPatterns.FORGOT_PASSWORD.getMather(input)) != null) {
            error = terminalForgotPassword(matcher);
        } else if ((matcher = LoginMenuPatterns.EXIT.getMather(input)) != null) {
            App.getAppView().getTerminal().printMessage("Goodbye!");
            App.getAppView().getTerminal().close();
            return LoginMenuErrors.NO_ERROR;
        } else {
            error = LoginMenuErrors.INVALID_COMMAND;
        }
        return error;
    }

    private LoginMenuErrors terminalLoginUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        User user = App.getUserByUsername(username);
        if (user == null) {
            return LoginMenuErrors.USER_DOESNT_EXIST;
        }
        if (!user.getPassword().equals(password)) {
            return LoginMenuErrors.PASSWORD_DOESNT_MATCH;
        }
        loginUser(user);
        goToMainMenu();
        return LoginMenuErrors.LOGIN_SUCCESSFUL;
    }

    private void goToMainMenu() {
        App.setCurrentMenu(Menu.MAIN_MENU);
        Controller.MAIN_MENU_CONTROLLER.run();
    }

    private LoginMenuErrors terminalForgotPassword(Matcher matcher) {
        String username = matcher.group("username");
        forgotenPasswordUser = App.getUserByUsername(username);
        if (forgotenPasswordUser == null) {
            return LoginMenuErrors.USER_DOESNT_EXIST;
        }
        showSecurityQuestion(forgotenPasswordUser);
        currentStep = LoginMenuStep.FORGOT_PASSWORD;
        return LoginMenuErrors.NO_ERROR;
    }

    private LoginMenuErrors handlePassword(String newPassword, String confirmPassword) {
        if (!isValidPassword(newPassword)) {
            return LoginMenuErrors.INVALID_PASSWORD;
        }
        if (isWeakPassword(newPassword)) {
            return LoginMenuErrors.WEAK_PASSWORD;
        }
        if (!newPassword.equals(confirmPassword)) {
            return LoginMenuErrors.PASSWORD_DOESNT_MATCH;
        }
        return null;
    }

    private void showSecurityQuestion(User user) {
        App.getAppView().getTerminal().printMessage(user.getSecurityQuestion());
    }

    private LoginMenuErrors terminalRegisterUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String confirmPassword = matcher.group("confirmPassword");
        String nickname = matcher.group("nickname");
        String email = matcher.group("email");


        return registerUser(username, password, confirmPassword, nickname, email, false);
    }

    private LoginMenuErrors registerUser(String username, String password, String confirmPassword, String nickname, String email, Boolean isFromGraphic) {
        if (!isValidUsername(username)) {
            return LoginMenuErrors.INVALID_USERNAME;
        }
        if (App.getUserByUsername(username) != null) {
            return LoginMenuErrors.USER_ALREADY_EXISTS;
        }
        LoginMenuErrors error = handlePassword(password, confirmPassword);
        if (error != null) {
            return error;
        }
        if (!isValidEmail(email)) {
            return LoginMenuErrors.INVALID_EMAIL;
        }

        if (!isFromGraphic) {
            showSecurityQuestions();
            registeringUser = new User(username, password, nickname, email);
            currentStep = LoginMenuStep.REGISTER_FIRST_STEP;
            return LoginMenuErrors.REGISTER_FIRST_STEP_SUCCESSFUL;
        } else {
            showGraphicalSecurityQuestions();
            registeringUser = new User(username, password, nickname, email);
            currentStep = LoginMenuStep.REGISTER_FIRST_STEP;
            return LoginMenuErrors.REGISTER_FIRST_STEP_SUCCESSFUL;
        }
    }

    private void showGraphicalSecurityQuestions() {

    }

    private void showSecurityQuestions() {
        App.getAppView().getTerminal().printMessage("Choose a security question:");
        for (int i = 1; i <= App.getSecurityQuestions().size(); i++) {
            App.getAppView().getTerminal().printMessage(i + ". " + App.getSecurityQuestions().get(i - 1));
        }
        //TODO
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

    private void printInvalidCommand() {
        App.getAppView().getTerminal().printError("invalid command");
    }

    public void menuEnter(Menu menu) {
    }

    public void menuExit() {

    }

    public void showCurrentMenu() {
    }

    public void registerNewUser(String username, String password, String nickname, String email, int securityQuestionNumber, String securityQuestionAnswer) {
    }

    private String generateRandomPassword() {
        return null;
    }

    public void loginUser(User user) {
        App.setLoggedInUser(user);
    }
}
