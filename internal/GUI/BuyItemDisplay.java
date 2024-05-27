package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyItemDisplay extends BaseLayer {
    private ActionListener XattackButton, XdefenseButton, potionButton, superPotionButton;
    private JPanel panel;
    private Player player;
    private JButton Xattack, Xdefense, potion, superPotion;


    // Constructor to set up the GUI
    public BuyItemDisplay() {
        super(new JPanel(null));
        setTitle("Game Shop");
        setSize(960, 590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background Panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\gameshop1.jpg"));
        panel.add(background);
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 300)); // Adjust the last parameter to position buttons vertically

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));


        Xattack = new JButton("Buy X Attack");
        Xattack.setBorder(new LineBorder(Color.DARK_GRAY, 2)); // Set border color and thickness
        Xdefense = new JButton("Buy X Defense");
        Xdefense.setBorder(new LineBorder(Color.DARK_GRAY, 2)); // Set border color and thickness
        potion = new JButton("Buy Potion");
        potion.setBorder(new LineBorder(Color.DARK_GRAY, 2)); // Set border color and thickness
        superPotion = new JButton("Buy Super Potion");
        superPotion.setBorder(new LineBorder(Color.DARK_GRAY, 2)); // Set border color and thickness

        Xattack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "X Attack Item bought!");
            }
        });

        Xdefense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "X Defense Item bought!");
            }
        });

        potion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Health Potion bought!");
            }
        });

        superPotion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Super Health Potion bought!");
            }
        });

        // Adding buttons to the background label
        background.add(Xattack);
        background.add(Xdefense);
        background.add(potion);
        background.add(superPotion);


        // Adding the panel to the frame
        add(panel);

        // Center the window
        setLocationRelativeTo(null);
    }

    private void setUpButton(){
        Xattack = createButton("X attack", 70, 300, 300, 30, Color.LIGHT_GRAY, XattackButton);
        Xdefense = createButton("X defense", 70, 350, 300, 30, Color.LIGHT_GRAY, XdefenseButton);
        potion = createButton("Potion", 70, 400, 300, 30, Color.LIGHT_GRAY, potionButton);
        superPotion = createButton("superPotion", 70, 400, 300, 30, Color.LIGHT_GRAY, superPotionButton);
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color background, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(background);
        if (action != null) {
            button.addActionListener(action);
        }
        getContentPane().add(button);
        return button;
    }

    public static void main(String[] args) {
        // Run the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuyItemDisplay().setVisible(true);
            }
        });
    }
}
