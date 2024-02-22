import java.awt.*;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.Instant;
import javax.swing.*;

public class App {
  static final String TITLE = "Guardian's Gamble™";

  public static void main(String[] _args) {
    switch (JOptionPane.showOptionDialog(
        null,
        "Slow - play games of " + TITLE + "\n" + "Fast - Simulate games of " + TITLE,
        "Select Mode",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        null,
        new String[] {"Slow", "Fast"},
        -1)) {
      case 0:
        new SlowMode().run();
        break;
      case 1:
        new FastMode().run();
        break;
    }
  }
}

// TODO: consider putting stats into a popup

class SlowMode {
  private static final String TITLE = App.TITLE + " - Slow Mode";
  private static final Dimension SIZE = new Dimension(900, 600);

  Game game;

  /** Number of games to simulate. */
  int count = 10;

  /** Player-entered wager in USD. */
  int wager = 100;

  GridBagConstraints cons;

  public SlowMode() {
    cons = new GridBagConstraints();
  }

  public void run() {
    new MainScreen().run();
  }

  class MainScreen extends JFrame {
    boolean coinFlipped;
    JButton coinFlipButton;
    JLabel coinFlipValue;

    boolean dieRolled1;
    boolean dieRolled2;
    JButton dieRollButton;
    JLabel dieRollValue;

    JButton continueButton;

    public MainScreen() {
      super(TITLE);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridBagLayout());
    }

    public void run() {
      try {
        count = Integer.parseInt(JOptionPane.showInputDialog(null, "number of games", count));
      } catch (Exception ignored) {
      }

      cons.insets = new Insets(48, 48, 48, 48);

      cons.gridwidth = 1;
      cons.gridheight = 1;
      cons.gridx = 0;
      cons.gridy = 0;
      cons.fill = GridBagConstraints.BOTH;
      cons.weightx = 0.5;

      // truth is... the game was rigged from the start :3
      game = new Game(wager);

      add(coinFlipButton = new JButton("flip a coin"), cons);

      cons.gridx++;
      coinFlipButton.addActionListener(
          _action -> {
            coinFlipped = true;
            coinFlipValue.setText(game.coin ? "heads" : "tails");
            coinFlipButton.setEnabled(false);
          });

      add(coinFlipValue = new JLabel("-"), cons);

      cons.gridx = 0;
      cons.gridy++;

      add(dieRollButton = new JButton("roll 2 die"), cons);

      cons.gridx++;
      dieRollButton.addActionListener(
          _action -> {
            if (!dieRolled1) {
              dieRolled1 = true;
              dieRollValue.setText("" + game.roll1);
            } else {
              dieRolled2 = true;
              dieRollValue.setText(game.roll1 + ", " + game.roll2);
              dieRollButton.setEnabled(false);
            }
          });

      add(dieRollValue = new JLabel("-"), cons);

      cons.gridx = 0;
      cons.gridy++;
      cons.gridwidth = 0;

      add(continueButton = new JButton("continue..."), cons);

      cons.gridy++;
      cons.gridwidth = 1;
      continueButton.addActionListener(
          _action -> {
            if (coinFlipped && dieRolled2) {
              setVisible(false);
              new ResultScreen().run();
            }
          });

      setSize(SIZE);
      setVisible(true);
    }
  }

  class ResultScreen extends JFrame {
    public ResultScreen() {
      super(TITLE + " - Results");

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridBagLayout());
    }

    public void run() {
      cons.gridx = 0;
      cons.gridy = 0;

      cons.gridwidth = 0;
      cons.gridheight = 1;

      JButton revealScore;
      add(revealScore = new JButton("reveal score :O"), cons);

      cons.gridx = 0;
      cons.gridy++;

      cons.gridwidth = 1;
      cons.gridheight = 1;

      JLabel playerScoreValue;

      add(new JLabel("player score"), cons);

      cons.gridx++;

      add(playerScoreValue = new JLabel("-"), cons);

      cons.gridx = 0;
      cons.gridy++;

      JLabel houseScoreValue;

      this.add(new JLabel("house score"), cons);

      cons.gridx++;

      add(houseScoreValue = new JLabel("-"), cons);

      cons.gridx = 0;
      cons.gridy++;

      revealScore.addActionListener(
          _action -> {
            revealScore.setEnabled(false);
            playerScoreValue.setText("" + game.playerScore());
            houseScoreValue.setText("" + game.houseScore());

            if (game.playerWins()) winDialog();
            else {
              try {
                game.guess =
                    Integer.valueOf(
                        JOptionPane.showInputDialog(
                            null,
                            "you lose! but if you can guess the correct number (1-100), you can still win!",
                            "guess?",
                            JOptionPane.QUESTION_MESSAGE));
              } catch (Exception ignored) {
              }

              if (game.playerWins()) winDialog();
              else
                JOptionPane.showMessageDialog(
                    null, String.format("too bad, it was %d :P", game.random));
            }

            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
          });

      setSize(SIZE);
      setVisible(true);
    }

    private void winDialog() {
      JOptionPane.showMessageDialog(null, "you win :DDD");
    }
  }
}

