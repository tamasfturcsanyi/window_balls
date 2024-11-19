package game.model;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;

import game.model.physicksbodies.Pole;

public class Ring {
    static final double RADIUS_OF_POLES = 15;
    static final double DISTANCE_BETWEEN_POLES = 120;
    static final int DISTANCE_FROM_EDGE = 100;

    Pole leftPole;
    Pole rightPole;


    Random rng = new Random();

    int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public Ring(){
        double x = rng.nextInt(DISTANCE_FROM_EDGE,windowWidth - DISTANCE_FROM_EDGE);
        double y = rng.nextInt(DISTANCE_FROM_EDGE, windowHeight - DISTANCE_FROM_EDGE);
        leftPole = new Pole(new Vector2D(x,y),RADIUS_OF_POLES,Color.BLACK);
        rightPole = new Pole(new Vector2D(x + DISTANCE_BETWEEN_POLES,y),RADIUS_OF_POLES,Color.BLACK);
    }

    public Pole getLeftPole() {
        return leftPole;
    }
    public Pole getRightPole() {
        return rightPole;
    }
}
