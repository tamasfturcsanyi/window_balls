package game.model.physicsbodies;

import java.awt.Color;

import game.model.Vector2D;

/**
 * The VolleyBall class represents a volleyball in the game, which is a type of Ball.
 * It initializes the volleyball with a specific position, radius, color, mass, and friction.
 * The class also provides a method to reset the volleyball's position and velocity.
 */
public class VolleyBall extends Ball{
    public VolleyBall(){
        super(new Vector2D(512, 100),80,Color.PINK,1,0.1);
    }

    /**
     * Resets the position and velocity of the volleyball.
     *
     * @param starter an integer value used to adjust the starting position of the volleyball.
     */
    public void reset(int starter){
        setPosition(new Vector2D(512.0 - 80 + starter,100.0 - 80));
        setVelocity(new Vector2D(0,0));
    }
}
