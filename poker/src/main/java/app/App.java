package app;

import core.game.Game;
import ui.InputHandler;

/**
 * Entry point for the Java Poker application.
 * <p>
 * Prompts the user for the number of AI opponents and starts the game.
 */
public class App {

    /**
     * Main method to launch the Poker game.
     * <p>
     * Prompts for the number of bots (1-10) and uses a default of 3 on invalid input.
     *
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        int numBots = 3; // Default number of players
        System.out.println("Welcome to Poker!");
        System.out.println("Enter the number of bots (1-10): ");
        try {
            InputHandler scanner = new InputHandler();
            numBots = Integer.parseInt(scanner.getCommand());
            if (numBots < 1 || numBots > 10) {
                System.out.println("Invalid number of bots.. Using default value of 3.");
                numBots = 3;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value of 3.");
        }
        Game game = new Game(numBots);
        game.run();
    }
}