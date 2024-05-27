package PBOFINALPROJECTHURA.internal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridMap extends JFrame {
    private static final int ROWS = 15;
    private static final int COLUMNS = 20;
    private JButton[][] gridButtons = new JButton[ROWS][COLUMNS];
    private int playerX = 0;
    private int playerY = 0;
    private ImageIcon playerIcon;
    private ImageIcon mapImage;
    private JLabel topObject, rightObject, bottomObject;

    public GridMap() {
        setTitle("Home Base");
        setSize(700, 525);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        playerIcon = new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\player.jpg");
        mapImage = new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\baseLayer.jpg");

        GridPanel backgroundPanel = new GridPanel(mapImage);
        backgroundPanel.setLayout(null); // Use null layout for absolute positioning
        setContentPane(backgroundPanel);

        initializeGrid(backgroundPanel);
        initializeObjects(backgroundPanel);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                movePlayer(e.getKeyCode());
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void initializeGrid(JPanel panel) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                JButton button = new JButton();
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setFocusable(false);
                button.setBounds(j * 35, i * 35, 35, 35); // Adjust to consistent cell size
                gridButtons[i][j] = button;
                panel.add(button);
            }
        }
        updatePlayerPosition(playerX, playerY);
    }

    private void initializeObjects(JPanel panel) {
        // Path correction and icon resizing as needed
        try {
            ImageIcon originalTopIcon = new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\exitIcon.png");
            ImageIcon topIcon = resizeIcon(originalTopIcon, 35, 35); // Resize to cell size
            topObject = new JLabel(topIcon);
            topObject.setBounds(300, 0, 35, 35); // Ensure bounds match resized dimensions
            panel.add(topObject);

            ImageIcon originalRightIcon = new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\exitIcon.png");
            ImageIcon rightIcon = resizeIcon(originalRightIcon, 35, 35);
            rightObject = new JLabel(rightIcon);
            rightObject.setBounds(620, 205, 35, 35);
            panel.add(rightObject);

            ImageIcon originalBottomIcon = new ImageIcon("C:\\Users\\USER\\Desktop\\Asset PBO\\exitIcon.png");
            ImageIcon bottomIcon = resizeIcon(originalBottomIcon, 35, 35);
            bottomObject = new JLabel(bottomIcon);
            bottomObject.setBounds(300, 420, 35, 35);
            panel.add(bottomObject);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading or resizing icons: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void updatePlayerPosition(int newX, int newY) {
        gridButtons[playerY][playerX].setIcon(null);
        playerX = newX;
        playerY = newY;
        gridButtons[playerY][playerX].setIcon(playerIcon);
        checkForInteraction();
    }

    private void movePlayer(int keyCode) {
        int newX = playerX, newY = playerY;
        switch (keyCode) {
            case KeyEvent.VK_UP: newY = Math.max(0, playerY - 1); break;
            case KeyEvent.VK_DOWN: newY = Math.min(ROWS - 1, playerY + 1); break;
            case KeyEvent.VK_LEFT: newX = Math.max(0, playerX - 1); break;
            case KeyEvent.VK_RIGHT: newX = Math.min(COLUMNS - 1, playerX + 1); break;
        }
        if (newX != playerX || newY != playerY) {
            updatePlayerPosition(newX, newY);
        }
    }

    private void checkForInteraction() {
        Rectangle playerRect = new Rectangle(playerX * 35, playerY * 35, 35, 35);
        System.out.println("Player Bounds: " + playerRect);

        // Check each object for intersection with the player's rectangle
        if (topObject != null && playerRect.intersects(topObject.getBounds())) {
            System.out.println("Top Object Bounds: " + topObject.getBounds());
            JOptionPane.showMessageDialog(this, "Interacted with Top Object!");
        }
        if (rightObject != null && playerRect.intersects(rightObject.getBounds())) {
            System.out.println("Right Object Bounds: " + rightObject.getBounds());
            JOptionPane.showMessageDialog(this, "Interacted with Right Object!");
        }
        if (bottomObject != null && playerRect.intersects(bottomObject.getBounds())) {
            System.out.println("Bottom Object Bounds: " + bottomObject.getBounds());
            JOptionPane.showMessageDialog(this, "Interacted with Bottom Object!");
        }
    }

    class GridPanel extends JPanel {
        private ImageIcon mapImage;

        public GridPanel(ImageIcon mapImage) {
            this.mapImage = mapImage;
            this.setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(mapImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GridMap().setVisible(true));
    }
}