import javax.swing.*;
import java.awt.*;

public class App {
    private final int fps;

    private Image framebuf;
    private final JFrame frame;
    private final Snowflakes snowflakes;
    private final Snowman snowman;

    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static final int MILLIS_PER_SECOND = 1000;

    private App() {
        fps = 60;
        frame = new JFrame();

        snowman = new Snowman();
        snowflakes = new Snowflakes(new Vec2(WIDTH, HEIGHT), 100);

        snowflakes.setVelocity(snowflakes.velocity().add(new Vec2(5, 0)));

        Color bg = new Color(55, 60, 112);

        frame.getContentPane().setBackground(bg);
        frame.setBackground(bg);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
    }

    private void update() {
        SwingUtilities.invokeLater(() -> {
            if (framebuf == null) {
                framebuf = frame.createImage(frame.getWidth(), frame.getHeight());
            }

            Graphics2D gfx = (Graphics2D) framebuf.getGraphics();

            gfx.clearRect(0, 0, frame.getWidth(), frame.getHeight());

            // "back"-ground hehe~
            gfx.setColor(new Color(223, 229, 232));
            gfx.fillArc(157, 448, 1014, 224, 0, 360);
            // "fore"-ground~
            gfx.setColor(new Color(237, 241, 243));
            gfx.fillArc(-341, 394, 1147, 224, 0, 360);
            // tree trunk
            gfx.setColor(new Color(111, 54, 22));
            gfx.rotate(-0.035);
            gfx.fillRect(149, 278, 61, 146);
            // tree leaves
            gfx.setColor(new Color(123, 215, 94));
            gfx.fillPolygon(new int[] {180, 54, 302}, new int[] {145, 366, 361}, 3);
            gfx.rotate(0.035);

            snowman.draw(gfx);
            snowflakes.draw(gfx);
            snowflakes.applyVelocity();

            frame.getGraphics().drawImage(framebuf, 0, 0, frame);
        });
    }

    public static void main(String[] args) throws InterruptedException {
        App app = new App();

        app.frame.setVisible(true);

        while (true) {
            Thread current = Thread.currentThread();
            synchronized (current) {
                current.wait(MILLIS_PER_SECOND / app.fps);
            };
            app.update();
        }
    }
}