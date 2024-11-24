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
    private JComboBox<String> colorComboBox;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JTextField centerXField;
    private JTextField centerYField;
    private JTextField radiusField;
    private JTextField bouncinessField;
    private JTextField massField;


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
        colorComboBox = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Yellow", "Black", "White", "Gray", "Cyan", "Magenta", "Pink", "Orange"});

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
        centerXField = new JTextField();
        panel.add(centerXField);
        panel.add(new JLabel("Center Y:"));
        centerYField = new JTextField();
        panel.add(centerYField);
        panel.add(new JLabel("Radius:"));
        radiusField = new JTextField();
        panel.add(radiusField);
        panel.add(new JLabel("Color:"));
        colorComboBox = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Yellow", "Black", "White", "Gray", "Cyan", "Magenta", "Pink", "Orange"});
        panel.add(colorComboBox);
        panel.add(new JLabel("Bounciness:"));
        bouncinessField = new JTextField();
        panel.add(bouncinessField);
        panel.add(new JLabel("Mass:"));
        massField = new JTextField();
        panel.add(massField);
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
        centerXField = new JTextField();
        panel.add(centerXField);
        panel.add(new JLabel("Center Y:"));
        centerYField = new JTextField();
        panel.add(centerYField);
        panel.add(new JLabel("Radius:"));
        radiusField = new JTextField();
        panel.add(radiusField);
        panel.add(new JLabel("Color:"));
        panel.add(colorComboBox);
        return panel;
    }

    void createBody() {
        switch ((String)bodyTypeComboBox.getSelectedItem()) {
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

    Ball createBall() {
        return new Ball(
                new Vector2D(Double.parseDouble(centerXField.getText()),
                        Double.parseDouble(centerYField.getText())),
                Double.parseDouble(radiusField.getText()),
                Color.getColor((String) colorComboBox.getSelectedItem()),
                Double.parseDouble(bouncinessField.getText()), Double.parseDouble(massField.getText()));
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
                new Vector2D(Double.parseDouble(centerXField.getText()),
                        Double.parseDouble(centerYField.getText())),
                Double.parseDouble(radiusField.getText()),
                Color.getColor((String) colorComboBox.getSelectedItem()));
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