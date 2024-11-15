package game.controller;

import game.model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.KeyStroke;

public class PlayerController implements KeyListener{
    private Player player;

    int UP;
    int DOWN;
    int LEFT;
    int RIGHT;

    PlayerController(Player player){
        this.player = player;
        if(player.getNum() == 1){
            initPlayer1();
        }else{
            initPlayer2();
        }
    }

    void initPlayer2(){
        UP = KeyEvent.VK_UP;
        DOWN = KeyEvent.VK_DOWN;
        LEFT = KeyEvent.VK_LEFT;
        RIGHT = KeyEvent.VK_RIGHT;
    }

    void initPlayer1(){
        UP = KeyEvent.VK_W;
        DOWN = KeyEvent.VK_S;
        LEFT = KeyEvent.VK_A;
        RIGHT = KeyEvent.VK_D;
    }


    public Player getPlayer() {
        return player;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch ( e.getKeyCode() ) {
            case KeyEvent.VK_UP:
                player.jump();
                break;
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing
    }

}
