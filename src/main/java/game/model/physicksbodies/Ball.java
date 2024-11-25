package game.model.physicksbodies;

import java.awt.Color;

import game.model.shapes.CollisionCircle;
import game.model.shapes.CollisionShape;
import game.view.Visualizer.Visual;
import game.view.Visualizer;
import game.model.SimulationParameters;
import game.model.Vector2D;

/**
 * The Ball class represents a mobile body in the simulation with properties such as bounciness and mass.
 * It extends the MobileBody class and provides specific implementations for calculating forces, velocity, 
 * and position updates, as well as handling collisions.
 */
public class Ball extends MobileBody{
    private double bounciness = 1;
    private static final double SPEED_LIMIT_STRETCH_FACTOR = 0.9;

    private boolean previousIntersecting = false;

    /**
     * Constructs a new Ball object with the specified initial center, radius, color, bounciness, and mass.
     *
     * @param initialCenter the initial center position of the ball as a Vector2D object
     * @param initialRadius the initial radius of the ball
     * @param color the color of the ball
     * @param bounciness the bounciness coefficient of the ball
     * @param mass the mass of the ball
     */
    public Ball(Vector2D initialCenter,double initialRadius,Color color,double bounciness, double mass){
        super(new CollisionCircle(initialCenter.add(new Vector2D(-initialRadius,-initialRadius)), initialRadius),mass,color);
        this.bounciness = bounciness;
    }

    @Override
    public Vector2D getPosition() {
        return collisionShape.getPosition();
    }

    public Vector2D getCenter(){
        return collisionShape.getCenter();
    }

    @Override
    public void setPosition(Vector2D position) {
        collisionShape.setPosition(position);
    }

    @Override
    public Vector2D getDimension() {
        return new Vector2D(collisionShape.getBoundingBox().getWidth(),collisionShape.getBoundingBox().getHeight());
    }

    /**
     * Calculates the forces acting on the ball.
     * 
     * This method resets the current force to zero, applies gravity based on the 
     * simulation parameters and the mass of the ball, and adds any external forces 
     * acting on the ball. After applying the external forces, it resets the 
     * external forces to zero.
     * 
     * @param params The simulation parameters containing the gravity vector.
     */
    @Override
    void calculateForces(SimulationParameters params){

        force.set(0, 0);
        //apply gravity
        force = force.add(params.getGravity().stretch(mass));

        force = force.add(externalForces);

        externalForces.set(0, 0);
    }

    /**
     * Calculates the new velocity of the ball based on the applied forces, 
     * simulation parameters, and time delta.
     *
     * @param delta The time delta for the simulation step.
     * @param params The simulation parameters containing energy loss factors and other settings.
     */
    @Override
    void calculateNewVelocity(double delta, SimulationParameters params){
        //apply mass
        Vector2D acceleration = force.stretch(1/mass);

        //apply acceleration
        velocity = velocity.add(acceleration.stretch(delta));

        //bounceLoss
        if(previousIntersecting && !intersecting){
            velocity = velocity.stretch(params.getBounceEnergyRemaining());
        }

        //generalEnergyLoss
        velocity = velocity.stretch(1 - params.getGeneralEnergyLoss());
        

        //speed limit
        if(velocity.length() > params.getSpeedLimit()){
            velocity = velocity.stretch(SPEED_LIMIT_STRETCH_FACTOR);
        }
    }

    /**
     * Handles the collision with another physics body.
     * Applies a force to this body based on the bounce effect from the collision.
     *
     * @param otherBody the other physics body involved in the collision
     */
    @Override
    public void collide(PhysicksBody otherBody) {
        addForce(otherBody.bounce(collisionShape).stretch(1  + bounciness));
    }

    /**
     * Calculates the new position of the ball based on the given time delta.
     * The new position is determined by adding the product of the velocity and delta to the current position.
     *
     * @param delta the time interval over which to calculate the new position
     */
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
        delta = delta * 10 * params.getSimulationSpeed();

        
        
        calculateForces(params);
        calculateNewVelocity(delta, params);
        calculateNewPosition(delta);
        
        previousIntersecting = intersecting;
        previousTime = currentTime;
    }

    /**
     * Calculates the bounce vector when this object collides with another collision shape.
     *
     * @param otherCollisionShape The other collision shape involved in the collision.
     * @return A vector representing the direction and magnitude of the bounce.
     */
    @Override
    public Vector2D bounce(CollisionShape otherCollisionShape) {
        double maxDistanceFromCenters = otherCollisionShape.getMaxDistanceFromCenter() + collisionShape.getMaxDistanceFromCenter();
        Vector2D diff = collisionShape.getCenter().diff(otherCollisionShape.getCenter());
        double overlapDistance = maxDistanceFromCenters-diff.length();
        if (overlapDistance < 0){
            overlapDistance = 0;
        }
        return diff.stretch(overlapDistance);
    }

    /**
     * Returns the visual representation of this Ball object using the provided Visualizer.
     *
     * @param visualizer the Visualizer used to create the visual representation
     * @return the visual representation of this Ball object
     */
    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}
