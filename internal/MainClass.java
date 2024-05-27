package PBOFINALPROJECTHURA.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        String filePath = "src/PBOFINALPROJECTHURA/internal/GUI/game_progress.txt";
        if (isFileEmpty(filePath)) {
            Game g = new Game(new Player("PLACEHOLDERNAMEFORAWHILE123456789"));
        } else {
            Game g = new Game(GameManager.loadGameProgress());
        }
    }

    public static boolean isFileEmpty(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return true; // File tidak ada dianggap kosong
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (br.readLine() == null) {
                return true; // File kosong
            } else {
                return false; // File tidak kosong
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true; // Jika ada kesalahan, anggap file kosong
        }
    }
}
