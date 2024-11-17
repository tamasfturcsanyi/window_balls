package game.model;

import game.model.physicksbodies.volley.PlayerBody;

public class Player{
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

    PlayerBody body;

    public Player(int num){
        this.num = num;
        body = new PlayerBody(num);
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

    public PlayerBody getBody() {
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
}
