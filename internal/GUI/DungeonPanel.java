package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.DungeonTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DungeonPanel extends BaseLayer {
    private char[][] map;
    private int playerX;
    private int playerY;
    private ImageIcon wallIcon;
    private ImageIcon floorIcon;
    private ImageIcon playerIcon;
    private ImageIcon exitIcon;
    private DungeonTest dungeon;
    private boolean canMove = true;  // Flag to control player movement

    public DungeonPanel(DungeonTest dungeon, char[][] map, int playerX, int playerY) {
        super(new JPanel(null)); // Using the constructor of BaseLayer that expects a JPanel
        setUpBackground("", "dungeon");
        setTitle("Dungeon Game"); // Set the title of the JFrame
        setSize(517, 563); // Set the size of the JFrame
        this.dungeon = dungeon;
        this.map = map;
        this.playerX = playerX;
        this.playerY = playerY;
        loadIcons();

        setFocusable(true);  // Memungkinkan panel menerima fokus
        requestFocusInWindow();  // Meminta fokus input

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (canMove){
                    int key = e.getKeyCode();
                    switch (key) {
                        case KeyEvent.VK_UP:
                            dungeon.movePlayer(0, -1);
                            break;
                        case KeyEvent.VK_DOWN:
                            dungeon.movePlayer(0, 1);
                            break;
                        case KeyEvent.VK_LEFT:
                            dungeon.movePlayer(-1, 0);
                            break;
                        case KeyEvent.VK_RIGHT:
                            dungeon.movePlayer(1, 0);
                            break;
                    }
                }
            }
        });

        repaint(); // Call to paint the components
    }

    public void enableMovement() {
        canMove = true;
    }

    public void disableMovement() {
        canMove = false;
    }

    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX >= 0 && newX < map[0].length && newY >= 0 && newY < map.length && map[newY][newX] == ' ') {
            map[playerY][playerX] = ' '; // Clear the old player position
            playerX = newX;
            playerY = newY;
            map[playerY][playerX] = 'P';
            repaint();  // Redraw the panel to show the new player position
        }
    }

    private void loadIcons() {
        wallIcon = new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\dungeon\\wallIcon.png");
        floorIcon = new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\dungeon\\floorIcon.png");
        playerIcon = new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\dungeon\\playerIcon.png");
        exitIcon =  new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\dungeon\\exitIcon.png");
    }

    protected void paintComponent() {
        Graphics g = getContentPane().getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        int tileSize = getWidth() / map.length;
        int boundX = 50, boundY = 50;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                ImageIcon icon = null;
                switch (map[row][col]) {
                    case '#':
                        icon = wallIcon;
                        break;
                    case ' ':
                        icon = floorIcon;
                        break;
                    case 'P':
                        icon = playerIcon;
                        break;
                    case 'X':
                        icon = exitIcon;
                        break;
                }
                if (icon != null) {
                    g2d.drawImage(icon.getImage(), col * boundX, row * boundY, tileSize, tileSize, this);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintComponent(); // Delegate to paintComponent for custom painting
    }
}
