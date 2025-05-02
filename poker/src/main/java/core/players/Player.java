package core.players;

import core.cards.Card;
import core.game.Game;

import java.util.ArrayList;

/**
 * Represents a participant in the poker game, managing their state,
 * hand of cards, chip count, and betting actions.
 */
public class Player {

    /** Player's display name. */
    private String name;
    /** The hand of cards currently held by the player. */
    private ArrayList<Card> hand;
    /** The number of chips the player currently has. */
    private int chips;
    /** Whether the player has folded in the current round. */
    private boolean folded;
    /** Reference to the game instance this player is part of. */
    protected Game game;
    /** The amount the player has bet in the current round. */
    private int bet;

    /**
     * Constructs a new player with a given name, initial chip count, and game context.
     *
     * @param name         the player's display name
     * @param initialChips the starting number of chips for the player
     * @param game         reference to the current Game instance
     */
    public Player(String name, int initialChips, Game game) {
        this.name = name;
        this.chips = initialChips;
        this.hand = new ArrayList<>();
        this.folded = false;
        this.game = game;
        this.bet = 0;
    }

    /**
     * Folds the player's hand, marking them as inactive for the remainder of the round.
     * Prints a message indicating the fold action.
     */
    public void fold() {
        folded = true;
        System.out.println(name + " folded.");
    }

    /**
     * Places a bet for the player, deducting the amount from their chips
     * and adding it to the game's pot. Bets up to the player's available chips.
     *
     * @param amount the desired bet amount
     */
    public void bet(int amount) {
        amount = Math.min(amount, chips);
        chips -= amount;
        bet += amount;
        game.getTable().addBet(amount);
        System.out.println(name + " bet " + amount + " chips.");
    }

    /**
     * Matches the current highest bet in the game, if possible.
     * Calculates the difference and places the required chips as a bet.
     */
    public void call() {
        int highestBet = game.getHighestBet();
        int amount = Math.min(highestBet - bet, chips);
        bet(amount);
    }

    /**
     * Checks (passes) if the player does not wish to increase the bet.
     * Prints a message indicating the check action.
     */
    public void check() {
        System.out.println(name + " checked.");
    }

    /**
     * Retrieves the player's current hand of cards.
     *
     * @return list of cards in the player's hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Retrieves the player's display name.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the number of chips the player currently has.
     *
     * @return current chip count
     */
    public int getChips() {
        return chips;
    }

    /**
     * Indicates whether the player has folded in this round.
     *
     * @return true if the player has folded, false otherwise
     */
    public boolean isFolded() {
        return folded;
    }

    /**
     * Retrieves the total amount the player has bet in the current round.
     *
     * @return current bet amount
     */
    public int getBetAmount() {
        return bet;
    }

    /**
     * Updates the player's chip count directly.
     *
     * @param chips the new chip count to set
     */
    public void setChips(int chips) {
        this.chips = chips;
    }

    /**
     * Resets the player's state for a new game round.
     * If the player is out of chips, resets them to 1000.
     * Clears the hand, resets folded status and bet amount.
     */
    public void reset() {
        if (getChips() == 0) {
            System.out.println(name + "'s chips were reset to 1000.");
            setChips(1000);
        }
        hand.clear();
        folded = false;
        bet = 0;
    }
}
