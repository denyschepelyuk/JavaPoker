package main.java.core.players;

import main.java.core.cards.Card;
import main.java.core.game.Game;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int chips;
    private boolean folded;
    protected Game game;
    private int bet;

    public Player(String name, int initialChips, Game game) {
        this.name = name;
        this.chips = initialChips;
        this.hand = new ArrayList<>();
        this.folded = false;
        this.game = game;
        this.bet = 0;
    }

    /*
     * controls the player's betting behavior
     */
    public void fold() {
        folded = true;
        System.out.println(name + " folded.");
    }

    public void bet(int amount) {
        amount = Math.min(amount, chips);
        chips -= amount;
        bet += amount;
        game.getTable().addBet(amount);
        System.out.println(name + " bet " + amount + " chips.");
    }

    public void call() {
        int highestBet = game.getHighestBet();
        int amount = Math.min(highestBet - bet, chips);
        bet(amount);
    }

    public void check() {
        System.out.println(name + " checked.");
    }

    /*
     * get players properties
     */
    public ArrayList<Card> getHand() { return hand; }
    public String getName() { return name; }
    public int getChips() { return chips; }
    public boolean isFolded() { return folded; }
    public int getBetAmount() { return bet; }

    /*
     * set players properties
     */
    public void setChips(int chips) { this.chips = chips; }
    public void reset() {
        hand.clear();
        folded = false;
        bet = 0;
    }
}