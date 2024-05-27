package PBOFINALPROJECTHURA.internal;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.Random;

public class BattleArenaDisplay extends BaseLayer implements ActionListener {
    private ActionListener attackAction40, attackAction80, eAttackAction80, eAttackAction120, fightButtonAction, itemButtonAction, runButtonAction, monsterButtonAction, monster1Chosen, monster2Chosen, monster3Chosen, item1Action, item2Action, item3Action, item4Action;
    private MonsterDetailBox ourMonsterDetail, enemyMonsterDetail;
    private Monster playerMonster, enemyMonster;
    private final Player player;
    private int currentMonster;
    private JPopupMenu popupMenuMonster, popupMenuItem;
    private JMenuItem potionMenuItem, superPotionMenuItem, xAttackMenuItem, xDefenseMenuItem;

    private JButton tombol1, tombol2, tombol3, tombol4;
    private JPanel panel1;
    private boolean battleTrainer;

    public BattleArenaDisplay(Player player, Monster enemyMonster, boolean battleTrainer) {
        super(new JPanel(null)); // Mengatur layout ke null untuk mengatur posisi secara manual
        panel1 = (JPanel) getContentPane();
        setTitle("Battle Arena");

        panel1.setLayout(null);
        initializeActionListeners();
        setBattleTrainer(battleTrainer);
        this.player = player;

        setUpMonster(player.getMonster1(), enemyMonster);
        setUpButton();
        setUpMonsterPopUpMenu();
        setUpItemPopUpMenu();

        panel1.revalidate();
        panel1.repaint();
    }

    public void setBattleTrainer(boolean battleTrainer) {
        this.battleTrainer = battleTrainer;
    }

    public boolean getBattleTrainer() {
        return battleTrainer;
    }

    private void setUpButton() {
        tombol1 = createButton("Fight", 60, 380, 120, 25, new Color(255, 155, 80), fightButtonAction);
        tombol2 = createButton("Monster", 240, 380, 120, 25, new Color(65, 201, 226), monsterButtonAction);
        tombol3 = createButton("Item", 420, 380, 120, 25, new Color(196, 153, 243), itemButtonAction);
        tombol4 = createButton("Run", 600, 380, 120, 25, new Color(161, 238, 189), runButtonAction);
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color background, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(background);
        if (action != null) {
            button.addActionListener(action);
        }
        panel1.add(button);
        return button;
    }

    private void clearButtonActionListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    private void setUpOriginalButton(){
        clearButtonActionListeners(tombol1);
        clearButtonActionListeners(tombol2);
        clearButtonActionListeners(tombol3);
        clearButtonActionListeners(tombol4);

        tombol1.setText("Fight");
        tombol1.setBackground(new Color(255,	155,	80));

        tombol2.setText("Monster");
        tombol2.setBackground(new Color(65,	201,	226));

        tombol3.setText("Item");
        tombol3.setBackground(new Color(196,	153,	243));

        tombol4.setText("Run");
        tombol4.setBackground(new Color(161,	238,	189));

        tombol1.addActionListener(fightButtonAction);
        tombol2.addActionListener(monsterButtonAction);
        tombol3.addActionListener(itemButtonAction);
        tombol4.addActionListener(runButtonAction);
    }

    private void fightButtonClicked(Color color) {
        clearButtonActionListeners(tombol1);
        clearButtonActionListeners(tombol2);
        clearButtonActionListeners(tombol3);
        clearButtonActionListeners(tombol4);

        // Change the background color of all buttons to the color of the button clicked
        tombol1.setBackground(color);
        tombol1.setText("Basic Attack");
        tombol1.addActionListener(attackAction40);

        tombol2.setBackground(color);
        tombol2.setText("Special Attack");
        tombol2.addActionListener(attackAction80);

        tombol3.setBackground(color);
        tombol3.setText("E Attack");
        tombol3.addActionListener(eAttackAction80);

        tombol4.setBackground(color);
        tombol4.setText("Special E Attack");
        tombol4.addActionListener(eAttackAction120);
    }

    private void itemButtonClicked(){
        enemyMonster.setCurrentHPPlus(100);
        System.out.printf("%s used Potion, it was restored by %d point(s).\n", enemyMonster.getName(), 100);
        System.out.printf("%s has %d's remaining HP.\n", enemyMonster.getName(), enemyMonster.getHP());
        enemyMonsterDetail.healHp(100);
    }

