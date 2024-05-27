package PBOFINALPROJECTHURA.internal;

public class BattleArena {
    Monster monster;
    Monster enemy;
    Player player;
    Dungeon dungeon;
    BattleArenaDisplay battleArenaDisplay;

    BattleArena(Player player, Monster enemy, Dungeon dungeon){
        this.player = player;
        this.enemy = enemy;
        this.dungeon = dungeon;
        battleArenaDisplay = new BattleArenaDisplay(player, enemy, false);
    }

    public void defaultDisplay(){

    }

    public void fightDisplay(){

    }

    public void itemDisplay(){

    }

    public void monsterDisplay(){

    }

    public void action(String action){

    }

    public void swapMonster(Monster monster, Monster swappedMonster){

    }

    public boolean useItem(Item item){
        return false;
    }

    public void runningAway(Monster monster){
        monster.runningAway();
    }

    public void handleException(){

    }
}