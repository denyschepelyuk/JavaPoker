package ui;

import core.game.Game;
import core.players.Player;

import java.util.ArrayList;

/**
 * Processes console commands entered by the user and maps them to game actions.
 * Utilizes an InputHandler to read input, then invokes corresponding methods
 * on the active player in the Game instance.
 */
public class CommandHandler {
    private Game game;
    private InputHandler input;

    /**
     * Creates a CommandHandler for the given game.
     *
     * @param game the Game instance whose state will be modified by user commands
     */
    public CommandHandler(Game game) {
        this.game = game;
        this.input = new InputHandler();
    }

    /**
     * Extracts the command keyword from a full input line.
     *
     * @param line the raw input string
     * @return the first token representing the command
     */
    private String extractCommand(String line) {
        return line.split(" ")[0];
    }

    /**
     * Extracts a numerical parameter from the input line, if present.
     *
     * @param line the raw input string
     * @return the parsed integer or -1 if parsing fails
     */
    private int extractNum(String line) {
        try {
            return Integer.parseInt(line.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * Reads a command from the console and executes the matching game action.
     * Supported commands:
     * <ul>
     *   <li>"fold" - the player folds their hand</li>
     *   <li>{@code bet <amount>} - the player bets the specified number of chips</li>
     *   <li>"call" - the player calls the current highest bet</li>
     *   <li>"check" - the player checks if no higher bet is required</li>
     *   <li>"exit", "e", "quit", "q" - exits the game loop</li>
     *   <li>Any other input displays help text and reprompts</li>
     * </ul>
     */
    public void handleInput() {
        String line = input.getCommand();
        String command = extractCommand(line);
        int num = extractNum(line);

        ArrayList<Player> players = game.getPlayers();

        switch (command) {
            case "fold":
                players.get(0).fold();
                break;
            case "bet":
                if (num != -1) {
                    players.get(0).bet(num);
                } else {
                    System.out.println("Invalid bet amount");
                }
                break;
            case "call":
                players.get(0).call();
                break;
            case "check":
                if (game.getHighestBet() > players.get(0).getBetAmount()) {
                    System.out.println("You can't check, you need to call, raise or fold.");
                    handleInput();
                    break;
                }
                players.get(0).check();
                break;
            case "exit":
            case "e":
            case "quit":
            case "q":
                System.out.println("Exiting game...");
                game.stop();
                break;
            default:
                System.out.println("Commands: check, bet, call, fold, help");
                handleInput();
                break;
        }
    }
}