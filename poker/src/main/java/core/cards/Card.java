package main.java.core.cards;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public enum Suit {
    HEARTS("Hearts", "♥"),
    DIAMONDS("Diamonds", "♦"),
    CLUBS("Clubs", "♣"),
    SPADES("Spades", "♠");

    private final String name;
    private final String symbol;

    Suit(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    private static final Map<String, Suit> lookup = new HashMap<>();

    static {
        for (Suit s : Suit.values()) {
            lookup.put(s.getName(), s);
        }
    }

    public static Suit get(String name) {
        return lookup.get(name);
    }
}
    public enum Rank {
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        private final String name;

        Rank(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private static final Map<String, Rank> lookup = new HashMap<>();

        static {
            for (Rank r : Rank.values()) {
                lookup.put(r.getName(), r);
            }
        }

        public static Rank get(String name) {
            return lookup.get(name);
        }
    }

    private Suit suit;     // e.g. "Hearts", "Diamonds", "Clubs", "Spades"
    private Rank rank;     // e.g. "2", "3", "4", "5", ..., "K", "A"

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns a readable description of the card.
     * Example: "♠A"
     */
    public String getDescription() {
        return suit.getSymbol() + rank.getName();
    }

    /**
     * Compares two cards based on rank.
     */
    public boolean isGreaterThan(Card other) {
        return getRankValue(this.rank) > getRankValue(other.rank);
    }

    public int getRankValue(Rank rank) {
        switch (rank) {
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 11;
            case QUEEN: return 12;
            case KING: return 13;
            case ACE: return 14;
            default: throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }
}
