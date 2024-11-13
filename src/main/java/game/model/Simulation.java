package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.model.physicksbodies.Ball;
import game.model.physicksbodies.Brick;
import game.model.physicksbodies.PhysicksBody;
import game.model.physicksbodies.Pole;
import game.model.physicksbodies.Wall;

import java.awt.Color;
import java.awt.Rectangle;

public class Simulation{
    String title = "Simulation";

    SimulationParameters params = new SimulationParameters();

    List<PhysicksBody> bodies = new ArrayList<>();

    Wall[] walls = new Wall[4];

    java.awt.Rectangle windowBounds = new Rectangle(0, 0, 500, 400);

    public Simulation(){
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

    public void preset1(){//NOSONAR
        Random rng = new Random();

        title = "Preset_1";

        params = new SimulationParameters(10, 10, 0.6, 200,0.001);

        addBody(new Brick(new Vector2D(300, 400), new Vector2D(10, 500)));

        addBody(new Pole(new Vector2D(200, 200), 20, Color.black));
        for(int i = 0; i < 5;++i){
            addBody(new Ball(new Vector2D(10 + 10*i,10), 20 + 5*i, new Color(rng.nextInt(0,256*256*256)), 0.5, 1 + i));
        }
    }
}