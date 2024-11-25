package game.view;

import java.awt.Color;

import javax.swing.JComboBox;

/**
 * The ColorPicker class extends JComboBox<String> to provide a dropdown menu
 * for selecting colors. It initializes with a predefined list of color names.
 * 
 * The class provides methods to get the selected color as a Color object.
 * 
 * Methods:
 * - ColorPicker(): Constructor that initializes the JComboBox with color names.
 * - Color getColor(): Returns the selected color as a Color object.
 * - Color toColor(String colorName): Converts a color name to a corresponding Color object.
 * 
 * Example usage:
 * ColorPicker colorPicker = new ColorPicker();
 * Color selectedColor = colorPicker.getColor();
 */
public class ColorPicker extends JComboBox<String> {
    /**
     * Constructs a new ColorPicker with a predefined set of color options.
     * The available colors are: Red, Green, Blue, Yellow, Black, White, Gray, Cyan, Magenta, Pink, and Orange.
     */
    public ColorPicker(){
        super(new String[]{"Red", "Green", "Blue", "Yellow", "Black", "White", "Gray", "Cyan", "Magenta", "Pink", "Orange"});
    }

    /**
     * Retrieves the currently selected color.
     *
     * @return the selected color as a {@link Color} object
     */
    public Color getColor(){
        return toColor((String)getSelectedItem());
    }
    
    /**
     * Converts a color name to its corresponding {@link Color} object.
     *
     * @param colorName the name of the color as a {@link String}
     * @return the corresponding {@link Color} object, or {@link Color#BLACK} if the color name is not recognized
     */
    public Color toColor(String colorName){
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
}
