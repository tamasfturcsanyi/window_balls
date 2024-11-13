package game.view;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.Color;

import game.model.Vector2D;
import game.model.physicksbodies.Brick;
import java.awt.Rectangle;

public class SolidButton extends JButton{
    Color normalColor = Color.WHITE;
    
    transient Brick body;

    public SolidButton(String title, Vector2D position, Vector2D dimensions){
        super(title);
        body = new Brick(position, dimensions);
        body.setVisible(false);
    }

    public Brick getBody() {
        return body;
    }

    public void updateApperence(){
        ButtonModel model = getModel();
        if (model.isRollover()) {
            setBackground(normalColor.darker());  // Hover color
        }
         else {
            setBackground(normalColor);        // Normal color
        }
    }

    public void updatePosition(Rectangle windowBounds){
        this.setBounds( (int)body.getPosition().getX() - windowBounds.x,
                        (int)body.getPosition().getY() - windowBounds.y,
                        (int)body.getDimension().getX(),
                        (int)body.getDimension().getY());
    }

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }
}
