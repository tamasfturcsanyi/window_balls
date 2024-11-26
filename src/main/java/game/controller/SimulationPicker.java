package game.controller;

import java.io.File;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.Cursor;


/**
 * The SimulationPicker class is responsible for creating a GUI window that allows users to manage simulation files.
 * It provides functionalities to add, open, delete, and navigate back to the main menu.
 * The class initializes the main window, buttons panel, and simulations panel.
 * It also handles the selection and deletion of simulation files.
 * 
 * Constants:
 * - WINDOW_WIDTH: The width of the window.
 * - WINDOW_HEIGHT: The height of the window.
 * - JSON_DIR_PATH: The directory path where JSON files are stored.
 * - FONT_NAME: The font name used for buttons.
 * 
 * Components:
 * - JFrame window: The main window of the application.
 * - JPanel buttonsPanel: The panel containing action buttons.
 * - JPanel simulationsPanel: The panel displaying simulation buttons.
 * - JButton addButton: The button to add a new simulation.
 * - JButton openButton: The button to open a selected simulation.
 * - JButton deleteButton: The button to delete a selected simulation.
 * - JButton backButton: The button to navigate back to the main menu.
 * - ArrayList<SimulationButton> simulationButtons: The list of simulation buttons.
 * - SimulationButton selectedButton: The currently selected simulation button.
 * 
 * Methods:
 * - SimulationPicker(): Constructor that initializes the window.
 * - void initWindow(): Initializes the main window and its components.
 * - void initAddButton(): Initializes the "Add" button.
 * - void initOpenButton(): Initializes the "Open" button.
 * - void initDeleteButton(): Initializes the "Delete" button.
 * - void initBackButton(): Initializes the "Back" button.
 * - void initButtonsPanel(): Initializes the panel containing action buttons.
 * - void initCoolButton(JButton button, Color defaultColor): Sets the appearance of a button.
 * - void initSimulationsPanel(): Initializes the panel displaying simulation buttons.
 * - void selectSimulation(SimulationButton button): Handles the selection of a simulation button.
 * - void deleteSimulation(): Deletes the selected simulation file.
 * - void startNewSimulation(): Starts a new simulation.
 * - void openSimulation(): Opens the selected simulation.
 * - void startMainMenu(): Navigates back to the main menu.
 */
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
    JButton backButton;

    ArrayList<SimulationButton> simulationButtons = new ArrayList<>();

    SimulationButton selectedButton;

    /**
     * Constructs a new SimulationPicker and initializes the window.
     */
    public SimulationPicker() {
        initWindow();
    }

    /**
     * Initializes the main window for the Simulation Picker application.
     * Sets up the window properties such as title, size, layout, and default close operation.
     * Also initializes the buttons panel and simulations panel, and makes the window visible.
     */
    void initWindow() {
        window = new JFrame("Simulation Picker");
        window.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initButtonsPanel();
        initSimulationsPanel();

        window.setVisible(true);
    }

    @SuppressWarnings("unused")
    void initAddButton(){
        addButton = new JButton("Add");
        initCoolButton(addButton,Color.GREEN);
        addButton.addActionListener(s -> startNewSimulation());
        buttonsPanel.add(addButton);
    }

    @SuppressWarnings("unused")
    void initOpenButton(){
        openButton = new JButton("Open");
        initCoolButton(openButton,Color.CYAN);
        openButton.addActionListener(o -> openSimulation());
        openButton.setEnabled(false);
        buttonsPanel.add(openButton);
    }

    @SuppressWarnings("unused")
    void initDeleteButton(){
        deleteButton = new JButton("Delete");
        initCoolButton(deleteButton,Color.RED);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(d -> deleteSimulation());

        buttonsPanel.add(deleteButton);
    }

    @SuppressWarnings("unused")
    void initBackButton(){
        backButton = new JButton();
        ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        backButton.setIcon(backIcon);
        initCoolButton(backButton, Color.ORANGE);
        backButton.addActionListener(m -> startMainMenu());
        buttonsPanel.add(backButton);
    }

    void initButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.PINK);
        buttonsPanel.setPreferredSize(new Dimension(500, 200));

        initAddButton();

        initOpenButton();

        initDeleteButton();

        initBackButton();

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

    /**
     * Initializes the simulations panel by creating buttons for each simulation file found in the JSON directory.
     * The panel is set up with a grid layout where each row contains one button.
     * Each button is associated with a simulation file and has an action listener to handle selection.
     * The panel is then added to a scroll pane with a vertical scroll bar.
     * 
     * Suppresses:
     * - "unused" warning as the method might be used through reflection or other means.
     */
    @SuppressWarnings("unused")
    void initSimulationsPanel() {
        simulationsPanel = new JPanel();

        File jsonsFolder = new File(JSON_DIR_PATH);

        if (!jsonsFolder.exists()) {
            jsonsFolder.mkdirs();
        }

        File[] jsonFiles = jsonsFolder.listFiles();

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

    /**
     * Selects or deselects a simulation button.
     * <p>
     * If the provided button is already selected, it will be deselected,
     * and the open and delete buttons will be disabled. If the provided
     * button is not selected, all other buttons will be deselected, the
     * provided button will be selected, and the open and delete buttons
     * will be enabled.
     * </p>
     *
     * @param button the simulation button to select or deselect
     */
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

    /**
     * Deletes the currently selected simulation.
     * <p>
     * This method performs the following actions:
     * <ul>
     *   <li>Checks if a simulation is selected. If not, it returns immediately.</li>
     *   <li>Deletes the JSON file associated with the selected simulation button.</li>
     *   <li>Removes the selected simulation button from the simulations panel and the list of simulation buttons.</li>
     *   <li>Resets the selected button to null.</li>
     *   <li>Disables the open and delete buttons.</li>
     *   <li>Revalidates and repaints the window to reflect the changes.</li>
     * </ul>
     * </p>
     */
    void deleteSimulation(){
        if(selectedButton == null){
            return;
        }
        File jsonFile = new File(selectedButton.getJsonPath());
        if(jsonFile.delete()){//NOSONAR
            simulationsPanel.remove(selectedButton);
            simulationButtons.remove(selectedButton);
            selectedButton = null;
            openButton.setEnabled(false);
            deleteButton.setEnabled(false);
            window.revalidate();
            window.repaint();
        }
    }

    /**
     * Starts a new simulation by creating a new instance of SimulationPlayer,
     * running it in a separate thread, and then disposing of the current window.
     */
    void startNewSimulation(){
        SimulationPlayer sPlayer = new SimulationPlayer();
        Thread simPlayerThread = new Thread(sPlayer);
        simPlayerThread.start();

        window.dispose();
    }

    /**
     * Opens a simulation based on the selected button's JSON path.
     * If no button is selected, the method returns immediately.
     * Otherwise, it creates a new SimulationPlayer with the JSON path,
     * starts it in a new thread, and disposes of the current window.
     */
    void openSimulation(){
        if(selectedButton == null){
            return;
        }

        String jsonPath =  selectedButton.getJsonPath();
        SimulationPlayer sPlayer = new SimulationPlayer(jsonPath);
        Thread simPlayerThread = new Thread(sPlayer);
        simPlayerThread.start();

        window.dispose();
    }

    /**
     * Starts the main menu by creating and starting a new thread that runs the MainMenu.
     * Disposes of the current window after starting the main menu thread.
     */
    void startMainMenu(){
        Thread menuThread = new Thread(new MainMenu());
        menuThread.start();

        window.dispose();
    }
}
