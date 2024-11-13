package game;

import game.controller.MainMenu;

public class WindowBasket {

    MainMenu simPlayer = new MainMenu();

    Thread simulationThread = new Thread(simPlayer);

    void run(){
        simulationThread.start();
    }
}
