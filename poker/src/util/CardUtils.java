package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import core.Card;
import core.Game;
import core.Player;

public class CardUtils {
    public static Player compareHands(Game game) {
        Player winner = null;
        int bestHandValue = Integer.MAX_VALUE;

        for (Player player : game.getPlayers()) {
            int playerHandValue = evaluateHand(player.getHand(), game.getTable().getCommunityCards());
            if (winner == null || playerHandValue < bestHandValue) {
                winner = player;
                bestHandValue = playerHandValue;
            }
        }
        return winner;
    }

    public static int evaluateHand(ArrayList<Card> playerHand, ArrayList<Card> communityCards) {
        ArrayList<Card> hand = new ArrayList<>(playerHand);
        hand.addAll(communityCards);
        sortCardsByDecreasingRank(hand);

        if (isRoyalFlush(hand)) return 1;
        if (isStraightFlush(hand) != null) return 2;
        if (isFourOfAKind(hand) != null) return 3;
        if (isFullHouse(hand) != null) return 4;
        if (isFlush(hand) != null) return 5;
        if (isStraight(hand) != null) return 6;
        if (isThreeOfAKind(hand) != null) return 7;
        if (isTwoPair(hand) != null) return 8;
        if (isPair(hand) != null) return 9;
        return 10;
    }

    private static boolean isRoyalFlush(ArrayList<Card> hand) {
        return false;
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
    private static Card.Rank isStraight(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isThreeOfAKind(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isTwoPair(ArrayList<Card> hand) {
        return null;
    }
    private static Card.Rank isPair(ArrayList<Card> hand) {
        return null;
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
