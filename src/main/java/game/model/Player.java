package game.model;

import game.model.physicksbodies.Ball;
import game.view.Visualizer.Visual;
import game.view.Visualizer;
import game.view.Visualizer.Visualizable;
import java.awt.Font;

import java.awt.Color;

public class Player implements Visualizable{
    public enum Face{
        LEMMY(" ͡° ͜ʖ ͡°"),
        SERIOUS("▀̿Ĺ̯▀̿ ̿"),
        SAD("T ʖ̯ T"),
        HAPPY("ʘ‿ʘ"),
        EVIL("◣_◢"),
        CUTE("｡◕‿◕｡" ,0,40),
        BEAR("ʕ•ᴥ•ʔ", 8, 40),   
        DOG("▼・ᴥ・▼",-15,40),
        CAT("^•ﻌ•^");


        private final String string;
        private final Font font;

        int offsetX = 12;
        int offsetY = 40; 

        Face(String string){
            this.string = string;
            font = new Font("Arrial",Font.PLAIN,26);
        }

        Face(String string, int offsetX, int offsetY){
            this.string = string;
            font = new Font("Arrial",Font.PLAIN,26);
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }

        public String getString() {
            return string;
        }
        public Font getFont() {
            return font;
        }

        public int getOffsetX() {
            return offsetX;
        }

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


    public Player(int num, Color color, Face face){
        this.num = num;
        this.face = face;
        body = new Ball( num == 1 ? new Vector2D(500,500): new Vector2D(800,500),40, color,1,1);
        body.setVisible(false);
    }

    public void update(){
        move();
    }

    void move(){
        if(up && onGround){
            jump();
        }
        if(right){
            moveRight();
        }
        if(left){
            moveLeft();
        }
        if(down && !up){
            moveDown();
        }
        double horizontalVelocity = body.getVelocity().getX();
        if(!right && !left){
            body.addForce(new Vector2D(-horizontalVelocity*FRICTION,0));
        }
    }

    public Ball getBody() {
        return body;
    }

    public int getNum() {
        return num;
    }

    public void jump(){
        body.addForce(new Vector2D(0,-JUMP_FORCE));
    }

    void moveLeft(){
        body.addForce(new Vector2D(-WALK_FORCE,0));
    }

    void moveRight(){
        body.addForce(new Vector2D(WALK_FORCE,0));
    }

    void moveDown(){
        body.addForce(new Vector2D(0,DOWN_FORCE));
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Face getFace() {
        return face;
    }

    @Override
    public Visual getVisual(Visualizer visualizer) {
        return visualizer.visualize(this);
    }
}
