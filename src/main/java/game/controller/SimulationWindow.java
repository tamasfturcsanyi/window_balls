package game.controller;

import game.model.Simulation;
import game.model.physicksbodies.PhysicksBody;
import game.model.serialization.SimulationSerializer;
import game.view.Visualizer;
import game.view.Visualizer.Visualizable;
import game.view.GraphicsPanel;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.HashSet;

public class SimulationWindow implements Runnable{
    private volatile boolean running = true;

    GraphicsPanel view;

    Simulation modelWorld;

    HashSet<Visualizable> visualizables = new HashSet<>();

    JFrame window;

    int maxFPS = 240;
    long frameTime = 1000/maxFPS;

    public SimulationWindow(String title, Rectangle windowBounds){
        modelWorld = new Simulation(title,windowBounds);
        view = new GraphicsPanel();
        initView();
        initWindow();
    }

    public SimulationWindow(String jsonPath){
        modelWorld = SimulationSerializer.loadWorld(jsonPath);
        view = new GraphicsPanel();
        initView();
        initWindow();
    }

    void initWindow(){
        window = new JFrame();
        window.setTitle(modelWorld.getTitle());
        window.setBounds(modelWorld.getWindowBounds());
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.add(view);
    }

    void initView(){
        view.setBackgroundColor(modelWorld.getParams().getSimulationBackground());
        for (PhysicksBody body : modelWorld.getPhysicksBodies()) {
            if(body.isVisible()){
                visualizables.add(body);
            }
        }
    }

    public void addToViewAndSimulation(PhysicksBody body){
        visualizables.add(body);
        modelWorld.addBody(body);
    }

    public void removeFromViewAndSimulation(PhysicksBody body){
        visualizables.remove(body);
        modelWorld.removeBody(body);
    }

    void addVisuals(){
        for (Visualizable v : visualizables) {
            view.addVisual(v.getVisual(new Visualizer(modelWorld.getWindowBounds())));
        }
    }

    void updateView(){
        addVisuals();
        window.repaint();
    }

    void updateModel(){
        if(modelWorld.getParams().isShakeable()){
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
