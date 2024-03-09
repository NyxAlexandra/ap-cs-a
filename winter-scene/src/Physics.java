public interface Physics {
    public Vec2 position();

    public void setPosition(Vec2 position);

    public default Vec2 velocity() {
        return Vec2.ZERO;
    }

    public default void setVelocity(Vec2 velocity) {}

    public default void applyVelocity() {
        setPosition(position().add(velocity()));
    }
}
