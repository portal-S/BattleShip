package ru.portal.battleship;

import ru.portal.battleship.handlers.BsHandler;
import ru.portal.battleship.maps.EnemyMap;
import ru.portal.battleship.maps.PlayerMap;

import java.util.Scanner;

public class BattleShip {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Игра начинается! Игрок 1, введите никнейм");

        PlayerMap map1 = new PlayerMap(1);
        PlayerMap map2 = new PlayerMap(2);
        String p1 = scanner.next();
        BsHandler.playerAddShip(scanner, p1, map1);
        Player player1 = new Player(map1, new EnemyMap(), p1);

        System.out.println("Игрок 2, введите никнейм");
        String p2 = scanner.next();
        BsHandler.playerAddShip(scanner, p2, map2);
        Player player2 = new Player(map2, new EnemyMap(), p2);

        BsHandler.playerAttack(player1, player2, scanner);

        scanner.close();
    }
}
// 3,4;3,5;3,6;3,7 7,1;8,1;9,1 9,1;9,2;9,3