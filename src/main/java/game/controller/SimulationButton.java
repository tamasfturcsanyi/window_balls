package game.controller;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class SimulationButton extends JButton{

    public SimulationButton(File jsonFile){
        SimulationPlayer sPlayer = new SimulationPlayer(jsonFile.getAbsolutePath());
        sPlayer.window.setVisible(false);
        sPlayer.window.revalidate();

        String title = sPlayer.modelWorld.getTitle();

        this.setText(title);

        sPlayer.addVisuals();
        this.setIcon( new ImageIcon(sPlayer.view.drawOnImage()));

        SwingUtilities.invokeLater(sPlayer::disposeWindow);
        
        this.setHorizontalTextPosition(SwingConstants.CENTER); // Text in the center horizontally
        this.setVerticalTextPosition(SwingConstants.TOP);   // Text below the image
    }
}
