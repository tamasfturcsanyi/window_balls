package game.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimulationMouseListener implements MouseListener{
    SimulationPlayer simulationPlayer;

    SimulationMouseListener(SimulationPlayer simulationPlayer){
        this.simulationPlayer = simulationPlayer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        simulationPlayer.click(e.getPoint());
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
