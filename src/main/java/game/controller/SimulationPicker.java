package game.controller;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimulationPicker{
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 700;

    JFrame window;

    JPanel buttonsPanel;

    JPanel simulationsPanel;

    

    public SimulationPicker(){
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

        buttonsPanel.add(new JButton("Add"));
        buttonsPanel.add(new JButton("Open"));
        buttonsPanel.add(new JButton("Delete"));

        window.add(buttonsPanel);
    }

    void initSimulationsPanel(){
        simulationsPanel = new JPanel();
        
        simulationsPanel.add(new JButton("sim1"));
        simulationsPanel.add(new JButton("sim2"));
        simulationsPanel.add(new JButton("sim3"));

        window.add(simulationsPanel);
    }
} 