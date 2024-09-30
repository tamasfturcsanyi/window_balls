package ttm;

import java.awt.geom.Point2D;


public abstract class PhysicksBody {



    long previousTime;

    //pos is modified by velocity every frame
    Point2D velocity;

    //velocity is modified by force/weight every frame
    Point2D force;

    double mass = 1;

    PhysicksBody(){
        Point2D nulla = new Point2D.Double(0,0);
        force = nulla;
        velocity = nulla;
        previousTime = System.nanoTime();
    }

    abstract Point2D getPos(); 
    abstract void setPos(Point2D newPos);

    void calculateForces(){
        force = new Point2D.Double(0,0);
        //apply gravity
        force = new Point2D.Double(force.getX(), force.getY() + PhysicksWorld.GRAVITY);
    }

    void calculateNewVelocity(double delta){
        Point2D acceleration = new Point2D.Double(force.getX()/mass,force.getY()/mass);
        velocity = new Point2D.Double(velocity.getX() + acceleration.getX() * delta,velocity.getY() + acceleration.getY() * delta);
    }

    void calculateNewPosition(double delta, Point2D oldPos){
        this.setPos( new Point2D.Double(oldPos.getX() + velocity.getX() * delta,oldPos.getY() + velocity.getY() * delta));
        
    }
    
    //calculates new position, applies forces, moves Body
    void physicksUpdate(Point2D oldPos){
        long currentTime = System.nanoTime();
        long nanoDelta = System.nanoTime() - previousTime;
        double delta = nanoDelta * 0.000000001 * PhysicksWorld.SPEED;
        
        calculateForces();
        calculateNewVelocity(delta);
        calculateNewPosition(delta, oldPos);
        

        previousTime = currentTime;
    }

    void addForce(Point2D f){
        force = new Point2D.Double(force.getX()+f.getX(),force.getY()+f.getY());
    }

    
}
