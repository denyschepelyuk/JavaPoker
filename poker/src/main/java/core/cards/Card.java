package core.cards;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a standard playing card with a specific suit and rank.
 * Provides methods to describe and compare cards.
 */
public class Card {
    /**
     * Enumeration of the four suits in a standard deck of cards,
     * each with a human-readable name and a symbol.
     */
    public enum Suit {
        /** The Hearts suit (♥). */
        HEARTS("Hearts", "♥"),
        /** The Diamonds suit (♦). */
        DIAMONDS("Diamonds", "♦"),
        /** The Clubs suit (♣). */
        CLUBS("Clubs", "♣"),
        /** The Spades suit (♠). */
        SPADES("Spades", "♠");

        private final String name;
        private final String symbol;

        /**
         * Constructs a suit with its display name and symbol.
         *
         * @param name   human-readable name of the suit
         * @param symbol symbol associated with this suit
         */
        Suit(String name, String symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        /**
         * Returns the human-readable name of the suit.
         *
         * @return name of the suit
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the symbol associated with the suit.
         *
         * @return symbol of the suit
         */
        public String getSymbol() {
            return symbol;
        }

        private static final Map<String, Suit> lookup = new HashMap<>();

        static {
            for (Suit s : Suit.values()) {
                lookup.put(s.getName(), s);
            }
        }

        /**
         * Retrieves a Suit by its human-readable name.
         *
         * @param name name of the suit (e.g., "Hearts")
         * @return corresponding Suit, or null if no match
         */
        public static Suit get(String name) {
            return lookup.get(name);
        }
    }

    /**
     * Enumeration of the thirteen ranks in a standard deck of cards,
     * each with its display value.
     */
    public enum Rank {
        /** The Two rank. */
        TWO("2"),
        /** The Three rank. */
        THREE("3"),
        /** The Four rank. */
        FOUR("4"),
        /** The Five rank. */
        FIVE("5"),
        /** The Six rank. */
        SIX("6"),
        /** The Seven rank. */
        SEVEN("7"),
        /** The Eight rank. */
        EIGHT("8"),
        /** The Nine rank. */
        NINE("9"),
        /** The Ten rank. */
        TEN("10"),
        /** The Jack rank. */
        JACK("J"),
        /** The Queen rank. */
        QUEEN("Q"),
        /** The King rank. */
        KING("K"),
        /** The Ace rank. */
        ACE("A");

        private final String name;

        /**
         * Constructs a rank with its display value.
         *
         * @param name display value of the rank (e.g., "A" for Ace)
         */
        Rank(String name) {
            this.name = name;
        }

        /**
         * Returns the display name of the rank.
         *
         * @return name of the rank
         */
        public String getName() {
            return name;
        }

        private static final Map<String, Rank> lookup = new HashMap<>();

        static {
            for (Rank r : Rank.values()) {
                lookup.put(r.getName(), r);
            }
        }

        /**
         * Retrieves a Rank by its display name.
         *
         * @param name display name of the rank (e.g., "K" for King)
         * @return corresponding Rank, or null if no match
         */
        public static Rank get(String name) {
            return lookup.get(name);
        }
    }

    private Suit suit;     // e.g. "Hearts", "Diamonds", "Clubs", "Spades"
    private Rank rank;     // e.g. "2", "3", "4", "5", ..., "K", "A"

    /**
     * Constructs a card with the specified suit and rank.
     *
     * @param suit suit of the card
     * @param rank rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns a readable description of the card combining its symbol and rank.
     * For example, "♠A" for Ace of Spades.
     *
     * @return descriptive string of the card
     */
    public String getDescription() {
        return suit.getSymbol() + rank.getName();
    }

    /**
     * Compares this card to another based on their rank values.
     *
     * @param other the card to compare against
     * @return true if this card's rank is greater than the other card's rank
     */
    public boolean isGreaterThan(Card other) {
        return getRankValue(this.rank) > getRankValue(other.rank);
    }

    /**
     * Converts a Rank to its corresponding numeric value for comparison.
     *
     * @param rank the rank to evaluate
     * @return integer value of the rank (2 through 14)
     * @throws IllegalArgumentException if the rank is unknown
     */
    public int getRankValue(Rank rank) {
        switch (rank) {
            case TWO:   return 2;
            case THREE: return 3;
            case FOUR:  return 4;
            case FIVE:  return 5;
            case SIX:   return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE:  return 9;
            case TEN:   return 10;
            case JACK:  return 11;
            case QUEEN: return 12;
            case KING:  return 13;
            case ACE:   return 14;
            default:    throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }

    /**
     * Returns the rank of this card.
     *
     * @return rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the suit of this card.
     *
     * @return suit of the card
     */
    public Suit getSuit() {
        return suit;
    }
}
