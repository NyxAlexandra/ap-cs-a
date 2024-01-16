import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] _args) {
    final int NUM_GAMES = 10;

    Game[] games = new Game[NUM_GAMES];

    System.out.println("# Guardian's Gamble™");
    System.out.println();
    System.out.println(
        NUM_GAMES
            + " games of Guardian's Gamble™ will be played in a row.\n"
            + "after all games are played, statistics will be shown.");

    Scanner scanner = new Scanner(System.in);

    for (int i = 0; i < 10; i++) {
      System.out.println();
      System.out.printf("> game %d\n", i + 1);

      System.out.println();
      System.out.print("wager: ");
      int wager = scanner.nextInt();

      games[i] = new Game(wager);
      Game game = games[i];

      System.out.println();
      System.out.println("coin flip: " + (game.coin ? "heads" : "tails"));
      System.out.printf("player score: %d + %d = %d\n", game.roll1, game.roll2, game.playerScore());
      System.out.printf("house score: %d + %d = %d\n", game.draw1, game.draw2, game.houseScore());

      System.out.println();
      System.out.println("-> " + (game.playerWins() ? "win" : "loss"));

      if (!game.playerWins()) {
        System.out.println();
        System.out.print("guess? ");
        if (scanner.hasNextInt()) game.guess = scanner.nextInt();

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
