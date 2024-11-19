package game.controller;

import java.awt.Rectangle;

public class SimulationPlayer extends SimulationWindow{
    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld.preset1();
        initVisualizables();
    }
}
