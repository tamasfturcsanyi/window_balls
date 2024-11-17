package game.model.physicksbodies.volley;

import java.awt.Color;

import game.model.Vector2D;
import game.model.physicksbodies.Ball;

public class VolleyBall extends Ball{
    public VolleyBall(){
        super(new Vector2D(512, 100),80,Color.PINK,1,0.1);
    }

    public void reset(int starter){
        setPosition(new Vector2D(512.0 - 80 + starter,100.0 - 80));
        setVelocity(new Vector2D(0,0));
    }
}
