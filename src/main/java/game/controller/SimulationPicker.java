package game.controller;

import java.io.File;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.Cursor;


public class SimulationPicker {
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 700;

    static final String JSON_DIR_PATH = "src/main/resources/jsons";

    static final String FONT_NAME = "Impact";

    JFrame window;
    JPanel buttonsPanel;

    JPanel simulationsPanel;

    JButton addButton;
    JButton openButton;
    JButton deleteButton;

    ArrayList<SimulationButton> simulationButtons = new ArrayList<>();

    SimulationButton selectedButton;

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

    void initAddButton(){
        addButton = new JButton("Add");
        initCoolButton(addButton,Color.GREEN);
        buttonsPanel.add(addButton);
    }

    void initOpenButton(){
        openButton = new JButton("Open");
        initCoolButton(openButton,Color.CYAN);
        openButton.addActionListener(s -> startSimulation());
        openButton.setEnabled(false);
        buttonsPanel.add(openButton);
    }

    void initDeleteButton(){
        deleteButton = new JButton("Delete");
        initCoolButton(deleteButton,Color.RED);
        deleteButton.setEnabled(false);
        buttonsPanel.add(deleteButton);
    }

    void initButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(500, 200));

        initAddButton();

        initOpenButton();

        initDeleteButton();

        window.add(buttonsPanel);
    }

    void initCoolButton(JButton button, Color defaultColor){
        button.setFont(new Font(FONT_NAME,Font.BOLD,32));
        button.setBackground(defaultColor);
        button.setPreferredSize(new Dimension(100,100));
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
            SimulationButton simButton = new SimulationButton(file);
            simButton.updateApperence();
            simButton.addActionListener(s -> selectSimulation(simButton));
            simulationButtons.add(simButton);
            simulationsPanel.add(simButton);
        }

        JScrollPane scrollPane = new JScrollPane(simulationsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        window.add(scrollPane);
    }

    void selectSimulation(SimulationButton button){
        if(button == selectedButton){
            selectedButton = null;
            button.selected = false;
            button.updateApperence();
            openButton.setEnabled(false);
            deleteButton.setEnabled(false);
            return;
        }
        for (SimulationButton simulationButton : simulationButtons) {
            simulationButton.selected = false;
            simulationButton.updateApperence();
        }
        button.selected = true;
        selectedButton = button;
        button.updateApperence(); 
        openButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }

    void startSimulation(){
        if(selectedButton == null){
            return;
        }

        String jsonPath =  selectedButton.getJsonPath();
        SimulationPlayer sPlayer = new SimulationPlayer(jsonPath);
        Thread simPlayerThread = new Thread(sPlayer);
        simPlayerThread.start();

        window.dispose();
        }

        public static void main(String[] args) {
        new SimulationPicker();
    }
}
