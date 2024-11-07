package game.model.physicksbodies;

import java.awt.Color;

import game.model.SimulationParameters;
import game.model.shapes.CollisionShape;

public abstract class FixBody extends PhysicksBody{
    protected FixBody(CollisionShape collisionShape, Color color){
        super(collisionShape,color);
    }

    @Override
    public void physicksUpdate(SimulationParameters params) {
        //fixBodies don't move
    }

    @Override
    public void collide(PhysicksBody otherBody) {
    }

    @Override
    public boolean getFix() {
        return true;
    }
}
