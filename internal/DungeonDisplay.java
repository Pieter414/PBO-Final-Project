import javax.swing.*;

public class DungeonDisplay extends BaseLayer{
    private JPanel panel;
    int x, y;

    public DungeonDisplay() {
        super(new JPanel(null)); // Mengatur layout ke null untuk mengatur posisi secara manual
        panel = (JPanel) getContentPane();

        panel.setLayout(null);
        setSize(500, 1000);
        setUpBackground("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\grid 10x10.png", "dungeon");
        initializeActionListeners();

        panel.revalidate();
        panel.repaint();
    }

    private void initializeActionListeners() {

    }
}
