package PBOFINALPROJECTHURA.internal.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class BaseLayer extends JFrame implements ActionListener {
    private BackgroundPanel backgroundPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveItem;
    private JMenuItem exitItem;
    private ImageIcon saveIcon;
    private ImageIcon exitIcon;
    private boolean canExit = false;
    private JPanel interfaceUtamaPanel;

    public BaseLayer(JPanel interfaceUtamaPanel) {
        setUpMenuBar();
        setContentPane(interfaceUtamaPanel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (canExit) {
                    dispose();
                } else {
                    showSaveErrorMessage();
                }
            }
        });

        setUpBackground("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\backgroundBattle.jpg", "battleArena");

        setTitle("Area");
        setSize(787, 480);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void setUpMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        saveIcon = setUpIcon(new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\saveIcon.png"), 20, 20);
        exitIcon = setUpIcon(new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\exitIcon.png"), 20, 20);

        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);

        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    private ImageIcon setUpIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void setUpBackground(String filepath, String detail) {
        if (backgroundPanel != null) {
            remove(backgroundPanel); // Remove old background panel if exists
        }
        ImageIcon backgroundIcon = new ImageIcon(filepath);
        Image image = backgroundIcon.getImage();

        // Create the custom panel with the background image
        backgroundPanel = new BackgroundPanel(image, detail);
        backgroundPanel.setLayout(null);

        // Add the background panel to the specified panel
        setContentPane(backgroundPanel);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveItem) {
            canExit = true;
        } else if (e.getSource() == exitItem) {
            if (!canExit) {
                showSaveErrorMessage();
            } else {
                dispose();
            }
        }
    }

    public void showSaveErrorMessage() {
        JOptionPane.showMessageDialog(this, "Program cannot be exited right now");
    }
}