    private void attackPerformed(int damage, String attack){
        enemyMonster.setCurrentHPMinus(damage);
        enemyMonsterDetail.changeHpValue(damage);
        System.out.println("OUR:");
        printAttackDetail(playerMonster, enemyMonster, attack, damage);
        if (enemyKilled()){
            // muncul poin anda menang dan dapat duit, lalu forced exit
            int money, exp = 100;
            if (getBattleTrainer()){
                money = 2000;
            }
            else{
                money = 500;
            }
            player.setMoneyPlus(money);
            playerMonster.setEPPlus(exp);
            showWinMessage(money, exp);
        }
        else{
            // enemy nyerang dan ngecek apakah nyawa cukup
            System.out.println("ENEMY:");
            enemyAttack();
            // switched pokemon dan mengecek apakah masih ada monster yang bisa digunakan
            if (ourKilled()){
                // jika semua pokemon mati semua
                if (allOurKilled()){
                    int money;
                    if (getBattleTrainer()){
                        money = 2000;
                    }
                    else{
                        money = 500;
                    }
                    player.setMoneyMinus(money);
                    showLoseMessage(money);
                }
                // jika masih ada pokemon blom mati
                else{
                    System.out.printf("%s defeated\n", playerMonster.getName());
                    swapEmergency();
                }
            }
        }
        setUpOriginalButton();
    }

    private void enemyAttack(){
        Random random = new Random();
        int attackOption = random.nextInt(1, 4);
        switch (attackOption){
            case 1:
                enemyAttacked(enemyMonster.basicAttack(enemyMonster, playerMonster), "Basic Attack");
                break;
            case 2:
                enemyAttacked(enemyMonster.specialAttack(enemyMonster, playerMonster), "Special Attack");
                break;
            case 3:
                enemyAttacked(enemyMonster.elementAttack1(enemyMonster, playerMonster), "Elemental Attack");
                break;
            case 4:
                enemyAttacked(enemyMonster.elementAttack2(enemyMonster, playerMonster), "Special Elemental Attack");
                break;
        }
    }

    private void enemyAttacked(int damage, String attack){
        playerMonster.setCurrentHPMinus(damage);
        ourMonsterDetail.changeHpValue(damage);
        printGetAttackDetail(enemyMonster, playerMonster, attack, damage);
    }

    private boolean enemyKilled(){
        return (enemyMonster.getHP() == 0);
    }

    private boolean ourKilled(){
        return (playerMonster.getHP() == 0);
    }

    private boolean allOurKilled(){
        return (player.getMonster1().getHP() == 0 && player.getMonster2().getHP() == 0 && player.getMonster3().getHP() == 0);
    }

    private void printAttackDetail(Monster playerMonster, Monster enemyMonster, String attack, int damage){
        System.out.printf("%s used %s!\n", playerMonster.getName(), attack);
        int difference = (int) (Math.floor( (double) damage / enemyMonster.getMaxHP() * 100));
        System.out.printf("(The opposing %s lost %d (%d%%) of its health!)\n", enemyMonster.getName(), damage, difference);
        System.out.printf("%s only have %d\n\n", enemyMonster.getName(), enemyMonster.getHP());
    }

    private void printGetAttackDetail(Monster playerMonster, Monster enemyMonster, String attack, int damage){
        System.out.printf("%s used %s!\n", playerMonster.getName(), attack);
        int difference = (int) (Math.floor( (double) damage / enemyMonster.getMaxHP() * 100));
        System.out.printf("(%s lost %d (%d%%) of its health!)\n", enemyMonster.getName(), damage, difference);
        System.out.printf("%s only have %d\n\n", enemyMonster.getName(), enemyMonster.getHP());
    }

