package PBOFINALPROJECTHURA.internal.GUI;
import PBOFINALPROJECTHURA.internal.Item;
import PBOFINALPROJECTHURA.internal.XItem;
import PBOFINALPROJECTHURA.internal.Potion;
import PBOFINALPROJECTHURA.internal.MoneyException;
import PBOFINALPROJECTHURA.internal.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyItemDisplay extends BaseLayer implements ActionListener {
    private JButton XattackButton, XdefenseButton, potionButton, superPotionButton, backButton;
    private JPanel panel;
    private JLabel goldLabel;
    private int goldAmount = 1000;
    private Player player;

    public BuyItemDisplay(Player player) {
        super(new JPanel(null), player);
        panel = (JPanel) getContentPane();
        setTitle("Game Shop");
        setSize(960, 590);

        panel.setLayout(null);
        setUpBackground("src/PBOFINALPROJECTHURA/assets/images/gameshop1.jpg", "homeBase");
        setUpButton();
        setUpGoldLabel();

        revalidate();
        repaint();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    private void setUpButton() {
        XattackButton = createButton("Xattack", 240, 210, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/xattack.png", this);
        XdefenseButton = createButton("Xdefense", 580, 210, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/xdefense.png", this);
        potionButton = createButton("Potion", 240, 380, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/potion.png", this);
        superPotionButton = createButton("Super Potion", 580, 380, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/superPotion.png", this);
        backButton = createButton(" ", 10, 10, 50, 50, "src/PBOFINALPROJECTHURA/assets/images/saveIcon.png", this);
    }

    private void setUpGoldLabel() {
        goldLabel = new JLabel();
        goldLabel.setBounds(785, 8, 150, 55);
        goldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon goldIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/gold.png"); // Use the uploaded image path
        if (goldIcon.getIconWidth() == -1) {
            System.out.println("Gold icon image not found!");
        } else {
            goldLabel.setIcon(goldIcon);
            goldLabel.setIconTextGap(5);  // Adds a gap between icon and text
        }
        goldLabel.setText("Gold:" + goldAmount);
        goldLabel.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(goldLabel);
    }

    private JButton createButton(String text, int x, int y, int width, int height, String imagePath, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.LIGHT_GRAY);

        // Load the image and set it as the button icon
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getIconWidth() == -1) {
            System.out.println("Image for " + text + " not found at " + imagePath);
        } else {
            button.setIcon(icon);
        }

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
        try {
            if (e.getSource() == XattackButton) {
                int itemCost = 100;
                decreaseGold(itemCost);
                Item xAttack = new XItem(50, "x attack");  // Assuming '50' is the bonus given
                player.addItem(xAttack);
                System.out.println("Xattack item purchased");
            } else if (e.getSource() == XdefenseButton) {
                int itemCost = 100;
                decreaseGold(itemCost);
                Item xDefense = new XItem(50, "x defense");  // Similarly, assuming '50' is the bonus given
                player.addItem(xDefense);
                System.out.println("Xdefense item purchased");
            } else if (e.getSource() == potionButton) {
                int itemCost = 150;
                decreaseGold(itemCost);
                Item potion = new Potion(50, "potion");  // Assuming '50' is the health given
                player.addItem(potion);
                System.out.println("Potion item purchased");
            } else if (e.getSource() == superPotionButton) {
                int itemCost = 250;
                decreaseGold(itemCost);
                Item superPotion = new Potion(200, "super potion");  // Assuming '200' is the health given
                player.addItem(superPotion);
                System.out.println("Super Potion item purchased");
            }
        } catch (MoneyException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Purchase Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void decreaseGold(int amount) throws MoneyException {
        if (goldAmount < amount) {
            throw new MoneyException("Not enough gold to complete the purchase.");
        }
        goldAmount -= amount;
        goldLabel.setText("Gold: " + goldAmount);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Player player = new Player("PlayerName"); // Create a new player
            BuyItemDisplay bs = new BuyItemDisplay(player);
            bs.setPlayer(player);  // Set the player
            bs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bs.setVisible(true);
        });
    }

}