package game.controller;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SimulationButton extends JButton{

    public SimulationButton(String simFilePath){
        SimulationPlayer sPlayer = new SimulationPlayer(simFilePath);
        Thread simThread = new Thread(sPlayer);
        simThread.start();
        String title = sPlayer.modelWorld.getTitle();

        this.setText(title);
        try {
            Thread.sleep(100);            
        } catch (Exception e) {
            e.printStackTrace();
        }

        sPlayer.addVisuals();
        
        this.setIcon( new ImageIcon(sPlayer.view.drawOnImage()));

        SwingUtilities.invokeLater(sPlayer::disposeWindow);
        
        this.setHorizontalTextPosition(SwingConstants.CENTER); // Text in the center horizontally
        this.setVerticalTextPosition(SwingConstants.TOP);   // Text below the image
    }

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Image Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Create an ImageIcon
        ImageIcon icon = new ImageIcon("src/main/resources/button_icon.png"); // Replace with your image path

        // Create a JButton with both text and icon
        JButton button = new JButton("Button Title", icon);
        
        // Customize text and image position
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Text in the center horizontally
        button.setVerticalTextPosition(SwingConstants.TOP);   // Text below the image

        // Add the button to the frame
        frame.add(button);

        // Make the frame visible
        frame.setVisible(true);
    }
}
