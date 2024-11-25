package game;

import game.controller.MainMenu;
/**
 * Main class of the application. It starts the main menu.
 */
public class WindowBalls {
    /**
     * Method that starts the main menu.
     */
    void run(){
        Thread simulationThread = new Thread(new MainMenu());
        simulationThread.start();
    }

    /**
     * Main method that starts the application.
     * @param args
     */
    public static void main(String[] args) {
        WindowBalls basket = new WindowBalls();
        basket.run();
    }
}
