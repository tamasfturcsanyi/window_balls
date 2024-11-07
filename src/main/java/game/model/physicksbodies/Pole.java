package game.model.physicksbodies;

import java.awt.Color;

import game.model.Vector2D;
import game.model.shapes.CollisonCircle;
import game.model.shapes.CollisonShape;
import game.view.Visual;
import game.view.Visualizer;

public class Pole extends FixBody{
    public Pole(Vector2D center, double radius, Color color){
        super(new CollisonCircle(center.add(new Vector2D(-radius,-radius)), radius),color);
    }

    @Override
    public Vector2D getDimension() {
        return new Vector2D(collisonShape.getBoundingBox().getWidth(),collisonShape.getBoundingBox().getHeight());
    }

    @Override
    public Vector2D getPosition() {
        return collisonShape.getPosition();
    }
    @Override
    public void setPosition(Vector2D position) {
        collisonShape.setPosition(position);
    }

    @Override
    public CollisonShape getCollisonShape() {
        return collisonShape;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Vector2D bounce(CollisonShape otherCollisonShape) {
        double maxDistance = otherCollisonShape.getMaxDistanceFromCenter()+collisonShape.getMaxDistanceFromCenter();
        Vector2D diff = collisonShape.getCenter().diff(otherCollisonShape.getCenter());
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
