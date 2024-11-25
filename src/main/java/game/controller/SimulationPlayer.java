package game.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.SimulationParameters;
import game.model.Vector2D;
import game.model.physicksbodies.PhysicksBody;
import game.model.serialization.CollisionShapeTypeAdapter;
import game.model.serialization.ColorTypeAdapter;
import game.model.serialization.PhysicksBodyTypeAdapter;
import game.model.serialization.SimulationSerializer;
import game.model.shapes.CollisionShape;
import game.view.CreateBodyDialog;
import game.view.ParamsDialog;
import game.view.SaveDialog;


/**
 * The SimulationPlayer class extends the SimulationWindow class and provides
 * functionality for controlling a simulation through a graphical user interface.
 * It includes buttons for saving, adding, playing, fast-forwarding, slowing down,
 * accessing settings, and going back to a previous screen. The class also handles
 * mouse and keyboard events for interacting with the simulation.
 */
public class SimulationPlayer extends SimulationWindow{
    static final int BUTTON_HEIGHT = 75;

    JPanel buttonPanel;

    JButton saveButton;
    JButton addButton;

    JButton playButton;
    ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");

    JButton fastButton;
    JButton slowButton;
    JLabel speedLabel = new JLabel();

    JButton settingsButton;
    JButton backButton;

    boolean playing = false;

    PhysicksBody selectedBody;

