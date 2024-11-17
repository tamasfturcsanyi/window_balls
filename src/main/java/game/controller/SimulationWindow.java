package game.controller;

import game.model.Simulation;
import game.model.physicksbodies.PhysicksBody;
import game.view.Visualizer;
import game.view.FPSCounter;
import game.view.GraphicsPanel;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SimulationWindow implements Runnable{
    private volatile boolean running = true;

    GraphicsPanel view;

    Simulation modelWorld;


    JFrame window;

    int maxFPS = 240;
    long frameTime = 1000/maxFPS;

    void initWindow(){
        window = new JFrame();
        window.setTitle(modelWorld.getTitle());
        window.setBounds(modelWorld.getWindowBounds());
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
        window.add(view);
    }

    public SimulationWindow(){
        modelWorld = new Simulation();
        view = new GraphicsPanel();
        initWindow();
    }

    public SimulationWindow(String title, Rectangle windowBounds){
        modelWorld = new Simulation(title,windowBounds);
        view = new GraphicsPanel();

        initWindow();
    }

    void updateView(){
        view.reset();
        for (PhysicksBody physicksBody : modelWorld.getPhysicksBodies()) {
            if(physicksBody.isVisible()){
                view.addVisual(physicksBody.getVisual(new Visualizer(modelWorld.getWindowBounds())));
            }
        }
        window.repaint();
    }

    void updateModel(){
        if(modelWorld.getParams().getShakeable()){
            modelWorld.setWindowBounds(window.getBounds());
        }
        modelWorld.update();
    }

    void disposeWindow(){
        window.dispose();
        running = false;
    }

    void cycle() {
        updateModel();

        // update window in its own Thread
        SwingUtilities.invokeLater(this::updateView);
    }

    @Override
    public synchronized void run(){            
        //main loop, only exits if window is closed
        while(running){
            long startTime = System.currentTimeMillis();
            
            cycle();

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
