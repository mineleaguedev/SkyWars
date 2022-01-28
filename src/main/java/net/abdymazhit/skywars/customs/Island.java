package net.abdymazhit.skywars.customs;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Остров
 */
public class Island {

    /** Id острова */
    private final int id;

    /** Тег острова */
    private final char tag;

    /** Местоположение спавна игроков острова */
    private final Location spawn;

    /** Список местоположений сундуков острова */
    private final List<Location> chests;

    /** Список игроков острова */
    private final LinkedHashSet<Player> players;

    /**
     * Создает новый остров
     * @param id Id острова
     * @param spawn Местоположение спавна игроков острова
     * @param chests Список местоположений сундуков острова
     */
    public Island(int id, char tag, Location spawn, List<Location> chests) {
        this.id = id;
        this.tag = tag;
        this.spawn = spawn;
        this.chests = chests;
        this.players = new LinkedHashSet<>();
    }

    /**
     * Получает id острова
     * @return Id острова
     */
    public int getId() {
        return id;
    }

    /**
     * Получает тег острова
     * @return Тег острова
     */
    public char getTag() {
        return tag;
    }

    /**
     * Получает местоположение спавна игроков острова
     * @return Местоположение спавна игроков острова
     */
    public Location getSpawn() {
        return spawn;
    }

    /**
     * Получает список местоположений сундуков острова
     * @return Список местоположений сундуков острова
     */
    public List<Location> getChests() {
        return chests;
    }

    /**
     * Добавляет игрока в остров
     * @param player Игрок
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Удаляет игрока из острова
     * @param player Игрок
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Получает список игроков острова
     * @return Список игроков острова
     */
    public LinkedHashSet<Player> getPlayers() {
        return players;
    }
}
