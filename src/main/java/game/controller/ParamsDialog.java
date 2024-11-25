package game.controller;

import game.model.SimulationParameters;
import game.model.Vector2D;
import game.view.ColorPicker;

import javax.swing.*;
import java.awt.*;

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

    @SuppressWarnings("unused")
    void initOKButton(){
        JButton okButton = new JButton("OK");
        okButton.addActionListener(o -> okPressed());
        add(okButton);
    }

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

    @SuppressWarnings("unused")
    void initCancelButton(){
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(c -> cancelPressed());
        add(cancelButton);
    }

    void cancelPressed(){
        currentParameters = null;
        setVisible(false);
    }

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

    private void setDialogProperties() {
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
    }

    public SimulationParameters getSimulationParameters() {
        return currentParameters;
    }
}