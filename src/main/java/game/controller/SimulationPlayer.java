package game.controller;

import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.SimulationParameters;
import game.model.physicksbodies.PhysicksBody;
import game.model.serialization.SimulationSerializer;


public class SimulationPlayer extends SimulationWindow{
    static final int BUTTON_HEIGHT = 75;

    JPanel buttonPanel;

    JButton saveButton;

    JButton playButton;
    ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");

    JButton fastButton;
    JButton slowButton;
    JLabel speedLabel = new JLabel();

    JButton settingsButton;
    JButton addButton;

    boolean playing = false;

    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        initButtonsPanel();
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

    void initFastButton(){
        fastButton = new JButton();
        fastButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon fastIcon = new ImageIcon("src/main/resources/fastForward.png");
        fastButton.setIcon(fastIcon);
        fastButton.addActionListener(s -> speedUp());
        buttonPanel.add(fastButton);
    }

    void initSlowButton(){
        slowButton = new JButton();
        slowButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon slowIcon = new ImageIcon("src/main/resources/slowDown.png");
        slowButton.setIcon(slowIcon);
        slowButton.addActionListener(s -> slowDown());
        buttonPanel.add(slowButton);
    }

    void initSettingsButton(){
        settingsButton = new JButton();
        settingsButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon settingsIcon = new ImageIcon("src/main/resources/settings.png");
        settingsButton.setIcon(settingsIcon);
        settingsButton.addActionListener(d -> startParamsDialog());
        buttonPanel.add(settingsButton);
    }

    void initSaveButton(){
        saveButton = new JButton();
        saveButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        saveButton.setIcon(new ImageIcon("src/main/resources/save.png"));
        saveButton.addActionListener(s -> startSaveDialog());

        buttonPanel.add(saveButton);
    }

    void initAddButton(){
        addButton = new JButton();
        addButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        addButton.setIcon(new ImageIcon("src/main/resources/add.png"));
        addButton.addActionListener(c -> startCreateDialog());

        buttonPanel.add(addButton);
    }

    void initButtonsPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(window.getWidth(),BUTTON_HEIGHT));
        buttonPanel.setMinimumSize(new Dimension(500,BUTTON_HEIGHT));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        
        initSaveButton();
        initSlowButton();
        initPlayButton();
        initFastButton();
        buttonPanel.add(speedLabel);
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());

        initSettingsButton();

        initAddButton();

        window.add(buttonPanel);
        window.add(view);
    }

    @Override
    void updateModel() {
        if(modelWorld.getParams().isShakeable()){
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
            resumeSimulation();
        }else{
            pauseSimulation();
        }
        playing = !playing;
    }

    void resumeSimulation(){
        playButton.setIcon(pauseIcon);
        modelWorld.stopSimulation();
        settingsButton.setEnabled(false);
        addButton.setEnabled(false);
        saveButton.setEnabled(false);
    }

    void pauseSimulation(){
        playButton.setIcon(playIcon);
        settingsButton.setEnabled(true);
        addButton.setEnabled(true);
        saveButton.setEnabled(true);
    }

    void speedUp(){
        SimulationParameters params = modelWorld.getParams();
        modelWorld.setSimulationSpeed(params.getSimulationSpeed() + 0.25);
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());
    }

    void slowDown(){
        SimulationParameters params = modelWorld.getParams();
        modelWorld.setSimulationSpeed(Math.max(params.getSimulationSpeed() - 0.25,0));
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());
    }

    void startParamsDialog(){
        ParamsDialog pDialog = new ParamsDialog(window,modelWorld.getParams());
        pDialog.setVisible(true);

        SimulationParameters params = pDialog.getSimulationParameters();
        if(params != null){
            modelWorld.setParams(params);
        }
    }

    void startCreateDialog(){
        CreateBodyDialog cDialog = new CreateBodyDialog(window);
        cDialog.setVisible(true);
        PhysicksBody body = cDialog.getBody();
        if(body != null){
            addToViewAndSimulation(body);
        }
    }

    void startSaveDialog(){
       SaveDialog sDialog = new SaveDialog(window);
        sDialog.setVisible(true);

        if(sDialog.getSave()){
            if(!sDialog.toExistingFile()){
                modelWorld.setTitle(sDialog.getFileName());
            }
            SimulationSerializer.saveWorld(modelWorld);
        }
    }
}
