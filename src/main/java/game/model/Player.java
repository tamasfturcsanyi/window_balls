package game.model;

import game.model.physicksbodies.Ball;
import game.view.Visualizer.Visual;
import game.view.Visualizer;
import game.view.Visualizer.Visualizable;
import java.awt.Font;

import java.awt.Color;

public class Player implements Visualizable{
    public enum Face{
        HAPPY(" ͡° ͜ʖ ͡°"),
        SERIOUS("▀̿Ĺ̯▀̿ ̿"),
        SAD("ಠ╭╮ಠ"),
        CUTE("｡◕‿◕｡"),
        BEAR("ʕ•ᴥ•ʔ");


        private final String string;
        private final Font font;

        Face(String string){
            this.string = string;
            font = new Font("Arrial",Font.PLAIN,26);
        }

        public String getString() {
            return string;
        }
        public Font getFont() {
            return font;
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


    public Player(int num){
        this.num = num;
        face = (num == 1) ? Face.SERIOUS : Face.CUTE;
        body = new Ball( num == 1 ? new Vector2D(500,500): new Vector2D(800,500),40, num == 1 ? Color.CYAN:Color.red,1,1);
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
