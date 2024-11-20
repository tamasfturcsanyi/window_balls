package game.controller;

import java.awt.Rectangle;

import game.model.serialization.SimulationSerializer;

public class SimulationPlayer extends SimulationWindow{
    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld.preset1();
        SimulationSerializer.saveWorld(modelWorld);
        modelWorld = SimulationSerializer.loadWorld("src/main/resources/Preset_1.json");
        initVisualizables();
    }
}
