package game.controller;


import java.awt.Rectangle;

import game.model.Player;

public class Volley extends SimulationWindow{
    Player player1;
    Player player2;

    public Volley(){
        super("Volley",new Rectangle(0, 0, 1024, 768));
        setupPlayer1();
        setupPlayer2();
        modelWorld.volleyPreset();
    }

    void setupPlayer1(){
        player1 = new Player(1);
        modelWorld.addBody(player1.getBody());
        PlayerController pc1 = new PlayerController(player1);
        window.addKeyListener(pc1);
    }

    void setupPlayer2(){
        player2 = new Player(2);
        modelWorld.addBody(player2.getBody());
        PlayerController pc2 = new PlayerController(player2);
        window.addKeyListener(pc2);
    }
}
