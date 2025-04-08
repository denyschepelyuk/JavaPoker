package core.cards;

import core.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        Card.Suit[] suits = {Card.Suit.HEARTS, Card.Suit.DIAMONDS, Card.Suit.CLUBS, Card.Suit.SPADES};
        Card.Rank[] ranks = {Card.Rank.TWO, Card.Rank.THREE, Card.Rank.FOUR, Card.Rank.FIVE, Card.Rank.SIX, Card.Rank.SEVEN, Card.Rank.EIGHT, Card.Rank.NINE, Card.Rank.TEN, Card.Rank.JACK, Card.Rank.QUEEN, Card.Rank.KING, Card.Rank.ACE};

        for (Card.Suit suit : suits) {
            for (Card.Rank rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // or throw an exception if desired
    }

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
