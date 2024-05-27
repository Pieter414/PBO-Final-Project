import javax.swing.*;

public class TestClassMainDungeon {
    public static void main(String[] args) {
        Player p = new Player("Pieter");
        Monster m1 = new FireMonster("Alex", 50, 1000);
        Monster m2 = new WaterMonster("Bianca", 50, 1000);
        Monster m3 = new IcedMonster("Charlie", 50, 1000);
        Monster m4 = new GroundMonster("Daniel", 50, 1000);
        Monster m5 = new GrassMonster("Emily", 50, 1000);

        Monster e1 = new FireMonster("Andy", 100, 1000);
        Monster e2 = new WaterMonster("Blake", 50, 1000);
        Monster e3 = new IcedMonster("Clara", 50, 1000);
        Monster e4 = new GroundMonster("Dominic", 50, 1000);
        Monster e5 = new GrassMonster("Emilia", 50, 1000);

        for (int i = 0; i < 10; i++){
            p.addItem(new Potion(50, "potion"));
            p.addItem(new Potion(200, "super potion"));
            p.addItem(new XItem(2, "x attack"));
            p.addItem(new XItem(2, "x defense"));
        }

        p.setMonster1(m1);
        p.setMonster2(m2);
        p.setMonster3(m3);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dungeon");
            DungeonTest dungeon = new DungeonTest(p);  // Create the DungeonTest instance
            DungeonPanel dungeonPanel = dungeon.getDungeonPanel();  // Get the fully initialized panel
        });
    }
}