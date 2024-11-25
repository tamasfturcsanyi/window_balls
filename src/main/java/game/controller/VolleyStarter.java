package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import game.model.Player;
import game.model.Player.Face;
import game.view.ColorPicker;

/**
 * The VolleyStarter class extends SimulationWindow and is responsible for initializing
 * and managing the UI components and game settings for a volleyball simulation game.
 * It allows players to customize their avatars and set the points required for victory.
 * 
 * <p>Key functionalities include:</p>
 * <ul>
 *   <li>Initializing player color and face pickers</li>
 *   <li>Setting up the points to victory spinner</li>
 *   <li>Starting the volleyball game</li>
 * </ul>
 * 
 * <p>UI components are arranged using a GridLayout and include labels, color pickers,
 * face pickers, a spinner for points to victory, and a start button.</p>
 * 
 * <p>Upon starting the game, a new Volley instance is created and run in a separate thread.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #VolleyStarter()}: Constructor that initializes the game window and components.</li>
 *   <li>{@link #initPlayer1ColorPicker()}: Initializes the color picker for player 1.</li>
 *   <li>{@link #initPlayer2ColorPicker()}: Initializes the color picker for player 2.</li>
 *   <li>{@link #initPlayer1FacePicker()}: Initializes the face picker for player 1.</li>
 *   <li>{@link #initPlayer2FacePicker()}: Initializes the face picker for player 2.</li>
 *   <li>{@link #initPointsToVictorySpinner()}: Initializes the spinner for setting points to victory.</li>
 *   <li>{@link #initStartButton()}: Initializes the start button for starting the game.</li>
 *   <li>{@link #initButtons()}: Sets up the layout and adds all UI components to the view.</li>
 *   <li>{@link #setDefaultValues()}: Sets default values for the color and face pickers.</li>
 *   <li>{@link #replacePlayer1()}: Replaces player 1 with a new instance based on selected settings.</li>
 *   <li>{@link #replacePlayer2()}: Replaces player 2 with a new instance based on selected settings.</li>
 *   <li>{@link #startVolley()}: Starts the volleyball game in a new thread.</li>
 * </ul>
 */
public class VolleyStarter extends SimulationWindow {
    Player player1;
    Player player2;

    private JComboBox<Face> player1FacePicker;
    private JComboBox<Face> player2FacePicker;
    private ColorPicker player1ColorPicker;
    private ColorPicker player2ColorPicker;

    JSpinner pointsToVictorySpinner;
    Font font = new Font("Impact", Font.PLAIN, 42);
    Font titleFont = new Font("Impact", Font.PLAIN, 72);


    /**
     * Constructs a new VolleyStarter instance.
     * Initializes the game window with the title "Volley" and a specified rectangle size.
     * Sets up the game world with a volley preset and adds two players to the game.
     * The players are initialized with specific IDs, colors, and faces.
     * Adds the players' bodies to the model world and includes them in the list of visualizables.
     * Finally, initializes the game buttons.
     */
    public VolleyStarter(){
        super("Volley", new Rectangle(0,0,1024,768));
        modelWorld.volleyPreset();
        player1 = new Player(1,Color.CYAN,Face.LEMMY);
        player2 = new Player(2,Color.RED,Face.SAD);
        modelWorld.addBody(player1.getBody());
        modelWorld.addBody(player2.getBody());
        visualizables.add(player1);
        visualizables.add(player2);

        initButtons();
    }

    /**
     * Initializes the color picker for player 1.
     * This method sets up the color picker with a specific font,
     * adds an action listener to handle color changes, and removes
     * the "Black" and "White" color options from the picker.
     * Finally, it adds the color picker to the view.
     */
    @SuppressWarnings("unused")
    void initPlayer1ColorPicker(){
        player1ColorPicker = new ColorPicker();
        player1ColorPicker.setFont(font);
        player1ColorPicker.addActionListener(e -> replacePlayer1());
        player1ColorPicker.removeItem("Black");
        player1ColorPicker.removeItem("White");

        view.add(player1ColorPicker);
    }

    /**
     * Initializes the color picker for player 2.
     * This method creates a new ColorPicker instance for player 2, sets its font,
     * adds an action listener to handle color changes, and removes the "Black" and "White"
     * color options from the picker. Finally, it adds the color picker to the view.
     */
    @SuppressWarnings("unused")
    void initPlayer2ColorPicker(){
        player2ColorPicker = new ColorPicker();
        player2ColorPicker.setFont(font);
        player2ColorPicker.addActionListener(e -> replacePlayer2());
        player2ColorPicker.removeItem("Black");
        player2ColorPicker.removeItem("White");

        view.add(player2ColorPicker);
    }

    /**
     * Initializes the player1FacePicker JComboBox with the available Face values.
     * Sets the font for the JComboBox, adds an ActionListener to handle player replacement,
     * and adds the JComboBox to the view.
     * 
     * @SuppressWarnings("unused") is used to suppress warnings about the unused method.
     */
    @SuppressWarnings("unused")
    void initPlayer1FacePicker(){
        player1FacePicker = new JComboBox<>(Face.values());
        player1FacePicker.setFont(font);
        player1FacePicker.addActionListener(e -> replacePlayer1());
        view.add(player1FacePicker);
    }

