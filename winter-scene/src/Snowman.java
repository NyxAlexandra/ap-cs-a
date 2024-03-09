import java.awt.*;

public class Snowman implements Element {
    @Override
    public void draw(Graphics2D gfx) {
        // lower body
        gfx.setColor(new Color(210, 219, 223));
        gfx.fillArc(542, 362, 100, 100, 0, 360);
        // left arm
        gfx.rotate(0.262, 505, 342);
        gfx.setColor(new Color(111, 54, 22));
        gfx.fillRect(510, 342, 51, 16);
        gfx.rotate(-0.262, 505, 342);
        // right arm
        gfx.rotate(-0.312, 622, 349);
        gfx.fillRect(622, 349, 56, 16);
        gfx.rotate(0.312, 622, 349);
        // torso
        gfx.setColor(new Color(230, 234, 238));
        gfx.fillArc(551, 324, 81, 81, 0, 360);
        // face
        gfx.setColor(Color.WHITE);
        gfx.fillArc(559, 282, 64, 64, 0, 360);
        // left eye
        gfx.setColor(new Color(29, 29, 29));
        gfx.fillArc(568, 304, 12, 12, 0, 360);
        // right eye
        gfx.fillArc(600, 308, 12, 12, 0, 360);
        // yankee with brim
        gfx.fillArc(553, 278, 78, 23, 0, 360);
        // top of the hat to 'ya
        gfx.fillArc(573, 260, 34, 29, 0, 360);
    }
}
