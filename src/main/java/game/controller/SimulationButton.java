package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class SimulationButton extends JButton{

    boolean selected = false;

    String jsonPath;

    public SimulationButton(File jsonFile){
        jsonPath = jsonFile.getAbsolutePath();
        SimulationPlayer sPlayer = new SimulationPlayer(jsonPath);
        sPlayer.window.setVisible(false);
        sPlayer.window.revalidate();

        String title = sPlayer.modelWorld.getTitle();

        this.setText(title);
        this.setFont(new Font("Impact", Font.BOLD, 24)); // Set the font size to 16

        sPlayer.addVisuals();
        this.setIcon( new ImageIcon(sPlayer.view.drawOnImage()));

        SwingUtilities.invokeLater(sPlayer::disposeWindow);
        
        this.setHorizontalTextPosition(SwingConstants.CENTER); // Text in the center horizontally
        this.setVerticalTextPosition(SwingConstants.TOP);   // Text above the image
    }

    public void updateApperence() {
        if(!selected){
            setBackground(Color.decode("#63d8ff"));
        }else{
            setBackground(Color.yellow);
        }
    }

    public String getJsonPath() {
        return jsonPath;
    }
}
