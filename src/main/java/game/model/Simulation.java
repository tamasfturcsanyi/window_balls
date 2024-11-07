package game.model;

import java.util.ArrayList;
import java.util.List;

import game.model.physicksbodies.PhysicksBody;
import game.model.physicksbodies.Wall;

import java.awt.Rectangle;

public class Simulation{
    SimulationParameters params = new SimulationParameters();

    List<PhysicksBody> bodies = new ArrayList<>();

    java.awt.Rectangle windowBounds = new Rectangle(0, 0, 500, 400);

    void wallInit(){
        Wall[] walls = new Wall[4];

        walls[0] = new Wall(Wall.Direction.NORTH,50000,windowBounds);
        walls[1] = new Wall(Wall.Direction.EAST,50000,windowBounds);
        walls[2] = new Wall(Wall.Direction.SOUTH,50000,windowBounds);
        walls[3] = new Wall(Wall.Direction.WEST,50000,windowBounds);

        for (Wall wall : walls) {
            addBody(wall);
        }
    }

    public void addBody(PhysicksBody body){
        bodies.add(body);
    }

    public void update(){
        for (PhysicksBody physicksBody : bodies) {
            physicksBody.physicksUpdate(params);
            for (PhysicksBody otherBody : bodies) {
                if(physicksBody != otherBody && physicksBody.getCollisonShape().haveCollided(otherBody.getCollisonShape())){
                    physicksBody.collide(otherBody);
                }
            }
        }
    }

    public java.awt.Rectangle getWindowBounds() {
        return windowBounds;
    }

    public void setWindowBounds(java.awt.Rectangle windowBounds) {
        this.windowBounds = windowBounds;
    }

    public List<PhysicksBody> getPhysicksBodies(){
        return bodies;
    }
/*

    void preset1(){
        title = "Preset_1";

        for(int i = 0; i < 10;++i){
            Ball ball = new Ball((20 + i * 10), 200,10,1,false,1,Color.BLUE);
            addBody(ball);
        }

        //Brick brick = new Brick(new Vector2D(300,500),new Vector2D(500,15));
        Brick brick2 = new Brick(new Vector2D(300,500),new Vector2D(15,500));

        //addBody(brick);
        addBody(brick2);

        wallInit();
    }

    void preset2(){
        title = "Preset_2";

        params = new SimulationParameters(10,10,0.9999,500); 

        for(int i = 0; i < 10;++i){
            Ball ball = new Ball((20 + i * 30), 200,10,1,false,1,Color.BLUE);
            addBody(ball);
        }

        for(int i = 0; i < 10;++i){
            Ball ball = new Ball((20 + i * 30), 300,15,1,false,1,Color.RED);
            addBody(ball);
        }

        Brick brick = new Brick(new Vector2D(300,500),new Vector2D(500,30));

        addBody(brick);

        wallInit();
    }

    void preset4(){
        params = new SimulationParameters(10,10,0.999,500); 
        windowBounds = new Rectangle(500, 300);

        counter.start();

        for(int i = 0; i < 1000; ++i){
            addBody(new Ball(100 + i * 10,100,10,1,false,0.01,new Color(0, 255, 0)));
        }

        wallInit();
    }

    void preset3(){

        params = new SimulationParameters(10,10,0.9999,500); 


        title = "Preset_3";
        int off = 0;

        counter.start();

        for(int i = 0; i < 3;++i){
            Ball ball = new Ball(200,100,10,1,false,0.01,new Color(0, i*90, 0));
            addBody(ball);
        }
        

        windowBounds = new Rectangle(500, 300);
        for(int j = 0; j < 10; ++j){
            for(int i = 0; i < 10;++i){
                if(j % 2 == 0){
                    off = 0;

                }else{
                    off = 25;
                }
                Ball ball = new Ball((120 + i * 50 + off), 100 + j * 50,10,1,true,1,Color.BLUE);
                addBody(ball);
            }
        }
    

        Brick brick = new Brick(new Vector2D(300,500),new Vector2D(500,15));

        addBody(brick);

        wallInit();
    }*/
}