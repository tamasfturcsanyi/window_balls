package game.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import game.model.Player;
import game.model.Vector2D;
import game.model.physicksbodies.volley.VolleyBall;
import game.view.SolidButton;

public class Volley extends SimulationWindow{
    Player player1;
    Player player2;

    VolleyBall ball = new VolleyBall();

    JLabel score;
    JLabel vicotryLabel;

    SolidButton restartButton;
    SolidButton exitButton;

    int pointsToVictory;

    boolean gameEnded = false;

    public Volley(Player player1, Player player2, int pointsToVictory){
        super("Volley",new Rectangle(0, 0, 1024, 768));
        this.pointsToVictory = pointsToVictory;
        this.player1 = player1;
        this.player2 = player2;
        setupPlayer1();
        setupPlayer2();
        ball = new VolleyBall();
        score = new JLabel();
        updateScore();
        score.setFont(new Font("Impact", Font.BOLD, 52));
        view.add(score);

        vicotryLabel = new JLabel();
        vicotryLabel.setVisible(false);
        view.add(vicotryLabel);

        modelWorld.volleyPreset();
        modelWorld.addBody(ball);
        initView();
    }

    void setupPlayer1(){
        modelWorld.addBody(player1.getBody());
        visualizables.add(player1);
        PlayerController pc1 = new PlayerController(player1,ControlScheme.WASD);
        window.addKeyListener(pc1);
    }

    void setupPlayer2(){
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
        if(gameEnded){
            restartButton.updatePosition(window.getBounds());
            restartButton.updateApperence();
            exitButton.updatePosition(window.getBounds());
            exitButton.updateApperence();
        }
    }

    void updateScore(){
        score.setText(player1.getScore() + " : " + player2.getScore());
    }

    void initRestartButton(){
        Vector2D restartPosition = new Vector2D(window.getBounds().getX() + 150, window.getBounds().getY() + 400);
        restartButton = new SolidButton("Restart",restartPosition,new Vector2D(300,100));
        restartButton.impactStyle();
        restartButton.setNormalColor(Color.GREEN);
        restartButton.addActionListener(e -> restart());

        modelWorld.addBody(restartButton.getBody());
        view.add(restartButton);
    }

    void initExitButton(){
        Vector2D exitPosition = new Vector2D(window.getBounds().getX() + 575, window.getBounds().getY() + 400);
        exitButton = new SolidButton("Exit",exitPosition,new Vector2D(300,100));
        exitButton.impactStyle();
        exitButton.setNormalColor(Color.RED);
        exitButton.addActionListener(e -> exit());

        modelWorld.addBody(exitButton.getBody());
        view.add(exitButton);
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
        if(player1.getScore() == pointsToVictory || player2.getScore() == pointsToVictory){
            endGame();
        }
    }

    void endGame(){
        gameEnded = true;
        removeBodyFromViewAndSimulation(ball);
       vicotryLabel.setFont(score.getFont().deriveFont(160.0f));
        if(player1.getScore() == pointsToVictory){
            vicotryLabel.setText("Player 1 wins!");
        }else{
            vicotryLabel.setText("Player 2 wins!");
        }
        vicotryLabel.setVisible(true);

        initRestartButton();
        initExitButton();
    }

    void restart(){
        player1.setScore(0);
        player2.setScore(0);
        Volley volley = new Volley(player1,player2,pointsToVictory);
        Thread volleyThread = new Thread(volley);
        volleyThread.start();

        disposeWindow();
    }

    void exit(){
        MainMenu mainMenu = new MainMenu();
        Thread mainMenuThread = new Thread(mainMenu);
        mainMenuThread.start();
        
        disposeWindow();
    }

    @Override
    void cycle() {
        updatePlayers();
        updateGameLogic();
        super.cycle();
    }
}
