package ttm;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Ball extends PhysicksBody implements Visual{ 
    
    Circle body;

    
    double bounciness = 1; 


    Ball(double x, double y, double radius, double bounciness){
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
    Point2D getPos(){
        return body.position;
    }

    @Override
    void setPos(Point2D newPos){
        body.position = newPos;
    }
}
