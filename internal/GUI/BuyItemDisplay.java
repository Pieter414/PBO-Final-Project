//package PBOFINALPROJECTHURA.internal.GUI;
//
//import PBOFINALPROJECTHURA.internal.Player;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class BuyItemDisplay extends JFrame {
//    private JButton XattackButton, XdefenseButton, potionButton, superPotionButton;
//    private JPanel panel;
//    private Player player;
//
//    // Constructor to set up the GUI
//    public BuyItemDisplay() {
//        setTitle("Game Shop");
//        setSize(960, 590);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Background Panel
//        panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        JLabel background = new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\gameshop1.jpg"));
//        panel.add(background);
//        background.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 300)); // Adjust the last parameter to position buttons vertically
//
//        // Creating buttons
//        JButton Xattack = new JButton("C:\\Users\\USER\\Desktop\\Asset PBO\\xattack.jpg");
//        JButton Xdefense = new JButton("C:\\Users\\USER\\Desktop\\Asset PBO\\xdefense.jpg");
//        JButton potion = new JButton("C:\\Users\\USER\\Desktop\\Asset PBO\\potion.jpg");
//        JButton superPotion = new JButton("C:\\Users\\USER\\Desktop\\Asset PBO\\superPotion.jpg");
//
//        // Adding action listeners to buttons
//        Xattack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "X attack bought!");
//            }
//        });
//
//        Xdefense.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "X defense bought!");
//            }
//        });
//
//        potion.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Potion bought!");
//            }
//        });
//
//        superPotion.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Super Potion bought!");
//            }
//        });
//
//        // Adding buttons to the background label
//        background.add(XattackButton);
//        background.add(XdefenseButton);
//        background.add(potionButton);
//        background.add(superPotionButton);
//
//
//        // Adding the panel to the frame
//        add(panel);
//
//        // Center the window
//        setLocationRelativeTo(null);
//    }
//
//    public static void main(String[] args) {
//        // Run the GUI
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new BuyItemDisplay().setVisible(true);
//            }
//        });
//    }
//}

package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuyItemDisplay extends BaseLayer implements ActionListener {
    private ActionListener XattackAction, XdefenseAction, potionAction, superPotionAction;
    private JLabel XattackLabel, XdefenseLabel, potionLabel, superPotionLabel;
    private JButton XattackButton, XdefenseButton, potionButton, superPotionButton;
    private JPanel panel;
    private Player player;

//    public BuyItemDisplay() {
//        super(new JPanel(null));
//        panel = (JPanel) getContentPane();
//        setTitle("Game Shop");
//        setSize(960, 590);
//
//        panel.setLayout(null);
//        setUpBackground("C:\\Users\\USER\\Desktop\\Asset PBO\\gameshop1.jpg", "homeBase");
//        initializeActionListeners();
//        setUpPokemonTitle();
//        setUpButton();
//        this.player = new Player("PLACEHOLDERNAMEFORAWHILE123456789");
//
//        revalidate();
//        repaint();
//    }

    public BuyItemDisplay(Player player) {
        super(new JPanel(null));
        panel = (JPanel) getContentPane();
        setTitle("Game Shop");
        setSize(960, 590);

        panel.setLayout(null);
        setUpBackground("C:\\Users\\USER\\Desktop\\Asset PBO\\gameshop1.jpg", "homeBase");
        initializeActionListeners();
        setUpPokemonTitle();
        setUpButton();
        this.player = player;

        revalidate();
        repaint();
    }

    private void setUpPokemonTitle(){
        ImageIcon XattackImage = new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\xattack.jpg");
        XattackLabel = new JLabel(XattackImage);
        XattackLabel.setLayout(null); // Use null layout to manually position buttons
        XattackLabel.setBounds(50, 40, XattackImage.getIconWidth(), XattackImage.getIconHeight());
        getContentPane().add(XattackLabel);
    }

    private void setUpButton(){
        XattackButton = createButton("Xattack", 70, 300, 300, 30, Color.LIGHT_GRAY, XattackAction);
        XdefenseButton = createButton("Xdefense", 70, 350, 300, 30, Color.LIGHT_GRAY, XdefenseAction);
        potionButton = createButton("Potion", 70, 400, 300, 30, Color.LIGHT_GRAY, potionAction);
        superPotionButton = createButton("Super Potion", 70, 400, 300, 30, Color.LIGHT_GRAY, superPotionAction);
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

    private void startButtonClicked(){
        // Start HomeBase dan Loading game
        if (player.getName().equals("PLACEHOLDERNAMEFORAWHILE123456789")){
            showPlayerInvalidMessage();
        }
        else{
            System.out.println("game starting");
            dispose();
        }
    }

    private void editButtonClicked(){
        // Edit Player dan pastikan sebelum start sudah ada player
        String input = JOptionPane.showInputDialog(panel, "Enter your name:");
        if (input != null) {  // Jika pengguna tidak membatalkan dialog
            player.setName(input);
        } else {
            System.out.println("User canceled the dialog.");
        }
    }

    private void exitButtonClicked(){
        dispose();
    }

    private void initializeActionListeners(){
        XattackAction = e -> startButtonClicked();
        XdefenseAction = e -> editButtonClicked();
        potionAction = e -> exitButtonClicked();
        superPotionAction = e -> exitButtonClicked();
    }

    private void showPlayerInvalidMessage() {
        JOptionPane.showMessageDialog(this, "You Need to Set Your Player Profile!");
    }
}

