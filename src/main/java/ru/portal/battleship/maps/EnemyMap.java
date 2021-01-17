package ru.portal.battleship.maps;

import ru.portal.battleship.Player;

public class EnemyMap {
    private String[][] map = new String[10][10];
    private int[][] miss = new int[10][10];
    private int[][] hit = new int[10][10];

    {
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = "\uD83D\uDFE5";
            }
        }
    }

    public int[][] getMiss() {
        return miss;
    }

    public int[][] getHit() {
        return hit;
    }

    public static boolean notAttack(int[][] attack, EnemyMap map){
        for(int i = 0; i < attack[0].length; i++) {
            int x = attack[0][i] - 1;
            int y = attack[1][i] - 1;
            int[][] miss = map.getMiss();
            int[][] hit = map.getHit();
            for(int j = 0; j < miss.length; j++) {
                try {
                    if(miss[x][y] == 1 || hit[x][y] == 1) return false;
                } catch (ArrayIndexOutOfBoundsException e){}
            }
        }
        return true;
    }

    public void add(int[][] attack, boolean result, PlayerMap map){
        if(result){
            hit[attack[0][0] - 1][attack[1][0] - 1] = 1;
            map.getMap()[attack[0][0] - 1][attack[1][0] - 1] = "\uD83D\uDFE5";
        }
        else miss[attack[0][0] - 1][attack[1][0] - 1] = 1;
    }

    public void print(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(!map[i][j].equals("\uD83D\uDFE5")) System.out.print(map[i][j]);
                else if(miss[i][j] != 0) System.out.print("❌");
                else if(hit[i][j] != 0) System.out.print("⚔");
                else System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

}
