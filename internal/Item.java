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