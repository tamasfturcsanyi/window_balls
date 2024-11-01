package game;

import game.engine.Simulation;
import game.engine.Vector2D;

import com.google.gson.*;

public class WindowBasket {

    Simulation sim = new Simulation(new Vector2D(500, 300));

    void run(){
        sim.run();
    }

    

}
