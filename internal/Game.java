package PBOFINALPROJECTHURA.internal;

import PBOFINALPROJECTHURA.internal.GUI.HomeScreen;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private Player player;

    public Game(Player player) {
        this.player = player;
        startGame();
    }

    public void startGame() {
        System.out.println("Game dimulai!");
        HomeScreen hs = new HomeScreen(player);
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

    public void handleException(MonsterException e) {
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
}