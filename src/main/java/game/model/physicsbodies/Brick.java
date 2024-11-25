package game.model.physicsbodies;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import game.model.Vector2D;

import game.model.shapes.CollisionRectangle;
import game.model.shapes.CollisionShape;
import game.view.Visualizer.Visual;
import game.view.Visualizer;
/**
 * The Brick class represents a fixed body in the game with specific bounciness properties.
 * It extends the FixBody class and provides methods to handle its position, dimension, and bouncing behavior.
 */
public class Brick extends FixBody{
    private double bounciness = 10;

    static final double LIMES = 10;

    public Brick(Vector2D position, Vector2D dimension){
        super(new CollisionRectangle(position, dimension),Color.ORANGE);
    }

    @Override
    public void setPosition(Vector2D position) {
        collisionShape.setPosition(position);
    }

    @Override
    public Vector2D getPosition() {
        return collisionShape.getPosition();
    }

    @Override
    public game.model.Vector2D getDimension() {
        return new Vector2D(collisionShape.getBoundingBox().getWidth(),collisionShape.getBoundingBox().getHeight());
    }

    /**
     * Calculates the vertical bounce vector for a collision between this object and another collision shape.
     *
     * @param a the collision shape that this object is colliding with
     * @param intersectionRectangle the rectangle representing the intersection area of the collision
     * @return a Vector2D representing the vertical bounce direction and magnitude
     */
    Vector2D bounceVertically(CollisionShape a, Rectangle2D intersectionRectangle){
        if(getPosition().getY() - a.getPosition().getY() < 0){
            return new Vector2D(0,intersectionRectangle.getHeight()).stretch(intersectionRectangle.getHeight()*bounciness);
        }else if(getPosition().getY() - a.getPosition().getY() > 0){
            return new Vector2D(0,-intersectionRectangle.getHeight()).stretch(intersectionRectangle.getHeight()*bounciness);
        }
        return new Vector2D(0,0);
    }

    /**
     * Calculates the horizontal bounce vector when this object collides with another object.
     *
     * @param a The collision shape of the other object.
     * @param intersectionRectangle The rectangle representing the intersection area of the collision.
     * @return A Vector2D representing the horizontal bounce direction and magnitude.
     */
    Vector2D bounceHorizontally(CollisionShape a, Rectangle2D intersectionRectangle){
        if(getPosition().getX() - a.getPosition().getX() < 0){
            return new Vector2D(intersectionRectangle.getWidth(),0).stretch(intersectionRectangle.getWidth()*bounciness);
        }else if(getPosition().getX() - a.getPosition().getX() > 0){
            return new Vector2D(-intersectionRectangle.getWidth(),0).stretch(intersectionRectangle.getWidth()*bounciness);
        }
        return new Vector2D(0,0);
    }

    /**
     * Calculates the bounce vector when this brick collides with another collision shape.
     * The method determines whether the bounce should be vertical or horizontal based on the
     * intersection dimensions of the bounding boxes of the two collision shapes.
     *
     * @param a The collision shape that this brick has collided with.
     * @return A Vector2D representing the bounce direction and magnitude. If the collision
     *         is near a corner (difference between width and height of intersection is less
     *         than a threshold), a zero vector is returned to prevent corner rockets.
     */
    @Override
    public Vector2D bounce(CollisionShape a) {
        Rectangle2D intersectionRectangle =  collisionShape.getBoundingBox().createIntersection(a.getBoundingBox());
        double difference = intersectionRectangle.getWidth() - intersectionRectangle.getHeight();
        //prevent corner rockets
        if(Math.abs(difference) < LIMES){
            return new Vector2D(0,0);
        }

        if(difference > 0){
            return bounceVertically(a, intersectionRectangle);
        }else{
            return bounceHorizontally(a, intersectionRectangle);
        }
    }

    /**
     * Returns the visual representation of this Brick object using the provided Visualizer.
     *
     * @param visualizer the Visualizer used to create the visual representation of this Brick
     * @return the Visual object representing this Brick
     */
    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}
