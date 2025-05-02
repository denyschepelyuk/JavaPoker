package app;

import core.game.Game;

public class App {
    public static void main(String[] args) {
        Game game = new Game(3);
        game.run();
    }
}