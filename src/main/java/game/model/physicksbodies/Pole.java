package game.model.physicksbodies;

import java.awt.Color;

import game.model.Vector2D;
import game.model.shapes.CollisionCircle;
import game.model.shapes.CollisionShape;
import game.view.Visualizer.Visual;
import game.view.Visualizer;

public class Pole extends FixBody{
    public Pole(Vector2D center, double radius, Color color){
        super(new CollisionCircle(center.add(new Vector2D(-radius,-radius)), radius),color);
    }

    @Override
    public Vector2D getDimension() {
        return new Vector2D(collisionShape.getBoundingBox().getWidth(),collisionShape.getBoundingBox().getHeight());
    }

    public Vector2D getCenter(){
        return collisionShape.getCenter();
    }
    
    @Override
    public Vector2D getPosition() {
        return collisionShape.getPosition();
    }
    @Override
    public void setPosition(Vector2D position) {
        collisionShape.setPosition(position);
    }

    @Override
    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Vector2D bounce(CollisionShape otherCollisionShape) {
        double maxDistance = otherCollisionShape.getMaxDistanceFromCenter()+collisionShape.getMaxDistanceFromCenter();
        Vector2D diff = collisionShape.getCenter().diff(otherCollisionShape.getCenter());
        double clip = maxDistance-diff.length();
        if (clip < 0){
            clip = 0;
        }
        return diff.stretch(clip);
    }

    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}
