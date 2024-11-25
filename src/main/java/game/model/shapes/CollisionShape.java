package game.model.shapes;
import game.model.Vector2D;

import java.awt.geom.Rectangle2D;

/**
 * The CollisionShape interface defines the methods required for a shape
 * that can participate in collision detection within a game.
 */
public interface CollisionShape{
    Vector2D getPosition();

    void setPosition(Vector2D position);

    public boolean haveCollided(CollisionShape otherShape);

    public Rectangle2D getBoundingBox();

    public double getMaxDistanceFromCenter();

    public Vector2D getCenter();
}
