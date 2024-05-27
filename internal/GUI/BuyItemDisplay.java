package PBOFINALPROJECTHURA.internal.GUI;

public class BuyItemDisplay {

<<<<<<< Updated upstream
=======
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyItemDisplay extends BaseLayer implements ActionListener {
    private JButton XattackButton, XdefenseButton, potionButton, superPotionButton;
    private JPanel panel;
//    private ImageIcon Xattack, Xdefense, potion, superPotion;

    public BuyItemDisplay() {
        super(new JPanel(null));
        panel = (JPanel) getContentPane();
        setTitle("Game Shop");
        setSize(960, 590);

        panel.setLayout(null);
        setUpBackground("C:\\Users\\USER\\Desktop\\Asset PBO\\gameshop1.jpg", "homeBase");
        setUpButton();

        revalidate();
        repaint();
    }

    private void setUpButton(){
        XattackButton = createButton("Xattack", 240, 210, 125, 125, "C:\\Users\\USER\\Desktop\\Asset PBO\\xattack.png", this);
        XdefenseButton = createButton("Xdefense", 580, 210, 125, 125, "C:\\Users\\USER\\Desktop\\Asset PBO\\xdefense.png", this);
        potionButton = createButton("Potion", 240, 380, 125, 125, "C:\\Users\\USER\\Desktop\\Asset PBO\\potion.png", this);
        superPotionButton = createButton("Super Potion", 580, 380, 125, 125, "C:\\Users\\USER\\Desktop\\Asset PBO\\superPotion.png", this);
    }

    private JButton createButton(String text, int x, int y, int width, int height, String imagePath, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.LIGHT_GRAY);

        // Load the image and set it as the button icon
        ImageIcon icon = new ImageIcon(imagePath);
        button.setIcon(icon);

        // Set icon alignment
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        // Set text position relative to the icon
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        if (action != null) {
            button.addActionListener(action);
        }
        getContentPane().add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == XattackButton) {
            // Handle Xattack button click
            System.out.println("Xattack button clicked");
            // Add logic for Xattack item purchase
        } else if (e.getSource() == XdefenseButton) {
            // Handle Xdefense button click
            System.out.println("Xdefense button clicked");
            // Add logic for Xdefense item purchase
        } else if (e.getSource() == potionButton) {
            // Handle potion button click
            System.out.println("Potion button clicked");
            // Add logic for Potion item purchase
        } else if (e.getSource() == superPotionButton) {
            // Handle super potion button click
            System.out.println("Super Potion button clicked");
            // Add logic for Super Potion item purchase
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BuyItemDisplay bs = new BuyItemDisplay();
            bs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bs.setVisible(true);
        });
    }
>>>>>>> Stashed changes
}
