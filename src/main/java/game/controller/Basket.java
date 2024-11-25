package game.controller;

import java.awt.Rectangle;

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
    int score = 0;
    int timeLeft = 10;

    int timeBonus = 5;


    boolean ballWasAboveRing = false;

    boolean timeIsTicking = false;

    FixLabel scoreLabel;

    FixLabel timeLabel;

    HashMap<Integer,Color> colors = new HashMap<>();

    Ball basketBall;
    Ring actualRing;


    public Basket(){
        super("Basket", new Rectangle(SCREEN_WIDTH/2 - WINDOW_WIDTH/2, SCREEN_HEIGHT/2 - WINDOW_HEIGHT/2, WINDOW_WIDTH, WINDOW_HEIGHT));
        initColors();
        initBalls();

        initTimer();
        initTimeLabel();
        initScoreLabel();
        
        window.setResizable(false);
        initView();
        view.setBackgroundColor(colors.get(10));

        updateBackground();
    }

    void initTimer(){
        Thread timeThread = new Thread(new Timer());
        timeIsTicking = true;
        timeThread.start();
    }

    void initBalls(){
        basketBall = new Ball(new Vector2D(500,400), 30, new Color(255,128,0), 0.6, 1);
        actualRing = new Ring();
        addToViewAndSimulation(basketBall);
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
        timeLabel.setFont(new Font("Impact",Font.BOLD,250));
        timeLabel.setForeground(Color.WHITE);
        view.add(timeLabel);
        updateBackground();
    }

    void initScoreLabel(){
        scoreLabel = new FixLabel("Score: " + score,new Vector2D(0,0));
        scoreLabel.setFont(new Font("Impact",Font.BOLD,52));
        scoreLabel.setPosition(new Vector2D((SCREEN_WIDTH - 150)/2.0,(SCREEN_HEIGHT + 200)/2));
        scoreLabel.setForeground(Color.WHITE);

        view.add(scoreLabel);
    }

    void updateBackground(){
        if(timeLeft < 10){
            timeLabel.setPosition(new Vector2D((SCREEN_WIDTH - 120)/2.0,(SCREEN_HEIGHT - 300)/2));
            view.setBackgroundColor(colors.get(timeLeft));
        }else{
            timeLabel.setPosition(new Vector2D((SCREEN_WIDTH - 200)/2.0,(SCREEN_HEIGHT - 300)/2));
            view.setBackgroundColor(colors.get(10));
        }
        timeLabel.setText(timeLeft + "");
        
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

    void showGameOverDialog(JFrame parentFrame) {
        // Create JDialog
        JDialog gameOverDialog = new JDialog(parentFrame, "Game Over", true);
        gameOverDialog.setSize(300, 200);
        gameOverDialog.setLayout(new BorderLayout());
        gameOverDialog.setLocationRelativeTo(parentFrame); // Center dialog on parent

        // Game Over message
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Impact", Font.BOLD, 24));
        gameOverDialog.add(gameOverLabel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        gameOverDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        restartButton.addActionListener(e -> {
            MainMenu menu = new MainMenu();
            menu.startBasket();
            gameOverDialog.dispose(); // Close the dialog
            disposeWindow();

            // Add restart logic here
        });
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
        removeFromViewAndSimulation(actualRing.getLeftPole());
        removeFromViewAndSimulation(actualRing.getRightPole());
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
        removeFromViewAndSimulation(actualRing.getLeftPole());
        removeFromViewAndSimulation(actualRing.getRightPole());
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

    @Override
    void cycle() {
        timeLabel.updatePosition(modelWorld.getWindowBounds());
        scoreLabel.updatePosition(modelWorld.getWindowBounds());
        updateGameLogic();
        super.cycle();
    }
}
