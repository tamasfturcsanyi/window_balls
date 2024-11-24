package game.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import game.model.Vector2D;
import game.model.physicksbodies.Ball;
import game.model.physicksbodies.Brick;
import game.model.physicksbodies.Pole;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.Color;

import game.model.physicksbodies.PhysicksBody;


public class CreateBodyDialog extends JDialog {
    private JComboBox<String> bodyTypeComboBox;
    private JComboBox<String> poleColorComboBox;
    private JComboBox<String> ballColorComboBox;
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


    private PhysicksBody body;

    public class FontUtil {
        public static void setUIFont(FontUIResource f) {
            Enumeration<Object> keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof FontUIResource) {
                    UIManager.put(key, f);
                }
            }
        }
    }
    
    public CreateBodyDialog(Frame owner) {
        super(owner, "Create Body", true);
        setLayout(new BorderLayout());
        FontUtil.setUIFont(new FontUIResource(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 32)));
        setLayout(new BorderLayout());
        bodyTypeComboBox = new JComboBox<>(new String[]{"Ball", "Brick", "Pole"});
        bodyTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, (String) bodyTypeComboBox.getSelectedItem());
            }
        });
        poleColorComboBox = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Yellow", "Black", "White", "Gray", "Cyan", "Magenta", "Pink", "Orange"});
        ballColorComboBox = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Yellow", "Black", "White", "Gray", "Cyan", "Magenta", "Pink", "Orange"});

        cardPanel = new JPanel(cardLayout = new CardLayout());

        cardPanel.add(createBallPanel(), "Ball");
        cardPanel.add(createBrickPanel(), "Brick");
        cardPanel.add(createPolePanel(), "Pole");

        add(bodyTypeComboBox, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Create");
        addButton.addActionListener(c -> createBody());

        add(addButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }
    
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

    private boolean validateFields() {
        try {
            switch ((String) bodyTypeComboBox.getSelectedItem()) {
                case "Ball":
                    Double.parseDouble(ballCenterXField.getText());
                    Double.parseDouble(ballCenterYField.getText());
                    Double.parseDouble(ballRadiusField.getText());
                    Double.parseDouble(ballBouncinessField.getText());
                    Double.parseDouble(ballMassField.getText());
                    break;
                case "Brick":
                    Double.parseDouble(positionXField.getText());
                    Double.parseDouble(positionYField.getText());
                    Double.parseDouble(widthField.getText());
                    Double.parseDouble(heightField.getText());
                    break;
                case "Pole":
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

    void createBody() {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch ((String) bodyTypeComboBox.getSelectedItem()) {
            case "Ball":
                body = createBall();
                break;
            case "Brick":
                body = createBrick();
                break;
            case "Pole":
                body = createPole();
                break;
            default:
                break;
        }
        setVisible(false);
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

    Ball createBall() {
        return new Ball(
                new Vector2D(Double.parseDouble(ballCenterXField.getText()),
                        Double.parseDouble(ballCenterYField.getText())),
                Double.parseDouble(ballRadiusField.getText()),
                toColor((String) ballColorComboBox.getSelectedItem()),
                Double.parseDouble(ballBouncinessField.getText()),
                Double.parseDouble(ballMassField.getText()));
    }

    Brick createBrick(){
        return new Brick(
                new Vector2D(Double.parseDouble(positionXField.getText()),
                        Double.parseDouble(positionYField.getText())),
                new Vector2D(Double.parseDouble(widthField.getText()),
                        Double.parseDouble(heightField.getText())));
    }

    Pole createPole(){
        return new Pole(
                new Vector2D(Double.parseDouble(poleCenterXField.getText()),
                        Double.parseDouble(poleCenterYField.getText())),
                Double.parseDouble(poleRadiusField.getText()),
                toColor((String) poleColorComboBox.getSelectedItem()));
    }

    public PhysicksBody getBody() {
        return body;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Test AddBodyDialog");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);
                ArrayList<PhysicksBody> bodies = new ArrayList<>();

                CreateBodyDialog dialog = new CreateBodyDialog(frame);
                dialog.setVisible(true);
                PhysicksBody body = dialog.getBody();
                if (body != null) {
                    bodies.add(body);
                }                  
            }
        });
    }
}