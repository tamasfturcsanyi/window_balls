package game.view;

import javax.swing.*;
import java.awt.*;



/**
 * SaveDialog is a custom dialog for saving a simulation. It provides options to save the simulation
 * with a specified name or to an existing file.
 * 
 * <p>This dialog contains the following components:
 * <ul>
 *   <li>A text field for entering the simulation name.</li>
 *   <li>A checkbox to indicate whether to save to an existing file.</li>
 *   <li>OK and Cancel buttons to confirm or cancel the save operation.</li>
 * </ul>
 * 
 * <p>The dialog's behavior includes:
 * <ul>
 *   <li>Enabling/disabling the OK button based on the state of the text field and checkbox.</li>
 *   <li>Enabling/disabling the text field based on the state of the checkbox.</li>
 *   <li>Setting the save flag and hiding the dialog when the OK button is pressed.</li>
 * </ul>
 * 
 * <p>Usage example:
 * <pre>
 * {@code
 * JFrame frame = new JFrame();
 * SaveDialog dialog = new SaveDialog(frame);
 * dialog.setVisible(true);
 * if (dialog.getSave()) {
 *     if (dialog.toExistingFile()) {
 *         // Save to existing file
 *     } else {
 *         String fileName = dialog.getFileName();
 *         // Save with the specified file name
 *     }
 * }
 * }
 * </pre>
 * 
 * @param parent the parent frame of the dialog
 */
public class SaveDialog extends JDialog {
    private JTextField nameField;
    private JCheckBox saveToExistingFileCheckBox;
    private JButton okButton;
    private JButton cancelButton;

    boolean save = false;

    public SaveDialog(Frame parent) {
        super(parent, "Save Simulation", true);
        initializeDialog(parent);
        initializeComponents();
        addComponentsToDialog();
        addListeners();
    }

    private void initializeDialog(Frame parent) {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        nameField = new JTextField();
        saveToExistingFileCheckBox = new JCheckBox();
        saveToExistingFileCheckBox.setSelected(true);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        okButton.setEnabled(true);
        nameField.setEnabled(false);
    }

    private void addComponentsToDialog() {
        JPanel panel = createMainPanel();
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Simulation Name:");
        JLabel saveToFileLabel = new JLabel("Save to existing file:");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(saveToFileLabel);
        panel.add(saveToExistingFileCheckBox);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    @SuppressWarnings("unused")
    private void addListeners() {
        okButton.addActionListener(s -> makeSave());
        cancelButton.addActionListener(s -> setVisible(false));

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
        });

        saveToExistingFileCheckBox.addActionListener(e -> enableSaving());
    }

    private void updateButtonState() {
        okButton.setEnabled(!nameField.getText().trim().isEmpty() || saveToExistingFileCheckBox.isSelected());
    }

    void enableSaving() {
        nameField.setEnabled(!saveToExistingFileCheckBox.isSelected());
        updateButtonState();
    }

    void makeSave(){
        save = true;
        setVisible(false);
    }

    public boolean toExistingFile(){
        return saveToExistingFileCheckBox.isSelected();
    }

    public boolean getSave() {
        return save;
    }

    public String getFileName(){
        return nameField.getText();
    }
}
