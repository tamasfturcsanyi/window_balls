package game.controller;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import game.model.Ring;
import game.model.Vector2D;
import game.model.physicsbodies.Ball;
import game.view.FixLabel;
import java.util.HashMap;

import java.awt.BorderLayout;


/**
 * The Basket class represents a basketball game simulation window.
 * It extends the SimulationWindow class and provides functionality
 * for initializing and managing the game, including the timer, score,
 * and game objects such as the basketball and ring.
 * 
 * The game starts with a timer and the player scores points by getting
 * the basketball through the ring. The background color changes based
 * on the remaining time, and the game ends when the timer reaches zero.
 * 
 * The class also handles displaying a game over dialog with the final
 * score and high score, and provides options to restart the game or
 * exit to the main menu.
 * 
 * Methods:
 * - Basket(): Constructor to initialize the game window and components.
 * - initTimer(): Initializes and starts the game timer.
 * - initBalls(): Initializes the basketball and ring objects.
 * - initBasketBall(): Initializes the basketball object.
 * - initRing(): Initializes the ring object.
 * - initColors(): Initializes the background colors based on remaining time.
 * - initTimeLabel(): Initializes the time label.
 * - initScoreLabel(): Initializes the score label.
 * - updateBackground(): Updates the background color and time label.
 * - updateTimeLabelPosition(): Updates the position of the time label.
 * - updateBackgroundColor(): Updates the background color based on remaining time.
 * - updateTimeLabelText(): Updates the text of the time label.
 * - updateTimeLabelPositionInView(): Updates the position of the time label in the view.
 * - showGameOverDialog(JFrame parentFrame): Displays a game over dialog with the final score and high score.
 * - startMainMenu(): Starts the main menu thread and disposes the game window.
 * - lose(): Handles the game over logic, including updating the background color, saving the high score, and showing the game over dialog.
 * - scoredPoint(): Handles the logic for scoring a point, including updating the score, time bonus, and background.
 * - newRing(): Creates a new ring object and adds it to the view and simulation.
 * - updateGameLogic(): Updates the game logic, including checking if the basketball has passed through the ring.
 * - restartBasket(): Restarts the game by creating a new Basket object and starting a new thread.
 * - loadHighScore(): Loads the high score from a file.
 * - saveHighScore(): Saves the high score to a file.
 * - cycle(): Overrides the cycle method to update the time label, score label, and game logic.
 * 
 * Inner Classes:
 * - Timer: Implements the Runnable interface to handle the game timer logic.
 */
public class Basket extends SimulationWindow{
    private static final int SCREEN_WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 400;

    private static final String FONT = "Impact";

    private int score = 0;

    private int highScore;

    private int timeLeft = 10;

    private int timeBonus = 5;


    private boolean ballWasAboveRing = false;

    private boolean timeIsTicking = false;

    private FixLabel scoreLabel;

    private FixLabel timeLabel;

    private HashMap<Integer,Color> colors = new HashMap<>();

    private Ball basketBall;
    private Ring actualRing;


    /**
     * Constructs a new Basket object.
     * Initializes the window with a title "Basket" and sets its size and position.
     * Calls methods to initialize colors, balls, timer, time label, high score, and score label.
     * Sets the window to be non-resizable and sets the background color of the view.
     * Updates the background.
     */
    public Basket(){
        super("Basket", new Rectangle(SCREEN_WIDTH/2 - WINDOW_WIDTH/2, SCREEN_HEIGHT/2 - WINDOW_HEIGHT/2, WINDOW_WIDTH, WINDOW_HEIGHT));
        initColors();
        initBalls();

        initTimer();
        initTimeLabel();
        loadHighScore();
        initScoreLabel();
        
        window.setResizable(false);
        view.setBackgroundColor(colors.get(10));

        updateBackground();
    }

    void initTimer(){
        Thread timeThread = new Thread(new Timer());
        timeIsTicking = true;
        timeThread.start();
    }

    void initBalls(){
        initBasketBall();
        initRing();
    }

    /**
     * Initializes the basketball object with specified properties and adds it to the view and simulation.
     * The basketball is created with a position of (700, 400), a radius of 30, an orange color (RGB: 255, 128, 0),
     * a restitution coefficient of 0.6, and a mass of 1.
     */
    void initBasketBall(){
        basketBall = new Ball(new Vector2D(700,400), 30, new Color(255,128,0), 0.6, 1);
        addToViewAndSimulation(basketBall);
    }

    /**
     * Initializes the ring by creating a new instance of the Ring class.
     * Adds the left and right poles of the ring to the view and simulation.
     */
    void initRing(){
        actualRing = new Ring();
        addToViewAndSimulation(actualRing.getLeftPole());
        addToViewAndSimulation(actualRing.getRightPole());
    }

