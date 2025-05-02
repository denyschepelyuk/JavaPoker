package core.cards;

import core.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Represents a deck of 52 standard playing cards.
 * Provides operations to initialize, shuffle, and deal cards or hands to players.
 */
public class Deck {
    private List<Card> cards;

    /**
     * Constructs a new deck and initializes it with all 52 cards.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Populates the deck with one card of each suit and rank combination.
     */
    private void initializeDeck() {
        Card.Suit[] suits = {Card.Suit.HEARTS, Card.Suit.DIAMONDS, Card.Suit.CLUBS, Card.Suit.SPADES};
        Card.Rank[] ranks = {
                Card.Rank.TWO, Card.Rank.THREE, Card.Rank.FOUR, Card.Rank.FIVE,
                Card.Rank.SIX, Card.Rank.SEVEN, Card.Rank.EIGHT, Card.Rank.NINE,
                Card.Rank.TEN, Card.Rank.JACK, Card.Rank.QUEEN, Card.Rank.KING,
                Card.Rank.ACE
        };

        for (Card.Suit suit : suits) {
            for (Card.Rank rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Randomly shuffles the cards in this deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Deals (removes and returns) the top card from the deck.
     *
     * @return the dealt Card, or null if the deck is empty
     */
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // or throw an exception if desired
    }

    /**
     * Deals a hand of cards to the specified player.
     * Each dealt card is added to both the returned list and the player's hand.
     *
     * @param numCards the number of cards to deal
     * @param player   the player who will receive the dealt cards
     * @return a list of Cards dealt to the player
     */
    public ArrayList<Card> dealHand(int numCards, Player player) {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            Card dealt = dealCard();
            if (dealt != null) {
                hand.add(dealt);
                player.getHand().add(dealt);
            }
        }
        return hand;
    }
}
