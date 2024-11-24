package game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimulationKeyListener implements KeyListener{
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
            System.out.println("copy!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
