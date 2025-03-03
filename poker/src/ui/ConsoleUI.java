package ui;

import core.Game;
import core.Player;
import core.Table;
import core.Card;

public class ConsoleUI {
    private static final int BLOCK_WIDTH = 45;

    private String createBlock(String title, String content) {
        StringBuilder block = new StringBuilder();
        block.append(" +").append("-".repeat(BLOCK_WIDTH - 2)).append("+\n");
        block.append(" |").append(centerText(title, BLOCK_WIDTH - 2)).append("|\n");
        block.append(" |").append(centerText(content, BLOCK_WIDTH - 2)).append("|\n");
        block.append(" +").append("-".repeat(BLOCK_WIDTH - 2)).append("+");
        return block.toString();
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }

    public void renderGame(Game game) {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        //System.out.println("=============================");

        Table table = game.getTable();

        StringBuilder communityCards = new StringBuilder();
        for (Card card : table.getCommunityCards()) {
            communityCards.append(card.getDescription()).append(" ");
        }

        System.out.println(createBlock("Community Cards", communityCards.toString().trim()));
        System.out.println(createBlock("Pot", String.valueOf(table.getPotAmount())));

        for (Player player : game.getPlayers()) {
            StringBuilder playerHand = new StringBuilder();
            if (player.isFolded()) {
                playerHand.append("Folded");
            } else if (player.getName().equals("You")) {
                for (Card c : player.getHand()) {
                    playerHand.append(c.getDescription()).append(" ");
                }
            } else {
                playerHand.append("&$#@*");
            }
            System.out.println(createBlock(player.getName() + " [Chips: " + player.getChips() + "]", playerHand.toString().trim()));
        }

        //System.out.println("=============================");
    }

    public void displayWinner(Game game, Player winner) {
        System.out.println("The winner is: " + winner.getName() + " with " + winner.getChips() + " chips!");

        Table table = game.getTable();

        StringBuilder communityCards = new StringBuilder();
        for (Card card : table.getCommunityCards()) {
            communityCards.append(card.getDescription()).append(" ");
        }

        System.out.println(createBlock("Community Cards", communityCards.toString().trim()));
        System.out.println(createBlock("Pot", String.valueOf(table.getPotAmount())));

        for (Player player : game.getPlayers()) {
            StringBuilder playerHand = new StringBuilder();
            if (player.isFolded()) {
                playerHand.append("Folded");
            } else {
                for (Card c : player.getHand()) {
                    playerHand.append(c.getDescription()).append(" ");
                }
            }
            System.out.println(createBlock(player.getName() + " [Chips: " + player.getChips() + "]", playerHand.toString().trim()));
        }
    }
}