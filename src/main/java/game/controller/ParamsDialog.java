package game.controller;

import game.model.SimulationParameters;
import game.model.Vector2D;
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
    private SimulationParameters originalParameters;
    private SimulationParameters currentParameters;
    
    public ParamsDialog(Frame parent, SimulationParameters simulationParameters) {
        super(parent, "Simulation Parameters", true);
        this.originalParameters = simulationParameters;
        setLayout(new GridLayout(8, 2));
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
    }

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

            currentParameters = new SimulationParameters(
                    new Vector2D(gravityX, gravityY),
                    simulationSpeed,
                    bounceEnergyRemaining,
                    speedLimit,
                    generalEnergyLoss,
                    shakeable);

            setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers in all fields.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

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
    }

    private void setDialogProperties() {
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
    }

    public SimulationParameters getSimulationParameters() {
        return currentParameters;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("ParamsDialog Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);

                ParamsDialog dialog = new ParamsDialog(frame,new SimulationParameters());
                dialog.setVisible(true);

                SimulationParameters params = dialog.getSimulationParameters();
                if (params != null) {
                    System.out.println("Gravity: " + params.getGravity());
                    System.out.println("Simulation Speed: " + params.getSimulationSpeed());
                    System.out.println("Bounce Energy Remaining: " + params.getBounceEnergyRemaining());
                    System.out.println("Speed Limit: " + params.getSpeedLimit());
                    System.out.println("General Energy Loss: " + params.getGeneralEnergyLoss());
                    System.out.println("Shakeable: " + params.isShakeable());
                } else {
                    System.out.println("Dialog was cancelled.");
                }
            }
        });
    }
}