package game.model.physicksbodies;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import game.model.shapes.CollisionRectangle;
import game.model.shapes.CollisionShape;
import game.view.Visualizer.Visual;
import game.view.Visualizer;
import game.model.Vector2D;

public class Wall extends FixBody{
    public enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    Direction direction;

    double thickness = 50000;

    static final double OFFSET_SOUTH = 40;
    static final double OFFSET_EAST = 15;

    
    public Wall(Direction direction,double thickness,java.awt.Rectangle windowBounds ){
        super(new CollisionRectangle(new Vector2D(0,0), new Vector2D(0,0)),Color.WHITE);
        this.thickness = thickness;
        this.direction = direction;
        update(windowBounds);
    }

    public void update(java.awt.Rectangle windowBounds) {
        switch (direction){
            case NORTH:
                collisionShape = new CollisionRectangle(
                    new Vector2D(windowBounds.getX() - thickness,windowBounds.getY() - thickness ),
                    new Vector2D(windowBounds.getWidth() + thickness, thickness));
                break;
            case EAST:
                collisionShape = new CollisionRectangle(
                    new Vector2D(windowBounds.getX()+ windowBounds.getWidth() - OFFSET_EAST,windowBounds.getY() -thickness),
                    new Vector2D(thickness, windowBounds.getHeight() + thickness));
                break;
            case SOUTH:
                collisionShape = new CollisionRectangle(
                    new Vector2D(windowBounds.getX() - thickness, windowBounds.getY() + windowBounds.getHeight() - OFFSET_SOUTH),
                    new Vector2D(windowBounds.getWidth() + thickness,thickness));
                break;
            case WEST:
                collisionShape = new CollisionRectangle(
                    new Vector2D(windowBounds.getX() - thickness,windowBounds.getY() - thickness),
                    new Vector2D(thickness,windowBounds.getHeight() + thickness));
                break;
            default:
                break;
        }
    }


    @Override
    public Vector2D bounce(CollisionShape a) {
        Rectangle2D rect =  collisionShape.getBoundingBox().createIntersection(a.getBoundingBox());
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
    public void setPosition(Vector2D position) {
        collisionShape.setPosition(position);
    }

    @Override
    public Vector2D getPosition() {
        return collisionShape.getPosition();
    }

    @Override
    public Vector2D getDimension() {
        return new Vector2D(collisionShape.getBoundingBox().getWidth(),collisionShape.getBoundingBox().getHeight());
    }

    @Override
    //no Visual representation of wall
    public Visual getVisual(Visualizer visualizer) {
        return null;
    }
}
