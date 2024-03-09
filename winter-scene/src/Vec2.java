public class Vec2 {
    public int x, y;

    public static final Vec2 ZERO = new Vec2(0, 0);

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 rhs) {
        return new Vec2(x + rhs.x, y + rhs.x);
    }
}
