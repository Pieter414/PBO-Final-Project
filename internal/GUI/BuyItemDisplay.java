package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyItemDisplay extends BaseLayer implements ActionListener {
    private JButton XattackButton, XdefenseButton, potionButton, superPotionButton, backButton;
    private JPanel panel;
    private Player player;
    private JLabel goldLabel;
    private int goldAmount;

    public BuyItemDisplay(Player player) {
        super(new JPanel(null), player);
        panel = (JPanel) getContentPane();
        setTitle("Game Shop");
        setSize(960, 590);
        setPlayer(player);
        this.goldAmount = player.getMoney();

        panel.setLayout(null);
        setUpBackground("src/PBOFINALPROJECTHURA/assets/images/gameshop1.jpg", "gameShop");
        setUpButton();
        setUpGoldLabel();

        revalidate();
        repaint();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    private void setUpButton() {
        XattackButton = createButton("X Attack", 240, 210, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/xattack.png", this);
        XdefenseButton = createButton("X Defense", 580, 210, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/xdefense.png", this);
        potionButton = createButton("Potion", 240, 380, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/potion.png", this);
        superPotionButton = createButton("Super Potion", 580, 380, 125, 125, "src/PBOFINALPROJECTHURA/assets/images/superPotion.png", this);
        backButton = createButton("Exit", 10, 10, 80, 50, "src/PBOFINALPROJECTHURA/assets/images/exit.png", this);
    }

    private void setUpGoldLabel() {
        goldLabel = new JLabel();
        goldLabel.setBounds(750, 15, 200, 55);
        goldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon goldIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/gold.png"); // Use the uploaded image path
        if (goldIcon.getIconWidth() == -1) {
            System.out.println("Gold icon image not found!");
        } else {
            goldLabel.setIcon(goldIcon);
            goldLabel.setIconTextGap(5);  // Adds a gap between icon and text
        }
        goldLabel.setText("Gold: " + goldAmount);
        goldLabel.setFont(new Font("Arial", Font.BOLD, 12));
        goldLabel.setForeground(Color.BLACK);
        goldLabel.setForeground(Color.WHITE);
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
        super.actionPerformed(e);
        try {
            if (e.getSource() == XattackButton) {
                int itemCost = 100;
                decreaseGold(itemCost);
                Item xAttack = new XItem(50, "x attack");  // Assuming '50' is the bonus given
                player.getListXAttack().add(xAttack);
                System.out.println("X attack item purchased");
            } else if (e.getSource() == XdefenseButton) {
                int itemCost = 100;
                decreaseGold(itemCost);
                Item xDefense = new XItem(50, "x defense");  // Similarly, assuming '50' is the bonus given
                player.getListXDefense().add(xDefense);
                System.out.println("X defense item purchased");
            } else if (e.getSource() == potionButton) {
                int itemCost = 150;
                decreaseGold(itemCost);
                Item potion = new Potion(50, "potion");  // Assuming '50' is the health given
                player.getListPotion().add(potion);
                System.out.println("Potion item purchased");
            } else if (e.getSource() == superPotionButton) {
                int itemCost = 250;
                decreaseGold(itemCost);
                Item superPotion = new Potion(200, "super potion");  // Assuming '200' is the health given
                player.getListSuperPotion().add(superPotion);
                System.out.println("Super Potion item purchased");
            } else if (e.getSource() == backButton){
                GameManager.saveGameProgress(player);
                dispose();
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
}