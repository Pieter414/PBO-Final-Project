package PBOFINALPROJECTHURA.internal;

public class Potion extends Item{
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
