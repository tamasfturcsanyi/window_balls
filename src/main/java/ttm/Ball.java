package ttm;

import java.awt.Rectangle;
import java.awt.Shape;

public class Ball extends Actor{ 
    
    Circle body;

    
    double bounciness = 1; 


    public Ball(double x, double y,double radius){
        super(1,false);
        bounciness = 1;
        body = new Circle(x,y,radius);
    }

    public Ball(double x, double y, double radius,double mass, boolean fix, double bounciness){
        super(mass,fix);

        body = new Circle(x,y,radius);
        this.bounciness = bounciness; 
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
}
