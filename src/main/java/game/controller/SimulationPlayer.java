package game.controller;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.model.serialization.SimulationSerializer;

public class SimulationPlayer extends SimulationWindow{
    static final int BUTTON_HEIGHT = 150;

    JPanel buttonPanel;

    JButton saveButton;
    JButton playButton;
    JButton settingsButton;
    JButton addButton;

    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld.preset3();
        initView();
        initPhotoButton();
    }

    public SimulationPlayer(String jsonPath){
        super(jsonPath);
        window.setSize(window.getWidth(), BUTTON_HEIGHT + window.getHeight());
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        //initPhotoButton();
        initButtonsPanel();
    }

    void initButtonsPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(window.getWidth(),BUTTON_HEIGHT));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        saveButton = new JButton("Save");
        saveButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(saveButton);

        playButton = new JButton("Play");
        playButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(playButton);

        settingsButton = new JButton("Settings");
        settingsButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(settingsButton);

        addButton = new JButton("Add");
        addButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(addButton);

        window.add(buttonPanel);
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
