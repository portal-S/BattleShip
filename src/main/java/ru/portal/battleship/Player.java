package ru.portal.battleship;

import ru.portal.battleship.maps.EnemyMap;
import ru.portal.battleship.maps.PlayerMap;

public class Player {
    private PlayerMap playerMap;
    private EnemyMap enemyMap;
    private String nick;

    public Player(PlayerMap playerMap, EnemyMap enemyMap, String nick) {
        this.playerMap = playerMap;
        this.enemyMap = enemyMap;
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public PlayerMap getPlayerMap() {
        return playerMap;
    }

    public EnemyMap getEnemyMap() {
        return enemyMap;
    }
}
