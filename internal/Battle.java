public interface Battle {
    int basicAttack(Monster player, Monster enemy);
    int specialAttack(Monster player, Monster enemy);
    int elementAttack1(Monster player, Monster enemy);
    int elementAttack2(Monster player, Monster enemy);

    void useItem(Monster player, Monster enemy, Item item);
    boolean runningAway();
}