package ttm;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle implements Visual{
    double radius = 10;
    //coordinate of the circle's center
    Point2D position;

    Ellipse2D.Double visual;

    Circle(double x,double y, double radius){
        this.position = new Point2D.Double(x,y);
        this.radius = radius;
        visual = new Ellipse2D.Double(0,0,0,0);
        updateVisuals(null);
    }

    public void updateVisuals(Rectangle windowBounds){
        if(windowBounds == null){
            windowBounds = new Rectangle(0,0,0,0);
        }
        visual.setFrame(position.getX()- windowBounds.getX(),position.getY()-windowBounds.getY(),2* radius, 2* radius);
    }

    

    public Ellipse2D.Double getShape(){
        return visual;
    }

}
