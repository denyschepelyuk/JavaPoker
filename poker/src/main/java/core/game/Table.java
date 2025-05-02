package core.game;

import core.cards.Card;
import core.cards.Deck;

import java.util.ArrayList;

/**
 * Manages the game table by tracking community cards and the betting pot.
 * <p>
 * Responsible for dealing community cards in stages and handling pot operations.
 */
public class Table {
    private ArrayList<Card> communityCards;
    private int pot;

    /**
     * Constructs a new Table with an empty community and a zeroed pot.
     */
    public Table() {
        communityCards = new ArrayList<>();
        pot = 0;
    }

    /**
     * Deals community cards from the provided deck according to poker rules:
     * <ul>
     *   <li>If no cards have been dealt yet, deals three cards (the flop).</li>
     *   <li>Otherwise, deals one card (turn or river) until five cards total.</li>
     * </ul>
     *
     * @param deck the Deck to draw cards from
     */
    public void deal(Deck deck) {
        if (communityCards.size() == 5) {
            return;
        }

        // Deal initial card(s)
        addCommunityCard(deck.dealCard());

        // If this is the first deal, add two more to complete the flop
        if (communityCards.size() == 1) {
            for (int i = 0; i < 2; i++) {
                addCommunityCard(deck.dealCard());
            }
        }
    }

    /**
     * Adds a single card to the community cards on the table.
     *
     * @param card the Card to add to the community
     */
    public void addCommunityCard(Card card) {
        communityCards.add(card);
    }

    /**
     * Resets the table for a new round by clearing all community cards
     * and resetting the pot to zero.
     */
    public void reset() {
        communityCards.clear();
        pot = 0;
    }

    /**
     * Retrieves the current total amount of chips in the pot.
     *
     * @return the current pot amount
     */
    public int getPotAmount() {
        return pot;
    }

    /**
     * Adds a specified amount of chips to the pot.
     *
     * @param amount number of chips to add
     */
    public void addBet(int amount) {
        pot += amount;
    }

    /**
     * Returns the list of community cards currently on the table.
     *
     * @return list of community Card objects
     */
    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }
}
