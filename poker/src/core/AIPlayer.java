package core;

public class AIPlayer extends Player {
    public AIPlayer(String name, int initialChips, Game game) {
        super(name, initialChips, game);
    }

    public void makeDecision() {
        double randomValue = Math.random();
        int toCall = game.getHighestBet();

        if (randomValue < 0.1) {
            fold();
            return;
        }

        if (toCall > getBetAmount()){
            if (randomValue < 0.5) {
                call();
            }
            else {
                int randomBet = (int) (Math.random() * getChips()/50);
                bet(toCall - getBetAmount() + randomBet * 50);
            }
        }
        else{
            if (randomValue < 0.5) {
                check();
            }
            else {
                int randomBet = (int) (Math.random() * getChips()/50);
                bet(toCall - getBetAmount() + randomBet * 50);
            }
        }
    }

}
