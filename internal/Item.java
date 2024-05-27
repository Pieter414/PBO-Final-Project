package PBOFINALPROJECTHURA.internal;

public abstract class Item {
    private String sifat;

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }
    public abstract void description();

    public abstract int use();
}

class Potion extends Item{
    private int healthGiven;

    public Potion(int healthGiven, String sifat){
        if(healthGiven == 50 || healthGiven == 200){
            this.healthGiven = healthGiven;
            setSifat(sifat);
        }else{
            System.out.println("Item tidak tersedia");
        }
    }

    public void description(){
        System.out.println("Potions are useful for recovering Health Points lost during battle. Potions have different strengths and recover different amounts of HP.");
    }

    @Override
    public int use() {
        return healthGiven;
    }
}

class XItem extends Item{
    private int bonusGiven;

    XItem(int bonusGiven, String sifat){
        this.bonusGiven = bonusGiven;
        setSifat(sifat);
    }

    public void description(){
        System.out.println("Item used to increase monster's attack with many variant of elemental item.");
    }

    @Override
    public int use() {
        return this.bonusGiven;
    }
}