package util;

import java.util.*;

import core.cards.Card;
import core.game.Game;
import core.players.Player;

/**
 * Utility class providing methods to evaluate poker hands,
 * compare players’ hands, and display hand combinations.
 */
public class CardUtils {
    /**
     * Compares all active (non-folded) players’ hands and returns the winning player.
     * Winners are determined first by hand strength, then by highest rank on ties.
     *
     * @param game the current Game instance containing players and community cards
     * @return the Player with the best hand, or null if no players remain
     */
    public static Player compareHands(Game game) {
        Player winner = null;
        int bestHandValue = 11;

        for (Player player : game.getPlayers()) {
            if (player.isFolded()) continue;

            int handValue = evaluateHand(player.getHand(), game.getTable().getCommunityCards());
            if (handValue < bestHandValue) {
                bestHandValue = handValue;
                winner = player;
            }
            else if (handValue == bestHandValue) {
                ArrayList<Card> playerHand = player.getHand();
                ArrayList<Card> winnerHand = winner.getHand();

                Card.Rank playerHighestRank = getHighestRank(playerHand, game.getTable().getCommunityCards());
                Card.Rank winnerHighestRank = getHighestRank(winnerHand, game.getTable().getCommunityCards());

                if (playerHighestRank.compareTo(winnerHighestRank) > 0) {
                    winner = player;
                }
            }
        }
        return winner;
    }

    /**
     * Evaluates the combined strength of a player's hand and the community cards.
     * Returns an integer where 1 represents the strongest hand (Royal Flush)
     * and 10 the weakest (High Card).
     *
     * @param playerHand     the list of the player's private cards
     * @param communityCards the list of community cards on the table
     * @return numeric strength ranking (1 strongest, 10 weakest)
     */
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

    /**
     * Finds the highest card rank present in the combined player and community hand.
     * This is used to break ties when multiple hands have equal strength.
     *
     * @param playerHand     the list of the player's private cards
     * @param communityCards the list of community cards on the table
     * @return the highest Card.Rank found
     */
    public static Card.Rank getHighestRank(ArrayList<Card> playerHand, ArrayList<Card> communityCards) {
        ArrayList<Card> hand = new ArrayList<>(playerHand);
        hand.addAll(communityCards);
        sortCardsByDecreasingRank(hand);

        if (isRoyalFlush(hand)) return Card.Rank.ACE;
        if (isStraightFlush(hand) != null) return isStraightFlush(hand);
        if (isFourOfAKind(hand) != null) return isFourOfAKind(hand);
        if (isFullHouse(hand) != null) return isFullHouse(hand);
        if (isFlush(hand) != null) return isFlush(hand);
        if (isStraight(hand) != null) return isStraight(hand);
        if (isThreeOfAKind(hand) != null) return isThreeOfAKind(hand);
        if (isTwoPair(hand) != null) return isTwoPair(hand);
        if (isPair(hand) != null) return isPair(hand);
        return hand.get(0).getRank();
    }

    /**
     * Prints the type of poker hand combination (e.g., "Full House", "Straight").
     *
     * @param playerHand     the list of the player's private cards
     * @param communityCards the list of community cards on the table
     */
    public static void printCombination(ArrayList<Card> playerHand, ArrayList<Card> communityCards) {
        ArrayList<Card> hand = new ArrayList<>(playerHand);
        hand.addAll(communityCards);
        sortCardsByDecreasingRank(hand);

        if (isRoyalFlush(hand)) System.out.print("Royal Flush");
        else if (isStraightFlush(hand) != null) System.out.print("Straight Flush");
        else if (isFourOfAKind(hand) != null) System.out.print("Four of a Kind");
        else if (isFullHouse(hand) != null) System.out.print("Full House");
        else if (isFlush(hand) != null) System.out.print("Flush");
        else if (isStraight(hand) != null) System.out.print("Straight");
        else if (isThreeOfAKind(hand) != null) System.out.print("Three of a Kind");
        else if (isTwoPair(hand) != null) System.out.print("Two Pair");
        else if (isPair(hand) != null) System.out.print("Pair");
        else System.out.print("High Card");
    }

