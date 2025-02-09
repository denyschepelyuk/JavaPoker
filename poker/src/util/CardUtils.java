package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import core.Card;
import core.Game;

public class CardUtils {

    public static void sortCardsByDecreasingRank(ArrayList<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return card2.isGreaterThan(card1) ? 1 : -1;
            }
        });
    }
}
