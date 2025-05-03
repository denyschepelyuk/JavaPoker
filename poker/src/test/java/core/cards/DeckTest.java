// poker/src/test/java/core/cards/DeckTest.java
package core.cards;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.game.Game;
import core.players.Player;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void initialSizeIsFiftyTwo() {
        int count = 0;
        while (deck.dealCard() != null) {
            count++;
        }
        assertEquals(52, count, "Deck should contain exactly 52 cards initially");
    }

    @Test
    void dealHandAddsToPlayer() {
        Player p = new Player("Tester", 1000, new Game(0));
        List<Card> hand = deck.dealHand(2, p);
        assertEquals(2, hand.size(), "dealHand should return the requested number of cards");
        assertEquals(2, p.getHand().size(), "Player's hand should reflect dealt cards");
    }

    @Test
    void shuffleDoesNotChangeCardCountOrUniqueness() {
        // Capture all cards before shuffle
        Deck fresh = new Deck();
        Set<String> before = new HashSet<>();
        Card c;
        while ((c = fresh.dealCard()) != null) {
            before.add(c.getDescription());
        }
        // Shuffle and capture again
        deck = new Deck();
        deck.shuffle();
        Set<String> after = new HashSet<>();
        while ((c = deck.dealCard()) != null) {
            after.add(c.getDescription());
        }
        assertEquals(52, after.size(), "Shuffled deck should still have 52 unique cards");
        assertEquals(before, after, "Shuffled deck must contain the same cards as a fresh deck");
    }

    @Test
    void dealCardReturnsNullWhenEmpty() {
        // Deal all cards
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.dealCard(), "Card " + i + " should be available");
        }
        // Next deal should return null
        assertNull(deck.dealCard(), "dealCard should return null when the deck is empty");
    }

    @Test
    void dealHandWithMoreThanRemainingCards() {
        // Attempt to deal more cards than exist
        Player p = new Player("Tester", 1000, new Game(0));
        List<Card> hand = deck.dealHand(60, p);
        // Should only deal 52 cards total
        assertEquals(52, hand.size(), "dealHand should deal at most the remaining cards");
        assertEquals(52, p.getHand().size(), "Player's hand should contain all dealt cards");
        // Further dealHand calls should yield empty list
        List<Card> empty = deck.dealHand(5, p);
        assertTrue(empty.isEmpty(), "dealHand on an empty deck should return an empty list");
    }
}
