package game.model.physicsbodies;

import java.awt.Color;
import game.view.Visualizer.Visualizable;
import game.model.SimulationParameters;
import game.model.Vector2D;
import game.model.shapes.CollisionShape;

/**
 * The PhysicsBody class represents an abstract physical body in a simulation.
 * It implements the Visualizable interface and provides methods for handling
 * collision shapes, colors, visibility, selection, and intersection states.
 * Subclasses must implement methods for getting and setting position and dimension,
 * handling collisions, bouncing, and updating physics.
 */
public abstract class PhysicsBody implements Visualizable{
    CollisionShape collisionShape;

    Color color = Color.BLUE;

    protected boolean visible = true;

    protected boolean intersecting = false;

    protected boolean selected = false;

    /**
     * Constructs a PhysicsBody with the specified collision shape and color.
     *
     * @param collisionShape the shape used for collision detection
     * @param color the color of the physics body
     */
    PhysicsBody(CollisionShape collisionShape, Color color){
        this.collisionShape = collisionShape;
        this.color = color;
    }

    public abstract Vector2D getPosition();
    public abstract void setPosition(Vector2D position);

    public abstract Vector2D getDimension();

    /**
     * Handles the collision between this physics body and another physics body.
     *
     * @param otherBody the other physics body involved in the collision
     */
    public abstract void collide(PhysicsBody otherBody);

    /**
     * Calculates the bounce vector when this physics body collides with another collision shape.
     *
     * @param otherCollisionShape the collision shape that this physics body collides with
     * @return the resulting bounce vector as a Vector2D object
     */
    public abstract Vector2D bounce(CollisionShape otherCollisionShape);

    //calculates new position, applies forces, moves Body
    public abstract void physicsUpdate(SimulationParameters params);

    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    public abstract boolean getFix();

    public Color getColor() {
        return color;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setIntersecting(boolean intersecting) {
        this.intersecting = intersecting;
    }

    public boolean isIntersecting(){
        return intersecting;
    }

    /**
     * Checks if this PhysicsBody is intersecting with another PhysicsBody.
     *
     * @param otherBody the other PhysicsBody to check for intersection
     * @return true if the two PhysicsBodies are intersecting, false otherwise
     */
    public boolean isIntersectingWith(PhysicsBody otherBody){
        return (this != otherBody && this.getCollisionShape().haveCollided(otherBody.getCollisionShape()));
    }

    /**reset delta to 0, so that the next frame will be calculated correctly
     * only used in MobileBody
     */
    public void resetDeltaTime(){}
}
