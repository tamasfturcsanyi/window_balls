package game.model.physicksbodies;

import java.awt.Color;
import game.model.shapes.CollisonShape;
import game.model.Vector2D;
import game.model.SimulationParameters;

public abstract class MobileBody extends PhysicksBody {
    protected MobileBody(CollisonShape collisonShape,double mass, Color color){
        super(collisonShape,color);
        this.mass = mass;

        Vector2D nulla = new Vector2D(0,0);
        force = nulla;
        externalForces = nulla;
        velocity = nulla;
        previousTime = 0;
    }

    long previousTime;

    //pos is modified by velocity every frame
    Vector2D velocity;

    //velocity is modified by force/weight every frame
    Vector2D force;

    Vector2D externalForces;

    double mass = 1;

    public Vector2D getExternalForce(){
        return externalForces;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void addForce(Vector2D f){
        externalForces = externalForces.add(f);
    }

    @Override
    public boolean getFix() {
        return false;
    }

    abstract void calculateForces(SimulationParameters params);

    abstract void calculateNewVelocity(double delta, SimulationParameters params);

    abstract void calculateNewPosition(double delta);
}
