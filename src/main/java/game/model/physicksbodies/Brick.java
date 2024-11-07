package game.model.physicksbodies;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import game.model.Vector2D;

import game.model.shapes.CollisionRectangle;
import game.model.shapes.CollisonShape;
import game.view.Visual;
import game.view.Visualizer;
public class Brick extends FixBody{
    double bounciness = 0.1;

    public Brick(Vector2D position, Vector2D dimension){
        super(new CollisionRectangle(position, dimension),Color.ORANGE);
    }

    @Override
    public void setPosition(Vector2D position) {
        collisonShape.setPosition(position);
    }

    @Override
    public Vector2D getPosition() {
        return collisonShape.getPosition();
    }

    @Override
    public game.model.Vector2D getDimension() {
        return new Vector2D(collisonShape.getBoundingBox().getWidth(),collisonShape.getBoundingBox().getHeight());
    }

    Vector2D bounceVertically(CollisonShape a, Rectangle2D intersectionRectangle){
        if(getPosition().getY() - a.getPosition().getY() < 0){
            return new Vector2D(0,intersectionRectangle.getHeight()).stretch(intersectionRectangle.getHeight()*bounciness);
        }else if(getPosition().getY() - a.getPosition().getY() > 0){
            return new Vector2D(0,-intersectionRectangle.getHeight()).stretch(intersectionRectangle.getHeight()*bounciness);
        }
        return new Vector2D(0,0);
    }

    Vector2D bounceHorizontally(CollisonShape a, Rectangle2D intersectionRectangle){
        if(getPosition().getX() - a.getPosition().getX() < 0){
            return new Vector2D(intersectionRectangle.getWidth(),0).stretch(intersectionRectangle.getWidth()*bounciness);
        }else if(getPosition().getX() - a.getPosition().getX() > 0){
            return new Vector2D(-intersectionRectangle.getWidth(),0).stretch(intersectionRectangle.getWidth()*bounciness);
        }
        return new Vector2D(0,0);
    }

    @Override
    public Vector2D bounce(CollisonShape a) {
        Rectangle2D intersectionRectangle =  collisonShape.getBoundingBox().createIntersection(a.getBoundingBox());
        return bounceVertically(a, intersectionRectangle);

        //if(intersectionRectangle.getWidth() > intersectionRectangle.getHeight()){
        //    return bounceVertically(a, intersectionRectangle);
        //}else{
        //    return bounceHorizontally(a, intersectionRectangle);
        //}
    }

    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}