    /**
     * Initializes the color mappings for different levels.
     * The colors are mapped based on the level values:
     * - Levels 10 to 7 are mapped to green (#00ff00).
     * - Levels 6 to 4 are mapped to yellow (#fff500).
     * - Levels 3 to 1 are mapped to orange (#ffac00).
     * - Level 0 is mapped to red (#ff0000).
     */
    void initColors(){
        Color green = Color.decode("#00ff00");
        Color yellow = Color.decode("#fff500");
        Color orange = Color.decode("#ffac00");
        Color red = Color.decode("#ff0000");
        colors.put(10, green);
        colors.put(9, green);
        colors.put(8, green);
        colors.put(7,green);
        colors.put(6,yellow);
        colors.put(5,yellow);
        colors.put(4,yellow);
        colors.put(3,orange);
        colors.put(2,orange);
        colors.put(1,orange);
        colors.put(0,red);
    }

    /**
     * Initializes the time label with the remaining time.
     * The label is created with the current time left, set to a bold font of size 250,
     * and its foreground color is set to white. The label is then added to the view,
     * and the background is updated.
     */
    void initTimeLabel(){
        timeLabel = new FixLabel(timeLeft+"",new Vector2D(0,0));
        timeLabel.setFont(new Font(FONT,Font.BOLD,250));
        timeLabel.setForeground(Color.WHITE);
        view.add(timeLabel);
        updateBackground();
    }

    void initScoreLabel(){
        scoreLabel = new FixLabel("Score: " + score,new Vector2D(0,0));
        scoreLabel.setFont(new Font(FONT,Font.BOLD,52));
        scoreLabel.setPosition(new Vector2D((SCREEN_WIDTH - 150)/2.0,(SCREEN_HEIGHT + 200)/2.0));
        scoreLabel.setForeground(Color.WHITE);

        view.add(scoreLabel);
    }

    /**
     * Updates the background of the basket game view.
     * This method performs the following actions:
     * 1. Updates the position of the time label.
     * 2. Updates the background color.
     * 3. Updates the text of the time label.
     * 4. Updates the position of the time label within the view.
     */
    void updateBackground() {
        updateTimeLabelPosition();
        updateBackgroundColor();
        updateTimeLabelText();
        updateTimeLabelPositionInView();
    }

    void updateTimeLabelPosition() {
        if (timeLeft < 10) {
            timeLabel.setPosition(new Vector2D((SCREEN_WIDTH - 120) / 2.0, (SCREEN_HEIGHT - 300) / 2.0));
        } else {
            timeLabel.setPosition(new Vector2D((SCREEN_WIDTH - 200) / 2.0, (SCREEN_HEIGHT - 300) / 2.0));
        }
    }

    void updateBackgroundColor() {
        if (timeLeft < 10) {
            view.setBackgroundColor(colors.get(timeLeft));
        } else {
            view.setBackgroundColor(colors.get(10));
        }
    }

    void updateTimeLabelText() {
        timeLabel.setText(timeLeft + "");
    }

    void updateTimeLabelPositionInView() {
        timeLabel.updatePosition(modelWorld.getWindowBounds());
    }

    /**
     * Timer is a Runnable implementation that decrements a time counter every second.
     * It updates the background and checks if the time has run out to trigger a loss condition.
     * 
     * <p>This class is intended to be run in a separate thread. It will continue to run
     * until the timeIsTicking flag is set to false or the thread is interrupted.</p>
     * 
     * <p>Methods:</p>
     * <ul>
     *   <li>{@code run()}: The main logic of the timer, which decrements the timeLeft variable,
     *   updates the background, and checks if the time has run out.</li>
     * </ul>
     * 
     * <p>Usage:</p>
     * <pre>{@code
     * Timer timer = new Timer();
     * Thread timerThread = new Thread(timer);
     * timerThread.start();
     * }</pre>
     */
    class Timer implements Runnable{

