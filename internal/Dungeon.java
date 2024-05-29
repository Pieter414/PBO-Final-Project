package PBOFINALPROJECTHURA.internal;

import PBOFINALPROJECTHURA.internal.GUI.DungeonPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Dungeon {
    public int[][] monsterFix = {{4, 1}, {4, 4}, {1, 8}, {9, 8}, {2, 8}, {3, 4}, {4, 1}, {9, 1}, {2, 9}, {0, 5}, {9, 1}, {8, 1}};
    public int[][] randomIndex = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, {1, 9}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, {2, 9}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {3, 6}, {3, 7}, {3, 8}, {3, 9}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, {4, 7}, {4, 8}, {4, 9}, {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}, {5, 6}, {5, 7}, {5, 8}, {5, 9}, {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7}, {6, 8}, {6, 9}, {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}, {8, 0}, {8, 1}, {8, 2}};
    private boolean[] defeated;
    protected char[][] map;
    private int playerX;
    private int playerY;
    private Player player;
    private DungeonPanel dungeonPanel; // Panel to hold the dungeon map

    public Dungeon(Player player) {
        initializeMap();
        playerX = 1;
        playerY = 9;
        this.player = player;
        dungeonPanel = new DungeonPanel(this, map, playerX, playerY, player);  // This is now a JFrame
        dungeonPanel.setVisible(true); // Make the frame visible
        this.defeated = new boolean[monsterFix.length];
    }

    public DungeonPanel getDungeonPanel() {
        return dungeonPanel;  // Return the fully initialized panel
    }


    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX >= 0 && newX < map[0].length && newY >= 0 && newY < map.length) {
            if (map[newY][newX] == 'X'){
                dungeonPanel.dispose();
                HomeBase hb = new HomeBase(player);
            }
            else if (map[newY][newX] == ' '){
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
        SwingUtilities.invokeLater(() -> {
            getDungeonPanel().disableMovement();
            BattleArena battleArena = new BattleArena(player, encounteredMonster, null);
            battleArena.onFinish(() -> {
                dungeonPanel.enableMovement();
            });
        });
    }

//    public boolean isThereMonster(int i, int j) {
//        List<int[]> list = new ArrayList<>(Arrays.asList(randomIndex));
//        Collections.shuffle(list);
//
//        Set<String> uniqueElementsSet = new HashSet<>();
//        java.util.List<int[]> resultList = new ArrayList<>();
//
//        for (int[] element : list) {
//            String elementString = Arrays.toString(element);
//            if (!uniqueElementsSet.contains(elementString)) {
//                uniqueElementsSet.add(elementString);
//                resultList.add(element);
//                if (resultList.size() == 3) {
//                    break;
//                }
//            }
//        }
//
//        List<int[]> listFix = new ArrayList<>(Arrays.asList(monsterFix));
//        resultList.addAll(listFix);
//
//        int[][] result = resultList.toArray(new int[resultList.size()][2]);
//
//        for (int k = 0; k < result.length; k++) {
//            if (result[k][0] == i && result[k][1] == j) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    public boolean isThereMonster(int x, int y) {
        // Check fixed monster locations first
        for (int[] location : monsterFix) {
            if (location[0] == x && location[1] == y) {
                return true;  // Monster is at this location
            }
        }

        for (int[] location : randomIndex) {
            Random random = new Random();
            boolean randomCheck = (random.nextInt(1, 24) % 4 == 0);
            if (location[0] == x && location[1] == y && randomCheck) {
                return true;
            }
        }

        return false;  // No monster at this location
    }

    public Monster generateRandomMonster() {
        final String[] MONSTER_NAMES = {"Fire Monster", "Water Monster", "Grass Monster", "Ground Monster", "Ice Monster"};
        final int MIN_LEVEL = Math.min(player.getMonster1().getLevel(), Math.max(player.getMonster2().getLevel(), player.getMonster3().getLevel())) / 2;
        final int MAX_LEVEL = Math.max(player.getMonster1().getLevel(), Math.max(player.getMonster2().getLevel(), player.getMonster3().getLevel()));

        Random random = new Random();

        int level = random.nextInt(MIN_LEVEL, MAX_LEVEL) + 1;
        int maxHP = level * 20; // HP antara 50 dan 250

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

    protected void initializeMap() {
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