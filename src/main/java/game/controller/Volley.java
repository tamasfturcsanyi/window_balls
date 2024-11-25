package game.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;

import javax.swing.JLabel;
import game.model.Player;
import game.model.Vector2D;
import game.model.physicksbodies.volley.VolleyBall;
import game.view.SolidButton;

/**
 * The Volley class represents a volleyball game simulation window.
 * It extends the SimulationWindow class and manages the game logic,
 * player setup, score updates, and game state transitions.
 */
public class Volley extends SimulationWindow{
    private static final String FONT_NAME = "Impact";

    Player player1;
    Player player2;

    VolleyBall ball = new VolleyBall();

    JLabel score;
    JLabel vicotryLabel;
    JLabel pauseLabel;
    JLabel pauseTip;

    SolidButton restartButton;
    SolidButton exitButton;

    int pointsToVictory;

    boolean gameEnded = false;
    boolean paused = false;

    /**
     * Constructs a new Volley game controller.
     *
     * @param player1 The first player in the game.
     * @param player2 The second player in the game.
     * @param pointsToVictory The number of points required to win the game.
     */
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
        score.setFont(new Font(FONT_NAME, Font.BOLD, 52));
        view.add(score);

        vicotryLabel = new JLabel();
        vicotryLabel.setVisible(false);
        view.add(vicotryLabel);

        modelWorld.volleyPreset();
        modelWorld.addBody(ball);

