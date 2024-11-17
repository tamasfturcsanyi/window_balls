package game.model.physicksbodies.volley;

import java.awt.Color;

import game.model.SimulationParameters;
import game.model.Vector2D;
import game.model.physicksbodies.Ball;

public class PlayerBody extends Ball{
    enum Face{
        HAPPY("( ͡° ͜ʖ ͡°)");

        private final String string;

        Face(String string){
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

    Face face = Face.HAPPY;

    public PlayerBody(int num){
        super( num == 1 ? new Vector2D(500,500): new Vector2D(800,500),40, num == 1 ? Color.CYAN:Color.red,1,1);
    }

    public String getFace() {
        return face.getString();
    }
}
