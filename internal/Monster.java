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

