package game.model.shapes;

import game.model.Vector2D;
import java.awt.geom.Rectangle2D;

/**
 * The CollisionRectangle class represents a rectangular collision shape.
 * It extends Rectangle2D.Double and implements the CollisionShape interface.
 * 
 * This class provides methods to get and set the position, get the bounding box,
 * get the center, calculate the maximum distance from the center, and check for collisions
 * with other shapes.
 * 
 * Fields:
 * - position: The upper left corner of the rectangle.
 * - dimension: The width and height of the rectangle.
 * 
 * Constructor:
 * - CollisionRectangle(Vector2D position, Vector2D dimension): Initializes the rectangle with the given position and dimension.
 * 
 * Methods:
 * - getPosition(): Returns the position of the rectangle.
 * - setPosition(Vector2D position): Sets the position of the rectangle.
 * - getBoundingBox(): Returns the bounding box of the rectangle.
 * - getCenter(): Returns the center of the rectangle.
 * - getMaxDistanceFromCenter(): Returns the maximum distance from the center of the rectangle.
 * - haveCollided(CollisionShape otherShape): Checks if the rectangle has collided with another shape.
 * - equals(Object obj): Checks if this rectangle is equal to another object.
 * - hashCode(): Returns the hash code of the rectangle.
 */
public class CollisionRectangle extends Rectangle2D.Double implements CollisionShape{
    //upper left corner
    Vector2D position;//NOSONAR
    //width and height
    Vector2D dimension;//NOSONAR

    public CollisionRectangle(Vector2D position, Vector2D dimension){
        super(position.getX(),position.getY() ,dimension.getX(), dimension.getY());
        this.position = position;
        this.dimension = dimension; 
    }

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
    public Rectangle2D getBoundingBox() {
        return this;
    }

    @Override
    public Vector2D getCenter() {
        return position.add(dimension.stretch(0.5));
    }

    @Override
    public double getMaxDistanceFromCenter() {
        return dimension.stretch(0.5).length();
    }

    @Override
    public boolean haveCollided(CollisionShape otherShape) {
        return intersects(otherShape.getBoundingBox());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