    private void swapEmergency(){
        if (currentMonster == 1 && (player.getMonster2().getHP() != 0)){
            switchToMonster(2);
        }
        else if (currentMonster == 1 && (player.getMonster3().getHP() != 0)){
            switchToMonster(3);
        }
        else if (currentMonster == 2 && (player.getMonster3().getHP() != 0)){
            switchToMonster(3);
        }
        else if (currentMonster == 2 && (player.getMonster1().getHP() != 0)){
            switchToMonster(1);
        }
        else if (currentMonster == 3 && (player.getMonster1().getHP() != 0)){
            switchToMonster(1);
        }
        else if (currentMonster == 3 && (player.getMonster2().getHP() != 0)){
            switchToMonster(2);
        }
    }

    private void setUpMonsterPopUpMenu(){
        popupMenuMonster = new JPopupMenu();

        JMenuItem item1 = new JMenuItem(player.getMonster1().getName());
        JMenuItem item2 = new JMenuItem(player.getMonster2().getName());
        JMenuItem item3 = new JMenuItem(player.getMonster3().getName());

        item1.addActionListener(monster1Chosen);
        item2.addActionListener(monster2Chosen);
        item3.addActionListener(monster3Chosen);

        popupMenuMonster.add(item1);
        popupMenuMonster.add(item2);
        popupMenuMonster.add(item3);
    }

    private void setUpItemPopUpMenu(){
        popupMenuItem = new JPopupMenu();

        if (!player.getListPotion().isEmpty()) {
            potionMenuItem = new JMenuItem(String.format("%s (%d)", player.getListPotion().get(0).getSifat().toUpperCase(), player.getListPotion().size()));
            potionMenuItem.addActionListener(item1Action);
            popupMenuItem.add(potionMenuItem);
        }
        if (!player.getListSuperPotion().isEmpty()){
            superPotionMenuItem = new JMenuItem(String.format("%s (%d)", player.getListSuperPotion().get(0).getSifat().toUpperCase(), player.getListSuperPotion().size()));
            superPotionMenuItem.addActionListener(item2Action);
            popupMenuItem.add(superPotionMenuItem);
        }
        if (!player.getListXAttack().isEmpty()) {
            xAttackMenuItem = new JMenuItem(String.format("%s (%d)", player.getListXAttack().get(0).getSifat().toUpperCase(), player.getListXAttack().size()));
            xAttackMenuItem.addActionListener(item3Action);
            popupMenuItem.add(xAttackMenuItem);
        }
        if (!player.getListXDefense().isEmpty()){
            xDefenseMenuItem = new JMenuItem(String.format("%s (%d)", player.getListXDefense().get(0).getSifat().toUpperCase(), player.getListXDefense().size()));
            xDefenseMenuItem.addActionListener(item4Action);
            popupMenuItem.add(xDefenseMenuItem);
        }
    }

    private void usedItem(String sifat){
        switch(sifat){
            case "potion":
                Item potion = player.getListPotion().remove(0);
                playerMonster.setCurrentHPPlus(potion.use());
                // update cli
                System.out.printf("%s used Potion, it was restored by %d point(s).\n", playerMonster.getName(), potion.use());
                System.out.printf("%s has %d's remaining HP.\n\n", playerMonster.getName(), playerMonster.getHP());
                ourMonsterDetail.healHp(potion.use());
                // update jumlah item
                if (!player.getListPotion().isEmpty())
                    potionMenuItem.setText(String.format("%s (%d)", player.getListPotion().get(0).getSifat().toUpperCase(), player.getListPotion().size()));
                else
                    popupMenuItem.remove(0);
                break;
            case "super potion":
                Item superPotion = player.getListSuperPotion().remove(0);
                playerMonster.setCurrentHPPlus(superPotion.use());
                // update cli
                System.out.printf("%s used Super Potion, it was restored by %d point(s).\n", playerMonster.getName(), superPotion.use());
                System.out.printf("%s has %d's remaining HP.\n\n", playerMonster.getName(), playerMonster.getHP());
                ourMonsterDetail.healHp(superPotion.use());
                // update jumlah item
                if (!player.getListSuperPotion().isEmpty())
                    superPotionMenuItem.setText(String.format("%s (%d)", player.getListSuperPotion().get(0).getSifat().toUpperCase(), player.getListSuperPotion().size()));
                else
                    popupMenuItem.remove(1);
                break;
            case "x attack":
                Item xAttack = player.getListXAttack().remove(0);
                playerMonster.setBonusAttack(xAttack.use());
                // update cli
                System.out.printf("%s used X Attack, Your Monster Can Attack Stronger.\n\n", playerMonster.getName());
                // update jumlah item
                if (!player.getListXAttack().isEmpty())
                    xAttackMenuItem.setText(String.format("%s (%d)", player.getListXAttack().get(0).getSifat().toUpperCase(), player.getListXAttack().size()));
                else
                    popupMenuItem.remove(3);
                break;
            case "x defense":
                Item xDefense = player.getListXDefense().remove(0);
                enemyMonster.setBonusDefense(xDefense.use());
                // update cli
                System.out.printf("%s used X Defense, Your Monster Can Take Attack Better.\n\n", playerMonster.getName());
                // update jumlah item
                if (!player.getListXDefense().isEmpty())
                    xDefenseMenuItem.setText(String.format("%s (%d)", player.getListXDefense().get(0).getSifat().toUpperCase(), player.getListXDefense().size()));
                else
                    popupMenuItem.remove(4);
                break;
        }
    }

