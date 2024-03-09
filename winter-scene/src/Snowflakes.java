import java.awt.*;
import java.util.Random;

public class Snowflakes extends PhysicsElement {
    Vec2[] positions;
    Vec2 velocity;
    Vec2 bounds;

    public Snowflakes(Vec2 bounds, int count) {
        this.bounds = bounds;
        velocity = new Vec2(0, 10);
        positions = new Vec2[count];

        Random rng = new Random();

        for (int i = 0; i < count; i++)
            positions[i] = new Vec2(rng.nextInt(0, bounds.x), rng.nextInt(0, bounds.y));
    }

    @Override
    public void draw(Graphics2D gfx) {
        gfx.setColor(Color.WHITE);

        for (Vec2 position : positions)
            gfx.fillArc(position.x, position.y, 2, 2, 0, 360);
    }

    @Override
    public Vec2 position() {
        return Vec2.ZERO;
    }

    @Override
    public void setPosition(Vec2 _position) {}

    @Override
    public Vec2 velocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vec2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void applyVelocity() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = positions[i].add(velocity);

            if (positions[i].x > bounds.x)
                positions[i].x -= bounds.x;
            if (positions[i].y > bounds.y)
                positions[i].y -= bounds.y;
        }
    }
}
