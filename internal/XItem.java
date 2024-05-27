package PBOFINALPROJECTHURA.internal;

public class XItem extends Item{
    private int bonusGiven;

    public XItem(int bonusGiven, String sifat){
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
