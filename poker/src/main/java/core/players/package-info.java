/**
 * The core.players package defines the participants in the Poker game.
 * It contains classes that represent both human players and their AI-controlled counterparts.
 * These classes manage player-specific actions such as betting, folding, and checking,
 * while keeping track of their respective hands, chip counts, and current bets.
 *
 * <p><b>{@link core.players.Player}</b>:
 * <ul>
 *   <li><b>Description:</b> Represents a participant in the game with basic functionalities
 *       for betting behavior and hand management.</li>
 *   <li><b>Key Public Properties and Methods:</b>
 *     <ul>
 *       <li><code>getHand()</code> - Retrieves the player's current hand of cards.</li>
 *       <li><code>getName()</code> - Returns the player's name.</li>
 *       <li><code>getChips()</code> - Returns the available chips for the player.</li>
 *       <li><code>isFolded()</code> - Indicates whether the player has folded.</li>
 *       <li><code>getBetAmount()</code> - Retrieves the current bet amount for the player.</li>
 *       <li><code>fold()</code> - Sets the player to a folded state.</li>
 *       <li><code>bet(int amount)</code> - Places a bet, deducts chips, and updates the game state accordingly.</li>
 *       <li><code>call()</code> - Matches the highest bet placed in the game.</li>
 *       <li><code>check()</code> - Indicates that the player opts to take no betting action.</li>
 *       <li><code>reset()</code> - Clears the player's hand and resets betting related attributes.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><b>{@link core.players.AIPlayer}</b>:
 * <ul>
 *   <li><b>Description:</b> Extends the Player class to incorporate basic AI-driven decision making
 *       for betting during a game session.</li>
 *   <li><b>Key Public Method:</b>
 *     <ul>
 *       <li><code>makeDecision()</code> - Implements logic based on hand strength and other factors to determine whether
 *           to fold, call, check, or raise. The decision is influenced by random factors and the current game state.</li>
 *     </ul>
 *   </li>
 * </ul>
 */
package core.players;
