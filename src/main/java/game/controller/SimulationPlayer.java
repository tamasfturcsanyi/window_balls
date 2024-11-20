package game.controller.simulation_player;

import java.awt.Rectangle;

import game.controller.SimulationWindow;

public class SimulationPlayer extends SimulationWindow{
    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld.preset1();
        initVisualizables();
    }
}
