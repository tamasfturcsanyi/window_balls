package game.engine;

import javax.swing.JLabel;

public class FPSCounter extends Thread{
    int fps = 0;

    JLabel label = new JLabel();

    public void increment(){
        ++fps;
    }

    public int getFps() {
        return fps;
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public synchronized void run() {
        while(true){
            try {
                wait(1000);
                label.setText("FPS: " + fps);
                fps = 0;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
