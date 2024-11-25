package game.model.physicsbodies;

import java.awt.Color;

import game.model.SimulationParameters;
import game.model.shapes.CollisionShape;

/**
 * The FixBody class represents a fixed physics body that does not move.
 * It extends the PhysicsBody class and provides implementations for the
 * abstract methods defined in the PhysicsBody class.
 * 
 * <p>This class is intended to be used as a base class for all fixed
 * physics bodies in the simulation.</p>
 * 
 * @param collisionShape The shape of the collision boundary for this body.
 * @param color The color of the body.
 */
public abstract class FixBody extends PhysicsBody{
    protected FixBody(CollisionShape collisionShape, Color color){
        super(collisionShape,color);
    }

    @Override
    public void physicsUpdate(SimulationParameters params) {
        //fixBodies don't move
    }

    @Override
    public void collide(PhysicsBody otherBody) {
    }

    @Override
    public boolean getFix() {
        return true;
    }
}
