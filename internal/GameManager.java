package PBOFINALPROJECTHURA.internal;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class GameManager {

    public static void saveGameProgress(Player player, int healthPotionCount, HomeBase homebase) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("game_progress.txt"))) {
            writer.println(player.getName());
            writer.println(player.getMoney());

            List<Monster> monsters = homebase.getListMonster();
            writer.println(monsters.size());
            for (Monster monster : monsters) {
                writer.println(monster.getName());
                writer.println(monster.getLevel());
                writer.println(monster.getHP());
                writer.println(monster.getEP());
                writer.println(monster.getElement());
            }

            List<Item> items = player.getListPotion();
            writer.println(items.size());
            List<Item> items2 = player.getListSuperPotion();
            writer.println(items2.size());
            List<Item> items3 = player.getListXAttack();
            writer.println(items3.size());
            List<Item> items4 = player.getListXDefense();
            writer.println(items4.size());

            System.out.println("Game progress saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save game progress.");
        }
    }

    public static Player loadGameProgress(HomeBase homebase, JTextArea textArea, JLabel healthPotionLabel, JLabel moneyLabel) {
        Player player = null;
        int healthPotionCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("game_progress.txt"))) {
            String playerName = reader.readLine();
            int playerMoney = Integer.parseInt(reader.readLine());

            player = new Player(playerName);
            player.setMoney(playerMoney);

            int monsterCount = Integer.parseInt(reader.readLine());
            ArrayList<Monster> monsters = new ArrayList<>();
            for (int i = 0; i < monsterCount; i++) {
                String name = reader.readLine();
                int level = Integer.parseInt(reader.readLine());
                int ep = Integer.parseInt(reader.readLine());
                String element = reader.readLine();

                Monster monster;
                switch (element) {
                    case "Fire":
                        monster = new FireMonster(name, level, 1000);
                        break;
                    case "Water":
                        monster = new WaterMonster(name, level, 1000);
                        break;
                    case "Grass":
                        monster = new GrassMonster(name, level, 1000);
                        break;
                    case "Ice":
                        monster = new IcedMonster(name, level, 1000);
                        break;
                    case "Ground":
                        monster = new GroundMonster(name, level, 1000);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + element);
                }
                monster.setEP(ep);
                monsters.add(monster);
                }
            homebase.setListMonster(monsters);





            int potionCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < potionCount; i++) {
                Item item = new Potion(50, "potion");
                player.addItem(item);
            }
            int superPotionCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < superPotionCount; i++) {
                Item item = new Potion(200, "super potion");
                player.addItem(item);
            }
            int xAttackCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < xAttackCount; i++) {
                Item item = new XItem(2, "x attack");
                player.addItem(item);
            }
            int xDefCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < xDefCount; i++) {
                Item item = new XItem(2, "x defense");
                player.addItem(item);
            }

            healthPotionLabel.setText("Health Potion: " + healthPotionCount);

            moneyLabel.setText("Money: " + player.getMoney());

            System.out.println("Game progress loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load game progress.");
        }
        return player;
    }
}
