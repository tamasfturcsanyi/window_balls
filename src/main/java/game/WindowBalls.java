package game;

import game.controller.MainMenu;

public class WindowBalls {
    void run(){
        Thread simulationThread = new Thread(new MainMenu());
        simulationThread.start();
    }

    public static void main(String[] args) {
        WindowBalls basket = new WindowBalls();
        basket.run();
    }
}
