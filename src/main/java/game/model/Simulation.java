package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.model.physicsbodies.Ball;
import game.model.physicsbodies.Brick;
import game.model.physicsbodies.PhysicsBody;
import game.model.physicsbodies.Pole;
import game.model.physicsbodies.Wall;
import game.model.serialization.SimulationSerializer;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * The Simulation class represents a physics simulation environment.
 * It manages the simulation parameters, physics bodies, and walls within a defined window bounds.
 * The class provides methods to add and remove physics bodies, update the simulation state,
 * handle collisions, and set various simulation parameters.
 */
public class Simulation{
    String title = "Simulation";

    SimulationParameters params = new SimulationParameters();

    List<PhysicsBody> bodies = new ArrayList<>();

    Wall[] walls = new Wall[4];

    java.awt.Rectangle windowBounds;

    /**
     * Constructs a new Simulation object with predefined window bounds and initializes the walls.
     * The window bounds are set to a rectangle with the specified dimensions.
     */
    public Simulation(){
        windowBounds = new Rectangle(500, 200, 700, 500);
        wallInit();
    }

    /**
     * Constructs a new Simulation with the specified title and window bounds.
     *
     * @param title the title of the simulation
     * @param windowBounds the bounds of the window as a Rectangle
     */
    public Simulation(String title,Rectangle windowBounds){
        this.title = title;
        this.windowBounds = windowBounds;
        wallInit();
    }

    /**
     * Initializes the walls of the simulation with predefined directions and sizes.
     * The walls are set to the NORTH, EAST, SOUTH, and WEST directions with a size of 50000
     * and are bounded by the windowBounds.
     */
    public void wallInit(){
        walls[0] = new Wall(Wall.Direction.NORTH,50000,windowBounds);
        walls[1] = new Wall(Wall.Direction.EAST,50000,windowBounds);
        walls[2] = new Wall(Wall.Direction.SOUTH,50000,windowBounds);
        walls[3] = new Wall(Wall.Direction.WEST,50000,windowBounds);
    }

    public void addBody(PhysicsBody body){
        bodies.add(body);
    }

    public void removeBody(PhysicsBody body){
        bodies.remove(body);
    }

    /**
     * Checks for collisions between the given physics body and the walls.
     * If a collision is detected, the physics body will collide with the wall
     * and be marked as intersecting.
     *
     * @param physicsBody the physics body to check for collisions with the walls
     */
    void bounceOfWalls(PhysicsBody physicsBody){
        for( Wall wall : walls){
            if(physicsBody.getCollisionShape().haveCollided(wall.getCollisionShape())){
                physicsBody.collide(wall);
                physicsBody.setIntersecting(true);
            }
        }
    }

    /**
     * Checks for collisions between the given physics body and other bodies in the simulation.
     * If a collision is detected, it handles the collision and marks the body as intersecting.
     *
     * @param physicsBody the physics body to check for collisions
     */
    void bounceOfOtherBodies(PhysicsBody physicsBody){
        for (PhysicsBody otherBody : bodies) {
            if(physicsBody != otherBody && physicsBody.getCollisionShape().haveCollided(otherBody.getCollisionShape())){
                physicsBody.collide(otherBody);
                physicsBody.setIntersecting(true);
            }
        }
    }

    /**
     * Updates the state of all physics bodies in the simulation.
     * 
     * This method iterates through all physics bodies, resets their intersecting state,
     * and applies physics updates to them. If a physics body is not fixed, it will also
     * handle bouncing off other bodies and walls.
     */
    public void update(){
        for (PhysicsBody physicsBody : bodies) {
            physicsBody.setIntersecting(false);
            if(!physicsBody.getFix()){
                bounceOfOtherBodies(physicsBody);
                bounceOfWalls(physicsBody);
            }
            physicsBody.physicsUpdate(params);
        }
    }

    public java.awt.Rectangle getWindowBounds() {
        return windowBounds;
    }

    public void setWindowBounds(java.awt.Rectangle windowBounds) {
        this.windowBounds = windowBounds;
        for (Wall wall : walls) {
            wall.update(windowBounds);
        }
    }

    public List<PhysicsBody> getphysicsBodies(){
        return bodies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SimulationParameters getParams() {
        return params;
    }

    public void setParams(SimulationParameters params) {
        this.params = params;
    }
    
    public boolean isOnTheGround(PhysicsBody body){
        return walls[2].isIntersectingWith(body);
    }

    /**
     * Resets the delta time for all physics bodies in the simulation.
     * This method iterates through the list of physics bodies and calls
     * their resetDeltaTime method to ensure that their delta time values
     * are reset.
     */
    public void resetDeltaTime(){
        for (PhysicsBody physicsBody : bodies) {
            physicsBody.resetDeltaTime();
        }
    }

    public void setSimulationSpeed(double speed){
        params.simulationSpeed = speed;
    }

    /**
     * Selects the first PhysicsBody at the given point and deselects all others.
     * 
     * This method iterates through all PhysicsBody objects in the 'bodies' list,
     * deselecting each one. It then checks if the given point is within the 
     * bounding box of any PhysicsBody's collision shape. If a match is found, 
     * that PhysicsBody is selected and returned.
     * 
     * @param p the point at which to select a PhysicsBody
     * @return the selected PhysicsBody if one is found at the given point, 
     *         otherwise null
     */
    public PhysicsBody selectBodyAt(Point p){
        for (PhysicsBody physicsBody : bodies) {
            physicsBody.setSelected(false);
        }
        for (PhysicsBody physicsBody : bodies) {
            if(physicsBody.getCollisionShape().getBoundingBox().contains(p)){
                physicsBody.setSelected(true);
                return physicsBody;
            }
        }
        return null;
    }

    /**
     * Sets up the simulation with a preset configuration for the Volley class.
     * 
     * The preset includes:
     * - Title set to "Volley"
     * - Simulation parameters with specific gravity, air resistance, and other properties
     * - Window bounds set to 1024x768 pixels
     * - Adds a brick body at a specific position
     * - Adds a pole body at a specific position with a specified color
     */
    public void volleyPreset(){
        title = "Volley";

        params = new SimulationParameters(new Vector2D(0,10),1,0.5,200,0.001,false,Color.WHITE);

        windowBounds = new Rectangle(0, 0, 1024, 768);

        addBody(new Brick(new Vector2D(windowBounds.width/2.0 - 15, 300), new Vector2D(30,500)));
        addBody(new Pole(new Vector2D(windowBounds.width/2.0,300),15,Color.orange));
    }

    /**
     * Sets up the simulation with a preset menu configuration.
     * Initializes the simulation parameters and adds various bodies (bricks, poles, and balls) to the simulation.
     * The balls are added with random colors and varying sizes and positions.
     * Finally, the current state of the simulation is saved using the SimulationSerializer.
     */
    public void menuPreset(){
        Random rng = new Random();//NOSONAR

        double windowX = windowBounds.getX();
        double windowY = windowBounds.getY();
        title = "MenuPreset";


        params = new SimulationParameters(new Vector2D(0,10), 1, 0.6, 200,0.001,true,new Color(100, 100, 255));

        addBody(new Brick(new Vector2D(300, 400), new Vector2D(10, 500)));

        addBody(new Pole(new Vector2D(200, 200), 20, Color.black));
        for(int i = 0; i < 5;++i){
            addBody(new Ball(new Vector2D(windowX+20 + 50*i,windowY + 50),(20 + 5*i), new Color(rng.nextInt(0,256*256*256)), 0.5,(1.0 + i)));
        }

        SimulationSerializer.saveWorld(this);
    }
}