    private static boolean isRoyalFlush(ArrayList<Card> hand) {
        return isStraightFlush(hand) == Card.Rank.TEN;
    }
    private static Card.Rank isStraightFlush(ArrayList<Card> hand) {
        // Group cards by suit
        Map<Card.Suit, List<Card.Rank>> suitMap = new HashMap<>();

        for (Card card : hand) {
            suitMap.putIfAbsent(card.getSuit(), new ArrayList<>());
            suitMap.get(card.getSuit()).add(card.getRank());
        }

        // Check for a straight in each suit
        for (List<Card.Rank> ranks : suitMap.values()) {
            if (ranks.size() >= 5) {
                Collections.sort(ranks, Comparator.comparingInt(Enum::ordinal));

                int consecutive = 1;
                for (int i = 1; i < ranks.size(); i++) {
                    if (ranks.get(i - 1).ordinal() + 1 == ranks.get(i).ordinal()) {
                        consecutive++;
                        if (consecutive >= 5) {
                            return ranks.get(i - 4);  // Return the lowest rank in the straight flush
                        }
                    } else {
                        consecutive = 1;  // Reset if sequence breaks
                    }
                }
            }
        }

        return null;
    }
    private static Card.Rank isFourOfAKind(ArrayList<Card> hand) {
        return findSameRank(hand, 4);
    }
    private static Card.Rank isFullHouse(ArrayList<Card> hand) {
        Map<Card.Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        Card.Rank threeOfKind = null;
        Card.Rank pair = null;

        for (Map.Entry<Card.Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() >= 3) {
                if (threeOfKind == null || entry.getKey().compareTo(threeOfKind) > 0) {
                    threeOfKind = entry.getKey();
                }
            }
        }

        for (Map.Entry<Card.Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() >= 2 && entry.getKey() != threeOfKind) {
                if (pair == null || entry.getKey().compareTo(pair) > 0) {
                    pair = entry.getKey();
                }
            }
        }

        return (threeOfKind != null && pair != null) ? threeOfKind : null;
    }
    private static Card.Rank isFlush(ArrayList<Card> hand) {
        Map<Card.Suit, List<Card>> suitMap = new HashMap<>();
        for (Card card : hand) {
            suitMap.computeIfAbsent(card.getSuit(), k -> new ArrayList<>()).add(card);
        }
        for (List<Card> suitedCards : suitMap.values()) {
            if (suitedCards.size() >= 5) {
                return suitedCards.get(0).getRank();
            }
        }
        return null;
    }
    private static Card.Rank isStraight(ArrayList<Card> hand) {
        Set<Card.Rank> uniqueRanks = new TreeSet<>(Comparator.comparingInt(Enum::ordinal));

        for (Card card : hand) {
            uniqueRanks.add(card.getRank());  // Remove duplicates automatically
        }

        List<Card.Rank> sortedRanks = new ArrayList<>(uniqueRanks);
        int consecutive = 1;

        for (int i = 1; i < sortedRanks.size(); i++) {
            if (sortedRanks.get(i - 1).ordinal() + 1 == sortedRanks.get(i).ordinal()) {
                consecutive++;
                if (consecutive >= 5) {
                    return sortedRanks.get(i - 4);  // Return the lowest card of the straight
                }
            } else {
                consecutive = 1;  // Reset counter if sequence breaks
            }
        }

        return null;
    }
    private static Card.Rank isThreeOfAKind(ArrayList<Card> hand) {
        return findSameRank(hand, 3);
    }
    private static Card.Rank isTwoPair(ArrayList<Card> hand) {
        Card.Rank firstPair = null;
        Card.Rank secondPair = null;

        Map<Card.Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        for (Map.Entry<Card.Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() >= 2) {
                if (firstPair == null) {
                    firstPair = entry.getKey();
                } else {
                    secondPair = entry.getKey();
                }
            }
        }

        if (firstPair != null && secondPair != null) {
            return firstPair.compareTo(secondPair) > 0 ? firstPair : secondPair;  // Return the higher pair
        }

        return null;
    }
    private static Card.Rank isPair(ArrayList<Card> hand) {
        return findSameRank(hand, 2);
    }
    private static Card.Rank findSameRank(ArrayList<Card> hand, int count) {
        Map<Card.Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }
        for (Map.Entry<Card.Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == count) {
                return entry.getKey();
            }
        }
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
