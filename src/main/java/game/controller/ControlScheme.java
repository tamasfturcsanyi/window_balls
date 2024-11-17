package game.controller;
import java.awt.event.KeyEvent;

public enum ControlScheme {
    ARROWS(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT),
    WASD(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);

    private final int upKey;
    private final int downKey;
    private final int leftKey;
    private final int rightKey;

    ControlScheme(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public int getUpKey() {
        return upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    // Map an input key to an action
    public String mapKeyToAction(int keyCode) {
        if (keyCode == upKey) {
            return "MOVE UP";
        } else if (keyCode == downKey) {
            return "MOVE DOWN";
        } else if (keyCode == leftKey) {
            return "MOVE LEFT";
        } else if (keyCode == rightKey) {
            return "MOVE RIGHT";
        } else {
            return "UNKNOWN ACTION";
        }
    }
}
