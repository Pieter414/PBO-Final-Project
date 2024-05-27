import javax.swing.*;
import java.awt.*;

public class DungeonPanel extends BaseLayer {
    private char[][] map;
    private int playerX;
    private int playerY;
    private ImageIcon wallIcon;
    private ImageIcon floorIcon;
    private ImageIcon playerIcon;
    private ImageIcon exitIcon;

    public DungeonPanel(char[][] map, int playerX, int playerY) {
        super(new JPanel(null)); // Using the constructor of BaseLayer that expects a JPanel
        setTitle("Dungeon Game"); // Set the title of the JFrame
        setSize(500, 500); // Set the size of the JFrame
        this.map = map;
        this.playerX = playerX;
        this.playerY = playerY;
        loadIcons();
        repaint(); // Call to paint the components
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
        int tileSize = 50;

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
                    g2d.drawImage(icon.getImage(), col * tileSize, row * tileSize, tileSize, tileSize, this);
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