    private void switchToMonster(int index) {
        Monster chosenMonster = switch (index) {
            case 1 -> player.getMonster1();
            case 2 -> player.getMonster2();
            case 3 -> player.getMonster3();
            default -> null;
        };
        if (chosenMonster != null && chosenMonster.getHP() > 0 && !playerMonster.equals(chosenMonster)) {
            System.out.printf("%s, switch out! Go %s!\n\n", playerMonster.getName(), chosenMonster.getName());
            this.currentMonster = index;
            this.playerMonster = chosenMonster;
            try {
                updateOurMonster(chosenMonster);
            } catch (IOException e) {
                System.out.println("Error updating monster: " + e.getMessage());
            }
        }
    }

    private void updateOurMonster(Monster playerMonster) throws IOException {
        // Remove existing components
        panel1.remove(ourMonsterDetail);
        for (Component comp : panel1.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getIcon() instanceof ImageIcon) {
                panel1.remove(comp); // This assumes you only have monster GIFs as ImageIcons in labels.
            }
        }

        // Create and add new MonsterDetailBox
        ourMonsterDetail = new MonsterDetailBox(playerMonster.getName(), playerMonster.getLevel(), playerMonster.getHP(), playerMonster.getMaxHP(), playerMonster.getColor());
        ourMonsterDetail.setBounds(550, 270, 200, 60);
        panel1.add(ourMonsterDetail);

        // Create and add new JLabel with GIF
        File ourMonsterFile = new File(playerMonster.getPathFileBack());
        ImageIcon ourMonsterGif = new ImageIcon(ourMonsterFile.toURI().toURL());
        JLabel ourMonsterLabel = new JLabel(ourMonsterGif);
        ourMonsterLabel.setBounds(180, 210, ourMonsterGif.getIconWidth(), ourMonsterGif.getIconHeight());
        panel1.add(ourMonsterLabel);
        ((ImageIcon) ourMonsterLabel.getIcon()).setImageObserver(ourMonsterLabel);

        File enemyMonsterFile = new File(enemyMonster.getPathFileFront());
        ImageIcon enemyMonsterGif = new ImageIcon(enemyMonsterFile.toURI().toURL());
        JLabel enemyMonsterLabel = new JLabel(enemyMonsterGif);
        enemyMonsterLabel.setBounds(480, 125, enemyMonsterGif.getIconWidth(), enemyMonsterGif.getIconHeight());
        panel1.add(enemyMonsterLabel);
        ((ImageIcon) enemyMonsterLabel.getIcon()).setImageObserver(enemyMonsterLabel);

