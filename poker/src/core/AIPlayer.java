package core;

import util.CardUtils;

public class AIPlayer extends Player {
    public AIPlayer(String name, int initialChips, Game game) {
        super(name, initialChips, game);
    }

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
