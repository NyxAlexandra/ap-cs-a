package game;

/** A D6. */
public class Die {
    public static int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}