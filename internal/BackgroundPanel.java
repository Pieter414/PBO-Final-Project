package PBOFINALPROJECTHURA.internal;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private String detail;

    // Constructor to set the background image
    public BackgroundPanel(Image image, String detail) {
        this.backgroundImage = image;
        this.detail = detail;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (detail.equalsIgnoreCase("battleArena"))
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight() - 50, this);
        else
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}