package game.view;

import game.model.SimulationParameters;
import game.model.Vector2D;

import javax.swing.*;
import java.awt.*;

/**
 * ParamsDialog is a custom dialog for setting simulation parameters.
 * It extends JDialog and provides a user interface for inputting various simulation settings.
 * 
 * The dialog includes fields for:
 * - Gravity X and Y components
 * - Simulation speed
 * - Bounce energy remaining
 * - Speed limit
 * - General energy loss
 * - Shakeable option
 * - Background color
 * 
 * The dialog provides OK and Cancel buttons to confirm or discard the changes.
 * 
 * Constructor:
 * @param parent The parent frame of the dialog.
 * @param simulationParameters The initial simulation parameters to be displayed in the dialog.
 * 
 * Methods:
 * - initLabels(): Initializes and adds labels and input fields to the dialog.
 * - initOKButton(): Initializes and adds the OK button to the dialog.
 * - okPressed(): Handles the action when the OK button is pressed, validates input, and updates the simulation parameters.
 * - initCancelButton(): Initializes and adds the Cancel button to the dialog.
 * - cancelPressed(): Handles the action when the Cancel button is pressed, discards changes, and closes the dialog.
 * - setDefaultValues(SimulationParameters params): Sets the default values of the input fields based on the provided simulation parameters.
 * - setDialogProperties(): Sets the properties of the dialog such as size and resizability.
 * - getSimulationParameters(): Returns the current simulation parameters set in the dialog.
 */
public class ParamsDialog extends JDialog {
    private JTextField gravityXField;
    private JTextField gravityYField;
    private JTextField simulationSpeedField;
    private JTextField bounceEnergyRemainingField;
    private JTextField speedLimitField;
    private JTextField generalEnergyLossField;
    private JCheckBox shakeableCheckBox;
    private ColorPicker backgroundColorPicker;

    private transient SimulationParameters originalParameters;
    private transient SimulationParameters currentParameters;
    
    /**
     * A dialog for configuring simulation parameters.
     * This dialog allows the user to view and modify the parameters for a simulation.
     * It is initialized with a parent frame and a set of simulation parameters.
     * 
     * @param parent The parent frame of the dialog.
     * @param simulationParameters The initial simulation parameters to be displayed and modified.
     */
    public ParamsDialog(Frame parent, SimulationParameters simulationParameters) {
        super(parent, "Simulation Parameters", true);
        this.originalParameters = simulationParameters;
        setLayout(new GridLayout(9, 2));
        setDialogProperties();

        initLabels();

        initOKButton();

        initCancelButton();        

        setDefaultValues(originalParameters);
        pack();
        setLocationRelativeTo(parent);
    }

    /**
     * Initializes and adds labels and corresponding input fields to the dialog.
     * The fields include:
     * - Gravity X: A text field for the X component of gravity.
     * - Gravity Y: A text field for the Y component of gravity.
     * - Simulation Speed: A text field for the speed of the simulation.
     * - Bounce Energy Remaining: A text field for the remaining energy after a bounce.
     * - Speed Limit: A text field for the maximum speed limit.
     * - General Energy Loss: A text field for the general energy loss.
     * - Shakeable: A checkbox to indicate if the object is shakeable.
     * - Background Color: A color picker for selecting the background color.
     */
    void initLabels(){
        add(new JLabel("Gravity X:"));
        gravityXField = new JTextField();
        add(gravityXField);

        add(new JLabel("Gravity Y:"));
        gravityYField = new JTextField();
        add(gravityYField);

        add(new JLabel("Simulation Speed:"));
        simulationSpeedField = new JTextField();
        add(simulationSpeedField);

        add(new JLabel("Bounce Energy Remaining:"));
        bounceEnergyRemainingField = new JTextField();
        add(bounceEnergyRemainingField);

        add(new JLabel("Speed Limit:"));
        speedLimitField = new JTextField();
        add(speedLimitField);

        add(new JLabel("General Energy Loss:"));
        generalEnergyLossField = new JTextField();
        add(generalEnergyLossField);

        add(new JLabel("Shakeable:"));
        shakeableCheckBox = new JCheckBox();
        add(shakeableCheckBox);

        add(new JLabel("Background Color:"));
        backgroundColorPicker = new ColorPicker();
        add(backgroundColorPicker);
    }

