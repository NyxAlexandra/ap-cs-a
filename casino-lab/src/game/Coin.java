package game;

import java.util.Random;

public enum Coin {
    heads,
    tails;

    public static Coin flip() {
        if (new Random().nextBoolean())
            return heads;
        else
            return tails;
    }
}
