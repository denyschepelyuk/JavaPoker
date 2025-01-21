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
        return new ArrayList<>();
    }

    private static Card.Rank isRoyalFlush(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isStraightFlush(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isFourOfAKind(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isFullHouse(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isFlush(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isStraight(ArrayList<Card> hand) { return null; }
    private static Card.Rank isThreeOfAKind(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isTwoPair(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isPair(ArrayList<Card> hand) {
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
