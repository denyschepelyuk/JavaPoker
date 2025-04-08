/**
 * The core.game package is responsible for managing the overall game state and mechanics 
 * of the Poker project. It handles the orchestration of game rounds, player interactions, 
 * dealing of cards, and tracking bets through the table.
 *
 * <p><b>{@link core.game.Game}</b>:
 * <ul>
 *   <li><b>Description:</b> Oversees the entire gameplay, including initialization, execution 
 *       of game rounds, updates to player actions, and determining the game winner.</li>
 *   <li><b>Public Methods:</b>
 *     <ul>
 *       <li><code>run(int playersCount)</code> - Initiates the game with the specified number of players
 *           and manages the game loop until completion.</li>
 *       <li><code>getTable()</code> - Retrieves the current Table instance which tracks community cards and pot.</li>
 *       <li><code>getPlayers()</code> - Provides access to the list of players participating in the game.</li>
 *       <li><code>getHighestBet()</code> - Returns the highest bet placed among the current players.</li>
 *     </ul>
 *   </li>
 *   <li><b>Remarks:</b> Although many internal operations (e.g., updating players, rendering the UI,
 *       and ending the game) are encapsulated within private methods, the public API provides essential 
 *       control over the game lifecycle and state.</li>
 * </ul>
 *
 * <p><b>{@link core.game.Table}</b>:
 * <ul>
 *   <li><b>Description:</b> Manages the table where the community cards are displayed and
 *       the betting pot is tracked during the game.</li>
 *   <li><b>Public Methods:</b>
 *     <ul>
 *       <li><code>deal(Deck deck)</code> - Deals cards from the provided Deck to the table’s community cards.</li>
 *       <li><code>addCommunityCard(Card card)</code> - Adds a specific card to the list of community cards.</li>
 *       <li><code>reset()</code> - Clears the community cards and resets the betting pot to its initial state.</li>
 *       <li><code>getPotAmount()</code> - Returns the current total of the pot.</li>
 *       <li><code>addBet(int amount)</code> - Increases the pot by the specified bet amount.</li>
 *       <li><code>getCommunityCards()</code> - Retrieves the list of community cards currently on the table.</li>
 *     </ul>
 *   </li>
 *   <li><b>Remarks:</b> The Table class is critical for handling the evolving game state, ensuring that
 *       community card distribution and pot calculations are managed consistently throughout each round.</li>
 * </ul>
 */
package core.game;
