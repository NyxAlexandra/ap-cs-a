/** A single game of <i>Guardian Gamble</i>™. */
public class Game {
  /** Amount player wagered. */
  int wager;

  /** Result of coin flip. */
  boolean coin;

  /** First die roll. */
  int roll1;

  /** Second die roll. */
  int roll2;

  /** First dealer-drawn card value. */
  int draw1;

  /** Second dealer-drawn card value. */
  int draw2;

  /**
   * Player-entered guess.
   *
   * <p>Optionally set after coin flip and die rolls. If `null`, no guess.
   */
  Integer guess;

  /** Simulates a random game with the given wager. */
  public Game(int wager) {
    this.wager = wager;

    coin = Rng.coin();
    roll1 = Rng.d6();
    roll2 = Rng.d6();
    draw1 = Rng.card();
    draw2 = Rng.card();
  }

  /** Set the player's guess. */
  public void setGuess(Integer guess) {
    this.guess = guess;
  }

  public boolean playerWins() {
    int player = playerScore();
    int house = houseScore();

    if (player == house) return coin;
    else return player > house;
  }

  public int playerScore() {
    if (coin) return roll1 + roll2;
    else return roll1 * roll2;
  }

  public int houseScore() {
    return draw1 + draw2;
  }

  @Override
  public String toString() {
    return String.format(
        "Game(%d, %d){ %s }", playerScore(), houseScore(), playerWins() ? "win" : "loss");
  }
}