        initPause();
        initView();
    }

    /**
     * Initializes the pause functionality for the game.
     * This method sets up the pause label and pause tip label with specific fonts and visibility settings.
     * It also adds a key listener to the window to listen for the ESC key press to pause the game.
     */
    void initPause(){
        pauseLabel = new JLabel("Paused");
        pauseLabel.setFont(new Font(FONT_NAME, Font.BOLD, 300));
        pauseLabel.setVisible(false);
        view.add(pauseLabel);
        pauseTip = new JLabel("Press ESC to resume");
        pauseTip.setFont(new Font(FONT_NAME, Font.BOLD, 110));
        pauseTip.setVisible(false);
        view.add(pauseTip);
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE){
                    pauseGame();
                }
            }
        });
    }

    /**
     * Sets up player 1 by adding their body to the model world, 
     * adding them to the list of visualizables, creating a player 
     * controller with the WASD control scheme, and adding the 
     * player controller as a key listener to the window.
     */
    void setupPlayer1(){
        modelWorld.addBody(player1.getBody());
        visualizables.add(player1);
        PlayerController pc1 = new PlayerController(player1,ControlScheme.WASD);
        window.addKeyListener(pc1);
    }

    /**
     * Sets up the second player in the game.
     * This method adds the second player's body to the model world,
     * adds the player to the list of visualizables, creates a new
     * PlayerController for the second player with the ARROWS control scheme,
     * and adds the PlayerController as a key listener to the window.
     */
    void setupPlayer2(){
        modelWorld.addBody(player2.getBody());
        visualizables.add(player2);
        PlayerController pc2 = new PlayerController(player2,ControlScheme.ARROWS);
        window.addKeyListener(pc2);
    }

    /**
     * Updates the state of the players in the game.
     * 
     * This method checks if each player is on the ground using the modelWorld's 
     * isOnTheGround method and updates their state accordingly.
     * 
     * It performs the following actions for each player:
     * 1. Checks if the player is on the ground and sets the player's onGround status.
     * 2. Calls the update method on the player to update its state.
     */
    void updatePlayers(){
        player1.setOnGround(modelWorld.isOnTheGround(player1.getBody()));
        player1.update();

        player2.setOnGround(modelWorld.isOnTheGround(player2.getBody()));
        player2.update();
    }

    /**
     * Updates the game logic by checking the state of the game and performing necessary actions.
     * <p>
     * This method performs the following actions:
     * <ul>
     *   <li>Checks if the ball is on the ground and calls {@code roundWon()} if true.</li>
     *   <li>If the game has ended, updates the position and appearance of the restart and exit buttons.</li>
     * </ul>
     */
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

    /**
     * Updates the score display with the current scores of player1 and player2.
     * The score is displayed in the format "player1Score : player2Score".
     */
    void updateScore(){
        score.setText(player1.getScore() + " : " + player2.getScore());
    }

    /**
     * Initializes the restart button for the game.
     * The button is positioned at a specific location within the window bounds,
     * styled with a green color, and configured to trigger the restart action
     * when clicked.
     * 
     * The button is added to the model world and the view.
     */
    @SuppressWarnings("unused")
    void initRestartButton(){
        Vector2D restartPosition = new Vector2D(window.getBounds().getX() + 150, window.getBounds().getY() + 400);
        restartButton = new SolidButton("Restart",restartPosition,new Vector2D(300,100));
        restartButton.impactStyle();
        restartButton.setNormalColor(Color.GREEN);
        restartButton.addActionListener(e -> restart());

        modelWorld.addBody(restartButton.getBody());
        view.add(restartButton);
    }

    /**
     * Initializes the exit button for the game window.
     * The button is positioned at a specific location within the window bounds,
     * styled with a red color, and configured to trigger the exit action when clicked.
     * The button is then added to the model world and the view.
     */
    @SuppressWarnings("unused")
    void initExitButton(){
        Vector2D exitPosition = new Vector2D(window.getBounds().getX() + 575, window.getBounds().getY() + 400);
        exitButton = new SolidButton("Exit",exitPosition,new Vector2D(300,100));
        exitButton.impactStyle();
        exitButton.setNormalColor(Color.RED);
        exitButton.addActionListener(e -> exit());

        modelWorld.addBody(exitButton.getBody());
        view.add(exitButton);
    }

    /**
     * Handles the logic for when a round is won in the game.
     * 
     * This method checks the position of the ball to determine which player has won the round.
     * If the ball's X position is less than 512, player 2 wins the round and their score is incremented.
     * Otherwise, player 1 wins the round and their score is incremented.
     * The ball is then reset to the appropriate position based on which player won.
     * 
     * After updating the scores, the player positions are reset and the score is updated on the display.
     * If either player's score reaches the points required for victory, the game ends.
     */
    void roundWon(){
        if(ball.getPosition().getX() < 512){
            player2.setScore(player2.getScore()+1);
            ball.reset(1);
        }else{
            player1.setScore(player1.getScore()+1);
            ball.reset(-1);
        }
        resetPlayerPositions();
        updateScore();
        if(player1.getScore() == pointsToVictory || player2.getScore() == pointsToVictory){
            endGame();
        }
    }

    /**
     * Resets the positions of player1 and player2 to their initial coordinates.
     * 
     * This method sets the position of player1 to (200, 500) and the position of player2 to (800, 500).
     */
    void resetPlayerPositions(){
        player1.getBody().setPosition(new Vector2D(200,500));
        player2.getBody().setPosition(new Vector2D(800,500));
    }

    /**
     * Ends the game by performing the following actions:
     * - Sets the gameEnded flag to true.
     * - Removes the ball from the view and simulation.
     * - Updates the victory label with the appropriate font and text based on the player's score.
     * - Displays the victory label.
     * - Initializes the restart button.
     * - Initializes the exit button.
     */
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

    /**
     * Restarts the game by resetting the scores of both players, 
     * resetting their positions, and starting a new game thread.
     * Disposes of the current game window.
     */
    void restart(){
        player1.setScore(0);
        player2.setScore(0);
        resetPlayerPositions();
        Volley volley = new Volley(player1,player2,pointsToVictory);
        Thread volleyThread = new Thread(volley);
        volleyThread.start();

        disposeWindow();
    }

    /**
     * Exits the current game and returns to the main menu.
     * This method creates a new instance of the MainMenu class,
     * starts it in a new thread, and then disposes of the current window.
     */
    void exit(){
        MainMenu mainMenu = new MainMenu();
        Thread mainMenuThread = new Thread(mainMenu);
        mainMenuThread.start();
        
        disposeWindow();
    }

    /**
     * Toggles the paused state of the game. When the game is paused, it makes the pause label and tip visible,
     * resets the delta time in the model world, and initializes the restart and exit buttons. When the game is 
     * unpaused, it removes the restart and exit buttons from the view and their corresponding bodies from the model world.
     */
    void pauseGame(){
        paused = !paused;
        pauseLabel.setVisible(paused);
        pauseTip.setVisible(paused);
        modelWorld.resetDeltaTime(); 
        if(paused){
            initRestartButton();  
            initExitButton();
        }else{
            modelWorld.removeBody(exitButton.getBody());
            view.remove(exitButton);
            modelWorld.removeBody(restartButton.getBody());
            view.remove(restartButton);
        }
    }

    /**
     * Executes a single cycle of the game loop. This method updates the players and game logic,
     * and if the game is not paused, it calls the superclass's cycle method to continue the game loop.
     */
    @Override
    void cycle() {
        updatePlayers();
        updateGameLogic();
        if(!paused){
            super.cycle();
        }
    }
}
