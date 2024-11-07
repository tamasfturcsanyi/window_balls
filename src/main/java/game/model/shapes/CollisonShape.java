package game.model.shapes;
import game.model.Vector2D;

import java.awt.geom.Rectangle2D;

public interface CollisonShape{
    Vector2D getPosition();

    void setPosition(Vector2D position);

    public boolean haveCollided(CollisonShape otherShape);

    public Rectangle2D getBoundingBox();

    public double getMaxDistanceFromCenter();

    public Vector2D getCenter();
}
