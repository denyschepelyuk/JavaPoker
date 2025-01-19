package core;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int chips;
    private boolean folded;

    public Player(String name, int initialChips) {
        this.name = name;
        this.chips = initialChips;
        this.hand = new ArrayList<>();
        this.folded = false;
    }

    public void makeBet(int amount) {
        if (amount <= chips && !folded) {
            chips -= amount;
            System.out.println(name + " bets " + amount + " chips.");
        } else {
            System.out.println(name + " cannot bet that amount.");
        }
    }

    public void fold() {
        folded = true;
        System.out.println(name + " folded.");
    }

    public void check() {
        if (!folded) {
            System.out.println(name + " checks.");
        }
    }

    public void call(int amount) {
        if (amount <= chips && !folded) {
            chips -= amount;
            System.out.println(name + " calls with " + amount + " chips.");
        } else {
            System.out.println(name + " cannot call " + amount + " chips.");
        }
    }

    public int bet() {
        return 10;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public boolean isFolded() {
        return folded;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

}