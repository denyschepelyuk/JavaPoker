package core.game;

import core.players.AIPlayer;
import core.players.Player;
import core.cards.Deck;
import ui.CommandHandler;
import ui.ConsoleUI;
import util.CardUtils;

import java.util.ArrayList;

/**
 * Orchestrates the main poker game flow, including initializing players,
 * handling rounds, dealing cards, updating players, and determining winners.
 */
public class Game {
    private Deck deck;
    private Table table;
    private ArrayList<Player> players;
    private ConsoleUI UI;
    private CommandHandler commandHandler;
    private boolean isRunning;
    private boolean isRoundRunning;

    /**
     * Constructs a new Game instance and initializes it with the given number of AI opponents.
     *
     * @param botsCount count of AI players to include alongside the human player
     */
    public Game(int botsCount) {
        _init(botsCount);
    }

    /**
     * Initializes game state, creates human and AI players, and prepares the first round.
     *
     * @param botsCount number of AI players to add
     */
    public void _init(int botsCount) {
        table = new Table();
        players = new ArrayList<>();
        UI = new ConsoleUI();
        commandHandler = new CommandHandler(this);
        isRunning = true;

        // Add human player
        players.add(new Player("You", 1000, this));
        // Add AI opponents
        for (int i = 1; i <= botsCount; i++) {
            players.add(new AIPlayer("Bot" + i, 1000, this));
        }
        // Prepare and start the first round
        prepareForNextRound();
    }

    /**
     * Prepares for a new round by resetting the table, shuffling a fresh deck,
     * and dealing two cards to each player.
     */
    private void prepareForNextRound() {
        table.reset();
        isRoundRunning = true;
        deck = new Deck();
        deck.shuffle();
        for (Player p : players) {
            p.reset();
            deck.dealHand(2, p);
        }
    }

    /**
     * Determines whether the current round has ended, either by all community cards
     * being dealt or only one active player remaining.
     *
     * @return true if the round is complete, false otherwise
     */
    private boolean _roundEnded() {
        if (table.getCommunityCards().size() == 5) {
            return true;
        }
        int active = 0;
        for (Player p : players) {
            if (!p.isFolded()) active++;
        }
        return active <= 1;
    }

    /**
     * Indicates whether the overall game has been stopped.
     *
     * @return true if game run loop should exit, false otherwise
     */
    public boolean _gameEnded() {
        return !isRunning;
    }

    /**
     * Updates each player's turn in the round. Human players invoke console input;
     * AI players make an automated decision. Recurses until all active players
     * have matched the highest bet.
     *
     * @param isFirstRound true if this is the first betting phase of the round
     */
    private void _updatePlayers(boolean isFirstRound) {
        for (Player p : players) {
            if (_gameEnded()) return;
            if (p.isFolded()) continue;
            if (!isFirstRound && (p.getBetAmount() == getHighestBet() || p.getChips() == 0)) continue;
            if (p instanceof AIPlayer) {
                ((AIPlayer) p).makeDecision();
            } else {
                commandHandler.handleInput();
            }
        }
        for (Player p : players) {
            if (!p.isFolded() && p.getBetAmount() != getHighestBet() && p.getChips() != 0) {
                _updatePlayers(false);
                break;
            }
        }
    }

    /**
     * Renders the current game state (community cards, pot, player status) to the console.
     */
    private void _render() {
        UI.renderGame(this);
    }

    /**
     * Determines and announces the round winner by comparing hands,
     * awards the pot, and displays the result.
     */
    private void _findWinner() {
        Player winner = CardUtils.compareHands(this);
        winner.setChips(winner.getChips() + table.getPotAmount());
        UI.displayWinner(this, winner);
    }

    /**
     * Executes a full round of play: rendering, updating players, dealing community cards,
     * and finding a winner when the round ends.
     */
    private void round() {
        while (isRoundRunning) {
            _render();
            _updatePlayers(true);
            if (_roundEnded() || _gameEnded()) {
                isRoundRunning = false;
            } else {
                table.deal(deck);
            }
        }
    }

    /**
     * Main game loop that continues running rounds until the game is stopped.
     */
    public void run() {
        while (!_gameEnded()) {
            round();
            _findWinner();
            prepareForNextRound();
        }
    }

    /**
     * Stops the game, causing the run loop to exit.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Returns the current table instance containing community cards and pot.
     *
     * @return game Table object
     */
    public Table getTable() {
        return table;
    }

    /**
     * Returns the list of players participating in the game.
     *
     * @return list of Player instances
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Retrieves the highest bet amount placed by any player in the current round.
     *
     * @return highest bet value among active players
     */
    public int getHighestBet() {
        int highest = 0;
        for (Player p : players) {
            if (p.getBetAmount() > highest) {
                highest = p.getBetAmount();
            }
        }
        return highest;
    }
}
