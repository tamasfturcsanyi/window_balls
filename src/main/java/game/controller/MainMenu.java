package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;

import game.model.Vector2D;
import game.view.FixLabel;
import game.view.SolidButton;

public class MainMenu extends SimulationWindow{
    static final int WINDOW_X = 500;
    static final int WINDOW_Y = 200;
    static final int WINDOW_WIDTH = 700;
    static final int WINDOW_HEIGHT = 600;

    static final String FONT_NAME = "Impact";
    
    SolidButton simButton;

    SolidButton basketButton;

    SolidButton volleyButton;



    FixLabel titleLabel;
   
    public MainMenu(){
        super("WINDOW BALLS",new Rectangle(WINDOW_X, WINDOW_Y, WINDOW_WIDTH,WINDOW_HEIGHT));
        modelWorld.preset1();

        initTitle();

        view.setLayout(null);
        view.setBackground(new Color(100, 100, 255));


        initSimButton();
        initBasketButton();
        initVolleyButton();
        window.setResizable(false);
        initVisualizables();
    }

    void initTitle(){
        titleLabel = new FixLabel("WINDOW BALLS",new Vector2D(0 + 180.0,100));
        titleLabel.setFont(new Font("Impact",Font.BOLD,50));
        titleLabel.setForeground(Color.ORANGE);
        view.add(titleLabel);

    }

    void initSimButton(){
        simButton = new SolidButton("SIMULATION",
                                 new Vector2D(WINDOW_X + WINDOW_WIDTH/2.0 - 100,
                                              WINDOW_Y + 100.0),
                                 new Vector2D(200,100));

        simButton.setNormalColor(Color.PINK);
        simButton.setFont(new Font(FONT_NAME,Font.BOLD,32));
        simButton.setBorderPainted(true);
        simButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        simButton.setFocusPainted(false);

        simButton.addActionListener(e -> startSimulation());
        modelWorld.addBody(simButton.getBody());
        view.add(simButton);
    }

    void initBasketButton(){
        basketButton = new SolidButton("BASKET",
                                 new Vector2D(WINDOW_X + WINDOW_WIDTH/2.0 - 100,
                                              WINDOW_Y + 250.0),
                                 new Vector2D(200,100));

        basketButton.setNormalColor(Color.ORANGE);
        basketButton.setFont(new Font(FONT_NAME,Font.BOLD,32));
        basketButton.setBorderPainted(true);
        basketButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        basketButton.setFocusPainted(false);

        basketButton.addActionListener(e -> startBasket());


        modelWorld.addBody(basketButton.getBody());
        view.add(basketButton);
    }

    void initVolleyButton(){
        volleyButton = new SolidButton("VOLLEY",
                                 new Vector2D(WINDOW_X + WINDOW_WIDTH/2.0 - 100,
                                              WINDOW_Y + 400.0),
                                 new Vector2D(200,100));

        volleyButton.setNormalColor(Color.YELLOW);
        volleyButton.setFont(new Font(FONT_NAME,Font.BOLD,32));
        volleyButton.setBorderPainted(true);
        volleyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        volleyButton.setFocusPainted(false);

        volleyButton.addActionListener(e -> startVolley());

        modelWorld.addBody(volleyButton.getBody());
        view.add(volleyButton);
    }

    //starts a volley window, disposes the MainMenu Window
    void startVolley(){
        Thread volleyThread = new Thread(new Volley());
        volleyThread.start();

        disposeWindow();
    }

    void startBasket(){
        Thread basketThread = new Thread(new Basket());
        basketThread.start();

        disposeWindow();
    }

    void startSimulation(){
        Thread simulationThread = new Thread(new SimulationPlayer());
        simulationThread.start();

        disposeWindow();
    }

    void updateButtons(){
        simButton.updateApperence();
        simButton.updatePosition(modelWorld.getWindowBounds());

        basketButton.updateApperence();
        basketButton.updatePosition(modelWorld.getWindowBounds());

        volleyButton.updateApperence();
        volleyButton.updatePosition(modelWorld.getWindowBounds());
    }

    @Override
    void updateView() {
        updateButtons();
        
        titleLabel.updatePosition(modelWorld.getWindowBounds());
        super.updateView();
    }
}
