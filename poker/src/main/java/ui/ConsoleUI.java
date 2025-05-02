package ui;

import core.game.Game;
import core.players.Player;
import core.game.Table;
import core.cards.Card;
import util.CardUtils;

/**
 * Handles the visual display of the Poker game state on the console.
 * Renders community cards, pot amount, and each player's status in a formatted block layout.
 */
public class ConsoleUI {
    private static final int BLOCK_WIDTH = 45;

    /**
     * Constructs a text block with a title and content, framed by a border.
     *
     * @param title   the heading of the block
     * @param content the body text displayed beneath the title
     * @return formatted string representing the block
     */
    private String createBlock(String title, String content) {
        StringBuilder block = new StringBuilder();
        block.append(" +").append("-".repeat(BLOCK_WIDTH - 2)).append("+\n");
        block.append(" |").append(centerText(title, BLOCK_WIDTH - 2)).append("|\n");
        block.append(" |").append(centerText(content, BLOCK_WIDTH - 2)).append("|\n");
        block.append(" +").append("-".repeat(BLOCK_WIDTH - 2)).append("+");
        return block.toString();
    }

    /**
     * Centers the given text within a field of specified width by adding padding.
     *
     * @param text  the text to be centered
     * @param width the total field width
     * @return padded string with text centered
     */
    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }

    /**
     * Displays the current state of the game, including community cards, pot amount,
     * and each player's name, chip count, and hand or fold status.
     *
     * @param game the Game instance providing the current game state
     */
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

    /**
     * Displays the winner of the game, the winning hand combination, and the final table state.
     * After displaying, pauses briefly before the next round begins.
     *
     * @param game   the Game instance providing end-of-game state
     * @param winner the Player who won the pot
     */
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
            // Pause for 5000 milliseconds (5 seconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt flag and notify
            Thread.currentThread().interrupt();
            System.out.println("Sleep was interrupted");
        }
    }
}
