/**
 * The util package provides supporting functionality for the Poker game.
 * It contains utility classes that handle critical operations such as 
 * evaluating and comparing card hands, sorting cards, and managing game configuration.
 *
 * <p><b>{@link util.CardUtils}</b>:
 * <ul>
 *   <li><b>Description:</b> Offers a collection of static utility methods for evaluating poker hands,
 *       comparing players' card combinations, and formatting card information for display.
 *   </li>
 *   <li><b>Key Public Methods:</b>
 *     <ul>
 *       <li><code>compareHands(Game game)</code> - Compares the hands of all active players and returns the winning player.</li>
 *       <li><code>evaluateHand(ArrayList&lt;Card&gt; playerHand, ArrayList&lt;Card&gt; communityCards)</code> - Evaluates a player's hand (combined with community cards)
 *           and returns an integer representing the hand's strength.</li>
 *       <li><code>getHighestRank(ArrayList&lt;Card&gt; playerHand, ArrayList&lt;Card&gt; communityCards)</code> - Determines the highest card rank present
 *           in the combined hand from the player and community cards.</li>
 *       <li><code>printCombination(ArrayList&lt;Card&gt; playerHand, ArrayList&lt;Card&gt; communityCards)</code> - Prints a string representing the type of hand combination,
 *           such as "Royal Flush" or "Full House".</li>
 *       <li><code>sortCardsByDecreasingRank(ArrayList&lt;Card&gt; cards)</code> - Sorts a list of cards in descending order according to their rank.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><b>{@link util.GameConfig}</b>:
 * <ul>
 *   <li><b>Description:</b> Reserved for game configuration settings, including such properties
 *       as the number of players, the type of card deck to use, or other game rules and parameters.
 *       This class centralizes configuration to simplify adjustments and customization of the game.
 *   </li>
 *   <li><b>Usage:</b> Developers can modify or extend the configuration settings as the project’s requirements evolve.
 *   </li>
 * </ul>
 */
package util;
