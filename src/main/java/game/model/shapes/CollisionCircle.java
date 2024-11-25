package game.model.shapes;

import game.model.Vector2D;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


/**
 * The CollisionCircle class represents a circular collision shape.
 * It extends Ellipse2D.Double and implements the CollisionShape interface.
 * This class provides methods to get and set the position of the circle,
 * check for collisions with other shapes, and retrieve the bounding box
 * and center of the circle.
 */
public class CollisionCircle extends Ellipse2D.Double implements CollisionShape{
    double radius;

    //coordinate of the circle's upperleft corner
    Vector2D position;

    public CollisionCircle(Vector2D position, double radius){
        super(position.getX(),position.getY(),2*radius,2*radius);
        this.position = position;
        this.radius = radius;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2D position) {
        this.position = position;
        super.x = position.getX();
        super.y = position.getY();
    }

    @Override
    public boolean haveCollided(CollisionShape otherShape) {
        return (intersects(otherShape.getBoundingBox()));
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return getBounds2D();
    }

    @Override
    public double getMaxDistanceFromCenter() {
        return radius;
    }

    @Override
    public Vector2D getCenter() {
        return position.add(new Vector2D(radius,radius));
    }
}
