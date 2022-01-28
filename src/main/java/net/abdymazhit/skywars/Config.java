package net.abdymazhit.skywars;

import net.abdymazhit.skywars.customs.Island;
import net.abdymazhit.skywars.enums.Mode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Конфигурация игры
 */
public class Config {

    /** Режим игры */
    public static Mode mode;

    /** Мир, где будет проходить игра */
    public static World world;

    /** Местоположение, где будут появляться игроки при заходе в игру */
    public static Location lobbyLocation;

    /** Название карты */
    public static String mapName;

    /** Количество игроков на каждом острове */
    public static int islandPlayers;

    /** Высота на которой будут появляться зрители */
    public static int respawnY;

    /** Список местоположений обычных сундуков */
    public static List<Location> basicChests;

    /** Список местоположений центральных сундуков */
    public static List<Location> middleChests;

    /** Местоположение мистического сундука */
    public static Location mysteryChest;

    /** Список местоположений точек детматча */
    public static List<Location> deathmatchSpawns;

    /** Список островов */
    public static List<Island> islands;

    /**
     * Загружает данные с конфиг файла
     * @throws IllegalArgumentException Ошибка нехватки точек детматча
     */
    public static void load() {
        FileConfiguration config = SkyWars.getInstance().getConfig();
        SkyWars.getInstance().saveDefaultConfig();

        SkyWars.getInstance().getLogger().info("Загружается конфигурация игры...");

        mode = Mode.valueOf(config.getString("mode"));
        world = Bukkit.getWorld(config.getString("world"));
        lobbyLocation = getLocation(config.getString("lobby"));
        mapName = config.getString("name");
        islandPlayers = config.getInt("islandPlayers");
        respawnY = config.getInt("respawnY");

        basicChests = new ArrayList<>();
        for(Object chestLocation : config.getList("basicChests")) {
            basicChests.add(getLocation(chestLocation));
        }
        middleChests = new ArrayList<>();
        for(Object chestLocation : config.getList("middleChests")) {
            middleChests.add(getLocation(chestLocation));
        }
        mysteryChest = getLocation(config.getString("mysteryChest"));

        deathmatchSpawns = new ArrayList<>();
        for(Object chestLocation : config.getList("deathmatchSpawns")) {
            deathmatchSpawns.add(getLocation(chestLocation));
        }

        islands = new ArrayList<>();
        char letterCounter = 'A';

        for(Map<?, ?> island : config.getMapList("islands")) {
            int id = islands.size() + 1;

            char tag = letterCounter;
            letterCounter++;

            Location spawn = getLocation(island.get("spawn"));

            List<Location> chests = new ArrayList<>();
            for(Object chestLocation : (List) island.get("chests")) {
                chests.add(getLocation(chestLocation));
            }

            islands.add(new Island(id, tag, spawn, chests));
        }

        SkyWars.getInstance().getLogger().info("Конфигурация игры успешно загружена");
        SkyWars.getInstance().getLogger().info("Режим игры: " + mode.name());
        SkyWars.getInstance().getLogger().info("Формат игры: " + islandPlayers + "x" + islands.size());

        if(islands.size() > deathmatchSpawns.size()) {
            throw new IllegalArgumentException("Недостаточно местоположений точек детматча! Текущее количество: " + deathmatchSpawns.size() + ". Необходимо: " + islands.size());
        }
    }

    /**
     * Получает местоположение из объекта конфиг файла
     * @param object Объект конфиг файла
     * @return Местоположение
     */
    private static Location getLocation(Object object) {
        String stringLocation = (String) object;
        String[] locArgs = stringLocation.split(",");

        double x = Double.parseDouble(locArgs[0]);
        double y = Double.parseDouble(locArgs[1]);
        double z = Double.parseDouble(locArgs[2]);

        if(locArgs.length == 5) {
            float yaw = Float.parseFloat(locArgs[3]);
            float pitch = Float.parseFloat(locArgs[4]);
            return new Location(world, x, y, z, yaw, pitch);
        }

        return new Location(world, x, y, z);
    }
}
