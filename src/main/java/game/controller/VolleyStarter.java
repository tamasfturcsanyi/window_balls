package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.Player;
import game.model.Player.Face;

public class VolleyStarter extends SimulationWindow {
    Player player1;
    Player player2;

    private JComboBox<Face> player1FacePicker;
    private JComboBox<Face> player2FacePicker;
    private ColorPicker player1ColorPicker;
    private ColorPicker player2ColorPicker;

    Font font = new Font("Impact", Font.PLAIN, 18);


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

    void initPlayer1ColorPicker(){
        player1ColorPicker = new ColorPicker();
        player1ColorPicker.setFont(font);
        player1ColorPicker.addActionListener(e -> replacePlayer1());

        view.add(player1ColorPicker);
    }

    void initPlayer2ColorPicker(){
        player2ColorPicker = new ColorPicker();
        player2ColorPicker.setFont(font);
        player2ColorPicker.addActionListener(e -> replacePlayer2());

        view.add(player2ColorPicker);
    }

    void initPlayer1FacePicker(){
        player1FacePicker = new JComboBox<>(Face.values());
        player1FacePicker.setFont(font);
        player1FacePicker.addActionListener(e -> replacePlayer1());
        view.add(player1FacePicker);
    }

    void initPlayer2FacePicker(){
        player2FacePicker = new JComboBox<>(Face.values());
        player2FacePicker.setFont(font);
        player2FacePicker.addActionListener(e -> replacePlayer2());
        view.add(player2FacePicker);
    }

    void initButtons(){

        view.setLayout(new GridLayout(4, 2, 10, 10)); // Adding some spacing between components


        JLabel player1Label = new JLabel("Player 1");
        player1Label.setFont(font);
        view.add(player1Label);

        JLabel player2Label = new JLabel("Player 2");
        player2Label.setFont(font);
        view.add(player2Label);
        
        initPlayer1ColorPicker();

        initPlayer2ColorPicker();

        initPlayer1FacePicker();

        initPlayer2FacePicker();

        window.add(view);
        window.revalidate();
        window.repaint();
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

    static void example(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Volley Starter");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 2));

                JLabel player1Label = new JLabel("Player 1");
                panel.add(player1Label);

                JLabel player2Label = new JLabel("Player 2");
                panel.add(player2Label);

                ColorPicker player1ColorPicker = new ColorPicker();
                panel.add(player1ColorPicker);

                ColorPicker player2ColorPicker = new ColorPicker();
                panel.add(player2ColorPicker);

                JComboBox<Player.Face> player1FacePicker = new JComboBox<>(Player.Face.values());
                panel.add(player1FacePicker);

                JComboBox<Player.Face> player2FacePicker = new JComboBox<>(Player.Face.values());
                panel.add(player2FacePicker);

                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    
    }

    public static void main(String[] args) {
        //example();
        VolleyStarter vs = new VolleyStarter();
        Thread t = new Thread(vs);
        t.start();
    }
}
