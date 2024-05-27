import java.util.*;

public abstract class Dungeon {
    private ArrayList<Monster> listMonster;
    private Player currentPlayer;
    private Monster[] map;

    private char[][] gameMap;

    Dungeon(ArrayList<Monster> monsters, Player player, Monster[] monsterPick, char[][] gameMap) {
        this.listMonster = monsters;
        this.map = monsterPick;
        this.currentPlayer = player;
        this.gameMap = gameMap;
    }

    public Monster generateRandomMonster() {
        final String[] MONSTER_NAMES = {"Golem", "Squirtle", "Charmander", "Glaceon", "Pidgey"};
        final int MAX_LEVEL = 50;
        final int MAX_HP = 200;

        Random random = new Random();

        String name = MONSTER_NAMES[random.nextInt(MONSTER_NAMES.length)];
        int level = random.nextInt(MAX_LEVEL) + 1;
        int maxHP = random.nextInt(MAX_HP) + 50; // HP antara 50 dan 250

        switch (random.nextInt(5)) {
            case 0:
                return new GroundMonster(name, level, maxHP);
            case 1:
                return new WaterMonster(name, level, maxHP);
            case 2:
                return new FireMonster(name, level, maxHP);
            case 3:
                return new IcedMonster(name, level, maxHP);
            case 4:
                return new GrassMonster(name, level, maxHP);
            default:
                return null;
        }
    }

    public boolean move(String direction) {
        int[] playerPosition = findPlayer();
        if (playerPosition == null) {
            System.out.println("Player not found on the map!");
            return false;
        }

        int playerRow = playerPosition[0];
        int playerCol = playerPosition[1];

        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction.toLowerCase()) {
            case "up":
                newRow--;
                break;
            case "down":
                newRow++;
                break;
            case "left":
                newCol--;
                break;
            case "right":
                newCol++;
                break;
            default:
                System.out.println("Invalid direction!");
                return false;
        }

        if (isValidMove(newRow, newCol)) {
            gameMap[playerRow][playerCol] = ' ';
            gameMap[newRow][newCol] = 'P';
            return true;
        }

        return false;
    }

    private int[] findPlayer() {
        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[row].length; col++) {
                if (gameMap[row][col] == 'P') {
                    return new int[]{row, col};
                }
            }
        }
        return null; // player tidak ditemukan
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= gameMap.length || col < 0 || col >= gameMap[0].length) {
            return false; // Out of bounds
        }
        return gameMap[row][col] == ' ' || gameMap[row][col] == 'X'; // check kondisi map
    }

    public void printGameMap() {
        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[row].length; col++) {
                System.out.print(gameMap[row][col]);
            }
            System.out.println();
        }
    }

    public boolean isThereMonster(int i, int j) {
        int[][] monsterFix = {{4, 1}, {4, 4}, {1, 8}, {9, 8}};
        int[][] randomIndex = {{2, 2}, {3, 2}, {3, 3}, {3, 3}, {2, 3}, {1, 3}};

        List<int[]> list = new ArrayList<>(Arrays.asList(randomIndex));
        Collections.shuffle(list);

        Set<String> uniqueElementsSet = new HashSet<>();
        List<int[]> resultList = new ArrayList<>();

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
}

class SwampDungeon extends Dungeon {
    private static final char[][] SWAMP_MAP = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'X'},
            {'#', '#', '#', ' ', '#', '#', '#', '#', ' ', ' '},
            {'#', '#', ' ', ' ', '#', '#', ' ', ' ', ' ', '#'},
            {'#', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', '#'},
            {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#'},
            {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#'},
            {'#', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#'},
            {'#', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#'},
            {'#', 'P', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    SwampDungeon(ArrayList<Monster> monsters, Player player, Monster[] monsterPick) {
        super(monsters, player, monsterPick, SWAMP_MAP);
    }
}

class ForestDungeon extends Dungeon{
    private static final char[][] FOREST_MAP = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'X'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', ' '},
            {'#', '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' '},
            {'#', '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' '},
            {'#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
            {'#', '#', '#', '#', ' ', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', ' ', '#', '#', '#', '#', '#'},
            {'#', '#', '#', ' ', ' ', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#'},
            {'#', 'P', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    ForestDungeon(ArrayList<Monster> monsters, Player player, Monster[] monsterPick) {
        super(monsters, player, monsterPick, FOREST_MAP);
    }
}

class VolcanoDungeon extends Dungeon{
    private static final char[][] VOLCANO_MAP = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'X'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' '},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' '},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' '},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' '},
            {' ', '#', '#', '#', '#', ' ', '#', ' ', ' ', ' '},
            {' ', ' ', ' ', '#', '#', ' ', '#', ' ', ' ', ' '},
            {'#', '#', ' ', '#', '#', ' ', ' ', ' ', ' ', ' '},
            {'#', 'P', ' ', '#', '#', '#', '#', '#', '#', '#'}
    };

    VolcanoDungeon(ArrayList<Monster> monsters, Player player, Monster[] monsterPick) {
        super(monsters, player, monsterPick, VOLCANO_MAP);
    }
}