    /**
     * Initializes the player 2 face picker combo box.
     * This method creates a JComboBox with the available faces, sets its font,
     * adds an action listener to handle player 2 replacement, and adds the combo box to the view.
     * 
     * @SuppressWarnings("unused") is used to suppress warnings about the unused variable.
     */
    @SuppressWarnings("unused")
    void initPlayer2FacePicker(){
        player2FacePicker = new JComboBox<>(Face.values());
        player2FacePicker.setFont(font);
        player2FacePicker.addActionListener(e -> replacePlayer2());
        view.add(player2FacePicker);
    }

    /**
     * Initializes the spinner component for selecting the points required for victory.
     * This method creates a label and a spinner, sets their fonts, and adds them to the view.
     * The spinner allows the user to select a number between 1 and 100, with an initial value of 5.
     */
    void initPointsToVictorySpinner(){
        JLabel pointsToVictoryLabel = new JLabel("Points to victory:");
        pointsToVictoryLabel.setFont(font);

        view.add(pointsToVictoryLabel);

        SpinnerNumberModel model = new SpinnerNumberModel(5, 1, 100, 1);
        pointsToVictorySpinner = new JSpinner(model);
        pointsToVictorySpinner.setFont(font);

        view.add(pointsToVictorySpinner);
    }

    /**
     * Initializes the start button for the game.
     * The button is labeled "Start" and is added to the view.
     * When the button is clicked, it triggers the startVolley() method.
     * 
     * @throws NullPointerException if the font or view is not initialized.
     */
    @SuppressWarnings("unused")
    void initStartButton(){
            JButton startButton = new JButton("Start");
            startButton.setFont(font);
            startButton.addActionListener(e -> startVolley());

            view.add(startButton);
    }

    /**
     * Initializes the buttons and other UI components for the Volley game.
     * This method sets up the layout, adds labels, color pickers, face pickers,
     * points to victory spinner, and the start button to the view.
     * It also sets default values and updates the window.
     */
    void initButtons(){

        view.setLayout(new GridLayout(5, 3, 10, 10)); // Adding some spacing between components


        JLabel player1Label = new JLabel("Player 1");
        player1Label.setFont(font);
        view.add(player1Label);

        JLabel title = new JLabel("      VOLLEY");
        title.setFont(titleFont);
        title.setForeground(Color.ORANGE);
        view.add(title);


        JLabel player2Label = new JLabel("Player 2");
        player2Label.setFont(font);
        view.add(player2Label);

        
        initPlayer1ColorPicker();

        view.add(new JLabel(""));

        initPlayer2ColorPicker();


        initPlayer1FacePicker();

        view.add(new JLabel(""));
        

        initPlayer2FacePicker();

        initPointsToVictorySpinner();

        view.add(new JLabel(""));
        view.add(new JLabel(""));


        initStartButton();

        setDefaultValues();

        window.add(view);
        window.revalidate();
        window.repaint();
    }

    /**
     * Sets the default values for player1 and player2.
     * 
     * This method initializes the default selections for the color and face pickers
     * for both players. Player 1 is set to have a "Cyan" color and a "Serious" face,
     * while Player 2 is set to have a "Red" color and a "Cute" face.
     */
    void setDefaultValues(){
        player1ColorPicker.setSelectedItem("Cyan");
        player1FacePicker.setSelectedItem(Face.SERIOUS);

        player2ColorPicker.setSelectedItem("Red");
        player2FacePicker.setSelectedItem(Face.CUTE);
    }

    /**
     * Replaces the current player1 instance with a new Player object.
     * 
     * This method performs the following steps:
     * 1. Removes the current player1's body from the view and simulation.
     * 2. Removes the current player1 from the list of visualizables.
     * 3. Creates a new Player object with the same player number (1), 
     *    the color selected in player1ColorPicker, and the face selected in player1FacePicker.
     * 4. Adds the new player1's body to the model world.
     * 5. Adds the new player1 to the list of visualizables.
     */
    void replacePlayer1(){
        removeBodyFromViewAndSimulation(player1.getBody());
        removeVisualizable(player1);

        player1 = new Player(1, player1ColorPicker.getColor(), (Face) player1FacePicker.getSelectedItem());

        modelWorld.addBody(player1.getBody());
        visualizables.add(player1);
    }

    /**
     * Replaces the current player 2 with a new player instance.
     * 
     * This method performs the following steps:
     * 1. Removes the current player 2's body from the view and simulation.
     * 2. Removes the current player 2 from the list of visualizable objects.
     * 3. Creates a new player 2 instance with the selected color and face.
     * 4. Adds the new player 2's body to the model world.
     * 5. Adds the new player 2 to the list of visualizable objects.
     */
    void replacePlayer2(){
        removeBodyFromViewAndSimulation(player2.getBody());
        removeVisualizable(player2);

        player2 = new Player(2, player2ColorPicker.getColor(), (Face) player2FacePicker.getSelectedItem());

        modelWorld.addBody(player2.getBody());
        visualizables.add(player2);
    }

    /**
     * Starts a new volley game between player1 and player2 with the specified points to victory.
     * This method initializes a new Volley object and runs it in a separate thread.
     * After starting the volley thread, it disposes of the current window.
     */
    void startVolley(){
        Volley volley = new Volley(player1, player2, (int) pointsToVictorySpinner.getValue());
        Thread volleyThread = new Thread(volley);
        volleyThread.start();

        disposeWindow();
    }
}
