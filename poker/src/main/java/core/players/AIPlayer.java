package core.players;

import core.game.Game;
import util.CardUtils;

/**
 * Extends {@link Player} to implement AI-driven decision making
 * for poker gameplay based on hand strength, game state, and randomness.
 */
public class AIPlayer extends Player {

    /**
     * Constructs an AI-controlled player with the specified name,
     * initial chip count, and game context.
     *
     * @param name         display name of the AI player
     * @param initialChips starting number of chips for the AI player
     * @param game         reference to the current {@link Game} instance
     */
    public AIPlayer(String name, int initialChips, Game game) {
        super(name, initialChips, game);
    }

    /**
     * Makes a betting decision based on the AI player's hand strength,
     * the current highest bet, and a random factor to vary behavior.
     * <p>
     * Behavior is divided into three categories:
     * <ul>
     *   <li><b>Weak hands (strength ≥ 9):</b> Mostly fold if a bet is required, occasionally call; otherwise check.</li>
     *   <li><b>Medium hands (6 ≤ strength &lt; 9):</b> Usually call, sometimes raise or bet when able; otherwise check or bet.</li>
     *   <li><b>Strong hands (strength &lt; 6):</b> Occasionally slow-play by calling or bet aggressively when able.</li>
     * </ul>
     */
    public void makeDecision() {
        int handStrength = CardUtils.evaluateHand(getHand(), game.getTable().getCommunityCards());
        int toCall = game.getHighestBet() - getBetAmount();
        double randomFactor = Math.random();

        if (handStrength >= 9) { // Weak hands: High Card, Low Pair
            if (toCall > 0) {
                if (randomFactor < 0.7) fold(); // Mostly fold if a bet is needed
                else call(); // Occasionally call
            } else {
                check();
            }
        }
        else if (handStrength >= 6) { // Medium hands: Two Pair, Three of a Kind, Straight
            if (toCall > 0) {
                if (randomFactor < 0.6) call(); // Call most of the time
                else bet(toCall + 50); // Occasionally raise
            } else {
                if (randomFactor < 0.5) check();
                else bet(50); // Occasionally bet
            }
        }
        else { // Strong hands: Flush, Full House, Four of a Kind, Straight Flush, Royal Flush
            if (toCall > 0) {
                if (randomFactor < 0.2) call(); // Occasionally slow play
                else bet(toCall + (int)(Math.random() * getChips() / 10)); // Bet aggressively
            } else {
                bet((int)(Math.random() * getChips() / 8 + 50)); // Aggressive bet
            }
        }
    }
}
