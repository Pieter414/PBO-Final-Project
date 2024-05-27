package PBOFINALPROJECTHURA.internal;

import java.util.*;

public class ForestDungeon extends Dungeon {
    public ForestDungeon(Player player) {
        super(player);
    }

    @Override
    protected void initializeMap() {
        this.map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'X'},
                {'#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', ' '},
                {'#', ' ', ' ', '#', '#', '#', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', '#', ' ', ' ', ' ', ' '},
                {'#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
                {'#', ' ', ' ', '#', ' ', '#', '#', ' ', '#', '#'},
                {'#', '#', '#', '#', ' ', '#', '#', ' ', '#', '#'},
                {'#', '#', '#', ' ', ' ', '#', '#', ' ', '#', ' '},
                {'#', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', ' '},
                {'#', 'P', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
    }
}