// probability, win%

class FastMode extends JFrame {
  static final Dimension SIZE = new Dimension(900, 600);

  /** Number of games to simulate. */
  int count = 10;

  /** Player-entered wager in USD. */
  int wager = 100;

  /** Player win count. */
  int wins;

  /** Whether to live-update statistics. */
  boolean liveUpdate = true;

  JLabel gamesLabel;
  JSpinner gamesInput;

  JLabel wagerLabel;
  JSpinner wagerInput;

  JLabel liveUpdateLabel;
  JCheckBox liveUpdateValue;

  JButton runButton;
  JProgressBar progressBar;

  JLabel timer;
  Instant start;

  JLabel playerWinsLabel;
  JLabel playerWinsValue;

  JLabel houseWinsLabel;
  JLabel houseWinsValue;

  JLabel wonLabel;
  JLabel wonValue;

  JLabel expectedValueLabel;
  JLabel expectedValueValue; // heh

  public FastMode() {
    super(App.TITLE + " - Fast Mode");

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());

    GridBagConstraints cons = new GridBagConstraints();

    cons.insets = new Insets(8, 8, 8, 8);
    // note: `GridBagConstraints.grid*` make no sense, for example 1-by-1 is Java Swing™ for 2-by-2
    //
    // (source: an hour of pain and a nice little cry)
    cons.gridwidth = 1;
    cons.gridheight = 1;
    cons.gridx = 0;
    cons.gridy = 0;
    cons.fill = GridBagConstraints.BOTH;
    cons.weightx = 0.5;

    add(wagerLabel = new JLabel("wager (USD)"), cons);

    cons.gridx++;

    add(wagerInput = new JSpinner(), cons);

    wagerInput.setValue(wager);
    wagerInput.addChangeListener(
        event -> {
          wager = (Integer) ((JSpinner) event.getSource()).getValue();
        });

    cons.gridx = 0;
    cons.gridy++;

    add(gamesLabel = new JLabel("amount of games"), cons);

    cons.gridx++;

    add(gamesInput = new JSpinner(), cons);

    gamesInput.setValue(count);
    gamesInput.addChangeListener(
        event -> {
          count = (Integer) ((JSpinner) event.getSource()).getValue();
          progressBar.setMaximum(count);
        });

    cons.gridx = 0;
    cons.gridy++;

    add(runButton = new JButton("run"), cons);

    cons.gridx++;

    add(progressBar = new JProgressBar(), cons);

    runButton.addActionListener(_action -> simulate());
    progressBar.setMaximum(count);

    cons.gridx = 0;
    cons.gridy++;

    add(liveUpdateLabel = new JLabel("live update stats (big performance effect!!)"), cons);

    cons.gridx++;

    add(liveUpdateValue = new JCheckBox(), cons);

    if (liveUpdate) liveUpdateValue.doClick();
    liveUpdateValue.addActionListener(_action -> liveUpdate = !liveUpdate);

    cons.gridx = 0;
    cons.gridy++;

    add(timer = new JLabel("-"), cons);

    cons.gridx++;

    add(new JSeparator(), cons);

    cons.gridx = 0;
    cons.gridy++;

    add(playerWinsLabel = new JLabel("player wins"), cons);

    cons.gridx++;

    add(playerWinsValue = new JLabel("-"), cons);

    cons.gridx = 0;
    cons.gridy++;

    add(houseWinsLabel = new JLabel("house wins"), cons);

    cons.gridx = 1;

    add(houseWinsValue = new JLabel("-"), cons);

    cons.gridx = 0;
    cons.gridy++;

    add(wonLabel = new JLabel("won"), cons);

    cons.gridx++;

    add(wonValue = new JLabel("-"), cons);

    cons.gridx = 0;
    cons.gridy++;

    add(expectedValueLabel = new JLabel("expected value"), cons);

    cons.gridx++;

    add(expectedValueValue = new JLabel("-"), cons);
  }

  public void run() {
    setSize(SIZE);
    setVisible(true);
  }

  /** Run game simulation. */
  private void simulate() {
    start = Instant.now();

    new Thread(
            () -> {
              int wins = 0;

              for (int i = 1; i <= count; i++) {
                Game game = new Game(wager);

                if (game.playerWins()) wins++;

                progressBar.setValue(i);

                if (liveUpdate) update(i, count, wins);
              }

              update(count, count, wins);
            })
        .start();
  }

  // TODO: remove slowdown from live updating by moving work to Swing UI thread
  private void update(int i, int count, int wins) {
    playerWinsValue.setText("" + wins);
    houseWinsValue.setText("" + (i - wins));

    int won = (wager * wins - wager * (i - wins));
    int bet = wager * i;

    wonValue.setText("" + won);

    expectedValueValue.setText("" + (double) (won - bet) / count);

    timer.setText(Duration.between(start, Instant.now()).getSeconds() + " seconds");
  }
}
