package game.controller;

import java.awt.Color;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

public class ColorPicker extends JComboBox<String> {
    public ColorPicker(){
        super(new String[]{"Red", "Green", "Blue", "Yellow", "Black", "White", "Gray", "Cyan", "Magenta", "Pink", "Orange"});
    }

    Color getColor(){
        return toColor((String)getSelectedItem());
    }
    
    Color toColor(String colorName){
        switch (colorName) {
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "Blue":
                return Color.BLUE;
            case "Yellow":
                return Color.YELLOW;
            case "Black":
                return Color.BLACK;
            case "White":
                return Color.WHITE;
            case "Gray":
                return Color.GRAY;
            case "Cyan":
                return Color.CYAN;
            case "Magenta":
                return Color.MAGENTA;
            case "Pink":
                return Color.PINK;
            case "Orange":
                return Color.ORANGE;
            default:
                return Color.BLACK;
        }
    }

    public static void main(String[] args) {
        JComboBox<String> colorPicker = new JComboBox<>();
    }
}
