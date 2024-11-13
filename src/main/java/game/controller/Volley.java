package game.controller;

public class Volley extends SimulationWindow{
    @Override
    public synchronized void run() {
        modelWorld.preset1();
        super.run();
    }
}
