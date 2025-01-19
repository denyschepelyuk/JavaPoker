package ui;

import core.Game;
import core.Player;
import core.Table;
import core.Card;

public class ConsoleUI {
    public void renderGame(Game game) {
        Table table = game.getTable();

        System.out.println("\n--- Table State ---");
        System.out.println("Pot: " + table.getPotAmount());
        System.out.println("Community Cards:");

        for (Card card : table.getCommunityCards()) {
            System.out.println("  " + card.getDescription());
        }

        System.out.println("\n--- Players ---");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + " [Chips: " + player.getChips() + "]"
                    + (player.isFolded() ? " (Folded)" : ""));
            if (!player.isFolded()) {
                for (Card c : player.getHand()) {
                    System.out.println("  " + c.getDescription());
                }
            }
        }
        System.out.println();
    }

    public void displayWinner(Player player) {
        System.out.println("The winner is: " + player.getName() + " with " + player.getChips() + " chips!");
    }
}
