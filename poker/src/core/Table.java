package core;

import java.util.ArrayList;

public class Table {
    private ArrayList<Card> communityCards;
    private int pot;

    public Table() {
        communityCards = new ArrayList<>();
        pot = 0;
    }

    public void addCommunityCard(Card card) {
        communityCards.add(card);
    }

    public void resetTable() {
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
