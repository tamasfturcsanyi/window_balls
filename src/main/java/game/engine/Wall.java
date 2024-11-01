package game.engine;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public class Wall extends Actor{
    Rectangle body;

    Direction direction;

    double thickness;

    double offsetSouth = 50;
    double offsetEast = 10;

    double bonus = 50000;
    
    

    public Wall(Direction d,double thickness){
        super(1,true);
        this.thickness = thickness;
        direction = d;
        body = new Rectangle(new Vector2D(0,0),new Vector2D(0,0), Color.BLACK);
    }

    @Override
    public void updateVisuals(java.awt.Rectangle windowBounds) {
        switch (direction){
            case NORTH:
                body.position.x =  windowBounds.getX() - bonus;
                body.position.y = windowBounds.getY() - thickness;

                body.dimension.x = windowBounds.getWidth() + bonus;
                body.dimension.y = thickness;
                break;
            case EAST:
                body.position.x =  windowBounds.getX()+ windowBounds.getWidth() - offsetEast;
                body.position.y = windowBounds.getY() -bonus;

                body.dimension.x = thickness;
                body.dimension.y = windowBounds.getHeight() + bonus;
                break;
            case SOUTH:
                body.position.x =  windowBounds.getX() - bonus;
                body.position.y = windowBounds.getY() + windowBounds.getHeight() - offsetSouth;

                body.dimension.x = windowBounds.getWidth() + bonus;
                body.dimension.y = thickness;
                break;
            case WEST:
                body.position.x =  windowBounds.getX() - thickness;
                body.position.y = windowBounds.getY() - bonus;

                body.dimension.x = thickness;
                body.dimension.y = windowBounds.getHeight() + bonus;
                break;
            default:
                break;
        }        
        body.updateVisuals(windowBounds);
    }


    @Override
    public Vector2D bounce(Actor a) {
        Rectangle2D rect =  getShape().getBounds2D().createIntersection(a.getShape().getBounds2D());
        switch (direction) {
            case NORTH:
                return new Vector2D(0,rect.getHeight()).stretch(rect.getHeight());
            case SOUTH:
                return new Vector2D(0,-rect.getHeight()).stretch(rect.getHeight());
            case EAST:
                return new Vector2D(-rect.getWidth(),0).stretch(rect.getWidth());
            case WEST:
                return new Vector2D(rect.getWidth(),0).stretch(rect.getWidth());
            default:
                return new Vector2D(0,0);
        }
    }

    @Override
    public Vector2D getCenter() {
        return getPos();
    }

    @Override
    Vector2D getPos() {
        return body.position;
    }

    @Override
    void setPos(Vector2D newPos) {
        body.position = newPos;
    }

    @Override
    public Color getColor() {
        return new Color(0,0,0,0);
    }

    @Override
    public Shape getShape() {
        return body.getShape();
    }

    public double getBonus() {
        return bonus;
    }

    public double getOffsetEast() {
        return offsetEast;
    }
    public double getOffsetSouth() {
        return offsetSouth;
    }
        

    public Rectangle getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }
    public double getThickness() {
        return thickness;
    }
}
