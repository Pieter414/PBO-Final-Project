package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.Player;

import javax.swing.*;

public class DungeonDisplay extends BaseLayer{
    private JPanel panel;
    private Player player;
    int x, y;

    public DungeonDisplay(Player player) {
        super(new JPanel(null), player); // Mengatur layout ke null untuk mengatur posisi secara manual
        panel = (JPanel) getContentPane();

        panel.setLayout(null);
        setSize(500, 1000);
        setUpBackground("src/PBOFINALPROJECTHURA/assets/images/grid 10x10.png", "dungeon");
        initializeActionListeners();

        panel.revalidate();
        panel.repaint();
    }

    private void initializeActionListeners() {

    }
}
