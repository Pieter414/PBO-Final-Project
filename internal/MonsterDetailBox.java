package PBOFINALPROJECTHURA.internal;

import javax.swing.*;
import java.awt.*;

public class MonsterDetailBox extends JPanel {
    private JLabel nameLabel;
    private JLabel levelLabel;
    private JProgressBar hpBar;
    private JLabel hpLabel;
    private int maxHp;

    public MonsterDetailBox(String name, int level, int currentHP, int maxHP, Color color) {
        setLayout(null);
        setPreferredSize(new Dimension(200, 60));
        setBackground(color);
        this.maxHp = maxHP;

        nameLabel = new JLabel(name);
        nameLabel.setBounds(10, 5, 100, 20);
        add(nameLabel);

        levelLabel = new JLabel("Lv." + level);
        levelLabel.setBounds(150, 5, 50, 20);
        add(levelLabel);

        hpBar = new JProgressBar(0, maxHP);
        hpBar.setValue(currentHP);
        hpBar.setBounds(10, 25, 180, 10);
        hpBar.setForeground(Color.GREEN);
        add(hpBar);

        hpLabel = new JLabel(currentHP + "/" + maxHP);
        hpLabel.setBounds(10, 35, 100, 20);
        add(hpLabel);
    }


    public void changeHpValue(int damage) {
        int newHp = Math.max(0, hpBar.getValue() - damage); // Pastikan tidak kurang dari 0
        hpBar.setValue(newHp);
        hpLabel.setText(newHp + "/" + this.maxHp);
    }

    public void healHp(int heal) {
        int newHp = Math.min(this.maxHp, hpBar.getValue() + heal); // Pastikan tidak melebihi maxHP
        hpBar.setValue(newHp);
        hpLabel.setText(newHp + "/" + this.maxHp);
    }
}
