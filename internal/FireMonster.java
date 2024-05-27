package PBOFINALPROJECTHURA.internal;

import java.awt.*;
import java.util.Random;

public class FireMonster extends Monster {
    public FireMonster(String nama, int level, int maxHP) {
        this.setName(nama);
        this.setElement("Fire");
        this.setLevel(level);
        this.setMaxHP(maxHP);
        this.setHP(maxHP);
        this.setEP(0);
        this.setColor(new Color(250, 112, 112));
        this.setPathFileFront("src/PBOFINALPROJECTHURA/assets/monster/fire_front.gif");
        this.setpathFileBack("src/PBOFINALPROJECTHURA/assets/monster/fire_back.gif");
    }

    public double typeEffectiveness(String elementPlayer, String elementEnemy) {
        if (elementEnemy.equals("Ice")) return 2;
        else if (elementEnemy.equals("Water")) return 0.5;
        else return 1;
    }

    public int attack(Monster player, Monster enemy, int basePower, double stab) {
        Random random = new Random();
        int randomCritical = (int) (Math.random() * 10);
        double critical = (randomCritical == 8) ? 1.5 : 1;
        double randomRollAttack = (double) (random.nextInt(255 - 217) + 1 + 255) / 255;

        double damage;
        if (stab == 1){
            damage = (((((2 * player.getLevel() * critical) / 5) * basePower * (((double) player.getAttack() * player.getBonusAttack()) / (enemy.getDefense() * player.getBonusDefense()))) / 5) + 2) * stab * 1 * randomRollAttack;
        }
        else{
            damage = (((((2 * player.getLevel() * critical) / 5) * basePower * (((double) player.getAttack() * player.getBonusAttack()) / (enemy.getDefense() * player.getBonusDefense()))) / 5) + 2) * stab * typeEffectiveness(player.getElement(), enemy.getElement()) * randomRollAttack;
        }
        setBonusAttack(1); setBonusDefense(1);
        return (int) damage;
    }

    public int basicAttack(Monster player, Monster enemy) {
        return super.basicAttack(player, enemy);
    }

    public int specialAttack(Monster player, Monster enemy) {
        return super.specialAttack(player, enemy);
    }

    public int elementAttack1(Monster player, Monster enemy) {
        return super.elementAttack1(player, enemy);
    }

    public int elementAttack2(Monster player, Monster enemy) {
        return super.elementAttack2(player, enemy);
    }

    @Override
    public void useItem(Monster player, Monster enemy, Item item) {
        if (item instanceof Potion) {
            player.setHP(player.getHP() + ((new Potion(100, "x")).use()));
        } else if (item instanceof XItem) {
            if (player.getElement().equals("Ice")) {
                enemy.setHP(enemy.getHP() - (new XItem(100, "x")).use());
            }
        } else {
            //Pokeball belum
            System.out.println("Item antara Pokeball atau tidak termasuk");
        }
    }

    @Override
    public boolean runningAway() {
        int escapeValue = (int) (Math.random() * 10);
        if (7 >= escapeValue) {
            System.out.println("You success to run");
            return true;
        } else {
            System.out.println("You failed to run");
            return false;
        }
    }
}