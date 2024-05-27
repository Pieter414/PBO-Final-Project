package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeBaseDisplay extends BaseLayer {
    private BuyItemDisplay buyItemDisplay;
    private DungeonPanel dungeonDisplay;
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private JButton[][] mapButtons = new JButton[ROWS][COLUMNS]; // JButton array for GUI
    private char[][] map = {
           {'#', '#', '#', '#', '#', 'X', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'T'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', 'P', '#', '#', '#', 'M', '#', '#', '#', '#'}
    };
    private int playerX = 1; // Start at position of 'P'
    private int playerY = 9;
    private Player player;
    private ImageIcon playerIcon, wallIcon, pathIcon, exitIcon, buyIcon, chooseMonsterIcon;
    private ImageIcon mapImage;

    public HomeBaseDisplay(Player player) {
        super(new JPanel(null), player); // Mengatur layout ke null untuk mengatur posisi secara manual
        setTitle("Home Base");
        setSize(610, 657);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.player = player;

//        buyItemDisplay = new BuyItemDisplay();
//        buyItemDisplay.setPlayer(player);

        playerIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/player.png");
        mapImage = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/floor.png");

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
        wallIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/bricks.png");  // Load the wall image
        pathIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/floor.png");  // Load the path image
        playerIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/player.png");  // Load the player image
        exitIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/hole.png");  // Load the exit image
        chooseMonsterIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/pick.png");
        buyIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/shop.jpg");

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                JButton button = new JButton();
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setFocusable(false);
                button.setBounds(j * 60, i * 62, 60, 62);

                switch (map[i][j]) {
                    case '#':
                        button.setIcon(resizeIcon(wallIcon, 60, 62)); // Set wall icon
                        break;
                    case ' ':
                        button.setIcon(resizeIcon(pathIcon, 60, 62)); // Set path icon
                        break;
                    case 'P':
                        playerX = j;
                        playerY = i;
                        button.setIcon(resizeIcon(playerIcon, 60, 62)); // Set player icon
                        break;
                    case 'X':
                        button.setIcon(resizeIcon(exitIcon, 60, 62)); // Set exit icon
                        break;
                    case 'T':
                        button.setIcon(resizeIcon(buyIcon, 60, 62)); // Set exit icon
                        break;
                    case 'M':
                        button.setIcon(resizeIcon(chooseMonsterIcon, 60, 62)); // Set exit icon
                        break;
                }

                mapButtons[i][j] = button;
                panel.add(button);
            }
        }
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void movePlayer(int keyCode) {
        int newX = playerX, newY = playerY;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                newY = Math.max(0, playerY - 1);
                break;
            case KeyEvent.VK_DOWN:
                newY = Math.min(ROWS - 1, playerY + 1);
                break;
            case KeyEvent.VK_LEFT:
                newX = Math.max(0, playerX - 1);
                break;
            case KeyEvent.VK_RIGHT:
                newX = Math.min(COLUMNS - 1, playerX + 1);
                break;
        }
        if (newX != playerX || newY != playerY) {
            if (map[newY][newX] != '#') { // Ensure it's not a wall
                restorePreviousIcon(playerX, playerY);  // Restore the icon of the previous cell
                playerX = newX;
                playerY = newY;
                mapButtons[playerY][playerX].setIcon(resizeIcon(playerIcon, 60, 62)); // Set player icon at new position
                checkForExit(newX, newY);  // Check if the player has reached an exit
            }
        }
    }

    private void restorePreviousIcon(int x, int y) {
        switch (map[y][x]) {
            case ' ':
                mapButtons[y][x].setIcon(resizeIcon(pathIcon, 60, 62)); // Reset path icon
                break;
            case 'X':
                mapButtons[y][x].setIcon(resizeIcon(exitIcon, 60, 62)); // Reset exit icon
                break;
            case 'T':
                mapButtons[y][x].setIcon(resizeIcon(buyIcon, 60, 62)); // Reset exit icon
                break;
            case 'M':
                mapButtons[y][x].setIcon(resizeIcon(chooseMonsterIcon, 60, 62)); // Reset exit icon
                break;
            default:
                mapButtons[y][x].setIcon(null); // Clear any other icon (not necessary if every cell is handled)
        }
    }

    private void checkForExit(int x, int y) {
        if (map[y][x] == 'X') {
            if (player.getMonster1() != null && player.getMonster2() != null && player.getMonster3() != null){
                String[] options = {"Forest Dungeon", "Swamp Dungeon", "Volcano Dungeon"};

                int choice = JOptionPane.showOptionDialog(null,
                        "Pilih Dungeon yang Anda Inginkan:",
                        "Dungeon",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);

                switch (choice) {
                    case 0:
                        ForestDungeon fd = new ForestDungeon(player);
                        break;
                    case 1:
                        SwampDungeon sd = new SwampDungeon(player);
                        break;
                    case 2:
                        VolcanoDungeon vd = new VolcanoDungeon(player);
                        break;
                }
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "You Need To Have Full Team In The Dungeon!");
            }
        }
        else if (map[y][x] == 'T') {
            buyItemDisplay = new BuyItemDisplay(player);
            buyItemDisplay.setVisible(true);
            buyItemDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            JOptionPane.showMessageDialog(this, "Buy Item!");
        }
        else if (map[y][x] == 'M') {
            MonsterBoxDisplay mbd = new MonsterBoxDisplay(player);
            mbd.setVisible(true);
        }

    }


    private void initializeObjects(JPanel panel) {
        // Objects initialization remains unchanged.
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
        SwingUtilities.invokeLater(() -> new HomeBaseDisplay(new Player("")).setVisible(true));
    }
}

