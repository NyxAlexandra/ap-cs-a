import java.util.Objects;

/** A single game of <i>Guardian Gamble</i>™. */
public class Game {
  /** Amount player wagered. */
  public int wager;

  /** Result of coin flip. */
  public boolean coin;

  /** First die roll. */
  public int roll1;

  /** Second die roll. */
  public int roll2;

  /** First dealer-drawn card value. */
  public int draw1;

  /** Second dealer-drawn card value. */
  public int draw2;

  /** The number the player needs to guess. */
  public Integer random;

  /**
   * Player-entered guess.
   *
   * <p>Optionally set after coin flip and die rolls. If `null`, no guess.
   */
  public Integer guess;

  /** Simulates a random game with the given wager. */
  public Game(int wager) {
    this.wager = wager;

    coin = Rng.coin();
    roll1 = Rng.d6();
    roll2 = Rng.d6();
    draw1 = Rng.card();
    draw2 = Rng.card();
    random = Rng.number();
  }

  public boolean playerWins() {
    int player = playerScore();
    int house = houseScore();

    if (player == house) return coin;
    else if (Objects.equals(guess, random)) return true;
    else return player > house;
  }

  public int playerScore() {
    if (coin) return roll1 + roll2;
    else return roll1 * roll2;
  }

  public int houseScore() {
    return draw1 + draw2;
  }
}
