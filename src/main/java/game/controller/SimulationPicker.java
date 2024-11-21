package game.controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class SimulationPicker {
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 700;

    static final String JSON_DIR_PATH = "src/main/resources/jsons";

    JFrame window;
    JPanel buttonsPanel;
    JPanel simulationsPanel;

    public SimulationPicker() {
        initWindow();
    }

    void initWindow() {
        window = new JFrame("Simulation Picker");
        window.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initButtonsPanel();
        initSimulationsPanel();

        window.setVisible(true);
    }

    void initButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setPreferredSize(new Dimension(500, 100));

        buttonsPanel.add(new JButton("Add"));
        buttonsPanel.add(new JButton("Open"));
        buttonsPanel.add(new JButton("Delete"));

        window.add(buttonsPanel);
    }

    void initSimulationsPanel() {
        simulationsPanel = new JPanel();

        File jsonsFolder = new File(JSON_DIR_PATH);
        if (!jsonsFolder.isDirectory()) {
            System.err.println("The specified path is not a directory: " + JSON_DIR_PATH);
            return;
        }

        File[] jsonFiles = jsonsFolder.listFiles();
        if (jsonFiles == null) {
            System.err.println("Failed to list files in directory: " + JSON_DIR_PATH);
            return;
        }

        simulationsPanel.setLayout(new GridLayout(jsonFiles.length, 1));
        for (File file : jsonFiles) {
            simulationsPanel.add(new SimulationButton(file));
        }

        JScrollPane scrollPane = new JScrollPane(simulationsPanel);
        window.add(scrollPane);
    }

    public static void main(String[] args) {
        new SimulationPicker();
    }
}
