package game.model.physicksbodies;

import java.awt.Color;
import game.view.Visual;
import game.view.Visualizer;
import game.model.SimulationParameters;
import game.model.Vector2D;
import game.model.shapes.CollisionShape;

public abstract class PhysicksBody {
    CollisionShape collisionShape;

    Color color = Color.BLUE;

    PhysicksBody(CollisionShape collisionShape, Color color){
        this.collisionShape = collisionShape;
        this.color = color;
    }

    public abstract Vector2D getPosition();
    public abstract void setPosition(Vector2D position);

    public abstract Vector2D getDimension();

    public abstract void collide(PhysicksBody otherBody);

    public abstract Vector2D bounce(CollisionShape otherCollisionShape);

    //calculates new position, applies forces, moves Body
    public abstract void physicksUpdate(SimulationParameters params);

    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    public abstract boolean getFix();

    public abstract Visual getVisual(Visualizer visualizer);

    public Color getColor() {
        return color;
    }
}
