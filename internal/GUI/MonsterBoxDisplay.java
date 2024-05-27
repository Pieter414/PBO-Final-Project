package PBOFINALPROJECTHURA.internal.GUI;

import PBOFINALPROJECTHURA.internal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonsterBoxDisplay extends BaseLayer implements ActionListener {
    private JButton addFireMonsterButton, addWaterMonsterButton, addGroundMonsterButton, addGrassMonsterButton, addIceMonsterButton;
    private ActionListener addFireAction, addWaterAction, addGroundAction, addGrassAction, addIceAction;
    private JPanel panel;
    private Player player;

    public MonsterBoxDisplay(Player player) {
        super(new JPanel(null), player);
        panel = (JPanel) getContentPane();
        setTitle("Monster Box");
        setSize(720, 350);
        this.player = player;

        panel.setLayout(null);
        setUpBackground("", "box");
        initializeActionListeners();
        setUpButton();
        updateMonster();

        revalidate();
        repaint();
    }

    private void initializeActionListeners() {
        addFireAction = e -> actionPerformed("Fire");
        addWaterAction = e -> actionPerformed("Water");
        addGrassAction = e -> actionPerformed("Grass");
        addGroundAction = e -> actionPerformed("Ground");
        addIceAction = e -> actionPerformed("Ice");
    }

    private void setUpButton(){
        addFireMonsterButton = createButton("Add Fire", 20, 20, 100, 25, new FireMonster("", 1, 1).getColor(), addFireAction);
        addWaterMonsterButton = createButton("Add Water", 20, 60, 100, 25, new WaterMonster("", 1, 1).getColor(), addWaterAction);
        addGrassMonsterButton = createButton("Add Grass", 20, 100, 100, 25, new GrassMonster("", 1, 1).getColor(), addGrassAction);
        addGroundMonsterButton = createButton("Add Ground", 20, 140, 100, 25, new GroundMonster("", 1, 1).getColor(), addGroundAction);
        addIceMonsterButton = createButton("Add Ice", 20, 180, 100, 25, new IcedMonster("", 1, 1).getColor(), addIceAction);
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color color, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(color);

        if (action != null) {
            button.addActionListener(action);
        }
        getContentPane().add(button);
        return button;
    }

    public void actionPerformed(String s) {
        if (s.equalsIgnoreCase("Fire")) {
            addMonster(new FireMonster("Fire Monster", 50, 1000));
        } else if (s.equalsIgnoreCase("Water")) {
            addMonster(new WaterMonster("Water Monster", 50, 1000));
        } else if (s.equalsIgnoreCase("Ground")) {
            addMonster(new GroundMonster("Ground Monster", 50, 1000));
        } else if (s.equalsIgnoreCase("Grass")) {
            addMonster(new GrassMonster("Grass Monster", 50, 1000));
        }
        else if (s.equalsIgnoreCase("Ice")) {
            addMonster(new IcedMonster("Ice Monster", 50, 1000));
        }
    }

    protected void addMonster(Monster monster){
        if (player.getMonster1() == null){
            player.setMonster1(monster);
        }
        else if (player.getMonster2() == null){
            player.setMonster2(monster);
        }
        else if (player.getMonster3() == null){
            player.setMonster3(monster);
        }
        updateMonster();
    }

    public void updateMonster() {
        getContentPane().removeAll(); // Remove all existing components
        setUpButton(); // Re-add the initial buttons

        int xPosition = 200;
        for (int i = 0; i < 3; i++) {
            Monster monster = null;
            if (i == 0) monster = player.getMonster1();
            else if (i == 1) monster = player.getMonster2();
            else if (i == 2) monster = player.getMonster3();

            if (monster != null) {
                JLabel monsterLabel = new JLabel("<html>" + monster.getName() + "<br>Level: " + monster.getLevel() + "<br>HP: " + monster.getHP() + "<br>EP: " + monster.getEP() + "</html>");
                monsterLabel.setBounds(xPosition, 5, 150, 100);
                getContentPane().add(monsterLabel);

                JButton levelUpButton = new JButton("Level Up");
                levelUpButton.setBounds(xPosition, 110, 100, 30);
                Monster finalMonster = monster;
                levelUpButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (finalMonster.getEP() >= 100){
                            finalMonster.setLevel(finalMonster.getLevel()+1);
                            finalMonster.setMaxHP(finalMonster.getLevel() * 20);
                            finalMonster.setHP(finalMonster.getLevel() * 20);
                            finalMonster.setCanEvolve(true);
                            finalMonster.setEPMinus(100);
                            updateMonster();
                        }
                        else{
                            JOptionPane.showMessageDialog(getContentPane(), "Can't Level Up, You Don't Have EP Enough");
                        }
                    }
                });
                getContentPane().add(levelUpButton);

                JButton healButton = new JButton("Heal");
                healButton.setBounds(xPosition, 150, 100, 30);
                Monster finalMonster1 = monster;
                healButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        finalMonster1.setHP(finalMonster1.getMaxHP());
                        updateMonster();
                    }
                });
                getContentPane().add(healButton);

                JButton evolveButton = new JButton("Evolve");
                evolveButton.setBounds(xPosition, 190, 100, 30);
                Monster finalMonster2 = monster;
                evolveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (finalMonster2 == player.getMonster1()) {
                            if (player.getMonster1().isCanEvolve()) {
                                player.setMonster1(evolve(player.getMonster1()));
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "Can't Evolve, Level Up First");
                            }
                        }
                        else if (finalMonster2 == player.getMonster2()){
                            if (player.getMonster2().isCanEvolve()) {
                                player.setMonster2(evolve(player.getMonster2()));
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "Can't Evolve, Level Up First");
                            }
                        }
                        else if (finalMonster2 == player.getMonster3()){
                            if (player.getMonster3().isCanEvolve()) {
                                player.setMonster3(evolve(player.getMonster3()));
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "Can't Evolve, Level Up First");
                            }
                        }
                        updateMonster();
                    }
                });
                getContentPane().add(evolveButton);

                JButton removeButton = new JButton("Remove");
                removeButton.setBounds(xPosition, 230, 100, 30);
                Monster finalMonster3 = monster;
                removeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (finalMonster3 == player.getMonster1()) {
                            if (player.getMonster1() != null) {
                                player.setMonster1(null);
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "No Monster Chosen");
                            }
                        }
                        else if (finalMonster3 == player.getMonster2()) {
                            if (player.getMonster2() != null) {
                                player.setMonster2(null);
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "No Monster Chosen");
                            }
                        }
                        else if (finalMonster3 == player.getMonster3()) {
                            if (player.getMonster3() != null) {
                                player.setMonster3(null);
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "No Monster Chosen");
                            }
                        }
                        updateMonster();
                    }
                });
                getContentPane().add(removeButton);

                xPosition += 170; // Adjust this value to fit the layout
            }
        }
        revalidate(); // Revalidate the panel to ensure the changes are displayed
        repaint(); // Repaint the panel to refresh the UI
    }

    public Monster evolve(Monster monster){
        Monster newMonsterEvolve = null;
        if (monster instanceof FireMonster){
            newMonsterEvolve = new GrassMonster("Grass Monster", monster.getLevel(), monster.getMaxHP());
            newMonsterEvolve.setHP(monster.getHP());
            newMonsterEvolve.setEP(monster.getEP());
            newMonsterEvolve.setCanEvolve(false);
        }
        else if (monster instanceof WaterMonster){
            newMonsterEvolve = new IcedMonster("Water Monster", monster.getLevel(), monster.getMaxHP());
            newMonsterEvolve.setHP(monster.getHP());
            newMonsterEvolve.setEP(monster.getEP());
        }
        else if (monster instanceof GroundMonster){
            newMonsterEvolve = new FireMonster("Fire Monster", monster.getLevel(), monster.getMaxHP());
            newMonsterEvolve.setHP(monster.getHP());
            newMonsterEvolve.setEP(monster.getEP());
        }
        else if (monster instanceof GrassMonster){
            newMonsterEvolve = new WaterMonster("Water Monster", monster.getLevel(), monster.getMaxHP());
            newMonsterEvolve.setHP(monster.getHP());
            newMonsterEvolve.setEP(monster.getEP());
        }
        else if (monster instanceof IcedMonster){
            newMonsterEvolve = new GroundMonster("Ground Monster", monster.getLevel(), monster.getMaxHP());
            newMonsterEvolve.setHP(monster.getHP());
            newMonsterEvolve.setEP(monster.getEP());
        }
        return newMonsterEvolve;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Player player = new Player("n");
            MonsterBoxDisplay bs = new MonsterBoxDisplay(player);
            bs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bs.setVisible(true);
        });
    }
}
