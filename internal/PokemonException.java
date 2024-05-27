
public class PokemonException extends Exception {
    public PokemonException() {
        super("Player should bring 3 monsters to enter the dungeon.");
    }

    public PokemonException(String message) {
        super(message);
    }
}

//class Pemain {
//    private List<String> monsters;
//
//    public Pemain(List<String> monsters) {
//        this.monsters = monsters;
//    }
//
//    public void enterDungeon() throws PokemonException {
//        if (monsters.size() < 3 || monsters.size() > 3) {
//            throw new PokemonException();
//        } else {
//            System.out.println("Welcome to the dungeon!");
//        }
//    }
//
//    // Getter for monsters
//    public List<String> getMonsters() {
//        return monsters;
//    }
//}
//
//class PokemonGame {
//    public static void main(String[] args) {
//        // Example with less than 3 monsters
//        Pemain player1 = new Pemain(Arrays.asList("Pikachu", "Charmander"));
//
//        try {
//            player1.enterDungeon();
//        } catch (PokemonException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Example with 3 or more monsters
//        Pemain player2 = new Pemain(Arrays.asList("Pikachu", "Charmander", "Bulbasaur"));
//
//        try {
//            player2.enterDungeon();
//        } catch (PokemonException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}