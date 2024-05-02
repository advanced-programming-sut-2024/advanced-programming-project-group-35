package Controller;

import View.LoginMenuView;

import java.util.Scanner;

public class LoginMenuController {
    public static void run(Scanner scanner) {
        String input = LoginMenuView.getInput(scanner);
    }
}
