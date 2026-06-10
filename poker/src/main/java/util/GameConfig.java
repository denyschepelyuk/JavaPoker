package util;

public class GameConfig {
    // Game constants
    public static final int MAX_PLAYERS = 8;
    public static final int MAX_BOTS = MAX_PLAYERS - 1;
    public static final int DEFAULT_NUM_BOTS = 3;

    // Card constants
    public static final int HAND_SIZE = 2;
    public static final int MAX_COMMUNITY_CARDS = 5;

    // Betting constants
    public static final int STARTING_CHIPS = 1000;
    public static final int BIG_BLIND = 20;
    public static final int SMALL_BLIND = BIG_BLIND / 2;
}
