package game.controller;

import game.model.Simulation;
import game.model.physicksbodies.PhysicksBody;
import game.model.serialization.SimulationSerializer;
import game.view.Visualizer;
import game.view.FPSCounter;
import game.view.GraphicsPanel;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SimulationPlayer implements Runnable{
    GraphicsPanel view;

    Simulation modelWorld;

    FPSCounter counter = new FPSCounter();
    Thread counterThread = new Thread(counter);


    JFrame window;

    int maxFPS = 240;
    long frameTime = 1000/maxFPS;


    public SimulationPlayer(){
        modelWorld = new Simulation();
        view = new GraphicsPanel();
        window = new JFrame(modelWorld.getTitle());
        window.setBounds(modelWorld.getWindowBounds());
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);

        view.add(counter.getLabel());
        counterThread.start();
        window.add(view);
    }

    void updateView(){
        counter.increment();
        view.reset();
        for (PhysicksBody physicksBody : modelWorld.getPhysicksBodies()) {
            view.addVisual(physicksBody.getVisual(new Visualizer(modelWorld.getWindowBounds())));
        }
        window.repaint();
    }

    void updateModel(){
        modelWorld.setWindowBounds(window.getBounds());
        modelWorld.update();
    }


    @Override
    public synchronized void run(){    
        //modelWorld = new SimulationSerializer().loadWorld("src/main/resources/Preset_1.json");

        modelWorld.preset1();

        //new SimulationSerializer().saveWorld(modelWorld);

        //main loop, only exits if window is closed
        while(true){
            long startTime = System.currentTimeMillis();

            updateModel();

            //update window in its own Thread
            SwingUtilities.invokeLater(this::updateView);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            if(duration < frameTime){
                try {
                    wait(frameTime - duration);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}
