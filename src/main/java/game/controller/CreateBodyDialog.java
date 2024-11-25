package game.controller;

import javax.swing.*;
import java.awt.*;
import game.model.Vector2D;
import game.model.physicksbodies.Ball;
import game.model.physicksbodies.Brick;
import game.model.physicksbodies.Pole;


import game.model.physicksbodies.PhysicksBody;


/**
 * CreateBodyDialog is a custom dialog for creating different types of physical bodies
 * such as Ball, Brick, and Pole. It provides a user interface for inputting the properties
 * of these bodies and validates the input before creating the corresponding body object.
 * 
 * The dialog contains:
 * - A JComboBox for selecting the type of body to create.
 * - Different panels for inputting properties of Ball, Brick, and Pole.
 * - A button to create the body after validating the input fields.
 * 
 * The class provides methods to create specific body objects based on the input fields,
 * and a method to retrieve the created body.
 * 
 * Fields:
 * - BALL, BRICK, POLE: Constants representing the types of bodies.
 * - bodyTypeComboBox: JComboBox for selecting the type of body.
 * - poleColorComboBox, ballColorComboBox: ColorPickers for selecting colors.
 * - cardPanel: JPanel with CardLayout to switch between different input panels.
 * - Various JTextFields for inputting properties of Ball, Brick, and Pole.
 * - body: The created PhysicksBody object.
 * 
 * Methods:
 * - CreateBodyDialog(Frame owner): Constructor to initialize the dialog.
 * - createBallPanel(): Creates and returns the panel for inputting Ball properties.
 * - createBrickPanel(): Creates and returns the panel for inputting Brick properties.
 * - createPolePanel(): Creates and returns the panel for inputting Pole properties.
 * - validateFields(): Validates the input fields based on the selected body type.
 * - createBody(): Creates the body based on the input fields and selected body type.
 * - createBall(): Creates and returns a Ball object based on the input fields.
 * - createBrick(): Creates and returns a Brick object based on the input fields.
 * - createPole(): Creates and returns a Pole object based on the input fields.
 * - getBody(): Returns the created PhysicksBody object.
 * - fillWithDefaultValues(): Fills the input fields with default values.
 */
public class CreateBodyDialog extends JDialog {
    static final String BALL = "Ball";
    static final String BRICK = "Brick";
    static final String POLE = "Pole";

    private JComboBox<String> bodyTypeComboBox;
    private ColorPicker poleColorComboBox;
    private ColorPicker ballColorComboBox;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTextField ballCenterXField;
    private JTextField ballCenterYField;
    private JTextField ballRadiusField;
    private JTextField ballBouncinessField;
    private JTextField ballMassField;

    private JTextField poleCenterXField;
    private JTextField poleCenterYField;
    private JTextField poleRadiusField;


    private JTextField positionXField;
    private JTextField positionYField;
    private JTextField widthField;
    private JTextField heightField;


    private transient PhysicksBody body;
    
    /**
     * CreateBodyDialog is a dialog window that allows the user to create a body
     * with different types such as BALL, BRICK, or POLE. The dialog provides
     * options to select the type of body and customize its properties.
     *
     * @param owner the parent frame of the dialog
     */
    @SuppressWarnings("unused")
    public CreateBodyDialog(Frame owner) {
        super(owner, "Create Body", true);
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        bodyTypeComboBox = new JComboBox<>(new String[]{BALL, BRICK, POLE});
        bodyTypeComboBox.addActionListener(s -> cardLayout.show(cardPanel, (String) bodyTypeComboBox.getSelectedItem()));
        poleColorComboBox = new ColorPicker();
        ballColorComboBox = new ColorPicker();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createBallPanel(), BALL);
        cardPanel.add(createBrickPanel(), BRICK);
        cardPanel.add(createPolePanel(), POLE);
        fillWithDefaultValues();

        add(bodyTypeComboBox, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Create");
        addButton.addActionListener(c -> createBody());

        add(addButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }
    
    /**
     * Creates a JPanel containing input fields for configuring a ball's properties.
     * The panel includes fields for:
     * - Center X coordinate
     * - Center Y coordinate
     * - Radius
     * - Bounciness
     * - Mass
     * - Color
     *
     * @return a JPanel with input fields for ball properties
     */
    private JPanel createBallPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Center X:"));
        ballCenterXField = new JTextField();
        panel.add(ballCenterXField);
        panel.add(new JLabel("Center Y:"));
        ballCenterYField = new JTextField();
        panel.add(ballCenterYField);
        panel.add(new JLabel("Radius:"));
        ballRadiusField = new JTextField();
        panel.add(ballRadiusField);
        panel.add(new JLabel("Bounciness:"));
        ballBouncinessField = new JTextField();
        panel.add(ballBouncinessField);
        panel.add(new JLabel("Mass:"));
        ballMassField= new JTextField();
        panel.add(ballMassField);
        panel.add(new JLabel("Color:"));
        panel.add(ballColorComboBox);
        return panel;
    }
    
