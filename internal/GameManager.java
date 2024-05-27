package PBOFINALPROJECTHURA.internal;

import java.io.*;
import java.util.*;

public class GameManager {

    public static void saveGameProgress(Player player) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("game_progress.txt"))) {
            writer.println(player.getName());
            writer.println(player.getMoney());

            List<Monster> monsters = Arrays.asList(player.getMonster1(), player.getMonster2(), player.getMonster3());
            writer.println(monsters.size());

            for (Monster monster : monsters) {
                if (monster != null) {
                    writer.println(monster.getName());
                    writer.println(monster.getLevel());
                    writer.println(monster.getHP());
                    writer.println(monster.getEP());
                    writer.println(monster.getElement());
                } else {
                    writer.println("null");
                }
            }

            List<Item> items = player.getListPotion();
            writer.println(items.size());
            for (Item item : items) {
                writer.println(item.getSifat());
            }

            List<Item> items2 = player.getListSuperPotion();
            writer.println(items2.size());
            for (Item item : items2) {
                writer.println(item.getSifat());
            }

            List<Item> items3 = player.getListXAttack();
            writer.println(items3.size());
            for (Item item : items3) {
                writer.println(item.getSifat());
            }

            List<Item> items4 = player.getListXDefense();
            writer.println(items4.size());
            for (Item item : items4) {
                writer.println(item.getSifat());
            }

            System.out.println("Game progress saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save game progress.");
        }
    }

    public static Player loadGameProgress() {
        Player player = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("game_progress.txt"))) {
            String playerName = reader.readLine();
            int playerMoney = Integer.parseInt(reader.readLine());

            player = new Player(playerName);
            player.setMoney(playerMoney);

            int monsterCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < monsterCount; i++) {
                String name = reader.readLine();
                if (name.equals("null")) {
                    continue;
                }
                int level = Integer.parseInt(reader.readLine());
                int hp = Integer.parseInt(reader.readLine());
                int ep = Integer.parseInt(reader.readLine());
                String element = reader.readLine();

                Monster monster = null;
                switch (element) {
                    case "Fire":
                        monster = new FireMonster(name, level, hp);
                        break;
                    case "Water":
                        monster = new WaterMonster(name, level, hp);
                        break;
                    case "Grass":
                        monster = new GrassMonster(name, level, hp);
                        break;
                    case "Ice":
                        monster = new IcedMonster(name, level, hp);
                        break;
                    case "Ground":
                        monster = new GroundMonster(name, level, hp);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + element);
                }
                monster.setEP(ep);

                if (i == 0) {
                    player.setMonster1(monster);
                } else if (i == 1) {
                    player.setMonster2(monster);
                } else if (i == 2) {
                    player.setMonster3(monster);
                }
            }

            int potionCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < potionCount; i++) {
                String itemName = reader.readLine();
                Item item = new Potion(50, itemName);
                player.addItem(item);
            }

            int superPotionCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < superPotionCount; i++) {
                String itemName = reader.readLine();
                Item item = new Potion(200, itemName);
                player.addItem(item);
            }

            int xAttackCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < xAttackCount; i++) {
                String itemName = reader.readLine();
                Item item = new XItem(2, itemName);
                player.addItem(item);
            }

            int xDefCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < xDefCount; i++) {
                String itemName = reader.readLine();
                Item item = new XItem(2, itemName);
                player.addItem(item);
            }

            System.out.println("Game progress loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load game progress.");
        }
        return player;
    }
}
