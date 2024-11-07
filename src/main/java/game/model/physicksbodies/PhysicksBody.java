package game.model.physicksbodies;

import java.awt.Color;
import game.view.Visual;
import game.view.Visualizer;
import game.model.SimulationParameters;
import game.model.Vector2D;
import game.model.shapes.CollisonShape;

public abstract class PhysicksBody {
    CollisonShape collisonShape;

    Color color = Color.BLUE;

    PhysicksBody(CollisonShape collisonShape, Color color){
        this.collisonShape = collisonShape;
        this.color = color;
    }

    public abstract Vector2D getPosition();
    public abstract void setPosition(Vector2D position);

    public abstract Vector2D getDimension();

    public abstract void collide(PhysicksBody otherBody);

    public abstract Vector2D bounce(CollisonShape otherCollisonShape);

    //calculates new position, applies forces, moves Body
    public abstract void physicksUpdate(SimulationParameters params);

    public CollisonShape getCollisonShape() {
        return collisonShape;
    }

    public abstract boolean getFix();

    public abstract Visual getVisual(Visualizer visualizer);

    public Color getColor() {
        return color;
    }
}
