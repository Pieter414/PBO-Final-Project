package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.Dungeon;
import PBOFINALPROJECTHURA.internal.Player;

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
    private Dungeon dungeon;
    private boolean canMove = true;  // Flag to control player movement
    private Player player;

    public DungeonPanel(Dungeon dungeon, char[][] map, int playerX, int playerY, Player player) {
        super(new JPanel(null), player); // Using the constructor of BaseLayer that expects a JPanel
        setUpBackground("", "dungeon");
        setTitle("Dungeon Game"); // Set the title of the JFrame
        setSize(517, 563); // Set the size of the JFrame
        this.dungeon = dungeon;
        this.map = map;
        this.playerX = playerX;
        this.playerY = playerY;
        this.player = player;
        loadIcons();

        setFocusable(true);
        requestFocusInWindow();

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
        if(dungeon instanceof PBOFINALPROJECTHURA.internal.ForestDungeon) {
            wallIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/forestWall.png");
            floorIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/forestFloor.png");
            playerIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/player.png");
            exitIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/exitDungeon.png");
        }else if(dungeon instanceof PBOFINALPROJECTHURA.internal.SwampDungeon){
            wallIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/swampWall.png");
            floorIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/swampFloor.png");
            playerIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/player.png");
            exitIcon =  new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/exitDungeon.png");
        }else {
            wallIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/volcanoWall.png");
            floorIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/volcanoFloor.png");
            playerIcon = new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/player.png");
            exitIcon =  new ImageIcon("src/PBOFINALPROJECTHURA/assets/images/exitDungeon.png");
        }
    }

    protected void drawMap() {
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
        drawMap();
    }
}
