package game.controller;

import javax.swing.*;
import java.awt.*;



public class SaveDialog extends JDialog {
    private JTextField nameField;
    private JCheckBox saveToExistingFileCheckBox;
    private JButton okButton;
    private JButton cancelButton;

    boolean save = false;

    @SuppressWarnings("unused")
    public SaveDialog(Frame parent) {
        super(parent, "Save Simulation", true);
        setLayout(new BorderLayout());
        setSize(400, 300);
        
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Simulation Name:");
        nameField = new JTextField();

        JLabel saveToFileLabel = new JLabel("Save to existing file:");
        saveToExistingFileCheckBox = new JCheckBox();
        saveToExistingFileCheckBox.setSelected(true);

        

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(saveToFileLabel);
        panel.add(saveToExistingFileCheckBox);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        okButton = new JButton("OK");

        cancelButton = new JButton("Cancel");

        okButton.addActionListener(s -> makeSave());

        cancelButton.addActionListener(s -> setVisible(false));


        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.setEnabled(true);
        nameField.setEnabled(false);

        nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateButtonState();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateButtonState();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateButtonState();
            }

            public void updateButtonState() {
            okButton.setEnabled(!nameField.getText().trim().isEmpty() || saveToExistingFileCheckBox.isSelected());
            }
        });

        saveToExistingFileCheckBox.addActionListener(e -> enableSaving());

        add(buttonPanel, BorderLayout.SOUTH);
    }

    void enableSaving(){
        nameField.setEnabled(!saveToExistingFileCheckBox.isSelected());
        okButton.setEnabled(!nameField.getText().trim().isEmpty() || saveToExistingFileCheckBox.isSelected());
    }

    void makeSave(){
        save = true;
        setVisible(false);
    }

    boolean toExistingFile(){
        return saveToExistingFileCheckBox.isSelected();
    }

    public boolean getSave() {
        return save;
    }

    public String getFileName(){
        return nameField.getText();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SaveDialog dialog = new SaveDialog(frame);
        dialog.setVisible(true);
    }
}