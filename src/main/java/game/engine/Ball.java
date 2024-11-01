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
        return getPos();
    }

    @Override
    public Vector2D bounce(Actor a) {
        double maxDistance = a.getShape().getBounds2D().getWidth()/2+getShape().getBounds2D().getWidth()/2 ;
        Vector2D diff = getPos().diff(a.getPos());
        double clip = maxDistance-diff.length();
        if (clip < 0){
            clip = 0;
        }
        return diff.stretch(clip);
    }
}
