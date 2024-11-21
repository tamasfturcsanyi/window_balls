package game.controller;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;

import game.model.Ring;
import game.model.Vector2D;
import game.model.physicksbodies.Ball;
import game.view.FixLabel;
import java.util.HashMap;

import java.awt.BorderLayout;


public class Basket extends SimulationWindow{
    int score = 0;
    int timeLeft = 10;

    int timeBonus = 5;


    boolean ballWasAboveRing = false;

    boolean timeIsTicking = false;

    JLabel scoreLabel = new JLabel(score + "");

    FixLabel timeLabel;

    HashMap<Integer,Color> colors = new HashMap<>();

    Ball basketBall = new Ball(new Vector2D(500,400), 30, new Color(255,128,0), 0.6, 1);
    Ring actualRing = new Ring();


    public Basket(){
        super("Basket", new Rectangle(400, 300, 500, 400));
        initColors();

        Thread timeThread = new Thread(new Timer());
        timeIsTicking = true;
        timeThread.start();
        view.setBackground(colors.get(10));

        view.add(scoreLabel);
        initTimeLabel();
        addToViewAndSimulation(basketBall);
        addToViewAndSimulation(actualRing.getLeftPole());
        addToViewAndSimulation(actualRing.getRightPole());
        window.setResizable(false);
        initView();

    }

    void updateBackground(){
        if(timeLeft < 10){
            view.setBackground(colors.get(timeLeft));
        }else{
            view.setBackground(colors.get(10));
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
                } catch (Exception e) {
                    e.printStackTrace();
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
        JLabel gameOverLabel = new JLabel("Game Over", JLabel.CENTER);
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
        view.setBackground(Color.RED);
        removeFromViewAndSimulation(actualRing.getLeftPole());
        removeFromViewAndSimulation(actualRing.getRightPole());
        showGameOverDialog(window);
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
        timeLabel = new FixLabel(timeLeft+"",new Vector2D(512,200));
        timeLabel.setFont(new Font("Impact",Font.BOLD,250));
        timeLabel.setForeground(Color.WHITE);
        view.add(timeLabel);
        view.setComponentZOrder(timeLabel, view.getComponentCount()-1);
        updateBackground();
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
        
        
        scoreLabel.setText(score + "");
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
        if(ballBetweenPoles){
            if(ballWasAboveRing && ballUnderRing){
                scoredPoint();
            }
        }
        ballWasAboveRing = !ballUnderRing;
    }

    @Override
    void cycle() {
        timeLabel.updatePosition(modelWorld.getWindowBounds());
        updateGameLogic();
        super.cycle();
    }
}
