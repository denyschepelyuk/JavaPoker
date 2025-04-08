/**
 * The ui package handles all user interface operations within the Poker game.
 * It is responsible for processing user input and presenting game-related information
 * through the console. The package offers components for capturing commands, parsing
 * input, and visually rendering the game state.
 *
 * <p><b>{@link ui.InputHandler}</b>:
 * <ul>
 *   <li><b>Description:</b> Encapsulates console input handling using a Scanner.
 *       It prompts the user for commands and returns the entered text.</li>
 *   <li><b>Key Public Methods:</b>
 *     <ul>
 *       <li><code>getCommand()</code> - Displays a prompt ("Command: ") and retrieves a line of user input.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><b>{@link ui.CommandHandler}</b>:
 * <ul>
 *   <li><b>Description:</b> Interprets and processes user commands to control gameplay.
 *       It uses an InputHandler to receive input, extracts command tokens and numeric parameters,
 *       and invokes the corresponding actions on the game and player instances.</li>
 *   <li><b>Key Public Methods:</b>
 *     <ul>
 *       <li><code>handleInput()</code> - Reads a command from the console and maps it to a game action
 *           (e.g., "fold", "bet", "call", "check"), providing basic error handling and feedback to the user.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><b>{@link ui.ConsoleUI}</b>:
 * <ul>
 *   <li><b>Description:</b> Manages the visual output of the game state on the console.
 *       It formats and displays information such as the community cards, the current pot,
 *       and the individual statuses of each player.</li>
 *   <li><b>Key Public Methods:</b>
 *     <ul>
 *       <li><code>renderGame(Game game)</code> - Renders the current state of the game, including the table,
 *           community cards, the pot, and the players' hands or statuses.</li>
 *       <li><code>displayWinner(Game game, Player winner)</code> - Displays the winner along with the final game state,
 *           and shows additional details like the winning hand combination.</li>
 *     </ul>
 *   </li>
 * </ul>
 */
package ui;
