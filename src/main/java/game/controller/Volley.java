package game.controller;


import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import game.model.Player;
import game.model.physicksbodies.volley.VolleyBall;

public class Volley extends SimulationWindow{
    Player player1;
    Player player2;

    VolleyBall ball = new VolleyBall();

    JLabel score;

    public Volley(){
        super("Volley",new Rectangle(0, 0, 1024, 768));
        setupPlayer1();
        setupPlayer2();
        ball = new VolleyBall();
        score = new JLabel();
        updateScore();
        score.setFont(new Font("Impact", Font.BOLD, 52));
        view.add(score);
        modelWorld.volleyPreset(ball);
        initVisualizables();
    }

    void setupPlayer1(){
        player1 = new Player(1);
        modelWorld.addBody(player1.getBody());
        visualizables.add(player1);
        PlayerController pc1 = new PlayerController(player1,ControlScheme.WASD);
        window.addKeyListener(pc1);
    }

    void setupPlayer2(){
        player2 = new Player(2);
        modelWorld.addBody(player2.getBody());
        visualizables.add(player2);
        PlayerController pc2 = new PlayerController(player2,ControlScheme.ARROWS);
        window.addKeyListener(pc2);
    }

    void updatePlayers(){
        player1.setOnGround(modelWorld.isOnTheGround(player1.getBody()));
        player1.update();

        player2.setOnGround(modelWorld.isOnTheGround(player2.getBody()));
        player2.update();
    }

    void updateGameLogic(){
        if(modelWorld.isOnTheGround(ball)){
            roundWon();
        }
    }

    void updateScore(){
        score.setText(player1.getScore() + " : " + player2.getScore());
    }

    void roundWon(){
        if(ball.getPosition().getX() < 512){
            player2.setScore(player2.getScore()+1);
            ball.reset(1);
        }else{
            player1.setScore(player1.getScore()+1);
            ball.reset(-1);
        }
        updateScore();
    }

    @Override
    void cycle() {
        updatePlayers();
        updateGameLogic();
        super.cycle();
    }
}
