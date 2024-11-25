package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;


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
    FixLabel signo;
   
    public MainMenu(){
        super("WINDOW BALLS",new Rectangle(WINDOW_X, WINDOW_Y, WINDOW_WIDTH,WINDOW_HEIGHT));
        modelWorld.MenuPreset();

        initTitle();
        addSigno();

        view.setLayout(null);


        initSimButton();
        initBasketButton();
        initVolleyButton();
        window.setResizable(false);
        initView();
    }

    void initTitle(){
        titleLabel = new FixLabel("WINDOW BALLS",new Vector2D(680,200));
        titleLabel.setFont(new Font(FONT_NAME,Font.BOLD,50));
        titleLabel.setForeground(Color.ORANGE);
        view.add(titleLabel);
    }

    void addSigno(){
        signo = new FixLabel("By: Turcsányi Tamás",new Vector2D(1200,600));
        signo.setFont(new Font(FONT_NAME,Font.BOLD,30));
        signo.setForeground(Color.BLACK);
        view.add(signo);
    }

    @SuppressWarnings("unused")
    void initSimButton(){
        simButton = new SolidButton("SIMULATION",
                                 new Vector2D(WINDOW_X + WINDOW_WIDTH/2.0 - 100,
                                              WINDOW_Y + 100.0),
                                 new Vector2D(200,100));

        simButton.setNormalColor(Color.PINK);
        simButton.impactStyle();

        simButton.addActionListener(e -> startSimPicker());
        modelWorld.addBody(simButton.getBody());
        view.add(simButton);
    }

    @SuppressWarnings("unused")
    void initBasketButton(){
        basketButton = new SolidButton("BASKET",
                                 new Vector2D(WINDOW_X + WINDOW_WIDTH/2.0 - 100,
                                              WINDOW_Y + 250.0),
                                 new Vector2D(200,100));

        basketButton.setNormalColor(Color.ORANGE);
        basketButton.impactStyle();

        basketButton.addActionListener(e -> startBasket());


        modelWorld.addBody(basketButton.getBody());
        view.add(basketButton);
    }

    @SuppressWarnings("unused")
    void initVolleyButton(){
        volleyButton = new SolidButton("VOLLEY",
                                 new Vector2D(WINDOW_X + WINDOW_WIDTH/2.0 - 100,
                                              WINDOW_Y + 400.0),
                                 new Vector2D(200,100));

        volleyButton.setNormalColor(Color.YELLOW);
        volleyButton.impactStyle();

        volleyButton.addActionListener(e -> startVolley());

        modelWorld.addBody(volleyButton.getBody());
        view.add(volleyButton);
    }

    //starts a volley window, disposes the MainMenu Window
    void startVolley(){
        Thread volleyThread = new Thread(new VolleyStarter());
        volleyThread.start();

        disposeWindow();
    }

    void startBasket(){
        Thread basketThread = new Thread(new Basket());
        basketThread.start();

        disposeWindow();
    }

    void startSimPicker(){
        new SimulationPicker();
        
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

    void updateTitle(){
        titleLabel.setSize(800, 100);
        titleLabel.updatePosition(modelWorld.getWindowBounds());
        signo.setSize(800, 100);
        signo.updatePosition(modelWorld.getWindowBounds());
    }

    @Override
    void updateView() {
        updateButtons();
        updateTitle();
        super.updateView();
    }
}
