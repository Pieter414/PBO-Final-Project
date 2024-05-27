package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< Updated upstream
public class BuyItemDisplay extends BaseLayer {
    private ActionListener XattackButton, XdefenseButton, potionButton, superPotionButton;
    private JPanel panel;
    private Player player;
    private JButton Xattack, Xdefense, potion, superPotion;


    // Constructor to set up the GUI
    public BuyItemDisplay() {
        super(new JPanel(null));
=======
public class BuyItemDisplay extends JFrame {
    private JPanel panel;
    private Player player;

    // Constructor to set up the GUI
    public BuyItemDisplay() {
>>>>>>> Stashed changes
        setTitle("Game Shop");
        setSize(960, 590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background Panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
<<<<<<< Updated upstream
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
=======
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\gameshop.jpg"));
        panel.add(background);
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 300)); // Adjust the last parameter to position buttons vertically

        // Creating and styling buttons
        JButton Xattack = createStyledButton("Buy X Attack", new Color(255, 69, 0), new Color(255, 255, 255));
        JButton Xdefense = createStyledButton("Buy X Defense", new Color(30, 144, 255), new Color(255, 255, 255));
        JButton potion = createStyledButton("Buy Health Potion", new Color(50, 205, 50), new Color(255, 255, 255));
        JButton superPotion = createStyledButton("Buy Super Potion", new Color(148, 0, 211), new Color(255, 255, 255));
>>>>>>> Stashed changes

        // Adding buttons to the background label
        background.add(Xattack);
        background.add(Xdefense);
        background.add(potion);
        background.add(superPotion);

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        // Adding the panel to the frame
        add(panel);

        // Center the window
        setLocationRelativeTo(null);
    }

<<<<<<< Updated upstream
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
=======
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(220, 50)); // Set button size
        button.setBackground(bgColor); // Set background color
        button.setForeground(fgColor); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
        button.setBorder(new LineBorder(Color.WHITE, 3)); // Set border color and thickness
>>>>>>> Stashed changes
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
