package PBOFINALPROJECTHURA.internal;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player player;
    private Homebase homebase;
    private ArrayList<Dungeon> dungeons;

    public Game(Player player, Homebase homebase, ArrayList<Dungeon> dungeons) {
        this.player = player;
        this.homebase = homebase;
        this.dungeons = dungeons;
    }

    public void startGame() {
        // Implementasi logika untuk memulai game
        System.out.println("Game dimulai!");
        // Misalnya: Inisialisasi player, homebase, dan dungeons
    }

    public void loadGame() {
        // Implementasi logika untuk memulai game
        System.out.println("Game berhasil di load!");
        // Misalnya: Inisialisasi player, homebase, dan dungeons
    }

    public void saveGame() {
        // Implementasi logika untuk memulai game
        System.out.println("Game berhasil di save!");
        // Misalnya: Inisialisasi player, homebase, dan dungeons
    }

    public void handleException(PokemonException e) {
        // Implementasi penanganan exception
        System.err.println("Terjadi kesalahan: " + e.getMessage());
    }

    // Getter dan setter
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Homebase getHomebase() {
        return homebase;
    }

    public void setHomebase(Homebase homebase) {
        this.homebase = homebase;
    }

    public ArrayList<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(ArrayList<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }
}