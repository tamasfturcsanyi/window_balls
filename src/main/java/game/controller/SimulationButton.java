package game.controller;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.model.Simulation;
import game.model.physicksbodies.PhysicksBody;
import game.model.serialization.SimulationSerializer;
import game.view.GraphicsPanel;
import game.view.Visualizer;

public class SimulationButton extends JButton{

    ImageIcon makeIcon(Simulation sim){
        GraphicsPanel view = new GraphicsPanel();
        for (PhysicksBody body : sim.getPhysicksBodies()) {            
            view.addVisual(body.getVisual(new Visualizer(sim.getWindowBounds())));
        }
        view.drawOnImage();
        return new ImageIcon(view.getImagePath());
    }

    public SimulationButton(String simFilePath){
        SimulationPlayer sPlayer = new SimulationPlayer();
        sPlayer.window.setVisible(false);
        sPlayer.modelWorld = SimulationSerializer.loadWorld(simFilePath);
        String title = sPlayer.modelWorld.getTitle();

        this.setText(title);

        try {
            Thread.sleep(1000);
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        sPlayer.view.drawOnImage();
         this.setIcon( new ImageIcon(sPlayer.view.getImagePath()));
        //this.setIcon(makeIcon(sPlayer.modelWorld));
        
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
