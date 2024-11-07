package game.model.physicksbodies;

import java.awt.Color;

import game.model.shapes.CollisonCircle;
import game.model.shapes.CollisonShape;
import game.view.Visual;
import game.view.Visualizer;
import game.model.SimulationParameters;
import game.model.Vector2D;

public class Ball extends MobileBody{
    double bounciness = 1; 

    boolean bounced = false;

    public Ball(Vector2D center,double radius,Color color,double bounciness, double mass){
        super(new CollisonCircle(center.add(new Vector2D(-radius,-radius)), radius),mass,color);
        this.bounciness = bounciness;
    }

    @Override
    public Vector2D getPosition() {
        return collisonShape.getPosition();
    }

    @Override
    public void setPosition(Vector2D position) {
        collisonShape.setPosition(position);
    }

    @Override
    public Vector2D getDimension() {
        return new Vector2D(collisonShape.getBoundingBox().getWidth(),collisonShape.getBoundingBox().getHeight());
    }
    @Override
    void calculateForces(SimulationParameters params){
        Vector2D oldForce = force;

        force = new Vector2D(0,0);
        //apply gravity
        force = force.add(new Vector2D(0, params.getGravity() * mass));

        force = force.add(externalForces);

        bounced = (oldForce.diff(force).length() > 10);

        externalForces = new Vector2D(0,0);
    }

    @Override
    void calculateNewVelocity(double delta, SimulationParameters params){
        //apply mass
        Vector2D acceleration = force.stretch(1/mass);

        //apply acceleration
        velocity = velocity.add(acceleration.stretch(delta));

        //bounceLoss
        if(bounced){
            velocity = velocity.stretch(params.getBounceEnergyRemaining());
        }

        //generalEnergyLoss
        velocity = velocity.stretch(1 - params.getGeneralEnergyLoss());
        

        //speed limit
        if(velocity.length() > params.getSpeedLimit()){
            velocity = velocity.stretch(0.9);
        }
    }

    @Override
    public void collide(PhysicksBody otherBody) {
        addForce(otherBody.bounce(collisonShape).stretch(1  + bounciness));
    }

    @Override
    void calculateNewPosition(double delta){
        setPosition(getPosition().add(velocity.stretch(delta)));
    }
    
    //calculates new position, applies forces, moves Body
    @Override
    public void physicksUpdate(SimulationParameters params){
        long currentTime = System.nanoTime();

        //if theres no previous time: skip
        if(previousTime == 0){
            previousTime = currentTime;
            return;
        }
        long nanoDelta = currentTime - previousTime;

        //convert to seconds
        double delta = nanoDelta * 0.000000001;

        //apply simulationSpeed
        delta = delta * params.getSimulationSpeed();
        
        calculateForces(params);
        calculateNewVelocity(delta, params);
        calculateNewPosition(delta);
        
        previousTime = currentTime;
    }

    @Override
    public Vector2D bounce(CollisonShape otherCollisonShape) {
        double maxDistance = otherCollisonShape.getMaxDistanceFromCenter()+collisonShape.getMaxDistanceFromCenter();
        Vector2D diff = collisonShape.getCenter().diff(otherCollisonShape.getCenter());
        double clip = maxDistance-diff.length();
        if (clip < 0){
            clip = 0;
        }
        return diff.stretch(clip);
    }

    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }

    @Override
    public Color getColor() {
        if(bounced){
            return Color.RED;
        }

        return color;
    }
}
