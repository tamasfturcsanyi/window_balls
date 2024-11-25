package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.model.physicksbodies.Ball;
import game.model.physicksbodies.Brick;
import game.model.physicksbodies.PhysicksBody;
import game.model.physicksbodies.Pole;
import game.model.physicksbodies.Wall;
import game.model.serialization.SimulationSerializer;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Simulation{
    String title = "Simulation";

    SimulationParameters params = new SimulationParameters();

    List<PhysicksBody> bodies = new ArrayList<>();

    Wall[] walls = new Wall[4];

    java.awt.Rectangle windowBounds;

    public Simulation(){
        windowBounds = new Rectangle(500, 200, 700, 500);
        wallInit();
    }

    public Simulation(String title,Rectangle windowBounds){
        this.title = title;
        this.windowBounds = windowBounds;
        wallInit();
    }

    public void wallInit(){
        walls[0] = new Wall(Wall.Direction.NORTH,50000,windowBounds);
        walls[1] = new Wall(Wall.Direction.EAST,50000,windowBounds);
        walls[2] = new Wall(Wall.Direction.SOUTH,50000,windowBounds);
        walls[3] = new Wall(Wall.Direction.WEST,50000,windowBounds);
    }

    public void addBody(PhysicksBody body){
        bodies.add(body);
    }

    public void removeBody(PhysicksBody body){
        bodies.remove(body);
    }

    void bounceOfWalls(PhysicksBody physicksBody){
        for( Wall wall : walls){
            if(physicksBody.getCollisionShape().haveCollided(wall.getCollisionShape())){
                physicksBody.collide(wall);
                physicksBody.setIntersecting(true);
            }
        }
    }

    void bounceOfOtherBodies(PhysicksBody physicksBody){
        for (PhysicksBody otherBody : bodies) {
            if(physicksBody != otherBody && physicksBody.getCollisionShape().haveCollided(otherBody.getCollisionShape())){
                physicksBody.collide(otherBody);
                physicksBody.setIntersecting(true);
            }
        }
    }

    //model update cycle
    public void update(){
        for (PhysicksBody physicksBody : bodies) {
            physicksBody.setIntersecting(false);
            if(!physicksBody.getFix()){
                bounceOfOtherBodies(physicksBody);
                bounceOfWalls(physicksBody);
            }
            physicksBody.physicksUpdate(params);
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

    public List<PhysicksBody> getPhysicksBodies(){
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
    
    public boolean isOnTheGround(PhysicksBody body){
        return walls[2].isIntersectingWith(body);
    }

    public void resetDeltaTime(){
        for (PhysicksBody physicksBody : bodies) {
            physicksBody.stop();
        }
    }

    public void setSimulationSpeed(double speed){
        params.simulationSpeed = speed;
    }

    public PhysicksBody selectBodyAt(Point p){
        for (PhysicksBody physicksBody : bodies) {
            physicksBody.setSelected(false);
        }
        for (PhysicksBody physicksBody : bodies) {
            if(physicksBody.getCollisionShape().getBoundingBox().contains(p)){
                physicksBody.setSelected(true);
                return physicksBody;
            }
        }
        return null;
    }

    public void volleyPreset(){
        title = "Volley";

        params = new SimulationParameters(new Vector2D(0,10),1,0.5,200,0.001,false,Color.WHITE);

        windowBounds = new Rectangle(0, 0, 1024, 768);

        addBody(new Brick(new Vector2D(windowBounds.width/2.0 - 15, 300), new Vector2D(30,500)));
        addBody(new Pole(new Vector2D(windowBounds.width/2.0,300),15,Color.orange));
    }

    public void MenuPreset(){//NOSONAR
        Random rng = new Random();

        double windowX = windowBounds.getX();
        double windowY = windowBounds.getY();
        title = "MenuPreset";


        params = new SimulationParameters(new Vector2D(0,10), 1, 0.6, 200,0.001,true,new Color(100, 100, 255));

        addBody(new Brick(new Vector2D(300, 400), new Vector2D(10, 500)));

        addBody(new Pole(new Vector2D(200, 200), 20, Color.black));
        for(int i = 0; i < 5;++i){
            addBody(new Ball(new Vector2D(windowX+20 + 50*i,windowY + 50), (double)(20 + 5*i), new Color(rng.nextInt(0,256*256*256)), 0.5, 1.0 + i));
        }

        SimulationSerializer.saveWorld(this);
    }
}