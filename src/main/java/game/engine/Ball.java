package game.engine;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Color;

public class Ball extends Actor{ 
    
    Circle body;

    
    double bounciness = 1; 


    public Ball(double x, double y,double radius){
        super(1,false);
        bounciness = 1;
        body = new Circle(x,y,radius);
    }

    public Ball(double x, double y, double radius,double mass, boolean fix, double bounciness,Color color){
        super(mass,fix);

        body = new Circle(x,y,radius,color);
        this.bounciness = bounciness; 
    }

    @Override
    void calculateForces(SimulationParameters params){
        force = new Vector2D();
        //apply gravity
        force = force.add(new Vector2D(0, params.gravity * mass));

        force = force.add(externalForces);
        externalForces = new Vector2D(0,0);
    }

    @Override
    void calculateNewVelocity(double delta, SimulationParameters params){
        Vector2D acceleration = force.stretch(1/mass);

        //apply acceleration
        velocity = velocity.add(acceleration.stretch(delta));

        //friction
        velocity = velocity.stretch(params.energyLeftover);

        //speed limit
        if(velocity.length() > params.speedLimit){
            velocity = velocity.stretch(0.9);
        }

        
    }

    @Override
    void calculateNewPosition(double delta){
        this.setPos(getPos().add(velocity.stretch(delta)));
    }
    
    //calculates new position, applies forces, moves Body
    @Override
    void physicksUpdate(SimulationParameters params){
        long currentTime = System.nanoTime();

        //if theres no previous time: skip
        if(previousTime == 0){
            previousTime = currentTime;
            return;
        }
        long nanoDelta = currentTime - previousTime;

        //convert to seconds
        double delta = nanoDelta * 0.000000001 * params.speed;
        
        calculateForces(params);
        calculateNewVelocity(delta, params);
        calculateNewPosition(delta);
        

        previousTime = currentTime;
    }

    public Shape getShape(){
        return body.getShape();
    }

    public void updateVisuals(Rectangle windowBounds){
        body.updateVisuals(windowBounds);
    }

    @Override
    public Vector2D getPos(){
        return body.position;
    }

    @Override
    void setPos(Vector2D newPos){
        body.position = newPos;
    }

    void setColor(Color c){
        body.setColor(c);
    }

    @Override
    public Color getColor() {
        return body.getColor();
    }

    @Override
    public Vector2D getCenter() {
        return getPos().add(new Vector2D(body.radius,body.radius));
    }

    @Override
    public Vector2D bounce(Actor a) {
        double maxDistance = a.getShape().getBounds2D().getWidth()/2+getShape().getBounds2D().getWidth()/2 ;
        Vector2D diff = getPos().diff(a.getPos());
        double clip = maxDistance-diff.length();
        if (clip < 0){
            clip = 0;
        }
        return diff.stretch(clip * bounciness);
    }
}
