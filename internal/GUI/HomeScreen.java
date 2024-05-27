package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeScreen extends BaseLayer implements ActionListener {
    private ActionListener startButtonAction, editButtonAction, exitButtonAction;
    private JLabel logoLabel;
    private JButton startButton, editButton, exitButton;
    private JPanel panel;
    private Player player;

    public HomeScreen() {
        super(new JPanel(null));
        panel = (JPanel) getContentPane();
        setTitle("Home Screen");
        setSize(960, 590);

        panel.setLayout(null);
        setUpBackground("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\homeScreenBackground.jpg", "homeScreen");
        initializeActionListeners();
        setUpPokemonTitle();
        setUpButton();
        this.player = new Player("PLACEHOLDERNAMEFORAWHILE123456789");

        revalidate();
        repaint();
    }

    public HomeScreen(Player player) {
        super(new JPanel(null));
        panel = (JPanel) getContentPane();
        setTitle("Home Screen");
        setSize(960, 590);

        panel.setLayout(null);
        setUpBackground("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\homeScreenBackground.jpg", "homeScreen");
        initializeActionListeners();
        setUpPokemonTitle();
        setUpButton();
        this.player = player;

        revalidate();
        repaint();
    }

    private void setUpPokemonTitle(){
        ImageIcon logoIcon = new ImageIcon("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\images\\monsterLogo.png");
        logoLabel = new JLabel(logoIcon);
        logoLabel.setLayout(null); // Use null layout to manually position buttons
        logoLabel.setBounds(50, 40, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        getContentPane().add(logoLabel);
    }

    private void setUpButton(){
        startButton = createButton("Start", 70, 300, 300, 30, Color.LIGHT_GRAY, startButtonAction);
        editButton = createButton("Edit", 70, 350, 300, 30, Color.LIGHT_GRAY, editButtonAction);
        exitButton = createButton("Exit", 70, 400, 300, 30, Color.LIGHT_GRAY, exitButtonAction);
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color background, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(background);
        if (action != null) {
            button.addActionListener(action);
        }
        getContentPane().add(button);
        return button;
    }

    private void startButtonClicked(){
        // Start HomeBase dan Loading game
        if (player.getName().equals("PLACEHOLDERNAMEFORAWHILE123456789")){
            showPlayerInvalidMessage();
        }
        else{
            System.out.println("game starting");
            dispose();
            Homebase hb = new Homebase(player);
        }
    }

    private void editButtonClicked(){
        // Edit Player dan pastikan sebelum start sudah ada player
        String input = JOptionPane.showInputDialog(panel, "Enter your name:");
        if (input != null) {  // Jika pengguna tidak membatalkan dialog
            player.setName(input);
        } else {
            System.out.println("User canceled the dialog.");
        }
    }

    private void exitButtonClicked(){
        dispose();
    }

    private void initializeActionListeners(){
        startButtonAction = e -> startButtonClicked();
        editButtonAction = e -> editButtonClicked();
        exitButtonAction = e -> exitButtonClicked();
    }

    private void showPlayerInvalidMessage() {
        JOptionPane.showMessageDialog(this, "You Need to Set Your Player Profile!");
    }
}
