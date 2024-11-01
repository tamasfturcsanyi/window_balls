package game.engine;

import game.graphics.Visual;

public abstract class Actor extends PhysicksBody implements Visual {
    protected Actor(double mass, boolean fix){
        super(mass, fix);
    }

    public abstract Vector2D getCenter();

    public abstract Vector2D bounce(Actor a);

}
