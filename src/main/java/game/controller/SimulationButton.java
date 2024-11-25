package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


/**
 * The SimulationButton class extends JButton and represents a button that is used to 
 * start a simulation based on a JSON file. The button displays the title of the simulation 
 * and an icon generated from the simulation visuals.
 */
public class SimulationButton extends JButton{

    boolean selected = false;

    String jsonPath;

    /**
     * Constructs a SimulationButton with the specified JSON file.
     * Initializes the button with the title from the simulation window,
     * sets the font, icon, and text positions.
     * 
     * @param jsonFile the JSON file containing simulation data
     */
    public SimulationButton(File jsonFile){
        jsonPath = jsonFile.getAbsolutePath();
        SimulationWindow simWindow = new SimulationWindow(jsonPath);
        simWindow.window.setVisible(false);
        simWindow.window.revalidate();

        String title = simWindow.modelWorld.getTitle();

        this.setText(title);
        this.setFont(new Font("Impact", Font.BOLD, 24)); // Set the font size to 16

        simWindow.addVisuals();
        this.setIcon( new ImageIcon(simWindow.view.drawOnImage()));

        SwingUtilities.invokeLater(simWindow::disposeWindow);
        
        this.setHorizontalTextPosition(SwingConstants.CENTER); // Text in the center horizontally
        this.setVerticalTextPosition(SwingConstants.TOP);   // Text above the image
    }

    /**
     * Updates the appearance of the button based on its selection state.
     * If the button is not selected, it sets the background color to a light blue.
     * If the button is selected, it sets the background color to yellow.
     */
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
