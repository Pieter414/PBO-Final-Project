package PBOFINALPROJECTHURA.internal;

import java.awt.*;
import java.util.*;

public abstract class Monster implements Battle {
    private String name;
    private String element;
    private int level;
    private int maxHP;
    private int HP;
    private int EP;
    private int attack = 5;
    private int defense = 20;
    private int bonusAttack = 1;
    private int bonusDefense = 1;
    private Color color;
    private String pathFileFront;
    private String pathFileBack;

    private boolean canEvolve;
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setCurrentHPMinus(int damage){
        if (getHP() - damage <= 0){
            setHP(0);
        }
        else{
            setHP(getHP() - damage);
        }
    }

    public void setCurrentHPPlus(int heal){
        if (getHP() + heal >= getMaxHP()){
            setHP(getMaxHP());
        }
        else{
            setHP(getHP() + heal);
        }
    }

    public int getEP() {
        return EP;
    }

    public void setEP(int EP) {
        this.EP = EP;
    }

    public void setEPPlus(int bonus){
        setEP(getEP()+bonus);
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public void setBonusAttack(int bonusAttack) {
        this.bonusAttack = bonusAttack;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }

    public void setEPMinus(int minus){
        if (getEP() - minus <= 0){
            setEP(0);
        }
        else{
            setEP(getEP() - minus);
        }
    }

    public boolean isCanEvolve() {
        return canEvolve;
    }

    public void setCanEvolve(boolean canEvolve) {
        this.canEvolve = canEvolve;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public String getPathFileFront() {
        return pathFileFront;
    }

    public void setPathFileFront(String pathFileFront){
        this.pathFileFront = pathFileFront;
    }

    public String getPathFileBack() {
        return pathFileBack;
    }

    public void setpathFileBack(String pathFileBack){
        this.pathFileBack = pathFileBack;
    }

    Monster() {
    }

    public abstract double typeEffectiveness(String element1, String element2);


    public abstract void useItem(Monster player, Monster enemy, Item item);

    public abstract boolean runningAway();


    public abstract int attack(Monster player, Monster enemy, int basePower, double stab);

    public int basicAttack(Monster player, Monster enemy) {
        return attack(player, enemy, 40, 1);
    }

    public int specialAttack(Monster player, Monster enemy) {
        return attack(player, enemy, 80, 1);
    }

    public int elementAttack1(Monster player, Monster enemy) {
        return attack(player, enemy, 80, 1.5);
    }

    public int elementAttack2(Monster player, Monster enemy) {
        return attack(player, enemy, 110, 1.5);
    }
}

class GroundMonster extends Monster{
    public GroundMonster(String nama, int level, int maxHP){
        this.setName(nama);
        this.setElement("Ground");
        this.setLevel(level);
        this.setMaxHP(maxHP);
        this.setHP(maxHP);
        this.setEP(0);
        this.setColor(new Color(228, 197, 158));
        this.setPathFileFront("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\ground_front.gif");
        this.setpathFileBack("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\ground_back.gif");
    }

    public double typeEffectiveness(String elementPlayer, String elementEnemy){
        if (elementEnemy.equals("Water")) return 2;
        else if (elementEnemy.equals("Grass")) return 0.5;
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
            if (player.getElement().equals("Water")) {
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
        if(7 >= escapeValue){
            System.out.println("You success to run");
            return true;
        }else{
            System.out.println("You failed to run");
            return false;
        }
    }
}

class WaterMonster extends Monster {
    public WaterMonster(String nama, int level, int maxHP) {
        this.setName(nama);
        this.setElement("Water");
        this.setLevel(level);
        this.setMaxHP(maxHP);
        this.setHP(maxHP);
        this.setEP(0);
        this.setColor(new Color(90, 178, 255));
        this.setPathFileFront("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\water_front.gif");
        this.setpathFileBack("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\water_back.gif");
    }

    public double typeEffectiveness(String elementPlayer, String elementEnemy) {
        if (elementEnemy.equals("Fire")) return 2;
        else if (elementEnemy.equals("Ground")) return 0.5;
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
            if (player.getElement().equals("Fire")) {
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

class FireMonster extends Monster {
    public FireMonster(String nama, int level, int maxHP) {
        this.setName(nama);
        this.setElement("Fire");
        this.setLevel(level);
        this.setMaxHP(maxHP);
        this.setHP(maxHP);
        this.setEP(0);
        this.setColor(new Color(250, 112, 112));
        this.setPathFileFront("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\fire_front.gif");
        this.setpathFileBack("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\fire_back.gif");
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

class IcedMonster extends Monster {
    public IcedMonster(String nama, int level, int maxHP) {
        this.setName(nama);
        this.setElement("Ice");
        this.setLevel(level);
        this.setMaxHP(maxHP);
        this.setHP(maxHP);
        this.setEP(0);
        this.setColor(new Color(202,244,255));
        this.setPathFileFront("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\ice_front.gif");
        this.setpathFileBack("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\ice_back.gif");
    }

    public double typeEffectiveness(String elementPlayer, String elementEnemy) {
        if (elementEnemy.equals("Grass")) return 2;
        else if (elementEnemy.equals("Fire")) return 0.5;
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
            if (player.getElement().equals("Wind")) {
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

class GrassMonster extends Monster {
    public GrassMonster(String nama, int level, int maxHP) {
        this.setName(nama);
        this.setElement("Grass");
        this.setLevel(level);
        this.setMaxHP(maxHP);
        this.setHP(maxHP);
        this.setEP(0);
        this.setColor(new Color(195, 255, 147));
        this.setPathFileFront("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\grass_front.gif");
        this.setpathFileBack("D:\\Intellij\\PBO\\PBOFinalProject\\assets\\monster\\grass_back.gif");
    }

    public double typeEffectiveness(String elementPlayer, String elementEnemy) {
        if (elementEnemy.equals("Ground")) return 2;
        else if (elementEnemy.equals("Ice")) return 0.5;
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
            if (player.getElement().equals("Ground")) {
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