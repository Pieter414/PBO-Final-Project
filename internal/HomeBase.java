package PBOFINALPROJECTHURA.internal;

import PBOFINALPROJECTHURA.internal.GUI.HomeBaseDisplay;

import java.util.ArrayList;

public class HomeBase {
    private ArrayList<Monster> listMonster;

    public HomeBase(Player player) {
        listMonster = new ArrayList<>();
        HomeBaseDisplay hbd = new HomeBaseDisplay(player);
    }

    public Monster changeElement(Monster monster, String newElement) {
        // Implementasi untuk mengubah elemen dari monster
        monster.setElement(newElement);
        return monster;
    }

    public void setListMonster(ArrayList<Monster> listMonster) {
        this.listMonster = listMonster;
    }

    public void upgradeLevel(Monster monster) {
        // Implementasi untuk meningkatkan level monster
        monster.setLevel(monster.getLevel() + 1);
    }

    public void awakenMonster(Monster monster) {
        // Implementasi untuk membangkitkan monster (misalnya menambah max HP)
        monster.setMaxHP(monster.getMaxHP() + 10);  // Contoh: menambah max HP sebesar 10
    }

    public void restoreHPMonster(Monster monster) {
        // Implementasi untuk memulihkan HP monster ke max HP
        monster.setMaxHP(monster.getMaxHP());
    }

    public void addItem(Monster monster) {
        // Menambahkan monster ke dalam list
        listMonster.add(monster);
    }

    public ArrayList<Monster> getListMonster() {
        return listMonster;
    }
}