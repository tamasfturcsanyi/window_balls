package game;

import game.controller.SimulationPlayer;

public class WindowBasket {

    SimulationPlayer simPlayer = new SimulationPlayer();

    Thread simulationThread = new Thread(simPlayer);

    void run(){
        simulationThread.start();
    }
}
