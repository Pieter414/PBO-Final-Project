package PBOFINALPROJECTHURA.internal;

import PBOFINALPROJECTHURA.internal.GUI.DungeonPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DungeonTest {

    private int[][] monsterFix = {{4, 1}, {4, 4}, {1, 8}, {9, 8}};
    private boolean[] defeated;
    private int[][] randomIndex = {{2, 2}, {3, 2}, {3, 3}, {3, 3}, {2, 3}, {1, 3}};
    private char[][] map;
    private int playerX;
    private int playerY;
    private Player player;
    private DungeonPanel dungeonPanel; // Panel to hold the dungeon map

    public DungeonTest(Player player) {
        initializeMap();  // Initialize the map first
        playerX = 1;       // Set initial player coordinates
        playerY = 9;
        this.player = player;
        dungeonPanel = new DungeonPanel(this, map, playerX, playerY);  // This is now a JFrame
        dungeonPanel.setVisible(true); // Make the frame visible
        this.defeated = new boolean[monsterFix.length];

        // You can still use controlPanel but consider how it's used since DungeonPanel is a separate window
        // Perhaps manage this panel within DungeonPanel or in another coordinating JFrame
    }

    public DungeonPanel getDungeonPanel() {
        return dungeonPanel;  // Return the fully initialized panel
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");

        upButton.addActionListener(e -> movePlayer(0, -1));
        downButton.addActionListener(e -> movePlayer(0, 1));
        leftButton.addActionListener(e -> movePlayer(-1, 0));
        rightButton.addActionListener(e -> movePlayer(1, 0));

        panel.add(upButton);
        panel.add(downButton);
        panel.add(leftButton);
        panel.add(rightButton);

        return panel;
    }

    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX >= 0 && newX < map[0].length && newY >= 0 && newY < map.length && map[newY][newX] == ' ') {
            int indexDefeated = findIndex(newX, newY);
            if (indexDefeated != -1 && isThereMonster(newX, newX) && !defeated[indexDefeated]) {
                defeated[findIndex(newX, newY)] = true;
                Monster encounteredMonster = generateRandomMonster();
                initiateBattle(encounteredMonster);
            } else {
                map[playerY][playerX] = ' '; // Clear the old player position
                playerX = newX; // Update player's X position
                playerY = newY; // Update player's Y position
                map[playerY][playerX] = 'P'; // Set the new player position
                dungeonPanel.repaint(); // Request the panel to repaint
            }
        }
        else if (map[newY][newX] == 'X'){
            dungeonPanel.dispose();
            HomeBase hb = new HomeBase(player);
        }
    }

    private int findIndex(int x, int y){
        for (int i = 0; i < monsterFix.length; i++){
            if (monsterFix[i][0] == x && monsterFix[i][1] == y){
                return i;
            }
        }
        return -1;
    }


    public void initiateBattle(Monster encounteredMonster) {
        // Assume you have some Player class available
        // Fetch the player details, method needs to be defined or implemented
        SwingUtilities.invokeLater(() -> {
            getDungeonPanel().disableMovement();
            BattleArena battleArena = new BattleArena(player, encounteredMonster, null);
            battleArena.onFinish(() -> {
                dungeonPanel.enableMovement();
            });
        });
    }

    public boolean isThereMonster(int i, int j) {
        java.util.List<int[]> list = new ArrayList<>(Arrays.asList(randomIndex));
        Collections.shuffle(list);

        Set<String> uniqueElementsSet = new HashSet<>();
        java.util.List<int[]> resultList = new ArrayList<>();

        for (int[] element : list) {
            String elementString = Arrays.toString(element);
            if (!uniqueElementsSet.contains(elementString)) {
                uniqueElementsSet.add(elementString);
                resultList.add(element);
                if (resultList.size() == 3) {
                    break;
                }
            }
        }
        List<int[]> listFix = new ArrayList<>(Arrays.asList(monsterFix));
        resultList.addAll(listFix);

        int[][] result = resultList.toArray(new int[7][2]);

        for (int k = 0; k < result.length; k++) {
            if (result[k][0] == i && result[k][1] == j) {
                return true;
            }
        }
        return false;
    }

    public Monster generateRandomMonster() {
        final String[] MONSTER_NAMES = {"Fire Monster", "Water Monster", "Grass Monster", "Ground Monster", "Ice Monster"};
        final int MAX_LEVEL = 50;
        final int MAX_HP = 200;

        Random random = new Random();

        int level = random.nextInt(MAX_LEVEL) + 1;
        int maxHP = random.nextInt(MAX_HP) + 50; // HP antara 50 dan 250

        switch (random.nextInt(5)) {
            case 0:
                return new GroundMonster(MONSTER_NAMES[3], level, maxHP);
            case 1:
                return new WaterMonster(MONSTER_NAMES[1], level, maxHP);
            case 2:
                return new FireMonster(MONSTER_NAMES[0], level, maxHP);
            case 3:
                return new IcedMonster(MONSTER_NAMES[4], level, maxHP);
            case 4:
                return new GrassMonster(MONSTER_NAMES[2], level, maxHP);
            default:
                return null;
        }
    }

    private void initializeMap() {
        map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'X'},
                {'#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', ' '},
                {' ', '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' '},
                {' ', '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' '},
                {' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
                {' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#'},
                {' ', '#', '#', '#', ' ', '#', ' ', ' ', ' ', '#'},
                {' ', '#', '#', ' ', ' ', '#', ' ', '#', ' ', '#'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', 'P', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
    }
}