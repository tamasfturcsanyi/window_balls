package game.model;

import java.awt.Color;


import game.model.physicksbodies.Ball;

public class Player{
    static final double JUMP_FORCE = 300;
    static final double WALK_FORCE = 100;

    int num;

    Ball body = new Ball(new Vector2D(600,200),20,Color.cyan, 1, 1);

    public Player(int num){
        this.num = num;
        if(num == 1){
            initPlayer1();
        }else{
            initPlayer2();
        }
    }

    void initPlayer1(){
        body = new Ball(new Vector2D(600,400),20,Color.CYAN, 0.5, 1);
    }

    void initPlayer2(){
        body = new Ball(new Vector2D(800,400),20,Color.RED, 0.5, 1);
    }

    public Ball getBody() {
        return body;
    }

    public int getNum() {
        return num;
    }

    public void jump(){
        System.out.println("JUMP");
        body.addForce(new Vector2D(0,JUMP_FORCE));
    }

    public void moveLeft(){
        System.out.println("LEFT");
        body.addForce(new Vector2D(-WALK_FORCE,0));
    }

    public void moveRight(){
        System.out.println("RIGHT");
        body.addForce(new Vector2D(WALK_FORCE,0));
    }
}
