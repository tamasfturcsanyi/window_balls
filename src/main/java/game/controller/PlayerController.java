package game.controller;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import game.model.Player;

/**
 * The PlayerController class implements the KeyListener interface to handle
 * keyboard input for controlling a Player object. It maps key events to player
 * actions using a ControlScheme.
 */
public class PlayerController implements KeyListener {
    Player player;

    ControlScheme scheme;

    /**
     * Constructs a PlayerController with the specified player and control scheme.
     *
     * @param player the player to be controlled
     * @param scheme the control scheme to be used
     */
    PlayerController(Player player, ControlScheme scheme){
        this.player = player;
        this.scheme = scheme;
    }

    /**
     * Handles the key pressed event.
     * Maps the pressed key to an action and updates the player's movement state accordingly.
     *
     * @param e the KeyEvent triggered when a key is pressed
     */
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

    /**
     * Handles the event when a key is released.
     * Maps the released key to an action and updates the player's movement state accordingly.
     *
     * @param e the KeyEvent that indicates a key was released
     */
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

    /**
     * This method is called when a key is typed (pressed and released).
     * Currently, no action is performed when a key is typed.
     *
     * @param e the KeyEvent associated with the key typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // no action
    }
}
