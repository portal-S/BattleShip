package ru.portal.battleship.handlers;

import ru.portal.battleship.Player;
import ru.portal.battleship.maps.EnemyMap;
import ru.portal.battleship.maps.PlayerMap;
import ru.portal.battleship.ships.Ship;

import java.util.Random;
import java.util.Scanner;

public class BsHandler {

    public static int[][] infoToArray(String info){   //перевод вводимых данных в массив координат
        String[] s1 = info.split(";");
        int[][] array = new int[2][s1.length];
        for(int i = 0; i < s1.length; i++){
            array[0][i] = Integer.parseInt(s1[i].split(",")[0]);
            array[1][i] = Integer.parseInt(s1[i].split(",")[1]);
        }
        return array;
    }

    public static int addShip(PlayerMap map, Ship ship, String name, String info, int count){
        if(Ship.infoHandler(info, ship) && PlayerMap.hasShip(BsHandler.infoToArray(info), map, true)){
            map.shipAdd(BsHandler.infoToArray(info));
            map.print();
            System.out.printf("%s: Вы добавили корабль", name).println();
            count++;
        } else System.out.println("Вы ввели что-то не так, попробуйте снова");
        return count;
    }

    public static boolean attackEnemy(EnemyMap enemyMap, PlayerMap playerMap, int[][] attack){
        if(!PlayerMap.hasShip(attack, playerMap, false)){
            if(PlayerMap.hasDeath(attack, playerMap)){
                System.out.println("Утопил!");
            } else System.out.println("Попал");
            enemyMap.add(attack, true, playerMap);
            return true;

        } else {
            System.out.println("Вы промахнулись");
            enemyMap.add(attack, false, playerMap);
            return false;
        }
    }

    public static void playerAddShip(Scanner scanner, String player, PlayerMap map1){
        int counts = 1;

        while (counts <= 10){
            String info;
            if(counts == 1){
                System.out.printf("%s: укажите координаты %s корабля (осталось %d)", player, Ship.FOUR_SHIP.getName(), 1).println();
                info = scanner.next();
                counts = BsHandler.addShip(map1, Ship.FOUR_SHIP, player, info, counts);
            }
            else if(counts <= 3 && counts > 1){
                System.out.printf("%s: укажите координаты %s корабля (осталось %d)", player, Ship.THREE_SHIP.getName(), 4 - counts).println();
                info = scanner.next();
                counts = BsHandler.addShip(map1, Ship.THREE_SHIP, player, info, counts);
            }
            else if(counts <= 6 && counts > 3){
                System.out.printf("%s: укажите координаты %s корабля (осталось %d)", player, Ship.TWO_SHIP.getName(), 7 - counts).println();
                info = scanner.next();
                counts = BsHandler.addShip(map1, Ship.TWO_SHIP, player, info, counts);
            }
            else if(counts <= 10 && counts > 6){
                System.out.printf("%s: укажите координаты %s корабля (осталось %d)", player, Ship.ONE_SHIP.getName(), 11 - counts).println();
                info = scanner.next();
                counts = BsHandler.addShip(map1, Ship.ONE_SHIP, player, info, counts);
            }
        }
        for(int i = 0; i <= 100; i++) System.out.println("---------------------");
    }

    public static boolean hasWin(PlayerMap playerMap){
        String[][] map = playerMap.getMap();
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                if(PlayerMap.checkShip(new int[][]{{j + 1},{i + 1}}, playerMap)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void playerAttack(Player player1, Player player2, Scanner scanner){
        Random random = new Random();
        int c = random.nextInt(2);
        while (c <= 200){
            Player attack;
            Player def;
            if(c % 2 != 0){
                attack = player1;
                def = player2;
            } else {
                attack = player2;
                def = player1;
            }
            c++;
            if(attackValid(scanner, attack, def)) return;
        }
    }

    public static boolean attackValid(Scanner scanner, Player attack, Player def){
        attack.getEnemyMap().print();
        System.out.printf("Ходит игрок %s", attack.getNick()).println();
        String info = scanner.next();
        if(Ship.infoHandler(info, Ship.ONE_SHIP)){
            if(EnemyMap.notAttack(infoToArray(info), attack.getEnemyMap())){  //был ли атакован этот участок
                if(attackEnemy(attack.getEnemyMap(), def.getPlayerMap(), infoToArray(info))){  //если попал
                    if(hasWin(def.getPlayerMap())){
                        System.out.println(attack.getNick() + " выиграл!");
                        return true;
                    }
                    System.out.println("Атакуйте снова");
                } else return false;
            } else System.out.println("Вы уже атаковали данный участок, попробуйте снова");
        } else System.out.println("Вы ввели что-то не так, попробуйте снова");
        if(attackValid(scanner, attack, def)) return true;
        else return false;
    }

}
