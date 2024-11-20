package game.controller;

import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JButton;

import game.model.serialization.SimulationSerializer;

public class SimulationPlayer extends SimulationWindow{
    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld.preset2();
        initVisualizables();
        initPhotoButton();
    }

    public SimulationPlayer(String jsonPath){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld = SimulationSerializer.loadWorld(jsonPath);
        initVisualizables();
        initPhotoButton();
    }

    void initPhotoButton(){
        JButton button = new JButton("Click");
        button.addActionListener(e -> {
            addVisuals();
            view.drawOnImage();
            view.getImagePath();
        });
        view.setLayout(new FlowLayout());
        view.add(button);
    }

}
