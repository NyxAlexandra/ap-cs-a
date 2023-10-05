import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

public class SmileyFace extends Canvas {
    /// Width of the canvas.
    final int WIDTH = 800;
    /// Height of the canvas.
    final int HEIGHT = 600;

    public SmileyFace() {
        setSize(WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        var diameter = (int) ((double) Math.min(WIDTH, HEIGHT) * 0.75);
        var radius = diameter / 2;

        // face background

        var x = (WIDTH - diameter) / 2;
        var y = (HEIGHT - diameter) / 2;

        graphics.setColor(Color.YELLOW);
        graphics.fillOval(x, y, diameter, diameter);

        // eyes

        var eyeDiameter = (int) ((double) radius * 0.8) / 2;

        graphics.setColor(Color.BLACK);
        // left eye
        graphics.fillOval(x + eyeDiameter, y + eyeDiameter, eyeDiameter, eyeDiameter);
        // right eye
        graphics.fillOval(x + diameter - 2 * eyeDiameter, y + eyeDiameter, eyeDiameter, eyeDiameter);

        // mouth

        var mouthX = x + (int) (0.2 * (double) radius);
        var mouthY = y + (int) (0.4 * (double) radius);

        var mouthWidth = (int) (0.8 * (double) diameter);
        var mouthHeight = (int) (0.7 * (double) diameter);

        graphics.fillArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, -180);
    }
}