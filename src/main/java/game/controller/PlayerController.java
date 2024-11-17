package game.controller;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import game.model.Player;

public class PlayerController implements KeyListener {
    Player player;

    ControlScheme scheme;

    PlayerController(Player player, ControlScheme scheme){
        this.player = player;
        this.scheme = scheme;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String action = scheme.mapKeyToAction(e.getKeyCode());
        switch ( action) {
            case "MOVE UP":
                player.setUp(true);
                break;
            case "MOVE DOWN":
                player.setDown(true);
                break;
            case "MOVE LEFT":
                player.setLeft(true);
                break;
            case "MOVE RIGHT":
                player.setRight(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String action = scheme.mapKeyToAction(e.getKeyCode());
        switch ( action) {
            case "MOVE UP":
                player.setUp(false);
                break;
            case "MOVE DOWN":
                player.setDown(false);
                break;
            case "MOVE LEFT":
                player.setLeft(false);
                break;
            case "MOVE RIGHT":
                player.setRight(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // no action
    }
}
