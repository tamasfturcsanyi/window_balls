package game.model;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;

import game.model.physicsbodies.Pole;

/**
 * The Ring class represents a ring structure in the game, consisting of two poles.
 * The poles are positioned at a random location within the window boundaries, 
 * ensuring they are not too close to the edges.
 * 
 * Constants:
 * - RADIUS_OF_POLES: The radius of each pole.
 * - DISTANCE_BETWEEN_POLES: The distance between the two poles.
 * - DISTANCE_FROM_EDGE: The minimum distance from the edge of the window.
 * 
 * Fields:
 * - leftPole: The left pole of the ring.
 * - rightPole: The right pole of the ring.
 * - rng: A Random object used to generate random positions for the poles.
 * - windowWidth: The width of the window.
 * - windowHeight: The height of the window.
 * 
 * Constructor:
 * - Ring(): Initializes the ring with two poles at random positions within the window boundaries.
 * 
 * Methods:
 * - getLeftPole(): Returns the left pole.
 * - getRightPole(): Returns the right pole.
 */
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
