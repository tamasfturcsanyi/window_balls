package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import game.model.Player;
import game.model.Player.Face;

public class VolleyStarter extends SimulationWindow {
    Player player1;
    Player player2;

    private JComboBox<Face> player1FacePicker;
    private JComboBox<Face> player2FacePicker;
    private ColorPicker player1ColorPicker;
    private ColorPicker player2ColorPicker;

    JSpinner pointsToVictorySpinner;
    Font font = new Font("Impact", Font.PLAIN, 42);
    Font titleFont = new Font("Impact", Font.PLAIN, 72);


    public VolleyStarter(){
        super("Volley", new Rectangle(0,0,1024,768));
        modelWorld.volleyPreset();
        player1 = new Player(1,Color.CYAN,Face.LEMMY);
        player2 = new Player(2,Color.RED,Face.SAD);
        modelWorld.addBody(player1.getBody());
        modelWorld.addBody(player2.getBody());
        visualizables.add(player1);
        visualizables.add(player2);

        initButtons();
    }

    @SuppressWarnings("unused")
    void initPlayer1ColorPicker(){
        player1ColorPicker = new ColorPicker();
        player1ColorPicker.setFont(font);
        player1ColorPicker.addActionListener(e -> replacePlayer1());
        player1ColorPicker.removeItem("Black");
        player1ColorPicker.removeItem("White");

        view.add(player1ColorPicker);
    }

    @SuppressWarnings("unused")
    void initPlayer2ColorPicker(){
        player2ColorPicker = new ColorPicker();
        player2ColorPicker.setFont(font);
        player2ColorPicker.addActionListener(e -> replacePlayer2());
        player2ColorPicker.removeItem("Black");
        player2ColorPicker.removeItem("White");

        view.add(player2ColorPicker);
    }

    @SuppressWarnings("unused")
    void initPlayer1FacePicker(){
        player1FacePicker = new JComboBox<>(Face.values());
        player1FacePicker.setFont(font);
        player1FacePicker.addActionListener(e -> replacePlayer1());
        view.add(player1FacePicker);
    }

    @SuppressWarnings("unused")
    void initPlayer2FacePicker(){
        player2FacePicker = new JComboBox<>(Face.values());
        player2FacePicker.setFont(font);
        player2FacePicker.addActionListener(e -> replacePlayer2());
        view.add(player2FacePicker);
    }

    void initPointsToVictorySpinner(){
        JLabel pointsToVictoryLabel = new JLabel("Points to victory:");
        pointsToVictoryLabel.setFont(font);

        view.add(pointsToVictoryLabel);

        SpinnerNumberModel model = new SpinnerNumberModel(5, 1, 100, 1);
        pointsToVictorySpinner = new JSpinner(model);
        pointsToVictorySpinner.setFont(font);

        view.add(pointsToVictorySpinner);
    }

    @SuppressWarnings("unused")
    void initStartButton(){
            JButton startButton = new JButton("Start");
            startButton.setFont(font);
            startButton.addActionListener(e -> startVolley());

            view.add(startButton);
        }

    void initButtons(){

        view.setLayout(new GridLayout(5, 3, 10, 10)); // Adding some spacing between components


        JLabel player1Label = new JLabel("Player 1");
        player1Label.setFont(font);
        view.add(player1Label);

        JLabel title = new JLabel("      VOLLEY");
        title.setFont(titleFont);
        title.setForeground(Color.ORANGE);
        view.add(title);


        JLabel player2Label = new JLabel("Player 2");
        player2Label.setFont(font);
        view.add(player2Label);

        
        initPlayer1ColorPicker();

        view.add(new JLabel(""));

        initPlayer2ColorPicker();


        initPlayer1FacePicker();

        view.add(new JLabel(""));
        

        initPlayer2FacePicker();

        initPointsToVictorySpinner();

        view.add(new JLabel(""));
        view.add(new JLabel(""));


        initStartButton();

        setDefaultValues();

        window.add(view);
        window.revalidate();
        window.repaint();
    }

    void setDefaultValues(){
        player1ColorPicker.setSelectedItem("Cyan");
        player1FacePicker.setSelectedItem(Face.SERIOUS);

        player2ColorPicker.setSelectedItem("Red");
        player2FacePicker.setSelectedItem(Face.CUTE);
    }

    void replacePlayer1(){
        removeBodyFromViewAndSimulation(player1.getBody());
        removeVisualizable(player1);

        player1 = new Player(1, player1ColorPicker.getColor(), (Face) player1FacePicker.getSelectedItem());

        modelWorld.addBody(player1.getBody());
        visualizables.add(player1);
    }

    void replacePlayer2(){
        removeBodyFromViewAndSimulation(player2.getBody());
        removeVisualizable(player2);

        player2 = new Player(2, player2ColorPicker.getColor(), (Face) player2FacePicker.getSelectedItem());

        modelWorld.addBody(player2.getBody());
        visualizables.add(player2);
    }

    void startVolley(){
        Volley volley = new Volley(player1, player2, (int) pointsToVictorySpinner.getValue());
        Thread volleyThread = new Thread(volley);
        volleyThread.start();

        disposeWindow();
    }
}
