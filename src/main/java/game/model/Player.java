package game.model;

import game.view.Visualizer.Visual;
import game.model.physicsbodies.Ball;
import game.view.Visualizer;
import game.view.Visualizer.Visualizable;
import java.awt.Font;

import java.awt.Color;

/**
 * The Player class represents a player in the game, implementing the Visualizable interface.
 * It contains the player's face, body, movement controls, and score.
 */
public class Player implements Visualizable {

    /**
     * The Face enum represents different facial expressions for the player.
     */
    public enum Face {
        LEMMY(" ͡° ͜ʖ ͡°"),
        SERIOUS("▀̿Ĺ̯▀̿ ̿"),
        SAD("T ʖ̯ T"),
        HAPPY("ʘ‿ʘ"),
        EVIL("◣_◢"),
        CUTE("｡◕‿◕｡", 0, 40),
        BEAR("ʕ•ᴥ•ʔ", 8, 40),
        DOG("▼・ᴥ・▼", -15, 40),
        CAT("^•ﻌ•^");

        private final String string;
        private final Font font;
        int offsetX = 12;
        int offsetY = 40;

        /**
         * Constructor for Face with default offsets.
         *
         * @param string the string representation of the face
         */
        Face(String string) {
            this.string = string;
            font = new Font("Arrial",Font.PLAIN,26);
        }

        /**
         * Constructor for Face with specified offsets.
         *
         * @param string the string representation of the face
         * @param offsetX the horizontal offset
         * @param offsetY the vertical offset
         */
        Face(String string, int offsetX, int offsetY) {
            this.string = string;
            font = new Font("Arrial",Font.PLAIN,26);
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }

        /**
         * Gets the string representation of the face.
         *
         * @return the string representation of the face
         */
        public String getString() {
            return string;
        }

        /**
         * Gets the font used for the face.
         *
         * @return the font used for the face
         */
        public Font getFont() {
            return font;
        }

        /**
         * Gets the horizontal offset of the face.
         *
         * @return the horizontal offset of the face
         */
        public int getOffsetX() {
            return offsetX;
        }

        /**
         * Gets the vertical offset of the face.
         *
         * @return the vertical offset of the face
         */
        public int getOffsetY() {
            return offsetY;
        }
    }

    static final double JUMP_FORCE = 1000;
    static final double WALK_FORCE = 10;
    static final double DOWN_FORCE = 500;
    static final double FRICTION = 0.2;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean onGround = false;

    int num;
    int score = 0;

    Ball body;
    Face face;

    /**
     * Constructs a Player with the specified number, color, and face.
     *
     * @param num the player number
     * @param color the color of the player
     * @param face the face of the player
     */
    public Player(int num, Color color, Face face) {
        this.num = num;
        this.face = face;
        body = new Ball(num == 1 ? new Vector2D(200, 500) : new Vector2D(800, 500), 40, color, 1, 1);
        body.setVisible(false);
    }

    /**
     * Updates the player's state.
     */
    public void update() {
        move();
    }

    /**
     * Moves the player based on the current control states.
     */
    void move() {
        if (up && onGround) {
            jump();
        }
        if (right) {
            moveRight();
        }
        if (left) {
            moveLeft();
        }
        if (down && !up) {
            moveDown();
        }
        double horizontalVelocity = body.getVelocity().getX();
        if (!right && !left) {
            body.addForce(new Vector2D(-horizontalVelocity * FRICTION, 0));
        }
    }

    /**
     * Gets the player's body.
     *
     * @return the player's body
     */
    public Ball getBody() {
        return body;
    }

    /**
     * Gets the player's number.
     *
     * @return the player's number
     */
    public int getNum() {
        return num;
    }

    /**
     * Makes the player jump.
     */
    public void jump() {
        body.addForce(new Vector2D(0, -JUMP_FORCE));
    }

    /**
     * Moves the player to the left.
     */
    void moveLeft() {
        body.addForce(new Vector2D(-WALK_FORCE, 0));
    }

    /**
     * Moves the player to the right.
     */
    void moveRight() {
        body.addForce(new Vector2D(WALK_FORCE, 0));
    }

    /**
     * Moves the player down.
     */
    void moveDown() {
        body.addForce(new Vector2D(0, DOWN_FORCE));
    }

    /**
     * Sets the down control state.
     *
     * @param down the new down control state
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Sets the left control state.
     *
     * @param left the new left control state
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Sets the right control state.
     *
     * @param right the new right control state
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Sets the up control state.
     *
     * @param up the new up control state
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * Sets whether the player is on the ground.
     *
     * @param onGround the new on-ground state
     */
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    /**
     * Gets the player's score.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score.
     *
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the player's face.
     *
     * @return the player's face
     */
    public Face getFace() {
        return face;
    }

    /**
     * Gets the visual representation of the player.
     *
     * @param visualizer the visualizer to use
     * @return the visual representation of the player
     */
    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}
