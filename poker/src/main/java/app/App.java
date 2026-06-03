package app;

import core.game.Game;
import ui.InputHandler;
import util.GameConfig;

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
        int numBots = GameConfig.DEFAULT_NUM_BOTS; // Default number of players
        System.out.println("Welcome to Poker!");
        System.out.println("Enter the number of bots (1-" + GameConfig.MAX_BOTS + "): ");
        try {
            InputHandler scanner = new InputHandler();
            numBots = Integer.parseInt(scanner.getCommand());
            if (numBots < 1 || numBots > GameConfig.MAX_BOTS) {
                System.out.println("Invalid number of bots.. Using default value of " + GameConfig.DEFAULT_NUM_BOTS + ".");
                numBots = GameConfig.DEFAULT_NUM_BOTS;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value of " + GameConfig.DEFAULT_NUM_BOTS + ".");
        }
        Game game = new Game(numBots);
        game.run();
    }
}