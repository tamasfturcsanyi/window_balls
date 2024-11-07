package game.model.physicksbodies;

import java.awt.Color;

import game.model.SimulationParameters;
import game.model.shapes.CollisonShape;

public abstract class FixBody extends PhysicksBody{
    protected FixBody(CollisonShape collisonShape, Color color){
        super(collisonShape,color);
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
