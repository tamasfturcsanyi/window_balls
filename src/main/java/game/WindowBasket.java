package game;

import game.controller.MainMenu;

public class WindowBasket {
    void run(){
        Thread simulationThread = new Thread(new MainMenu());
        simulationThread.start();
    }
}
