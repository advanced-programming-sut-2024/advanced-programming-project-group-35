package View;

import java.util.Scanner;

public abstract class AppMenu {
    public abstract String getName();
    public abstract void output(Error error);
    public abstract void output(String message);
}
