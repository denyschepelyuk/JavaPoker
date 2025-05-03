package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import core.cards.Card;

class CardUtilsTest {

    /** Helper to turn varargs into an ArrayList&lt;Card&gt;. */
    private ArrayList<Card> list(Card... cards) {
        return new ArrayList<>(Arrays.asList(cards));
    }

    @Test
    void testEvaluateHighCard() {
        // Player: A♠, 7♦; Community: 2♣, 5♥, 9♠, J♦, 3♣
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.SPADES, Card.Rank.ACE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN)),
                list(new Card(Card.Suit.CLUBS, Card.Rank.TWO),
                        new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                        new Card(Card.Suit.SPADES, Card.Rank.NINE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(10, strength, "High Card should be ranked 10 (weakest)");
    }

    @Test
    void testEvaluatePair() {
        // Player: K♣, K♦; Community: 2♣, 5♥, 9♠, J♦, 3♣
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.CLUBS, Card.Rank.KING),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.KING)),
                list(new Card(Card.Suit.CLUBS, Card.Rank.TWO),
                        new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                        new Card(Card.Suit.SPADES, Card.Rank.NINE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(9, strength, "One Pair should be ranked 9");
    }

    @Test
    void testEvaluateTwoPair() {
        // Player: K♣, K♦; Community: 5♣, 5♥, 9♠, J♦, 3♣
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.CLUBS, Card.Rank.KING),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.KING)),
                list(new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
                        new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                        new Card(Card.Suit.SPADES, Card.Rank.NINE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(8, strength, "Two Pair should be ranked 8");
    }

    @Test
    void testEvaluateThreeOfAKind() {
        // Player: 7♣, 7♦; Community: 7♠, 2♦, 9♥, J♦, 3♣
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN)),
                list(new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.TWO),
                        new Card(Card.Suit.HEARTS, Card.Rank.NINE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(7, strength, "Three of a Kind should be ranked 7");
    }

    @Test
    void testEvaluateStraight() {
        // Player: 6♣, 8♦; Community: 7♠, 9♥, 10♦, 2♣, 3♣
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.CLUBS, Card.Rank.SIX),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT)),
                list(new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                        new Card(Card.Suit.HEARTS, Card.Rank.NINE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.TEN),
                        new Card(Card.Suit.CLUBS, Card.Rank.TWO),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(6, strength, "Straight should be ranked 6");
    }

    @Test
    void testEvaluateFlush() {
        // Player: 2♥, 9♥; Community: 5♥, J♥, K♥, 3♣, 4♦
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.HEARTS, Card.Rank.TWO),
                        new Card(Card.Suit.HEARTS, Card.Rank.NINE)),
                list(new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                        new Card(Card.Suit.HEARTS, Card.Rank.JACK),
                        new Card(Card.Suit.HEARTS, Card.Rank.KING),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR))
        );
        assertEquals(5, strength, "Flush should be ranked 5");
    }

    @Test
    void testEvaluateFullHouse() {
        // Player: 8♣, 8♦; Community: 8♠, 3♣, 3♦, J♥, Q♠
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT)),
                list(new Card(Card.Suit.SPADES, Card.Rank.EIGHT),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
                        new Card(Card.Suit.HEARTS, Card.Rank.JACK),
                        new Card(Card.Suit.SPADES, Card.Rank.QUEEN))
        );
        assertEquals(4, strength, "Full House should be ranked 4");
    }

    @Test
    void testEvaluateFourOfAKind() {
        // Player: 5♣, 5♦; Community: 5♠, 5♥, 2♥, J♦, Q♠
        int strength = CardUtils.evaluateHand(
                list(new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)),
                list(new Card(Card.Suit.SPADES, Card.Rank.FIVE),
                        new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                        new Card(Card.Suit.HEARTS, Card.Rank.TWO),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
                        new Card(Card.Suit.SPADES, Card.Rank.QUEEN))
        );
        assertEquals(3, strength, "Four of a Kind should be ranked 3");
    }

    @Test
    void testEvaluateStraightFlushAndRoyalFlush() {
        // Royal Flush: 10♠, A♠ with J♠, Q♠, K♠
        int royal = CardUtils.evaluateHand(
                list(new Card(Card.Suit.SPADES, Card.Rank.TEN),
                        new Card(Card.Suit.SPADES, Card.Rank.ACE)),
                list(new Card(Card.Suit.SPADES, Card.Rank.JACK),
                        new Card(Card.Suit.SPADES, Card.Rank.QUEEN),
                        new Card(Card.Suit.SPADES, Card.Rank.KING),
                        new Card(Card.Suit.HEARTS, Card.Rank.TWO),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(1, royal, "Royal Flush should be ranked 1");

        // Straight Flush (5♦, 6♦ with 7♦, 8♦, 9♦)
        int sf = CardUtils.evaluateHand(
                list(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.SIX)),
                list(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT),
                        new Card(Card.Suit.DIAMONDS, Card.Rank.NINE),
                        new Card(Card.Suit.HEARTS, Card.Rank.TWO),
                        new Card(Card.Suit.CLUBS, Card.Rank.THREE))
        );
        assertEquals(2, sf, "Straight Flush should be ranked 2");
    }

    @Test
    void testGetHighestRankOnTieBreaker() {
        // Both have two pair, but one has a higher second pair
        ArrayList<Card> hand1 = list(
                new Card(Card.Suit.CLUBS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.KING)
        );
        ArrayList<Card> hand2 = list(
                new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
                new Card(Card.Suit.SPADES, Card.Rank.QUEEN)
        );
        List<Card> community = list(
                new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.SPADES, Card.Rank.TEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.TWO),
                new Card(Card.Suit.CLUBS, Card.Rank.THREE)
        );

        Card.Rank high1 = CardUtils.getHighestRank(hand1, new ArrayList<>(community));
        Card.Rank high2 = CardUtils.getHighestRank(hand2, new ArrayList<>(community));

        assertTrue(high1.compareTo(high2) > 0,
                "Hand1's higher pair (King) should beat Hand2's pair (Queen)");
    }
}
