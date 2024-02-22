import java.util.Objects;
import java.util.Scanner;

public class Cli {
  static final int NUM_GAMES = 10;

  public static void main(String[] _args) {
    Game[] games = new Game[NUM_GAMES];

    System.out.println("# Guardian's Gamble™");
    System.out.println();
    System.out.println(
        NUM_GAMES
            + " games of Guardian's Gamble™ will be played in a row.\n"
            + "after all games are played, statistics will be shown.");

    Scanner scanner = new Scanner(System.in);

    for (int i = 0; i < NUM_GAMES; i++) {
      System.out.println();
      System.out.println("> game " + (i + 1));

      System.out.println();
      System.out.print("wager: ");
      int wager = scanner.nextInt();

      games[i] = new Game(wager);
      Game game = games[i];

      System.out.println();
      System.out.println("coin flip: " + (game.coin ? "heads" : "tails"));
      System.out.println("player score: " + game.playerScore());
      System.out.println("house score: " + game.houseScore());

      System.out.println();
      System.out.println("-> " + (game.playerWins() ? "win" : "loss"));

      if (!game.playerWins()) {
        System.out.println();
        System.out.print("guess? ");
        {
          String line = scanner.useDelimiter("\n").next();

          if (!line.isEmpty()) game.guess = Integer.valueOf(line);
        }

        System.out.println();
        System.out.printf(
            "%s == %s = %b\n", game.guess, game.random, Objects.equals(game.guess, game.random));

        System.out.println();
        System.out.println("-> " + (game.playerWins() ? "win" : "loss"));
      }
    }

    scanner.close();

    int wins = 0, won = 0, bet = 0;

    for (Game game : games) {
      bet += game.wager;
      if (game.playerWins()) {
        wins++;
        won += game.wager;
      }
    }

    System.out.println();
    System.out.println("%win: " + ((double) wins / (NUM_GAMES - wins)) * 100.0);
    System.out.println("$won: " + won);
    System.out.println("$bet: " + bet);
    System.out.println("expected value: " + (double) (won - bet) / NUM_GAMES);
  }
}
