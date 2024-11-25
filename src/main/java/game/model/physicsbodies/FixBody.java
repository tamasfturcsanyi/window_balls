package game.model.physicsbodies;

import java.awt.Color;

import game.model.SimulationParameters;
import game.model.shapes.CollisionShape;

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