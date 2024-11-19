package game.controller;

import java.awt.Rectangle;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import game.model.Ring;
import game.model.Vector2D;
import game.model.physicksbodies.Ball;
import game.view.FixLabel;


public class Basket extends SimulationWindow{
    int score = 0;
    int timeLeft = 10;


    boolean ballWasAboveRing = false;

    boolean timeIsTicking = false;

    JLabel scoreLabel = new JLabel(score + "");

    FixLabel timeLabel;


    Ball basketBall = new Ball(new Vector2D(500,400), 30, new Color(255,128,0), 0.6, 1);
    Ring actualRing = new Ring();

    public Basket(){
        super("Basket", new Rectangle(400, 300, 500, 400));

        Thread timeThread = new Thread(new Timer());
        timeIsTicking = true;
        timeThread.start();

        view.setBackground(new Color(128,255,0));
        view.add(scoreLabel);
        initTimeLabel();
        addToViewAndSimulation(basketBall);
        addToViewAndSimulation(actualRing.getLeftPole());
        addToViewAndSimulation(actualRing.getRightPole());
        window.setResizable(false);
        initVisualizables();
    }

    class Timer implements Runnable{

        @Override
        public void run() {
            while(timeIsTicking){
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                --timeLeft;
                timeLabel.setVisible(false);
                timeLabel.setText(timeLeft + "");
                timeLabel.updatePosition(modelWorld.getWindowBounds());
                timeLabel.setVisible(true);
                if(timeLeft == 0){
                    lose();
                }
            }
        }
    }

    void lose(){
        timeIsTicking = false;
        view.setBackground(Color.RED);
        removeFromViewAndSimulation(actualRing.getLeftPole());
        removeFromViewAndSimulation(actualRing.getRightPole());

    }

    void initTimeLabel(){
        timeLabel = new FixLabel(timeLeft+"",new Vector2D(512,200));
        timeLabel.setFont(new Font("Impact",Font.BOLD,250));
        timeLabel.setForeground(Color.WHITE);
        view.add(timeLabel);
        view.setComponentZOrder(timeLabel, view.getComponentCount()-1);
    }

    void scoredPoint(){
        ++score;
        scoreLabel.setText(score + "");
        timeLeft += 5;
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
