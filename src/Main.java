import Controller.Controller;
import Controller.LoginMenuController;
import Model.App;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller.LOGIN_MENU_CONTROLLER.run(scanner);
    }
}