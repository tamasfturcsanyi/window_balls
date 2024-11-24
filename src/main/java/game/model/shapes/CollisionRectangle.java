package game.model.shapes;

import game.model.Vector2D;
import java.awt.geom.Rectangle2D;

public class CollisionRectangle extends Rectangle2D.Double implements CollisionShape{
    //upper left corner
    Vector2D position;
    //width and height
    Vector2D dimension;

    public CollisionRectangle(Vector2D position, Vector2D dimension){
        super(position.getX(),position.getY() ,dimension.getX(), dimension.getY());
        this.position = position;
        this.dimension = dimension; 
    }

    public Vector2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2D position) {
        this.position = position;
        super.x = position.getX();
        super.y = position.getY();
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return this;
    }

    @Override
    public Vector2D getCenter() {
        return position.add(dimension.stretch(0.5));
    }

    @Override
    public double getMaxDistanceFromCenter() {
        return dimension.stretch(0.5).length();
    }

    @Override
    public boolean haveCollided(CollisionShape otherShape) {
        return intersects(otherShape.getBoundingBox());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
