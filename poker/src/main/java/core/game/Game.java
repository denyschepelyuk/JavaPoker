package core.game;

import core.players.AIPlayer;
import core.players.Player;
import core.cards.Deck;
import ui.CommandHandler;
import ui.ConsoleUI;
import util.CardUtils;

import java.util.ArrayList;

public class Game {
    private Deck deck;
    private Table table;
    private ArrayList<Player> players;
    private ConsoleUI UI;
    private CommandHandler commandHandler;
    private boolean isRunning;
    private boolean isRoundRunning;

    public Game(int botsCount) {
        _init(botsCount);
    }

    private void prepareForNextRound() {
        table.reset();
        isRoundRunning = true;
        // Initialize deck and shuffle
        deck = new Deck();
        deck.shuffle();
        // Deal 2 cards to each player
        for (Player p : players) {
            p.reset();
            deck.dealHand(2, p);
        }
    }

    public void _init(int botsCount) {
        // Initialize Game properties
        table = new Table();
        players = new ArrayList<>();
        UI = new ConsoleUI();
        commandHandler = new CommandHandler(this);
        isRunning = true;

        // Add Human player
        players.add(new Player("You", 1000, this));
        // Add AI players
        for (int i = 1; i <= botsCount; i++) {
            AIPlayer aiPlayer = new AIPlayer("Bot" + i, 1000, this);
            players.add(aiPlayer);
        }
        // Reset table, deck and players for the first round
        prepareForNextRound();
    }

    private boolean _roundEnded() {
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

    public boolean _gameEnded() {
        return !isRunning;
    }

    private void _updatePlayers(boolean isFirstRound) {
        for (Player p : players) {
            if (_gameEnded()) return;
            if (p.isFolded()) continue;

            if (!isFirstRound)
                if (p.getBetAmount() == getHighestBet() || p.getChips() == 0) continue;

            if (p instanceof AIPlayer) ((AIPlayer) p).makeDecision();
            else commandHandler.handleInput();

        }

        for (Player p : players)
            if (!p.isFolded() && p.getBetAmount() != getHighestBet() && p.getChips() != 0) {
                _updatePlayers(false);
                break;
            }
    }

    private void _render() {
        UI.renderGame(this);
    }

    private void _findWinner() {
        Player winner = CardUtils.compareHands(this);
        winner.setChips(winner.getChips() + table.getPotAmount());
        UI.displayWinner(this, winner);
    }

    // Getters
    public Table getTable() { return table; }
    public ArrayList<Player> getPlayers() { return players; }

    private void round() {
        // Round loop.
        while (isRoundRunning) {
            _render();
            _updatePlayers(true);
            if (_roundEnded() || _gameEnded()) {
                isRoundRunning = false;
                break;
            }
            table.deal(deck);
        }
    }

    public void run() {
        // Main game loop
        while (!_gameEnded()) {
            // Main game loop.
            round();
            _findWinner();
            prepareForNextRound();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public int getHighestBet() {
        int highestBet = 0;
        for (Player p : players)
            if (p.getBetAmount() > highestBet)
                highestBet = p.getBetAmount();
        return highestBet;
    }
}
