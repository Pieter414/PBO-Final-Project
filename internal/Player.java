package PBOFINALPROJECTHURA.internal;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    //    private static final long serialVersionUID = 1L;
    private String name;
    private int money;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Homebase homebase;
    private Dungeon location;
    private ArrayList<Item> listPotion;
    private ArrayList<Item> listSuperPotion;
    private ArrayList<Item> listXAttack;
    private ArrayList<Item> listXDefense;

    public Player(String name) {
        this.name = name;
        this.listPotion = new ArrayList<>();
        this.listSuperPotion = new ArrayList<>();
        this.listXAttack = new ArrayList<>();
        this.listXDefense = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setMoneyPlus(int money) {
        setMoney(getMoney() + money);
    }

    public void setMoneyMinus(int money) {
        if (getMoney() - money <= 0){
            setMoney(0);
        }
        else{
            setMoney(getMoney() - money);
        }
    }

    public Monster getMonster1() {
        return monster1;
    }

    public void setMonster1(Monster monster1) {
        this.monster1 = monster1;
    }

    public Monster getMonster2() {
        return monster2;
    }

    public void setMonster2(Monster monster2) {
        this.monster2 = monster2;
    }

    public Monster getMonster3() {
        return monster3;
    }

    public void setMonster3(Monster monster3) {
        this.monster3 = monster3;
    }

    public Homebase getHomebase() {
        return homebase;
    }

    public void setHomebase(Homebase homebase) {
        this.homebase = homebase;
    }

    public Dungeon getLocation() {
        return location;
    }

    public void setLocation(Dungeon location) {
        this.location = location;
    }

    public ArrayList<Item> getListPotion() {
        return listPotion;
    }

    public void setListPotion(ArrayList<Item> listPotion) {
        this.listPotion = listPotion;
    }

    public ArrayList<Item> getListSuperPotion() {
        return listSuperPotion;
    }

    public void setListSuperPotion(ArrayList<Item> listSuperPotion) {
        this.listSuperPotion = listSuperPotion;
    }

    public ArrayList<Item> getListXAttack() {
        return listXAttack;
    }

    public void setListXAttack(ArrayList<Item> listXAttack) {
        this.listXAttack = listXAttack;
    }

    public ArrayList<Item> getListXDefense() {
        return listXDefense;
    }

    public void setListXDefense(ArrayList<Item> listXDefense) {
        this.listXDefense = listXDefense;
    }

    public void addItem(Item item){
        if (item.getSifat().equalsIgnoreCase("potion")){
            getListPotion().add(item);
        }
        else if (item.getSifat().equalsIgnoreCase("super potion")){
            getListSuperPotion().add(item);
        }
        else if (item.getSifat().equalsIgnoreCase("x attack")){
            getListXAttack().add(item);
        }
        else if (item.getSifat().equalsIgnoreCase("x defense")){
            getListXDefense().add(item);
        }
    }

    public void viewMonster() {
        // Implementasi untuk melihat monster
        System.out.println("Monster 1: " + (monster1 != null ? monster1.getName() : "None"));
        System.out.println("Monster 2: " + (monster2 != null ? monster2.getName() : "None"));
        System.out.println("Monster 3: " + (monster3 != null ? monster3.getName() : "None"));
    }

    public void inputMonster(Monster monster, int slot) {
        // Implementasi untuk memasukkan monster ke dalam slot tertentu
        if (slot == 1) {
            setMonster1(monster);
        } else if (slot == 2) {
            setMonster2(monster);
        } else if (slot == 3) {
            setMonster3(monster);
        }
    }

    public void handleException(PokemonException e) {
        // Implementasi penanganan exception
        System.err.println("Terjadi kesalahan: " + e.getMessage());
    }
}