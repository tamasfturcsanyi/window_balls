package game.model.physicsbodies;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import game.model.Vector2D;

import game.model.shapes.CollisionRectangle;
import game.model.shapes.CollisionShape;
import game.view.Visualizer.Visual;
import game.view.Visualizer;
public class Brick extends FixBody{
    double bounciness = 10;

    static final double LIMES = 10;

    public Brick(Vector2D position, Vector2D dimension){
        super(new CollisionRectangle(position, dimension),Color.ORANGE);
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
    public game.model.Vector2D getDimension() {
        return new Vector2D(collisionShape.getBoundingBox().getWidth(),collisionShape.getBoundingBox().getHeight());
    }

    Vector2D bounceVertically(CollisionShape a, Rectangle2D intersectionRectangle){
        if(getPosition().getY() - a.getPosition().getY() < 0){
            return new Vector2D(0,intersectionRectangle.getHeight()).stretch(intersectionRectangle.getHeight()*bounciness);
        }else if(getPosition().getY() - a.getPosition().getY() > 0){
            return new Vector2D(0,-intersectionRectangle.getHeight()).stretch(intersectionRectangle.getHeight()*bounciness);
        }
        return new Vector2D(0,0);
    }

    Vector2D bounceHorizontally(CollisionShape a, Rectangle2D intersectionRectangle){
        if(getPosition().getX() - a.getPosition().getX() < 0){
            return new Vector2D(intersectionRectangle.getWidth(),0).stretch(intersectionRectangle.getWidth()*bounciness);
        }else if(getPosition().getX() - a.getPosition().getX() > 0){
            return new Vector2D(-intersectionRectangle.getWidth(),0).stretch(intersectionRectangle.getWidth()*bounciness);
        }
        return new Vector2D(0,0);
    }

    @Override
    public Vector2D bounce(CollisionShape a) {
        Rectangle2D intersectionRectangle =  collisionShape.getBoundingBox().createIntersection(a.getBoundingBox());
        double difference = intersectionRectangle.getWidth() - intersectionRectangle.getHeight();
        //prevent corner rockets
        if(Math.abs(difference) < LIMES){
            return new Vector2D(0,0);
        }

        if(difference > 0){
            return bounceVertically(a, intersectionRectangle);
        }else{
            return bounceHorizontally(a, intersectionRectangle);
        }
    }

    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}
