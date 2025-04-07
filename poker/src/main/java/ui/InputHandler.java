package main.java.ui;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getCommand() {
        System.out.print("Command: ");
        return scanner.nextLine();
    }
}
