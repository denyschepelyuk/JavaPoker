package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import core.Card;
import core.Game;

public class CardUtils {
    public static ArrayList<Card> compareHands(Game game) {
        // Compare hands of all players
        // Determine winner
        ArrayList<Card> winner = new ArrayList<>();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            ArrayList<Card> playerHand = game.getPlayers().get(i).getHand();
            ArrayList<Card> communityCards = game.getTable().getCommunityCards();
            int playerHandValue = evaluateHand(playerHand, communityCards);

            if (winner.isEmpty()) {
                winner = playerHand;
            } else {
                int winnerHandValue = evaluateHand(winner, communityCards);
                if (playerHandValue < winnerHandValue) {
                    winner = playerHand;
                }
            }
        }

        return winner;
    }

    private static Card.Rank isRoyalFlush(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isStraightFlush(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isFourOfAKind(ArrayList<Card> hand) {
        // checks whether there are 4 cards of the same rank
        for (int i = 0; i < hand.size() - 3; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() &&
                hand.get(i).getRank() == hand.get(i + 2).getRank() &&
                hand.get(i).getRank() == hand.get(i + 3).getRank()) {
                return hand.get(i).getRank();
            }
        }
        return null;
    }
    private static Card.Rank isFullHouse(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isFlush(ArrayList<Card> hand) {
        // checks whether there are 5 cards of the same suit
        int hearts = 0;
        int diamonds = 0;
        int clubs = 0;
        int spades = 0;

        for (Card card : hand) {
            switch (card.getSuit()) {
                case HEARTS:
                    hearts++;
                    break;
                case DIAMONDS:
                    diamonds++;
                    break;
                case CLUBS:
                    clubs++;
                    break;
                case SPADES:
                    spades++;
                    break;
            }
        }

        Card card = null;
        if (hearts >= 5) {
            card = new Card(Card.Suit.HEARTS, Card.Rank.TWO);
        } else if (diamonds >= 5) {
            card = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
        } else if (clubs >= 5) {
            card = new Card(Card.Suit.CLUBS, Card.Rank.TWO);
        } else if (spades >= 5) {
            card = new Card(Card.Suit.SPADES, Card.Rank.TWO);
        }

        if (card == null) return null;

        for (Card c : hand){
            if (c.getSuit() == card.getSuit()) {
                if (c.isGreaterThan(card)) {
                    card = c;
                }
            }
        }
        return card.getRank();
    }
    private static Card.Rank isStraight(ArrayList<Card> hand) { return null; }
    private static Card.Rank isThreeOfAKind(ArrayList<Card> hand) {
        // checks whether there are 3 cards of the same rank
        for (int i = 0; i < hand.size() - 2; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() &&
                hand.get(i).getRank() == hand.get(i + 2).getRank()) {
                return hand.get(i).getRank();
            }
        }
        return null;
    }
    private static Card.Rank isTwoPair(ArrayList<Card> hand) {
        // checks whether there are two pairs of cards of the same rank
        return null;
    }
    private static Card.Rank isPair(ArrayList<Card> hand) {
        // checks whether there is a pair of cards of the same rank
        for (int i = 0; i < hand.size() - 1; i++){
            if (hand.get(i).getRank() == hand.get(i + 1).getRank())
                return hand.get(i).getRank();
        }
        return null;
    }

    public static int evaluateHand(ArrayList<Card> playerHand, ArrayList<Card> communityCards) {
        ArrayList<Card> hand = new ArrayList<>();
        hand.addAll(hand);
        hand.addAll(communityCards);
        sortCardsByDecreasingRank(hand);

        if (isRoyalFlush(hand) != null) {
            return 1;
        }
        if (isStraightFlush(hand) != null) {
            return 2;
        }
        if (isFourOfAKind(hand) != null) {
            return 3;
        }
        if (isFullHouse(hand) != null) {
            return 4;
        }
        if (isFlush(hand) != null) {
            return 5;
        }
        if (isStraight(hand) != null) {
            return 6;
        }
        if (isThreeOfAKind(hand) != null) {
            return 7;
        }
        if (isTwoPair(hand) != null) {
            return 8;
        }
        if (isPair(hand) != null) {
            return 9;
        }

        else {return 10;}
    }

    public static void sortCardsByDecreasingRank(ArrayList<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return card2.isGreaterThan(card1) ? 1 : -1;
            }
        });
    }
}
