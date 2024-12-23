package game.model.physicsbodies;

import java.awt.Color;
import game.model.shapes.CollisionShape;
import game.model.Vector2D;
import game.model.SimulationParameters;

/**
 * The MobileBody class represents a physical body that can move within a simulation.
 * It extends the PhysicsBody class and adds properties and methods related to motion,
 * such as velocity, force, and mass.
 */
public abstract class MobileBody extends PhysicsBody {
    long previousTime;

    //pos is modified by velocity every frame
    Vector2D velocity;

    //velocity is modified by force/weight every frame
    Vector2D force;

    Vector2D externalForces;

    double mass = 1;

    protected MobileBody(CollisionShape collisionShape,double mass, Color color){
        super(collisionShape,color);
        this.mass = mass;

        Vector2D nulla = new Vector2D(0,0);
        force = nulla;
        externalForces = nulla;
        velocity = nulla;
        previousTime = 0;
    }

    public Vector2D getExternalForce(){
        return externalForces;
    }

    public Vector2D getForce(){
        return force;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
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

    @Override
    public void resetDeltaTime() {
        previousTime = 0;
    }

    abstract void calculateForces(SimulationParameters params);

    abstract void calculateNewVelocity(double delta, SimulationParameters params);

    abstract void calculateNewPosition(double delta);
}
