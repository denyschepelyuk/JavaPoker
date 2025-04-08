package core.game;

import core.cards.Card;
import core.cards.Deck;

import java.util.ArrayList;

public class Table {
    private ArrayList<Card> communityCards;
    private int pot;

    public Table() {
        communityCards = new ArrayList<>();
        pot = 0;
    }

    public void deal(Deck deck){
        if (communityCards.size() == 5) { return; }

        addCommunityCard(deck.dealCard());

        if (communityCards.size() == 1) {
            for (int i = 0; i < 2; i++) {
                addCommunityCard(deck.dealCard());
            }
        }
    }

    public void addCommunityCard(Card card) {
        communityCards.add(card);
    }

    public void reset() {
        communityCards.clear();
        pot = 0;
    }

    public int getPotAmount() {
        return pot;
    }

    public void addBet(int amount) {
        pot += amount;
    }

    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }
}