    /**
     * Creates a JPanel containing input fields for brick properties.
     * The panel is arranged in a 4x2 grid layout with labels and text fields for:
     * - Position X
     * - Position Y
     * - Width
     * - Height
     *
     * @return JPanel containing the input fields for brick properties.
     */
    private JPanel createBrickPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Position X:"));
        positionXField = new JTextField();
        panel.add(positionXField);
        panel.add(new JLabel("Position Y:"));
        positionYField = new JTextField();
        panel.add(positionYField);
        panel.add(new JLabel("Width:"));
        widthField = new JTextField();
        panel.add(widthField);
        panel.add(new JLabel("Height:"));
        heightField = new JTextField();
        panel.add(heightField);
        return panel;
    }

    /**
     * Creates a JPanel containing input fields for defining the properties of a pole.
     * The panel includes fields for the center coordinates (X and Y), radius, and color of the pole.
     *
     * @return a JPanel with input fields for pole properties.
     */
    private JPanel createPolePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Center X:"));
        poleCenterXField = new JTextField();
        panel.add(poleCenterXField);
        panel.add(new JLabel("Center Y:"));
        poleCenterYField = new JTextField();
        panel.add(poleCenterYField);
        panel.add(new JLabel("Radius:"));
        poleRadiusField = new JTextField();
        panel.add(poleRadiusField);
        panel.add(new JLabel("Color:"));
        panel.add(poleColorComboBox);
        return panel;
    }

    /**
     * Validates the input fields based on the selected body type.
     * <p>
     * This method checks if the input fields contain valid numerical values
     * according to the selected body type from the bodyTypeComboBox.
     * </p>
     * <ul>
     * <li>If the selected body type is BALL, it validates the fields:
     * ballCenterXField, ballCenterYField, ballRadiusField, ballBouncinessField, and ballMassField.</li>
     * <li>If the selected body type is BRICK, it validates the fields:
     * positionXField, positionYField, widthField, and heightField.</li>
     * <li>If the selected body type is POLE, it validates the fields:
     * poleCenterXField, poleCenterYField, and poleRadiusField.</li>
     * </ul>
     * 
     * @return true if all the relevant fields contain valid numerical values, false otherwise.
     */
    private boolean validateFields() {
        try {
            switch ((String) bodyTypeComboBox.getSelectedItem()) {
                case BALL:
                    Double.parseDouble(ballCenterXField.getText());
                    Double.parseDouble(ballCenterYField.getText());
                    Double.parseDouble(ballRadiusField.getText());
                    Double.parseDouble(ballBouncinessField.getText());
                    Double.parseDouble(ballMassField.getText());
                    break;
                case BRICK:
                    Double.parseDouble(positionXField.getText());
                    Double.parseDouble(positionYField.getText());
                    Double.parseDouble(widthField.getText());
                    Double.parseDouble(heightField.getText());
                    break;
                case POLE:
                    Double.parseDouble(poleCenterXField.getText());
                    Double.parseDouble(poleCenterYField.getText());
                    Double.parseDouble(poleRadiusField.getText());
                    break;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Creates a body based on the selected body type from the combo box.
     * If the fields are not validated, it shows an error message dialog.
     * Depending on the selected body type, it creates a corresponding body
     * (BALL, BRICK, or POLE) and then hides the dialog.
     */
    void createBody() {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch ((String) bodyTypeComboBox.getSelectedItem()) {
            case BALL:
                body = createBall();
                break;
            case BRICK:
                body = createBrick();
                break;
            case POLE:
                body = createPole();
                break;
            default:
                break;
        }
        setVisible(false);
    }


    /**
     * Creates a new Ball object using the values from the input fields.
     *
     * @return a new Ball object with the specified center coordinates, radius, color, bounciness, and mass.
     * @throws NumberFormatException if any of the input fields contain invalid numbers.
     */
    Ball createBall() {
        return new Ball(
                new Vector2D(Double.parseDouble(ballCenterXField.getText()),
                        Double.parseDouble(ballCenterYField.getText())),
                Double.parseDouble(ballRadiusField.getText()),
                 ballColorComboBox.getColor(),
                Double.parseDouble(ballBouncinessField.getText()),
                Double.parseDouble(ballMassField.getText()));
    }

    /**
     * Creates a new Brick object using the values from the input fields.
     *
     * @return a new Brick object with position and size based on the input fields.
     * @throws NumberFormatException if the text in any of the input fields cannot be parsed as a double.
     */
    Brick createBrick(){
        return new Brick(
                new Vector2D(Double.parseDouble(positionXField.getText()),
                        Double.parseDouble(positionYField.getText())),
                new Vector2D(Double.parseDouble(widthField.getText()),
                        Double.parseDouble(heightField.getText())));
    }

    /**
     * Creates a new Pole object using the values from the input fields.
     *
     * @return a new Pole object with the specified center coordinates, radius, and color.
     * @throws NumberFormatException if the text fields for the coordinates or radius do not contain valid double values.
     */
    Pole createPole(){
        return new Pole(
                new Vector2D(Double.parseDouble(poleCenterXField.getText()),
                        Double.parseDouble(poleCenterYField.getText())),
                Double.parseDouble(poleRadiusField.getText()),
                poleColorComboBox.getColor());
    }

    public PhysicksBody getBody() {
        return body;
    }

    /**
     * Fills the input fields with default values for the ball, position, and pole.
     * 
     * Sets the following default values:
     * - Ball:
     *   - Center X: 600
     *   - Center Y: 200
     *   - Radius: 30
     *   - Bounciness: 0.5
     *   - Mass: 1
     * - Position:
     *   - X: 600
     *   - Y: 300
     *   - Width: 100
     *   - Height: 100
     * - Pole:
     *   - Center X: 700
     *   - Center Y: 200
     *   - Radius: 30
     *   - Color: Blue
     */
    void fillWithDefaultValues(){
        ballCenterXField.setText("600");
        ballCenterYField.setText("200");
        ballRadiusField.setText("30");
        ballBouncinessField.setText("0.5");
        ballMassField.setText("1");

        positionXField.setText("600");
        positionYField.setText("300");
        widthField.setText("100");
        heightField.setText("100");

        poleCenterXField.setText("700");
        poleCenterYField.setText("200");
        poleRadiusField.setText("30");
        poleColorComboBox.setSelectedItem("Blue");
    }
}