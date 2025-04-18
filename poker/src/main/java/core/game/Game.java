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

    public Game() {
        deck = new Deck();
        table = new Table();
        players = new ArrayList<>();
        UI = new ConsoleUI();
        isRunning = true;
        commandHandler = new CommandHandler(this);
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

    private void _updatePlayers(boolean isFirstRound) {
        for (Player p : players) {
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

    public void run(int playersCount) {
        _init(playersCount);
        while (isRunning) {
            _render();
            // Players make decisions till those not folded have equal bets
            _updatePlayers(true);
            if (_gameEnded()) {
                isRunning = false;
                break;
            }
            table.deal(deck);
            System.out.println();
        }
        _findWinner();
        table.reset();
        for (Player p : players) {
            p.reset();
        }
    }

    public int getHighestBet() {
        int highestBet = 0;
        for (Player p : players)
            if (p.getBetAmount() > highestBet)
                highestBet = p.getBetAmount();
        return highestBet;
    }
}
