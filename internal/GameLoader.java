import java.io.*;

public class GameLoader {

    public static Game loadGameFromFile(String filename) throws IOException, ClassNotFoundException {
        Game game = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            game = (Game) ois.readObject();
            System.out.println("Game loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load game: " + e.getMessage());
            throw e;
        }
        return game;
    }

    public static void saveGame(Game game, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(game);
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
            throw e;
        }
    }
}