        @Override
        public void run() {
            while(timeIsTicking){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Re-interrupt the thread
                    return;
                }
                --timeLeft;
                updateBackground();
                if(timeLeft == 0){
                    lose();
                }
            }
        }
    }

    /**
     * Displays a game over dialog with the final score and high score.
     * 
     * @param parentFrame the parent frame for the dialog
     */
    @SuppressWarnings("unused")
    void showGameOverDialog(JFrame parentFrame) {
        // Create JDialog
        JDialog gameOverDialog = new JDialog(parentFrame, "Game Over", true);
        gameOverDialog.setSize(300, 200);
        gameOverDialog.setLayout(new BorderLayout());
        gameOverDialog.setLocationRelativeTo(parentFrame); // Center dialog on parent

        // Game Over message
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font(FONT, Font.BOLD, 52));
        gameOverDialog.add(gameOverLabel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());

        JLabel highScoreLabel = new JLabel("High Score: " + highScore, SwingConstants.CENTER);
        highScoreLabel.setFont(new Font(FONT, Font.BOLD, 18));
        scorePanel.add(highScoreLabel, BorderLayout.NORTH);

        JLabel finalScoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        finalScoreLabel.setFont(new Font(FONT, Font.BOLD, 18));
        scorePanel.add(finalScoreLabel, BorderLayout.SOUTH);

        gameOverDialog.add(scorePanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        gameOverDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        restartButton.addActionListener(r -> restartBasket()); // Restart game
        exitButton.addActionListener (e -> startMainMenu()); // Exit application

        // Display dialog
        gameOverDialog.setVisible(true);
    }

    void startMainMenu(){
        Thread menuThread = new Thread(new MainMenu());
        menuThread.start();

        disposeWindow();
    }

    /**
     * Handles the actions to be taken when the player loses the game.
     * This method performs the following steps:
     * 1. Stops the game timer.
     * 2. Changes the background color of the view to red.
     * 3. Removes the left and right poles of the current ring from the view and simulation.
     * 4. Checks if the current score is higher than the high score and saves it if true.
     * 5. Displays the game over dialog.
     */
    void lose(){
        timeIsTicking = false;
        view.setBackgroundColor(Color.RED);
        removeBodyFromViewAndSimulation(actualRing.getLeftPole());
        removeBodyFromViewAndSimulation(actualRing.getRightPole());
        if(score > highScore){
            saveHighScore();
        }
        showGameOverDialog(window);
    }

    /**
     * Updates the score and applies time bonuses based on the current score.
     * 
     * <p>This method increments the score by one and adjusts the time bonus 
     * according to the following rules:
     * <ul>
     *   <li>If the score is greater than 30, the time bonus is set to 1.</li>
     *   <li>If the score is greater than 20, the time bonus is set to 2.</li>
     *   <li>If the score is greater than 10, the time bonus is set to 3.</li>
     * </ul>
     * The remaining time is then increased by the time bonus.
     * 
     * <p>Additionally, this method updates the score label with the new score, 
     * updates the background, and creates a new ring.
     */
    void scoredPoint(){
        ++score;
        if(score > 30){
            timeBonus = 1;
        }else if(score > 20){
            timeBonus = 2;
        }else if(score > 10){
            timeBonus = 3;
        }
        
        timeLeft += timeBonus;
        
        scoreLabel.setText("Score: " + score);
        updateBackground();
        newRing();
    }

    /**
     * Replaces the current ring with a new one.
     * 
     * This method removes the left and right poles of the current ring from the view and simulation,
     * creates a new ring, and then adds the new ring's left and right poles to the view and simulation.
     */
    void newRing(){
        removeBodyFromViewAndSimulation(actualRing.getLeftPole());
        removeBodyFromViewAndSimulation(actualRing.getRightPole());
        actualRing = new Ring();
        addToViewAndSimulation(actualRing.getLeftPole());
        addToViewAndSimulation(actualRing.getRightPole());
    }

    /**
     * Updates the game logic to determine if a point has been scored.
     * 
     * This method checks if the basketball is between the poles of the ring and 
     * has passed below the ring after being above it. If these conditions are met, 
     * it calls the scoredPoint() method to register a point.
     * 
     * The method also updates the state of whether the ball was above the ring 
     * for the next call.
     */
    void updateGameLogic(){
        boolean ballBetweenPoles = (basketBall.getCenter().getX() > actualRing.getLeftPole().getCenter().getX() &&
        basketBall.getCenter().getX() < actualRing.getRightPole().getCenter().getX());
        boolean ballUnderRing = basketBall.getCenter().getY() > actualRing.getLeftPole().getCenter().getY();
        if(ballBetweenPoles && ballWasAboveRing && ballUnderRing){
            scoredPoint();
        }
        ballWasAboveRing = !ballUnderRing;
    }
    
    /**
     * Restarts the basket by creating a new instance of the Basket class,
     * starting it in a new thread, and disposing of the current window.
     */
    void restartBasket(){
        Basket basket = new Basket();
        Thread basketThread = new Thread(basket);
        basketThread.start();

        disposeWindow();
    }

    /**
     * Loads the high score from a file named "record.txt" located in the "src/main/resources" directory.
     * If the file does not exist or an error occurs while reading the file, the high score is set to 0.
     */
    void loadHighScore(){
        File highScoreFile = new File("src/main/resources/record.txt");
        if(!highScoreFile.isFile()){
            highScore = 0;
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(highScoreFile))) {
            highScore = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            highScore = 0;
        }
    }

    /**
     * Saves the current high score to a file named "record.txt" located in the 
     * "src/main/resources" directory. If the file does not exist, it will be created.
     * The method handles any IOExceptions that may occur during file creation and writing.
     */
    void saveHighScore(){
        File highScoreFile = new File("src/main/resources/record.txt");
        try {
            highScoreFile.createNewFile();//NOSONAR
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(highScoreFile)) {
            writer.write(String.valueOf(score));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the positions of the time and score labels based on the current window bounds,
     * updates the game logic, and then calls the superclass's cycle method.
     * This method is called periodically to update the game state.
     */
    @Override
    void cycle() {
        timeLabel.updatePosition(modelWorld.getWindowBounds());
        scoreLabel.updatePosition(modelWorld.getWindowBounds());
        updateGameLogic();
        super.cycle();
    }
}
