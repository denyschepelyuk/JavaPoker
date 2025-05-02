package ui;

import java.util.Scanner;

/**
 * Handles console input for the Poker game by prompting the user and
 * returning the entered command string.
 */
public class InputHandler {
    /** Scanner for reading console input. */
    private Scanner scanner;

    /**
     * Constructs a new InputHandler that reads from standard input.
     */
    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for a command and reads a full line of input.
     *
     * @return the command string entered by the user
     */
    public String getCommand() {
        System.out.print("Command: ");
        return scanner.nextLine();
    }
}
