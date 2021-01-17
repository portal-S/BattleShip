package ru.portal.battleship.ships;

public enum Ship {
    ONE_SHIP(2, "однопалубного"), TWO_SHIP(4, "двухпалубного"), THREE_SHIP(6, "трехпалубного"), FOUR_SHIP(8, "четырехпалубного");


    private int count;
    private String name;

    Ship(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public static boolean infoHandler(String info, Ship s){   //проверка координат на валидность
        if(info.split(";").length != (s.getCount() / 2)) return false;
        info = info.replace(";", ",");
        if(info.split(",").length != s.getCount()) return false;
        try {
            for(String ss : info.split(",")){
                if(Integer.parseInt(ss) > 10 || Integer.parseInt(ss) < 1) return false;
            }
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