    /**
     * Constructs a new SimulationPlayer instance.
     * This constructor initializes the player with the name "Simulation" and sets
     * the player's bounding rectangle to a specified size and position.
     * It also initializes the necessary panels for the player.
     */
    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        initPanels();
    }

    /**
     * Constructs a new SimulationPlayer with the specified JSON path.
     *
     * @param jsonPath the path to the JSON configuration file
     */
    public SimulationPlayer(String jsonPath){
        super(jsonPath);
        initPanels();
    }

    /**
     * Initializes the panels by setting up the buttons panel and adding necessary
     * mouse and key listeners to the view. The mouse listener handles mouse click
     * events to trigger the click action. The key listener handles key press events
     * to trigger copy and delete actions when specific keys are pressed.
     */
    void initPanels(){
        initButtonsPanel();
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                click(e.getPoint());
            }
        });
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
                    copySelectedBody();                        
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    deleteSelectedBody();
                }
            }
        });
        view.setFocusable(true);
    }

    /**
     * Initializes the window for the simulation player.
     * This method sets the window size, layout, and resizable property.
     * It first calls the superclass's initWindow method to perform any necessary
     * initialization steps defined in the parent class.
     * 
     * The window size is adjusted by adding the BUTTON_HEIGHT to the current height.
     * The layout of the window's content pane is set to a vertical BoxLayout.
     * The window is made resizable.
     */
    @Override
    void initWindow() {
        super.initWindow();
        window.setBounds(window.getX(), window.getY()+BUTTON_HEIGHT+28,
            window.getWidth(), BUTTON_HEIGHT + window.getHeight());
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setResizable(true);
    }

    @SuppressWarnings("unused")
    void initPlayButton(){
        playButton = new JButton();
        playButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        playButton.setIcon(playIcon);
        playButton.addActionListener(p -> playButtonAction());

        buttonPanel.add(playButton);
        
    }

    @SuppressWarnings("unused")
    void initFastButton(){
        fastButton = new JButton();
        fastButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon fastIcon = new ImageIcon("src/main/resources/fastForward.png");
        fastButton.setIcon(fastIcon);
        fastButton.addActionListener(s -> speedUp());
        buttonPanel.add(fastButton);
    }

    @SuppressWarnings("unused")
    void initSlowButton(){
        slowButton = new JButton();
        slowButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon slowIcon = new ImageIcon("src/main/resources/slowDown.png");
        slowButton.setIcon(slowIcon);
        slowButton.addActionListener(s -> slowDown());
        buttonPanel.add(slowButton);
    }

    @SuppressWarnings("unused")
    void initSettingsButton(){
        settingsButton = new JButton();
        settingsButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon settingsIcon = new ImageIcon("src/main/resources/settings.png");
        settingsButton.setIcon(settingsIcon);
        settingsButton.addActionListener(d -> startParamsDialog());
        buttonPanel.add(settingsButton);
    }

    @SuppressWarnings("unused")
    void initSaveButton(){
        saveButton = new JButton();
        saveButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        saveButton.setIcon(new ImageIcon("src/main/resources/save.png"));
        saveButton.addActionListener(s -> startSaveDialog());

        buttonPanel.add(saveButton);
    }

    @SuppressWarnings("unused")
    void initAddButton(){
        addButton = new JButton();
        addButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        addButton.setIcon(new ImageIcon("src/main/resources/add.png"));
        addButton.addActionListener(c -> startCreateDialog());

        buttonPanel.add(addButton);
    }

    @SuppressWarnings("unused")
    void initBackButton(){
        backButton = new JButton();
        backButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        backButton.setIcon(new ImageIcon("src/main/resources/backMini.png"));
        backButton.addActionListener(o -> openSimulationPicker());

        buttonPanel.add(backButton);
    }

    /**
     * Initializes the buttons panel for the simulation player interface.
     * This method sets up the button panel with specific dimensions, border, and adds various buttons
     * including save, add, slow, play, fast, settings, and back buttons. It also adds a speed label
     * to display the current simulation speed and adds the button panel and view to the window.
     */
    void initButtonsPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(1920,BUTTON_HEIGHT));
        buttonPanel.setMinimumSize(new Dimension(700,BUTTON_HEIGHT));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        
        initSaveButton();
        initAddButton();

        initSlowButton();
        initPlayButton();
        initFastButton();

        buttonPanel.add(speedLabel);
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());
        speedLabel.setFont(speedLabel.getFont().deriveFont(24.0f));

        initSettingsButton();
        initBackButton();


        window.add(buttonPanel);
        window.add(view);
    }
    /**
     * Updates the model based on the current state of the simulation.
     * 
     * If the model world is shakeable, it adjusts the window bounds by subtracting
     * the view's bounds and the button panel's height from the window's bounds,
     * and then sets the new window bounds in the model world.
     * 
     * If the simulation is playing, it updates the model world.
     * Otherwise, if a body is selected, it moves the selected body.
     */
    @Override
    void updateModel() {
        if(modelWorld.getParams().isShakeable()){
            Rectangle newWindowBounds = window.getBounds();
            newWindowBounds.x -= view.getBounds().x;
            newWindowBounds.y -= view.getBounds().y;
            newWindowBounds.height -= buttonPanel.getHeight();
            modelWorld.setWindowBounds(newWindowBounds);
        }
        if(playing){
            modelWorld.update();
        }else{
            if(selectedBody != null){
                moveSelectedBody();
            }
        }
    }

    /**
     * Moves the selected body to the current mouse pointer location.
     * The new position is adjusted by subtracting 50.0 from the x-coordinate
     * and 240.0 from the y-coordinate of the mouse pointer location.
     */
    void moveSelectedBody(){
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        selectedBody.setPosition(new Vector2D(point.x - 50.0, point.y - 240.0)); 
    }

    /**
     * Toggles the simulation between playing and paused states.
     * If the simulation is currently not playing, it resumes the simulation.
     * If the simulation is currently playing, it pauses the simulation.
     */
    void playButtonAction(){
        if(!playing){
            resumeSimulation();
        }else{
            pauseSimulation();
        }
        playing = !playing;
    }

    /**
     * Resumes the simulation by updating the play button icon, resetting the model world's delta time,
     * disabling certain buttons, and making the window non-resizable. If a body is currently selected,
     * it will be deselected and the reference will be cleared.
     */
    void resumeSimulation(){
        playButton.setIcon(pauseIcon);
        modelWorld.resetDeltaTime();
        settingsButton.setEnabled(false);
        addButton.setEnabled(false);
        saveButton.setEnabled(false);
        window.setResizable(false);
        if(selectedBody != null){
            selectedBody.setSelected(false);
            selectedBody = null;
        }   
    }

    /**
     * Pauses the simulation by updating the UI components:
     * - Sets the play button icon to the play icon.
     * - Enables the settings button.
     * - Enables the add button.
     * - Enables the save button.
     * - Makes the window resizable.
     */
    void pauseSimulation(){
        playButton.setIcon(playIcon);
        settingsButton.setEnabled(true);
        addButton.setEnabled(true);
        saveButton.setEnabled(true);
        window.setResizable(true);
    }

    /**
     * Increases the simulation speed by 0.25 units.
     * Updates the speed label to reflect the new simulation speed.
     */
    void speedUp(){
        SimulationParameters params = modelWorld.getParams();
        modelWorld.setSimulationSpeed(params.getSimulationSpeed() + 0.25);
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());
    }

    /**
     * Reduces the simulation speed by 0.25 units, ensuring that the speed does not go below 0.
     * Updates the speed label to reflect the new simulation speed.
     */
    void slowDown(){
        SimulationParameters params = modelWorld.getParams();
        modelWorld.setSimulationSpeed(Math.max(params.getSimulationSpeed() - 0.25,0));
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());
    }

    /**
     * Opens a dialog to set simulation parameters.
     * The dialog allows the user to modify simulation parameters.
     * If the user confirms the changes, the new parameters are applied to the model world
     * and the background color of the view is updated accordingly.
     */
    void startParamsDialog(){
        ParamsDialog pDialog = new ParamsDialog(window,modelWorld.getParams());
        pDialog.setVisible(true);

        SimulationParameters params = pDialog.getSimulationParameters();
        if(params != null){
            modelWorld.setParams(params);
            view.setBackgroundColor(params.getSimulationBackground());
        }
    }

    /**
     * Initiates the creation dialog for a new physics body.
     * 
     * This method creates an instance of the CreateBodyDialog, makes it visible to the user,
     * and retrieves the created PhysicksBody object from the dialog. If a body is created
     * (i.e., the returned body is not null), it adds the body to the view and simulation.
     */
    void startCreateDialog(){
        CreateBodyDialog cDialog = new CreateBodyDialog(window);
        cDialog.setVisible(true);
        PhysicksBody body = cDialog.getBody();
        if(body != null){
            addToViewAndSimulation(body);
        }
    }

    /**
     * Opens a save dialog for the user to save the current simulation state.
     * If the user confirms the save action, the simulation state is saved to a file.
     * 
     * The dialog allows the user to choose whether to save to an existing file or create a new one.
     * If a new file is chosen, the model world's title is updated with the new file name.
     * 
     * The method performs the following steps:
     * 1. Displays the save dialog.
     * 2. Checks if the user confirmed the save action.
     * 3. If saving to a new file, updates the model world's title.
     * 4. Saves the current state of the model world using the SimulationSerializer.
     */
    void startSaveDialog(){
       SaveDialog sDialog = new SaveDialog(window);
        sDialog.setVisible(true);

        if(sDialog.getSave()){
            if(!sDialog.toExistingFile()){
                modelWorld.setTitle(sDialog.getFileName());
            }
            SimulationSerializer.saveWorld(modelWorld);
        }
    }

    /**
     * Opens the simulation picker window and disposes of the current window.
     * This method creates a new instance of the SimulationPicker class and 
     * then calls the disposeWindow method to close the current window.
     */
    void openSimulationPicker(){
        new SimulationPicker();

        disposeWindow();
    }

    /**
     * Handles the click event on the simulation player.
     * 
     * @param p The point where the click occurred.
     * 
     * This method performs the following actions:
     * 1. Requests focus for the view window.
     * 2. If the simulation is currently playing, it returns immediately.
     * 3. If a body is already selected, it deselects it and returns.
     * 4. Translates the click point to the model world coordinates.
     * 5. Selects the body at the translated point in the model world.
     */
    void click(Point p){
        view.requestFocusInWindow();
        if(playing){
            return;
        }
        if(selectedBody != null){
            selectedBody.setSelected(false);
            selectedBody = null;
            return;
        }
        p.translate(modelWorld.getWindowBounds().x, modelWorld.getWindowBounds().y);
        selectedBody = modelWorld.selectBodyAt(p);
    }

    /**
     * Copies the currently selected body, if any, and adds the copied body to the view and simulation.
     * The copied body will have its selection status set to false and its position slightly offset from the original.
     * The method uses Gson to serialize and deserialize the selected body to create a deep copy.
     * 
     * Dependencies:
     * - Gson library for JSON serialization and deserialization.
     * - Custom type adapters for PhysicksBody, CollisionShape, and Color.
     * 
     * Preconditions:
     * - `selectedBody` must not be null.
     * 
     * Postconditions:
     * - A new instance of `PhysicksBody` is created, with its position offset by (10, 10) from the original.
     * - The new instance is added to the view and simulation.
     */
    void copySelectedBody(){
        if(selectedBody != null){
            Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(PhysicksBody.class, new PhysicksBodyTypeAdapter())
            .registerTypeAdapter(CollisionShape.class,new CollisionShapeTypeAdapter())
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .create();

            String json = gson.toJson(selectedBody);
            PhysicksBody copiedBody = gson.fromJson(json, selectedBody.getClass());
            copiedBody.setSelected(false);
            copiedBody.setPosition(new Vector2D(selectedBody.getPosition().getX() + 10, selectedBody.getPosition().getY() + 10)); // Offset the position slightly
            addToViewAndSimulation(copiedBody);
        }
    }

    /**
     * Deletes the currently selected body from the simulation and view.
     * If there is a selected body, it will be removed from both the view and the simulation,
     * and the selected body reference will be set to null.
     */
    void deleteSelectedBody(){
        if(selectedBody != null){
            removeBodyFromViewAndSimulation(selectedBody);
            selectedBody = null;
        }
    }
}
