import Controller.LoginMenuController;
import Model.Card.UnitCards.MedicCard;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenuController.run(scanner);
        MedicCard card = new MedicCard();
    }
}