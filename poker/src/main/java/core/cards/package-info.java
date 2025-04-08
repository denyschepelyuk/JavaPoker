/**
 * The core.cards package provides the necessary classes for card-related
 * operations in the poker game. It implements the concept of a standard playing card
 * (with its suit and rank) and a deck of cards used for gameplay.
 *
 * <p>This package contains two main classes:
 *
 * <p><b>{@link core.cards.Card}</b>:
 * <ul>
 *   <li><b>Nested Enumerations:</b>
 *     <ul>
 *       <li><b>Suit</b> - Defines the four card suits: HEARTS, DIAMONDS, CLUBS, and SPADES.
 *           Each suit has a name and symbol, accessible via <code>getName()</code> and <code>getSymbol()</code>.</li>
 *       <li><b>Rank</b> - Defines the thirteen card ranks ranging from TWO to ACE.
 *           Each rank has an associated name, accessible via <code>getName()</code>.</li>
 *     </ul>
 *   </li>
 *   <li><b>Public Methods in Card:</b>
 *     <ul>
 *       <li><code>getDescription()</code>: Returns a human-readable description of the card (for example, "♠A").</li>
 *       <li><code>isGreaterThan(Card other)</code>: Compares two cards based on their rank values.</li>
 *       <li><code>getRankValue(Rank rank)</code>: Returns an integer value corresponding to the rank, used for comparison.</li>
 *       <li><code>getRank()</code> and <code>getSuit()</code>: Accessor methods for retrieving the card's rank and suit.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><b>{@link core.cards.Deck}</b>:
 * <ul>
 *   <li><b>Internal Structure:</b> Maintains a list of Card objects representing a standard deck.
 *       The deck is initialized with all possible combinations of the defined suits and ranks.</li>
 *   <li><b>Public Methods in Deck:</b>
 *     <ul>
 *       <li><code>shuffle()</code>: Randomizes the order of the cards in the deck.</li>
 *       <li><code>dealCard()</code>: Removes and returns the top card from the deck.</li>
 *       <li><code>dealHand(int numCards, Player player)</code>: Deals a specified number of cards,
 *           adding each card to the given player's hand and returning them as a list.</li>
 *     </ul>
 *   </li>
 * </ul>
 */
package core.cards;
