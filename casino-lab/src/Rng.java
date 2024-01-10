import java.util.Random;

/** RNG elements. */
public final class Rng {
  static final Random rng = new Random();

  /** Roll a D6 (range [1, 6]). */
  public static int d6() {
    return rng.nextInt(0, 6) + 1;
  }

  /** Draw a card, ace-to-9 inclusive. */
  public static int card() {
    return rng.nextInt(0, 9) + 1;
  }

  /** Flip a coin, true if heads. */
  public static boolean coin() {
    return rng.nextBoolean();
  }
}
