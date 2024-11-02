package game.engine;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Rectangle;

public class PhysicksWorld{
    public static final double GRAVITY = 10;
    public static final double SPEED = 10;
    public static final double BONCINESS = 0.5;
    public static final double ENERGY_LEFTOVER = 0.999998;
    public static final double SPEED_LIMIT = 500;

    String title = "Simulation";

    List<Actor> bodies = new ArrayList<>();
    java.awt.Rectangle windowBounds = new Rectangle(0, 0, 500, 400);


    void wallInit(){
        Wall[] walls = new Wall[4];

        walls[0] = new Wall(Direction.NORTH,50000);
        walls[1] = new Wall(Direction.EAST,50000);
        walls[2] = new Wall(Direction.SOUTH,50000);
        walls[3] = new Wall(Direction.WEST,50000);

        for (Wall wall : walls) {
            addBody(wall);
        }
    }

    public void addBody(Actor b){
        bodies.add(b);
    }

    public void update(){
        for (Actor physicksBody : bodies) {
            if (!physicksBody.fix){
                physicksBody.physicksUpdate(physicksBody.getPos());
            }
            for (Actor otherBody : bodies) {
                if ( otherBody != physicksBody){
                    if(physicksBody.getShape().intersects(otherBody.getShape().getBounds2D())){
                        collide( physicksBody,otherBody);
                    }
                }
            }
            
        }
    }

    void preset1(){
        for(int i = 0; i < 10;++i){
            Ball ball = new Ball(20+i * 10, 200,10,1,false,1,Color.BLUE);
            addBody(ball);
        }

        Brick brick = new Brick(new Vector2D(300,500),new Vector2D(500,15));

        addBody(brick);
    }

    void collide(Actor a, Actor b){
        a.addForce(b.bounce(a).stretch(BONCINESS));
        b.addForce(a.bounce(b).stretch(BONCINESS));
    }

    public String getTitle() {
        return title;
    }

    public void setWindowBounds(java.awt.Rectangle windowBounds) {
        this.windowBounds = windowBounds;
    }
}