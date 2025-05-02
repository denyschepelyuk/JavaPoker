package ui;

import core.game.Game;
import core.players.Player;
import core.game.Table;
import core.cards.Card;
import util.CardUtils;

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
    }

    public void displayWinner(Game game, Player winner) {
        System.out.print(winner.getName() + " won " + game.getTable().getPotAmount() + " chips! with: ");
        CardUtils.printCombination(winner.getHand(), game.getTable().getCommunityCards());
        System.out.println();

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

        if (game._gameEnded()) return;
        System.out.println(createBlock("Next Round Starts", "Please wait 5 seconds..."));
        try {
            // pause for 3000 milliseconds (3 seconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // if another thread interrupts this one during sleep
            Thread.currentThread().interrupt(); // restore interrupt flag
            System.out.println("Sleep was interrupted");
        }
    }
}