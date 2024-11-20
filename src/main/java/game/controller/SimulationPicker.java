package game.controller;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import game.model.Simulation;
import game.model.serialization.SimulationSerializer;

public class SimulationPicker{
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 700;

    JFrame window;

    JPanel buttonsPanel;

    JPanel simulationsPanel;

    

    public SimulationPicker(){

        Simulation sim = new Simulation();
        sim.preset1();
        SimulationSerializer.saveWorld(sim);

        initwindow();
    }

    void initwindow(){
        window = new JFrame("Simulation Picker");
        window.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initButtonsPanel();
        initSimulationsPanel();

        window.setVisible(true);
    }

    void initButtonsPanel(){
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.setPreferredSize(new Dimension(500,100));

        buttonsPanel.add(new JButton("Add"));
        buttonsPanel.add(new JButton("Open"));
        buttonsPanel.add(new JButton("Delete"));

        window.add(buttonsPanel);
    }

    void initSimulationsPanel(){
        simulationsPanel = new JPanel();
        simulationsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        simulationsPanel.add(new SimulationButton("src/main/resources/Preset_1.json"));
        simulationsPanel.add(new SimulationButton("src/main/resources/Preset_2.json"));
        simulationsPanel.add(new SimulationButton("src/main/resources/Preset_3.json"));
        window.add(simulationsPanel);
    }

    public static void main(String[] args) {
        SimulationPicker picker = new SimulationPicker();
    }
} 