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
import game.model.physicksbodies.Ball;
import game.view.FixLabel;
import java.util.HashMap;

import java.awt.BorderLayout;


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

    void initBasketBall(){
        basketBall = new Ball(new Vector2D(700,400), 30, new Color(255,128,0), 0.6, 1);
        addToViewAndSimulation(basketBall);
    }

    void initRing(){
        actualRing = new Ring();
        addToViewAndSimulation(actualRing.getLeftPole());
        addToViewAndSimulation(actualRing.getRightPole());
    }

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

    void newRing(){
        removeBodyFromViewAndSimulation(actualRing.getLeftPole());
        removeBodyFromViewAndSimulation(actualRing.getRightPole());
        actualRing = new Ring();
        addToViewAndSimulation(actualRing.getLeftPole());
        addToViewAndSimulation(actualRing.getRightPole());
    }

    void updateGameLogic(){
        boolean ballBetweenPoles = (basketBall.getCenter().getX() > actualRing.getLeftPole().getCenter().getX() &&
        basketBall.getCenter().getX() < actualRing.getRightPole().getCenter().getX());
        boolean ballUnderRing = basketBall.getCenter().getY() > actualRing.getLeftPole().getCenter().getY();
        if(ballBetweenPoles && ballWasAboveRing && ballUnderRing){
            scoredPoint();
        }
        ballWasAboveRing = !ballUnderRing;
    }
    
    void restartBasket(){
        Basket basket = new Basket();
        Thread basketThread = new Thread(basket);
        basketThread.start();

        disposeWindow();
    }
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

    @Override
    void cycle() {
        timeLabel.updatePosition(modelWorld.getWindowBounds());
        scoreLabel.updatePosition(modelWorld.getWindowBounds());
        updateGameLogic();
        super.cycle();
    }
}
