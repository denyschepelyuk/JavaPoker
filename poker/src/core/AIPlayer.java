package core;

public class AIPlayer extends Player {
    public AIPlayer(String name, int initialChips, Game game) {
        super(name, initialChips, game);
    }

    public void makeDecision() {
        double randomValue = Math.random();
        if (randomValue < 0.2) {
            fold();
        } else if (randomValue < 0.4) {
            check();
        } else if (randomValue < 0.6) {
            check();
        } else {
            int randomBet = (int) (Math.random() * getChips()/50);
            bet(randomBet * 50);
        }
    }

}
