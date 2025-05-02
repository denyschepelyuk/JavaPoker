# Java Poker Project

The Java Poker Project is a console-based poker game developed in Java. The project is designed using object-oriented principles and is organized into several packages that separate game logic, user interface, utilities, and the application entry point.

## How to Run the Project

### Prerequisites
- Java JDK 1.8 or later
- Maven 3.x or later

### Build and Launch
1. **Compile the Project:**
   Open a terminal in the project root directory and run:
   ```bash
   mvn compile
   ```
This command compiles your Java source files and places the classes in the `target/classes` directory.

2. **Run the Application:**
   Use the Maven Exec Plugin to launch the application:
   ```bash
   mvn exec:java
   ```
   The application starts from the `app.App` class, which initializes a new game. By default, the game is set up for 3 players.

## How to Interact with the Game

The game runs in the console. When prompted, you can enter one of the following commands to interact with the game:
- **fold:** Folds your hand.
- **bet `<amount>`:** Places a bet with the specified number of chips.
- **call:** Matches the highest bet on the table.
- **check:** Indicates that you do not wish to bet further if conditions allow.
- **help:** Displays a list of available commands.

The `ui.CommandHandler` class processes your console input and maps it to game actions, while the `ui.ConsoleUI` class is responsible for rendering the game state (e.g., community cards, pot, and player information).

## Generating Javadoc Documentation

There is a workflow in this repository at `.github/workflows/pages.yml` that regenerate javadocs and then host website at:
<https://denyschepelyuk.github.io/JavaPoker/>


The project includes detailed Javadoc comments, which can be generated using Maven. The Javadoc documentation includes package overviews provided by `package-info.java` files in each package.

### To Generate Javadoc:
1. Run the following command from the project root:
   ```bash
   mvn javadoc:javadoc
   ```
2. Once generated, open the file located at:
   ```
   target/site/apidocs/index.html
   ```
   in your web browser to view the complete documentation.

The overview page (`overview.html`) located in `src/main/javadoc/` provides an introductory description of the project and its implementation, along with the package structure.

## Project Structure

The project is organized into the following packages:

### app
- **App.java:**  
  Contains the main method, which serves as the entry point for the application. This class initializes the game by invoking the `Game` class from the core package.

### core
- **cards**
    - **Card.java:**  
      Defines a playing card using two nested enumerations (Suit and Rank). Includes methods for obtaining a card's description and comparing card ranks.
    - **Deck.java:**  
      Represents a deck of cards. Provides functionalities to initialize, shuffle, and deal cards (both individual cards and hands for players).
- **game**
    - **Game.java:**  
      Orchestrates the overall game flow. Handles game initialization, gameplay loop, player updates, and game termination.
    - **Table.java:**  
      Manages the community cards (table) and the betting pot. Responsible for dealing cards to the table and tracking the state of the pot.
- **players**
    - **Player.java:**  
      Represents a human player. Manages player actions like folding, betting, calling, and checking, as well as maintaining the player’s hand and chip count.
    - **AIPlayer.java:**  
      Extends the `Player` class and implements basic AI logic for decision-making during the game.

### ui
- **InputHandler.java:**  
  Handles user input from the console.
- **CommandHandler.java:**  
  Processes and interprets user commands, mapping them to game actions.
- **ConsoleUI.java:**  
  Handles rendering the current game state to the console (community cards, pot, and individual player statuses).

### util
- **CardUtils.java:**  
  Provides utility methods for evaluating and comparing poker hands, including determining hand strength and sorting cards.
- **GameConfig.java:**  
  Reserved for future game configuration settings such as the number of players and deck types.

## Summary

The Java Poker Project provides a modular and extensible framework for simulating a poker game. Use Maven to compile and launch the project, interact with the game via simple console commands, and generate comprehensive Javadoc documentation for a detailed overview of the implementation.
