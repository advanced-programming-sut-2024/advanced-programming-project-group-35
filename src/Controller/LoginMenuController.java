package Controller;

import Model.App;
import Model.User.User;
import View.AppMenu;
import java.util.Scanner;

public class LoginMenuController extends AppController {
    @Override
    public void run(Scanner scanner) {

    }

    public void menuEnter(AppMenu menu) {
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
