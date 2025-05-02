package ui;

import core.game.Game;
import core.players.Player;

import java.util.ArrayList;

public class CommandHandler {
    private Game game;
    private InputHandler input;

    public CommandHandler(Game game) {
        this.game = game;
        this.input = new InputHandler();
    }

    private String extractCommand(String line) {
        return line.split(" ")[0];
    }
    private int extractNum(String line) {
        try{
            return Integer.parseInt(line.split(" ")[1]);
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

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
                }
                else {
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
