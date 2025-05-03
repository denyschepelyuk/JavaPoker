// poker/src/test/java/core/cards/CardTest.java
package core.cards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void descriptionCombinesSuitSymbolAndRank() {
        Card c = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        assertEquals("♠A", c.getDescription());
    }

    @Test
    void greaterThanRespectsRankOrdering() {
        Card twoOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.TWO);
        Card kingOfClubs   = new Card(Card.Suit.CLUBS,  Card.Rank.KING);
        assertTrue(kingOfClubs.isGreaterThan(twoOfHearts));
        assertFalse(twoOfHearts.isGreaterThan(kingOfClubs));
    }

    @Test
    void suitNameAndSymbolAreCorrect() {
        assertEquals("Hearts", Card.Suit.HEARTS.getName());
        assertEquals("♥",      Card.Suit.HEARTS.getSymbol());

        assertEquals("Spades", Card.Suit.SPADES.getName());
        assertEquals("♠",      Card.Suit.SPADES.getSymbol());
    }

    @Test
    void rankNameIsCorrect() {
        assertEquals("2",  Card.Rank.TWO.getName());
        assertEquals("10", Card.Rank.TEN.getName());
        assertEquals("J",  Card.Rank.JACK.getName());
        assertEquals("Q",  Card.Rank.QUEEN.getName());
        assertEquals("K",  Card.Rank.KING.getName());
        assertEquals("A",  Card.Rank.ACE.getName());
    }

    @Test
    void suitLookupByNameReturnsCorrectEnum() {
        assertEquals(Card.Suit.CLUBS,    Card.Suit.get("Clubs"));
        assertEquals(Card.Suit.DIAMONDS, Card.Suit.get("Diamonds"));
        assertEquals(Card.Suit.HEARTS,   Card.Suit.get("Hearts"));
        assertEquals(Card.Suit.SPADES,   Card.Suit.get("Spades"));
    }

    @Test
    void rankLookupByNameReturnsCorrectEnum() {
        assertEquals(Card.Rank.TWO,   Card.Rank.get("2"));
        assertEquals(Card.Rank.TEN,   Card.Rank.get("10"));
        assertEquals(Card.Rank.JACK,  Card.Rank.get("J"));
        assertEquals(Card.Rank.QUEEN, Card.Rank.get("Q"));
        assertEquals(Card.Rank.KING,  Card.Rank.get("K"));
        assertEquals(Card.Rank.ACE,   Card.Rank.get("A"));
    }

    @Test
    void getRankAndSuitAccessorsWork() {
        Card c = new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE);
        assertEquals(Card.Suit.DIAMONDS, c.getSuit());
        assertEquals(Card.Rank.FIVE,     c.getRank());
    }

    @Test
    void getRankValueMapsCorrectly() {
        Card dummy = new Card(Card.Suit.CLUBS, Card.Rank.TWO);
        assertEquals(2,  dummy.getRankValue(Card.Rank.TWO));
        assertEquals(10, dummy.getRankValue(Card.Rank.TEN));
        assertEquals(11, dummy.getRankValue(Card.Rank.JACK));
        assertEquals(12, dummy.getRankValue(Card.Rank.QUEEN));
        assertEquals(13, dummy.getRankValue(Card.Rank.KING));
        assertEquals(14, dummy.getRankValue(Card.Rank.ACE));
    }
}
