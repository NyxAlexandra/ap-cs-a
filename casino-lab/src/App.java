import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/** App state. */
public class App {
  static final String TITLE = "Guardian's Gamble™";

  public App() {}

  public void run() {
    switch (modeSelect()) {
      case 0:
        new GameMode().run();
        break;
      case 1:
        new TestMode().run();
        break;
    }
  }

  int modeSelect() {
    return JOptionPane.showOptionDialog(
        null,
        String.format("Game - %s game cabinet\n", TITLE)
            + String.format("Test - %s (mass) simulator", TITLE),
        "Select Mode",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        null,
        new String[] {"Game", "Test"},
        -1);
  }
}

class GameMode extends JFrame {
  static final Dimension SIZE = new Dimension(900, 600);

  public GameMode() {
    super(App.TITLE + " - Game Mode");
  }

  public void run() {
    setSize(SIZE);
    setVisible(true);
  }
}

class TestMode extends JFrame {
  static final int DEFAULT_COUNT = 10;
  static final int DEFAULT_WAGER = 100;
  static final Dimension SIZE = new Dimension(900, 600);

  /** Number of games to simulate. */
  int count = DEFAULT_COUNT;

  /** Default wager in USD. */
  int wager = DEFAULT_WAGER;

  /** Player win count. */
  int wins;

  /** Simulated games. */
  ArrayList<Game> games;

  JLabel gamesLabel;
  JSpinner gamesInput;

  JButton runButton;
  JProgressBar progressBar;

  JLabel playerWins;
  JLabel houseWins;

  //  JLabel playerProfit;
  //  JLabel houseProfit;

  public TestMode() {
    super(App.TITLE + " - Test Mode");

    setLayout(new GridBagLayout());

    games = new ArrayList<>(count);

    gamesLabel = new JLabel("num games");
    gamesInput = new JSpinner();

    add(gamesLabel);
    add(gamesInput);

    gamesInput.setValue(count);
    gamesInput.addChangeListener(
        event -> {
          count = (Integer) ((JSpinner) event.getSource()).getValue();
          progressBar.setMaximum(count);
        });

    runButton = new JButton("run");
    progressBar = new JProgressBar();

    add(runButton);
    add(progressBar);

    runButton.addActionListener(_e -> simulate());
    progressBar.setMaximum(count);
    // `progressBar` is updated during `TestMode::simulate`

    playerWins = new JLabel("player wins: " + wins);
    houseWins = new JLabel("house wins: " + (games.size() - wins));

    add(playerWins);
    add(houseWins);
  }

  public void run() {
    setSize(SIZE);
    setVisible(true);
  }

  /**
   * Run game simulation.
   *
   * <p>Blocks the thread.
   */
  void simulate() {
    games.clear();

    for (int i = 0; i < count; i++) {
      Game game = new Game(wager);

      games.add(game);
      progressBar.setValue(games.size());
      if (game.playerWins()) wins++;
    }

    System.out.println(games);
  }
}
