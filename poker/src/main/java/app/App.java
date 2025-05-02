package app;

import core.game.Game;
import ui.InputHandler;

public class App {
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