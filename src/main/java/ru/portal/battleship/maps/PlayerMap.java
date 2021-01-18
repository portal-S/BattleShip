package ru.portal.battleship.maps;

public class PlayerMap {
    private int id;
    private String[][] map = new String[10][10];
    private int[][] aura = new int[10][10];

    public PlayerMap(int id) {
        this.id = id;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = "\uD83D\uDFE5";
            }
        }
    }

    public void shipAdd(int[][] ship){
        for(int i = 0; i < ship[0].length; i++){
            map[ship[0][i] - 1][ship[1][i] - 1] = "\uD83D\uDEA2";
            auraAdd(ship);
        }
    }

    public void auraAdd(int[][] ship){
        for(int i = 0; i < ship[0].length; i++){
            int x = ship[0][i] - 1;
            int y = ship[1][i] - 1;
            aura[x][y] = 0;

            try {
                addInArray(x - 1, y); //по бокам
                addInArray(x + 1, y);

                addInArray(x, y + 1);
                addInArray(x, y - 1);

                addInArray(x - 1, y - 1); //по диагонали
                addInArray(x + 1, y - 1);

                addInArray(x + 1, y + 1);
                addInArray(x - 1, y + 1);

            } catch (ArrayIndexOutOfBoundsException e){};

        }
    }

    public void addInArray(int x, int y){
        try {
            aura[x][y] = 1;
        } catch (ArrayIndexOutOfBoundsException e){};
    }

    public String[][] getMap() {
        return map;
    }

    public int[][] getAura() {
        return aura;
    }

    public static boolean hasShip(int[][] ship, PlayerMap map, boolean auraCheck){
        if(!hasQueue(ship)) return false;
        for(int i = 0; i < ship[0].length; i++) {
            int x = ship[0][i];
            int y = ship[1][i];
            String[][] crds = map.getMap();
            if(auraCheck){
                int[][] aura = map.getAura();
                    try {
                        System.out.println(x + " " + y);
                        System.out.println(aura[x - 1][y - 1]);
                        if(aura[x - 1][y - 1] == 1) return false;
                    } catch (ArrayIndexOutOfBoundsException e){}
            }
            if(!crds[x - 1][y - 1].equals("\uD83D\uDFE5")) return false;
        }
        return true;
    }

    public static boolean checkShip(int[][] attck, PlayerMap map){

        for(int i = 0; i < attck[0].length; i++) {
            int x = attck[0][i];
            int y = attck[1][i];
            String[][] crds = map.getMap();
            try {
                if(crds[x - 1][y - 1].equals("\uD83D\uDEA2")) return true;
            } catch (ArrayIndexOutOfBoundsException e){}
        }
        return false;

    }

    public static boolean hasQueue(int[][] ship){
        if(ship[0].length == 1) return true;
        for(int i = 0; i < ship[0].length; i++){
            if(i == ship[0].length - 1) return true;
            if(Math.abs(ship[0][i + 1] - ship[0][i]) != 1) break;
        }
        for(int i = 0; i < ship[1].length; i++){
            if(i == ship[1].length - 1) return true;
            if(Math.abs(ship[1][i + 1] - ship[1][i]) != 1) break;
        }
        return false;
    }

    public static boolean hasDeath(int[][] attack, PlayerMap map){
        int x = attack[0][0];
        int y = attack[1][0];
        int[][] att = new int[][]{{x, x, x - 1, x + 1},{y + 1, y - 1, y, y}};
        if(checkShip(att, map)) return false;
        return true;
    }

    public void print(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(!map[i][j].equals("\uD83D\uDFE5")) System.out.print(map[i][j]);
                else if(aura[i][j] != 0) System.out.print("\uD83D\uDFE6");
                else System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

}
