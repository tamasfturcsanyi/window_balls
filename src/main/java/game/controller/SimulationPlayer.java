package game.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.SimulationParameters;
import game.model.Vector2D;
import game.model.physicksbodies.PhysicksBody;
import game.model.serialization.CollisionShapeTypeAdapter;
import game.model.serialization.ColorTypeAdapter;
import game.model.serialization.PhysicksBodyTypeAdapter;
import game.model.serialization.SimulationSerializer;
import game.model.shapes.CollisionShape;


public class SimulationPlayer extends SimulationWindow{
    static final int BUTTON_HEIGHT = 75;

    JPanel buttonPanel;

    JButton saveButton;
    JButton addButton;

    JButton playButton;
    ImageIcon playIcon = new ImageIcon("src/main/resources/play.png");
    ImageIcon pauseIcon = new ImageIcon("src/main/resources/pause.png");

    JButton fastButton;
    JButton slowButton;
    JLabel speedLabel = new JLabel();

    JButton settingsButton;
    JButton backButton;

    boolean playing = false;

    PhysicksBody selectedBody;

    public SimulationPlayer(){
        super("Simulation", new Rectangle(500, 200, 700, 500));
        initPanels();
    }

    public SimulationPlayer(String jsonPath){
        super(jsonPath);
        initPanels();
    }

    void initPanels(){
        initButtonsPanel();
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                click(e.getPoint());
            }
        });
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
                    copySelectedBody();                        
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    deleteSelectedBody();
                }
            }
        });
        view.setFocusable(true);
    }

    @Override
    void initWindow() {
        super.initWindow();
        window.setSize(window.getWidth(), BUTTON_HEIGHT + window.getHeight());
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setResizable(true);
    }

    @SuppressWarnings("unused")
    void initPlayButton(){
        playButton = new JButton();
        playButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        playButton.setIcon(playIcon);
        playButton.addActionListener(p -> playButtonAction());

        buttonPanel.add(playButton);
        
    }

    @SuppressWarnings("unused")
    void initFastButton(){
        fastButton = new JButton();
        fastButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon fastIcon = new ImageIcon("src/main/resources/fastForward.png");
        fastButton.setIcon(fastIcon);
        fastButton.addActionListener(s -> speedUp());
        buttonPanel.add(fastButton);
    }

    @SuppressWarnings("unused")
    void initSlowButton(){
        slowButton = new JButton();
        slowButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon slowIcon = new ImageIcon("src/main/resources/slowDown.png");
        slowButton.setIcon(slowIcon);
        slowButton.addActionListener(s -> slowDown());
        buttonPanel.add(slowButton);
    }

    @SuppressWarnings("unused")
    void initSettingsButton(){
        settingsButton = new JButton();
        settingsButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        ImageIcon settingsIcon = new ImageIcon("src/main/resources/settings.png");
        settingsButton.setIcon(settingsIcon);
        settingsButton.addActionListener(d -> startParamsDialog());
        buttonPanel.add(settingsButton);
    }

    @SuppressWarnings("unused")
    void initSaveButton(){
        saveButton = new JButton();
        saveButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        saveButton.setIcon(new ImageIcon("src/main/resources/save.png"));
        saveButton.addActionListener(s -> startSaveDialog());

        buttonPanel.add(saveButton);
    }

    @SuppressWarnings("unused")
    void initAddButton(){
        addButton = new JButton();
        addButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        addButton.setIcon(new ImageIcon("src/main/resources/add.png"));
        addButton.addActionListener(c -> startCreateDialog());

        buttonPanel.add(addButton);
    }

    @SuppressWarnings("unused")
    void initBackButton(){
        backButton = new JButton();
        backButton.setPreferredSize(new Dimension(BUTTON_HEIGHT,BUTTON_HEIGHT));
        backButton.setIcon(new ImageIcon("src/main/resources/backMini.png"));
        backButton.addActionListener(o -> openSimulationPicker());

        buttonPanel.add(backButton);
    }

    void initButtonsPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(1920,BUTTON_HEIGHT));
        buttonPanel.setMinimumSize(new Dimension(700,BUTTON_HEIGHT));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        
        initSaveButton();
        initAddButton();

        initSlowButton();
        initPlayButton();
        initFastButton();

        buttonPanel.add(speedLabel);
        speedLabel.setText("X"+modelWorld.getParams().getSimulationSpeed());
        speedLabel.setFont(speedLabel.getFont().deriveFont(24.0f));

        initSettingsButton();
        initBackButton();


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
        }else{
            if(selectedBody != null){
                moveSelectedBody();
            }
        }
    }

    void moveSelectedBody(){
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        selectedBody.setPosition(new Vector2D(point.x - 50.0, point.y - 240.0)); 
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
        modelWorld.resetDeltaTime();
        settingsButton.setEnabled(false);
        addButton.setEnabled(false);
        saveButton.setEnabled(false);
        window.setResizable(false);
        if(selectedBody != null){
            selectedBody.setSelected(false);
            selectedBody = null;
        }   
    }

    void pauseSimulation(){
        playButton.setIcon(playIcon);
        settingsButton.setEnabled(true);
        addButton.setEnabled(true);
        saveButton.setEnabled(true);
        window.setResizable(true);
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
            view.setBackgroundColor(params.getSimulationBackground());
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

    void openSimulationPicker(){
        new SimulationPicker();

        disposeWindow();
    }

    void click(Point p){
        view.requestFocusInWindow();
        if(playing){
            return;
        }
        if(selectedBody != null){
            selectedBody.setSelected(false);
            selectedBody = null;
            return;
        }
        p.translate(modelWorld.getWindowBounds().x, modelWorld.getWindowBounds().y);
        selectedBody = modelWorld.selectBodyAt(p);
    }

    void copySelectedBody(){
        if(selectedBody != null){
            Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(PhysicksBody.class, new PhysicksBodyTypeAdapter())
            .registerTypeAdapter(CollisionShape.class,new CollisionShapeTypeAdapter())
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .create();

            String json = gson.toJson(selectedBody);
            PhysicksBody copiedBody = gson.fromJson(json, selectedBody.getClass());
            copiedBody.setSelected(false);
            copiedBody.setPosition(new Vector2D(selectedBody.getPosition().getX() + 10, selectedBody.getPosition().getY() + 10)); // Offset the position slightly
            addToViewAndSimulation(copiedBody);
        }
    }

    void deleteSelectedBody(){
        if(selectedBody != null){
            removeBodyFromViewAndSimulation(selectedBody);
            selectedBody = null;
        }
    }
}
