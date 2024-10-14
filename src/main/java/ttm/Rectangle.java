package ttm;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
public class Rectangle implements Visual {
    Vector2D position;
    Vector2D dimension;

    Color color;

    Rectangle2D.Double visual;

    public Rectangle(Vector2D position, Vector2D dimension, Color color){
        this.position = position;
        this.dimension = dimension; 
        this.color = color;

        visual = new Rectangle2D.Double(position.getX(),position.getY() ,dimension.getX(), dimension.getY());
    }

    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Shape getShape() {
        return visual;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void updateVisuals(java.awt.Rectangle windowBounds) {
        visual.setRect(position.x - windowBounds.x, position.y - windowBounds.y,dimension.x,dimension.y);
    }
}
