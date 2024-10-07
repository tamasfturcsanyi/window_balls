package ttm;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Line implements Visual {
    Vector2D p1;
    Vector2D p2;

    Line2D visual;

    public Line(double p1X,double p1Y,double p2X,double p2Y){
        p1 = new Vector2D(p1X, p1Y);
        p2 = new Vector2D(p2X, p2Y);


        visual = new Line2D.Double(p1X,p1Y,p2X,p2Y);
    }

    public Line2D getShape(){
        return visual;
    }

    @Override
    public void updateVisuals(Rectangle windowBounds) {
        visual.setLine(p1.getX()- windowBounds.getX(),p1.getY()-windowBounds.getY(),p2.getX()- windowBounds.getX(),p2.getY()-windowBounds.getY());
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

}
