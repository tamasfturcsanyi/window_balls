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

/**
 * The SimulationWindow class represents a window that displays a simulation.
 * It implements the Runnable interface to allow the simulation to run in a separate thread.
 * The window updates at a specified maximum frames per second (FPS).
 * SimulationPlayer, Basket, and Volley classes extend this class to create specific simulations.
 */
public class SimulationWindow implements Runnable{
    private volatile boolean running = true;

    GraphicsPanel view;

    Simulation modelWorld;

    HashSet<Visualizable> visualizables = new HashSet<>();

    JFrame window;

    int maxFPS = 240;
    long frameTime = 1000/maxFPS;

    /**
     * Constructs a new SimulationWindow with the specified title and window bounds.
     *
     * @param title the title of the simulation window
     * @param windowBounds the bounds of the window, specified as a Rectangle
     */
    public SimulationWindow(String title, Rectangle windowBounds){
        modelWorld = new Simulation(title,windowBounds);
        view = new GraphicsPanel();
        initView();
        initWindow();
    }

    /**
     * Constructs a new SimulationWindow.
     *
     * @param jsonPath the path to the JSON file used to load the simulation world
     */
    public SimulationWindow(String jsonPath){
        modelWorld = SimulationSerializer.loadWorld(jsonPath);
        view = new GraphicsPanel();
        initView();
        initWindow();
    }

    /**
     * Initializes the main window for the simulation.
     * This method sets up the JFrame with the title, bounds, and other properties
     * from the modelWorld. It also ensures the window is not resizable and is visible.
     * The view component is added to the window.
     */
    void initWindow(){
        window = new JFrame();
        window.setTitle(modelWorld.getTitle());
        window.setBounds(modelWorld.getWindowBounds());
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.add(view);
    }

    /**
     * Initializes the view by setting the background color and adding visible
     * physics bodies to the list of visualizables.
     * 
     * This method performs the following steps:
     * 1. Sets the background color of the view using the simulation background color
     *    from the model world parameters.
     * 2. Iterates through all physics bodies in the model world.
     * 3. Adds each visible physics body to the list of visualizables.
     */
    void initView(){
        view.setBackgroundColor(modelWorld.getParams().getSimulationBackground());
        for (PhysicksBody body : modelWorld.getPhysicksBodies()) {
            if(body.isVisible()){
                visualizables.add(body);
            }
        }
    }

    /**
     * Adds a given PhysicksBody to both the visualizables list and the modelWorld.
     *
     * @param body the PhysicksBody to be added to the view and simulation
     */
    public void addToViewAndSimulation(PhysicksBody body){
        visualizables.add(body);
        modelWorld.addBody(body);
    }

    /**
     * Removes the specified PhysicksBody from both the visual representation and the simulation model.
     *
     * @param body the PhysicksBody to be removed from the view and simulation
     */
    public void removeBodyFromViewAndSimulation(PhysicksBody body){
        visualizables.remove(body);
        modelWorld.removeBody(body);
    }

    /**
     * Removes the specified visualizable object from the list of visualizables.
     *
     * @param visualizable the visualizable object to be removed
     */
    public void removeVisualizable(Visualizable visualizable){
        visualizables.remove(visualizable);
    }

    /**
     * Adds visual representations of all visualizable objects to the view.
     * This method iterates through the list of visualizable objects,
     * retrieves their visual representation using a Visualizer with the current
     * window bounds, and adds them to the view.
     */
    void addVisuals(){
        for (Visualizable v : visualizables) {
            view.addVisual(v.getVisual(new Visualizer(modelWorld.getWindowBounds())));
        }
    }

    /**
     * Updates the view by adding visuals and repainting the window.
     */
    void updateView(){
        addVisuals();
        window.repaint();
    }

    /**
     * Updates the model state. If the model world parameters indicate that the model is shakeable,
     * it sets the window bounds to the current window bounds. Then, it updates the model world.
     */
    void updateModel(){
        if(modelWorld.getParams().isShakeable()){
            modelWorld.setWindowBounds(window.getBounds());
        }
        modelWorld.update();
    }

    /**
     * Disposes of the current window and stops the simulation.
     * This method releases the resources associated with the window
     * and sets the running flag to false, indicating that the simulation
     * is no longer active.
     */
    void disposeWindow(){
        window.dispose();
        running = false;
    }

    /**
     * Executes a single cycle of the simulation.
     * This method updates the model and then schedules the view update
     * to be executed on the Event Dispatch Thread using SwingUtilities.invokeLater.
     */
    void cycle() {
        updateModel();

        // update window in its own Thread
        SwingUtilities.invokeLater(this::updateView);
    }

    /**
     * The main loop of the simulation window. This method runs continuously 
     * while the window is open and the simulation is running. It calculates 
     * the duration of each cycle and ensures that each cycle takes at least 
     * the specified frame time by waiting if necessary.
     * 
     * The method is synchronized to ensure thread safety and can be interrupted 
     * if the thread is interrupted during the wait period.
     */
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