    /**
     * Initializes the OK button, sets its label to "OK", 
     * adds an action listener to handle the button press event, 
     * and adds the button to the current container.
     */
    @SuppressWarnings("unused")
    void initOKButton(){
        JButton okButton = new JButton("OK");
        okButton.addActionListener(o -> okPressed());
        add(okButton);
    }

    /**
     * Handles the action when the OK button is pressed.
     * 
     * This method reads the input values from the dialog fields, parses them,
     * and updates the current simulation parameters. If any of the input values
     * are invalid (i.e., not a valid number), it shows an error message dialog.
     * 
     * The parameters updated include:
     * - Gravity in the X and Y directions
     * - Simulation speed
     * - Bounce energy remaining
     * - Speed limit
     * - General energy loss
     * - Whether the simulation is shakeable
     * - Background color of the simulation
     * 
     * If all inputs are valid, the dialog is hidden.
     */
    void okPressed() {
        try {
            double gravityX = Double.parseDouble(gravityXField.getText());
            double gravityY = Double.parseDouble(gravityYField.getText());
            double simulationSpeed = Double.parseDouble(simulationSpeedField.getText());
            double bounceEnergyRemaining = Double.parseDouble(bounceEnergyRemainingField.getText());
            double speedLimit = Double.parseDouble(speedLimitField.getText());
            double generalEnergyLoss = Double.parseDouble(generalEnergyLossField.getText());
            boolean shakeable = shakeableCheckBox.isSelected();
            Color backgroundColor = backgroundColorPicker.getColor();

            currentParameters = new SimulationParameters(
                    new Vector2D(gravityX, gravityY),
                    simulationSpeed,
                    bounceEnergyRemaining,
                    speedLimit,
                    generalEnergyLoss,
                    shakeable,
                    backgroundColor);

            setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers in all fields.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initializes the cancel button and adds it to the dialog.
     * The button is labeled "Cancel" and triggers the cancelPressed() method when clicked.
     * This method is intended to be used internally and is marked with the @SuppressWarnings("unused") annotation.
     */
    @SuppressWarnings("unused")
    void initCancelButton(){
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(c -> cancelPressed());
        add(cancelButton);
    }

    /**
     * Handles the action when the cancel button is pressed.
     * Sets the current parameters to null and hides the dialog.
     */
    void cancelPressed(){
        currentParameters = null;
        setVisible(false);
    }

    /**
     * Sets the default values for the simulation parameters in the dialog fields.
     *
     * @param params the SimulationParameters object containing the default values
     */
    public void setDefaultValues(SimulationParameters params) {
        gravityXField.setText(String.valueOf(params.getGravity().getX()));
        gravityYField.setText(String.valueOf(params.getGravity().getY()));
        simulationSpeedField.setText(String.valueOf(params.getSimulationSpeed()));
        bounceEnergyRemainingField.setText(String.valueOf(params.getBounceEnergyRemaining()));
        speedLimitField.setText(String.valueOf(params.getSpeedLimit()));
        generalEnergyLossField.setText(String.valueOf(params.getGeneralEnergyLoss()));
        shakeableCheckBox.setSelected(params.isShakeable());
        backgroundColorPicker.setSelectedItem("White");
    }

    /**
     * Sets the properties for the dialog.
     * This method sets the preferred size of the dialog to 400x300 pixels
     * and makes the dialog non-resizable.
     */
    private void setDialogProperties() {
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
    }

    public SimulationParameters getSimulationParameters() {
        return currentParameters;
    }
}