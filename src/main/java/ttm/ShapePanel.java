package ttm;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ShapePanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D )g;

        //g2.drawRect(50,30,50, 30);

        g2.fillOval(300,30,200,200);
        System.out.print("dude");
    }
}
