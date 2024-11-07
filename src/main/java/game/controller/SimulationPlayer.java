package game.controller;

import game.model.Simulation;
import game.model.Vector2D;
import game.model.physicksbodies.Ball;
import game.model.physicksbodies.PhysicksBody;
import game.model.physicksbodies.Pole;
import game.view.Visualizer;
import game.view.GraphicsPanel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SimulationPlayer implements Runnable{
    GraphicsPanel view;

    Simulation modelWorld;


    JFrame window;

    int maxFPS = 120;
    long frameTime = 1000/maxFPS;


    public SimulationPlayer(){
        modelWorld = new Simulation();
        view = new GraphicsPanel();
        window = new JFrame("Simulation");
        window.setBounds(modelWorld.getWindowBounds());
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

        modelWorld.preset1();


        window.add(view);
    }

    void updateView(){
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
