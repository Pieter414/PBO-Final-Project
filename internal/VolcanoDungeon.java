package PBOFINALPROJECTHURA.internal;

import java.util.*;

public class VolcanoDungeon extends Dungeon {
    public VolcanoDungeon(Player player) {
        super(player);
    }

    @Override
    protected void initializeMap() {
        this.map = new char[][]{
                {'#', '#', '#', '#', ' ', '#', '#', '#', '#', 'X'},
                {'#', '#', '#', '#', ' ', '#', '#', ' ', ' ', ' '},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' '},
                {'#', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' '},
                {'#', '#', '#', ' ', '#', '#', '#', '#', '#', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' '},
                {' ', '#', '#', '#', '#', ' ', '#', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', ' ', '#', ' ', ' ', ' '},
                {'#', '#', ' ', '#', '#', ' ', ' ', ' ', ' ', ' '},
                {'#', 'P', ' ', '#', '#', '#', '#', '#', '#', '#'}
        };
    }
}