        // Refresh the panel
        panel1.revalidate();
        panel1.repaint();
    }

    private void runButtonClicked(){
        if (getBattleTrainer()){
            showSaveErrorMessage();
        }
        else{
            dispose();
        }
    }

    private void initializeActionListeners() {
        attackAction40 = e -> attackPerformed(playerMonster.basicAttack(playerMonster, enemyMonster), "Basic Attack");
        attackAction80 = e -> attackPerformed(playerMonster.specialAttack(playerMonster, enemyMonster), "Special Attack");
        eAttackAction80 = e -> attackPerformed(playerMonster.elementAttack1(playerMonster, enemyMonster), "Elemental Attack");
        eAttackAction120 = e -> attackPerformed(playerMonster.elementAttack2(playerMonster, enemyMonster), "Special Elemental Attack");
        fightButtonAction = e -> fightButtonClicked(tombol1.getBackground());
        monsterButtonAction = e -> popupMenuMonster.show(tombol2, tombol2.getWidth()/2, tombol2.getHeight()/2);
        itemButtonAction = e -> popupMenuItem.show(tombol3, tombol3.getWidth()/2, tombol3.getHeight()/2);
        runButtonAction = e -> runButtonClicked();
        monster1Chosen = e -> switchToMonster(1);
        monster2Chosen = e -> switchToMonster(2);
        monster3Chosen = e -> switchToMonster(3);
        item1Action = e -> usedItem("potion");
        item2Action = e -> usedItem("super potion");
        item3Action = e -> usedItem("x attack");
        item4Action = e -> usedItem("x defense");
    }

    private void setUpMonster(Monster playerMonster, Monster enemyMonster){
        this.playerMonster = playerMonster;
        this.enemyMonster = enemyMonster;
        this.currentMonster = 1;
        setUpMonsterGif();
        setUpMonsterDetailBox();
    }

    private void setUpMonsterDetailBox() {
        ourMonsterDetail = new MonsterDetailBox(playerMonster.getName(), playerMonster.getLevel(), playerMonster.getHP(), playerMonster.getMaxHP(), playerMonster.getColor());
        ourMonsterDetail.setBounds(550, 270, 200, 60);
        panel1.add(ourMonsterDetail);

        enemyMonsterDetail = new MonsterDetailBox(enemyMonster.getName(), enemyMonster.getLevel(), enemyMonster.getHP(), enemyMonster.getMaxHP(), enemyMonster.getColor());
        enemyMonsterDetail.setBounds(30, 30, 200, 60);
        panel1.add(enemyMonsterDetail);

        panel1.revalidate();
        panel1.repaint();
    }

    private void setUpMonsterGif() {
        try {
            // Read the PNG file for Our Monster
            File ourMonsterFile = new File(playerMonster.getPathFileBack());
            ImageIcon ourMonsterGif = new ImageIcon(ourMonsterFile.toURI().toURL());

            // Create a JLabel to hold the animated GIF
            JLabel ourMonsterLabel = new JLabel(ourMonsterGif);
            ourMonsterLabel.setBounds(180, 210, ourMonsterGif.getIconWidth(), ourMonsterGif.getIconHeight());
            panel1.add(ourMonsterLabel);

            // Start the GIF animation
            ((ImageIcon) ourMonsterLabel.getIcon()).setImageObserver(ourMonsterLabel);
            ourMonsterLabel.repaint();

            // Read the GIF file for Enemy Monster
            File enemyMonsterFile = new File(enemyMonster.getPathFileFront());
            ImageIcon enemyMonsterGif = new ImageIcon(enemyMonsterFile.toURI().toURL());

            // Create a JLabel to hold the animated GIF
            JLabel enemyMonsterLabel = new JLabel(enemyMonsterGif);
            enemyMonsterLabel.setBounds(480, 125, enemyMonsterGif.getIconWidth(), enemyMonsterGif.getIconHeight());
            panel1.add(enemyMonsterLabel);

            // Start the GIF animation
            ((ImageIcon) enemyMonsterLabel.getIcon()).setImageObserver(enemyMonsterLabel);
            enemyMonsterLabel.repaint();

        } catch (IOException e) {
            System.out.println("Error reading the image: " + e.getMessage());
        }
    }

    public void showWinMessage(int money, int exp) {
        String sb = "You won the battle!\n" + String.format("%s gained %d Exp. Points!\n", playerMonster.getName(), exp) +
                String.format("You got $%d for winning!\n", money);

        int response = JOptionPane.showConfirmDialog(this, sb, "Battle Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (response == JOptionPane.OK_OPTION) {
            dispose();
        }
    }

    public void showLoseMessage(int money) {
        String sb = "You lose the battle!\n" + String.format("You lose $%d for losing!\n", money);

        int response = JOptionPane.showConfirmDialog(this, sb, "Battle Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (response == JOptionPane.OK_OPTION) {
            dispose();
        }
    }
}
