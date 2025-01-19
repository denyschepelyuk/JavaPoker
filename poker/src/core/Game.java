package core;

import ui.InputHandler;
import ui.ConsoleUI;
import util.CardUtils;

import java.util.ArrayList;

public class Game {
    private Deck deck;
    private Table table;
    private ArrayList<Player> players;
    private ConsoleUI UI;
    private InputHandler inputHandler;
    private boolean isRunning;

    public Game() {
        deck = new Deck();
        table = new Table();
        players = new ArrayList<>();
        UI = new ConsoleUI();
        inputHandler = new InputHandler();
        isRunning = true;
    }

    public void _init(int playersCount) {
        Player player1 = new Player("You", 1000, this);
        players.add(player1);

        for (int i = 1; i < playersCount; i++) {
            AIPlayer aiPlayer = new AIPlayer("Player" + i, 1000, this);
            players.add(aiPlayer);
        }

        deck = new Deck();
        deck.shuffle();

        for (Player p : players) {
            deck.dealHand(2, p);
        }
    }

    private boolean _gameEnded() {
        if (table.getCommunityCards().size() == 5) {
            return true;
        }
        int activePlayers = 0;
        for (Player p : players) {
            if (!p.isFolded()) {
                activePlayers++;
            }
        }
        return (activePlayers <= 1);
    }

    private void _update() {
        for (Player p : players) {
            if (p instanceof AIPlayer && !p.isFolded()) {
                ((AIPlayer) p).makeDecision();
            }
        }
    }

    private void _render() {
        UI.renderGame(this);
    }

    // TODO: Implement correct winner logic
    private void _findWinner() {
        Player winner = null;
        UI.displayWinner(winner);
    }

    // Getters
    public Table getTable() { return table; }
    public ArrayList<Player> getPlayers() { return players; }

    public void run(int playersCount) {
        _init(playersCount);
        while (isRunning) {
            // AI players make decisions
            _update();
            _render();

            // Human player makes decision
            handleInput();

            if (_gameEnded()) {
                isRunning = false;
            }

            table.deal(deck);

            System.out.println();
        }
        _findWinner();
        table.reset();
    }

    public int getHighestBet() {
        int highestBet = 0;
        for (Player p : players) {
            if (p.getBetAmount() > highestBet) {
                highestBet = p.getBetAmount();
            }
        }
        return highestBet;
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
    private void handleInput() {
        String line = inputHandler.getCommand();
        String command = extractCommand(line);
        int num = extractNum(line);

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
                if (getHighestBet() > players.get(0).getBetAmount()) {
                    System.out.println("You can't check, you need to call, raise or fold.");
                    handleInput();
                    break;
                }
                players.get(0).check();
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }
}
