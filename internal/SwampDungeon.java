package PBOFINALPROJECTHURA.internal;

import java.util.*;

public class SwampDungeon extends Dungeon {
    public SwampDungeon(Player player) {
        super(player);
    }

    @Override
    protected void initializeMap() {
        this.map = new char[][]{
                {'#', ' ', '#', '#', '#', '#', '#', '#', '#', 'X'},
                {' ', ' ', ' ', ' ', '#', '#', '#', '#', ' ', ' '},
                {'#', '#', ' ', ' ', '#', '#', ' ', ' ', ' ', '#'},
                {'#', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' '},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' '},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', '#'},
                {'#', 'P', '#', ' ', ' ', ' ', '#', '#', '#', '#'}
        };
    }


}