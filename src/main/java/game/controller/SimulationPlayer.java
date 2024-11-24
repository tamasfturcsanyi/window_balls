package game.controller;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class SimulationPlayer extends SimulationWindow{
    static final int BUTTON_HEIGHT = 75;

    JPanel buttonPanel;

    JButton saveButton;
    JButton playButton;
    ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");
    JButton settingsButton;
    JButton addButton;

    boolean playing = false;

    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        modelWorld.preset3();
        initView();
    }

    public SimulationPlayer(String jsonPath){
        super(jsonPath);
        window.setSize(window.getWidth(), BUTTON_HEIGHT + window.getHeight());
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        initButtonsPanel();
    }

    void initPlayButton(){
        playButton = new JButton();
        playButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        playButton.setIcon(playIcon);
        playButton.addActionListener(p -> playButtonAction());
        buttonPanel.add(playButton);
    }

    void initButtonsPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(window.getWidth(),BUTTON_HEIGHT));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        saveButton = new JButton("Save");
        saveButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(saveButton);

        initPlayButton();

        settingsButton = new JButton("Settings");
        settingsButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(settingsButton);

        addButton = new JButton("Add");
        addButton.setSize(BUTTON_HEIGHT,BUTTON_HEIGHT);
        buttonPanel.add(addButton);

        window.add(buttonPanel);
        window.add(view);
    }

    @Override
    void updateModel() {
        if(modelWorld.getParams().getShakeable()){
            Rectangle newWindowBounds = window.getBounds();
            newWindowBounds.x -= view.getBounds().x;
            newWindowBounds.y -= view.getBounds().y;
            newWindowBounds.height -= buttonPanel.getHeight();
            modelWorld.setWindowBounds(newWindowBounds);
        }
        if(playing){
            modelWorld.update();
        }
    }

    void playButtonAction(){
        if(!playing){
            playButton.setIcon(pauseIcon);
            modelWorld.stopSimulation();
        }else{
            playButton.setIcon(playIcon);
        }
        playing = !playing;
    }
}
