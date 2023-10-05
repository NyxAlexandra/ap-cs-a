import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicsRunner extends JFrame {
    public GraphicsRunner() {
        super("Alexandra Reaves - Smiley Face Project");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("ReassignedVariable")
    public void run() {
        // number of popups to show each time
        var numPopups = 32;
        // number of times to show the popup
        var repetitions = 16;

        try {
            var num = Integer.parseInt(JOptionPane.showInputDialog(this, "amount of faces", numPopups));

            if (num <= 0) throw new Exception();
            numPopups = num;

            var reps =
                    Integer.parseInt(JOptionPane.showInputDialog(this, "amount of repetitions", repetitions));

            if (reps <= 0) throw new Exception();
            repetitions = reps;
        } catch (Exception ignored) {
            JOptionPane.showConfirmDialog(this, "naughtiness detected. removing your computer privileges.");

            numPopups = Integer.MAX_VALUE;
            repetitions = Integer.MAX_VALUE;
        }

        var factory = new PopupFactory();
        var random = new Random();
        var popups = new Popup[numPopups];

        // query the graphical environment the program is being ran under
        var env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // retrieve the visible bounds to know where the popups can be placed
        var bounds = env.getMaximumWindowBounds();

        var width = bounds.width;
        var height = bounds.height;

        for (var _r = 0; _r < repetitions; _r++) {
            // create popups
            for (var i = 0; i < numPopups; i++) {
                // choose a random location for the popup
                //
                // note that the coordinate is for the upper-left corner
                var x = random.nextInt(0, width);
                var y = random.nextInt(0, height);

                popups[i] = factory.getPopup(this, new SmileyFace(), x, y);
                popups[i].show();
            }

            // close them
            for (var popup : popups) {
                // close the popup
                popup.hide();

                // they close faster than they open, so pause the thread to even it out a bit
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}