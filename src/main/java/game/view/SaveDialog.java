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

    /**
     * Constructs a SaveDialog with the specified parent frame.
     * This dialog is modal and titled "Save Simulation".
     * It initializes the dialog, components, and adds them to the dialog.
     * Additionally, it sets up the necessary listeners.
     *
     * @param parent the parent frame of this dialog
     */
    public SaveDialog(Frame parent) {
        super(parent, "Save Simulation", true);
        initializeDialog(parent);
        initializeComponents();
        addComponentsToDialog();
        addListeners();
    }

    /**
     * Initializes the dialog with the specified parent frame.
     * Sets the layout to BorderLayout, size to 400x300 pixels,
     * and centers the dialog relative to the parent frame.
     *
     * @param parent the parent frame to which this dialog is relative
     */
    private void initializeDialog(Frame parent) {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    /**
     * Initializes the components for the SaveDialog.
     * This method sets up the text field, check box, and buttons used in the dialog.
     * The saveToExistingFileCheckBox is selected by default.
     * The okButton is enabled, and the nameField is disabled initially.
     */
    private void initializeComponents() {
        nameField = new JTextField();
        saveToExistingFileCheckBox = new JCheckBox();
        saveToExistingFileCheckBox.setSelected(true);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        okButton.setEnabled(true);
        nameField.setEnabled(false);
    }

    /**
     * Adds the main components to the dialog.
     * This method creates and adds the main panel to the center of the dialog
     * and the button panel to the south of the dialog.
     */
    private void addComponentsToDialog() {
        JPanel panel = createMainPanel();
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates the main panel for the save dialog.
     * The panel contains a grid layout with 3 rows and 2 columns, 
     * and has an empty border with 20 pixels on each side.
     * 
     * The panel includes the following components:
     * - A label for the simulation name.
     * - A text field for entering the simulation name.
     * - A label for saving to an existing file.
     * - A checkbox for selecting whether to save to an existing file.
     * 
     * @return The main panel for the save dialog.
     */
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

    /**
     * Creates a panel containing the OK and Cancel buttons.
     *
     * @return a JPanel containing the OK and Cancel buttons.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    /**
     * Adds listeners to the components in the SaveDialog.
     * <p>
     * This method sets up action listeners for the OK and Cancel buttons,
     * a document listener for the name field, and an action listener for the
     * saveToExistingFileCheckBox. The listeners handle actions such as saving,
     * closing the dialog, updating button states, and enabling saving.
     * </p>
     */
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

    /**
     * Updates the state of the OK button based on the input in the name field and the state of the saveToExistingFileCheckBox.
     * The OK button is enabled if the name field is not empty or if the saveToExistingFileCheckBox is selected.
     */
    private void updateButtonState() {
        okButton.setEnabled(!nameField.getText().trim().isEmpty() || saveToExistingFileCheckBox.isSelected());
    }

    /**
     * Enables or disables the name field based on the state of the saveToExistingFileCheckBox.
     * If the checkbox is selected, the name field is disabled. Otherwise, it is enabled.
     * Also updates the state of the save button.
     */
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
