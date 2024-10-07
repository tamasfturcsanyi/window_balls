package ttm;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Circle implements Visual{
    double radius = 10;
    //coordinate of the circle's center
    Vector2D position;

    Color color;

    Ellipse2D.Double visual;

    Circle(double x,double y, double radius){
        this.position = new Vector2D(x,y);
        this.radius = radius;
        color = Color.BLACK;
        visual = new Ellipse2D.Double(0,0,0,0);
        updateVisuals(null);
    }
    Circle(double x,double y, double radius, Color color){
        this.position = new Vector2D(x,y);
        this.radius = radius;
        this.color = color;
        visual = new Ellipse2D.Double(0,0,0,0);
        updateVisuals(null);
    }

    public void updateVisuals(Rectangle windowBounds){
        if(windowBounds == null){
            windowBounds = new Rectangle(0,0,0,0);
        }
        visual.setFrame(position.getX()- windowBounds.getX(),position.getY()-windowBounds.getY(),2* radius, 2* radius);
    }

    public Color getColor(){
        return color;
    }

    public Ellipse2D.Double getShape(){
        return visual;
    }

}
